package management;

import ADT.GameState;
import view.GameOverlay;

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
						
						//demas acciones
	
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
