package ADT;

import java.sql.Time;

public class GameState {
	private static GameState instance = null;
	private ExtendedCharacter character;
	private int year = 1;
	private int day = 1;
	private static int maxYearDays;
	private static int grow;
	
	public static void init(int maxYearDays,int grow) {
		GameState.maxYearDays = maxYearDays;
		GameState.grow = grow;
	}
	
	
	private GameState() {

	}
	
	public static GameState getInstance() {
		if(instance == null)
			instance = new GameState();
		return instance;
	}

	public ExtendedCharacter getCharacter() {
		return character;
	}

	public void setCharacter(ExtendedCharacter character) {
		this.character = character;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	public String getDateString() {
		return "Day " + String.valueOf(getDay()) + ", Year " + String.valueOf(getYear());
	}
	
	public void increaseDay() {
		this.day += 1;
		if(this.day == maxYearDays) {
			increaseYear();
			setDay(1);
		}
	}

	public void increaseYear() {
		this.year += 1;
	}
	public void levelUp() {
		if (year%ADT.TimeConfig.getInstance().getGrow()==0) {
			character.levelUp();
		}
	}	
	
}
