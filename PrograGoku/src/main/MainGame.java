package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import view.*;

public class MainGame extends StateBasedGame {
	public static final boolean debug = true;
	
	
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
		
	public static void startup() {
		try {
			gameContainer = new AppGameContainer(new MainGame(gameName)); //screen
			gameContainer.setDisplayMode(screenWidth, screenHeight, false); //width, height, fullscreen?
			gameContainer.setShowFPS(debug);
			gameContainer.setTargetFrameRate(180);
			gameContainer.start();
		}
		catch(SlickException ex) {
			ex.printStackTrace();
		}
	}
	
	// - - - -
	
	public static void main(String[] args) {
		MainGame.startup();
	}
	
}
