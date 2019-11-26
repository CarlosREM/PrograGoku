package management;

import ADT.GameState;
import ADT.SicknessPool;
import abstraction.ASickness;
import view.GameOverlay;
import view.GameScreen;

public class ClockManager implements Runnable {
	
	private boolean stop = false;
	public void stop() { stop = true; }
	
	private boolean paused = false;
	public void setPause(boolean state) { paused = state; }
	
	private static int clockUpdateLapse = 500;
	private int minutes = 0;
	private int hours = 0;
	
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
						if (hours == 6) {
							GameScreen.getInstance().setMusicState(GameScreen.MUSIC_DAY);
							askSicknessAsign();
							//preguntar si esta muerto
						}
						else if (hours == 18) {
							GameScreen.getInstance().setMusicState(GameScreen.MUSIC_NIGHT);
							askSicknessAsign();
							//preguntar si esta muerto
						}
						//demas acciones
						growGarden();
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	private void growGarden() {
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
				sickness.visit(GameState.getInstance().getCharacter());
			}
		}
	}
	
}
