package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import ADT.TimeConfig;
import management.ClockManager;
import view.*;

public class MainGame extends StateBasedGame {
	public static boolean debug = false;
	
	public static final int FPS = 180;
	
	public static final String gameName = "Train-magotchi";
	public static AppGameContainer gameContainer;
	
	// DIMENSIONS
	public static final int screenWidth = 1280,
							screenHeight = 720;
	
	// SCREENS
	public static final int menuScreen = 0,
							gameScreen = 1;
	
	public MainGame(String gameName) {
		super(gameName);
		this.addState(new MenuScreen(menuScreen));
		this.addState(GameScreen.getInstance());
	}


	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//starting screen
		this.enterState(menuScreen);
	}
		
	private static void initResources() {
		ADT.Loader.load();
		TimeConfig timeConfig = TimeConfig.getInstance();
		ClockManager.init(timeConfig.getClockUpdateLapse());
		ADT.GameState.init(timeConfig.getMaxYearDays());
	}
	
	public static void startup() {
		try {
			gameContainer = new AppGameContainer(new MainGame(gameName)); //screen
			gameContainer.setDisplayMode(screenWidth, screenHeight, false); //width, height, fullscreen?
			gameContainer.setShowFPS(false);
			gameContainer.setTargetFrameRate(FPS);
			gameContainer.start();
		}
		catch(SlickException ex) {
			ex.printStackTrace();
		}
	}
	
	// - - - -
	
	public static void main(String[] args) {
		MainGame.initResources();
		MainGame.startup();
	}
	
}
