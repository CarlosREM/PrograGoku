package management;

import ADT.GameState;
import ADT.MoodVisitor;
import ADT.Proxy;
import ADT.RecoveryPool;
import ADT.SicknessPool;
import abstraction.ASickness;
import view.GameOverlay;
import view.GameScreen;

public class ClockManager implements Runnable {
	
	private boolean stop = false;
	public void stop() { stop = true; }
	
	private boolean paused = false;
	public void setPause(boolean state) { paused = state; }
	
	private static int clockUpdateLapse;
	private int minutes = 0;
	private int hours = 0;
	
	public static void init(int clockUpdateLapse) {
		ClockManager.clockUpdateLapse = clockUpdateLapse;
	}
	
	private boolean increaseTime() {
		minutes+=15;
		if(minutes == 60) {
			minutes = 0;
			hours+=1;
		}
		if(hours == 24) {
			hours = 0;
			
		}
		return minutes == 0 && hours == 0;
	}
	
	public String getClockString() {
		String hoursStr = String.valueOf(hours),
			   minutesStr = String.valueOf(minutes);
		if(hours<10)
			hoursStr = "0" + hoursStr;
		if(minutes == 0)
			minutesStr = "0" + minutesStr;
		
		return hoursStr + ":" + minutesStr;
	}
	
	@Override
	public void run() {
		GameOverlay.setDate(GameState.getInstance().getDateString());
		GameOverlay.setTime(getClockString());
		
		while(!stop) {
			try {
				Thread.sleep(clockUpdateLapse);
				if (!paused) {
					if(increaseTime()) {
						GameState.getInstance().increaseDay();
						GameOverlay.setDate(GameState.getInstance().getDateString());
					}
					GameOverlay.setTime(getClockString());
										
					setMusic();
					
					growGarden();
					checkHunger();
					checkFatigue();
					checkSicknesses();
					GameState.getInstance().getCharacter().visit(MoodVisitor.getInstance());
					generateLog();
					
					if (GameState.getInstance().getCharacter().getCurrentHealthPoints() <= 0 &&
						!GameScreen.getInstance().isDead())
						GameScreen.getInstance().triggerDeath();
					else
						GameOverlay.update(GameState.getInstance().getCharacter());
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setMusic() {
		if (minutes != 0) return;
		
		if (hours == 6)
			GameScreen.getInstance().setMusicState(GameScreen.MUSIC_DAY);
		else if (hours == 18)
			GameScreen.getInstance().setMusicState(GameScreen.MUSIC_NIGHT);
	}
	
	private void checkSicknesses() {
		if (minutes != 0) return;
		
		if (hours == 6 || hours == 18) {
			askSicknessAsign();
			//preguntar si esta muerto
		}
	}
	
	private void checkHunger() {
		if(minutes != 30)
			return;
		if(hours == 0 || hours == 6 || hours == 12 || hours == 18) {
			GameState.getInstance().getCharacter().increaseHunger(5);
			if(GameState.getInstance().getCharacter().getHunger() >= 70)
				GameState.getInstance().getCharacter().decreaseHealthPoints(5);
		}
	}
	
	private void checkFatigue() {
		if(minutes != 0)
			return;
		if(hours == 0 || hours == 8 || hours == 16) {
			GameState.getInstance().getCharacter().increaseFatigue(20);
			GameState.getInstance().getCharacter().decreaseMentalHealth(10);
		}
	}
	
	private void growGarden() {
		if(minutes != 0)
			return;
		switch(hours) { 
		case 6: case 16:
			GardenManager.loadGardenSpot(0);
			break;
			
		case 8: case 18:
			GardenManager.loadGardenSpot(1);
			break;
			
		case 10: case 20:
			GardenManager.loadGardenSpot(2);
			break;
		}
	}

	private void askSicknessAsign() {
		if(!GameState.getInstance().getCharacter().isImmortal()) {
			for(ASickness sickness : SicknessPool.sickness.values()) {
				GameState.getInstance().getCharacter().visit(sickness);
			}
		}
	}
	
	private void generateLog() {
		if(minutes != 45)
			return;
		if(hours == 23) {
			Proxy.generateFile();
			RecoveryPool.addState(GameState.getInstance().getDateString(), GameState.getInstance());
			GameState.newInstance();
		}
	}
}