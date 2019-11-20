package management;

import ADT.GameState;

public class ClockManager implements Runnable {
	
	private static int clockUpdateLapse = 100;
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
		while(true) {
			try {
				Thread.sleep(clockUpdateLapse);
				if(increaseTime())
					GameState.getInstance().increaseDay();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
