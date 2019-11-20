package ADT;

public class GameState {
	private static GameState instance = null;
	private ExtendedCharacter character;
	private int year = 0;
	private int day = 0;
	private static int maxYearDays = 5;
	
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
	
	public String getDayString() {
		return "Day " + String.valueOf(getDay());
	}
	
	public String getYearString() {
		return "Year " + String.valueOf(getYear());
	}
	
	public void increaseDay() {
		this.day += 1;
		if(this.day == maxYearDays) {
			increaseYear();
			setDay(0);
		}
	}
	
	public void increaseYear() {
		this.year += 1;
	}
	
}
