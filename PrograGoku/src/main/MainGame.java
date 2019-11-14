package main;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import screen.*;

public class MainGame extends StateBasedGame {
	
	public static final String gameName = "Train-magotchi";
	public static final int menuScreen = 0,
							gameScreen = 1;
	
	
	public MainGame(String gameName) {
		super(gameName);
		this.addState(new MenuScreen(menuScreen));
		this.addState(new GameScreen(menuScreen));
	}


	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//load screens
		this.getState(menuScreen).init(gc, this);
		this.getState(gameScreen).init(gc, this);
		
		//starting screen
		this.enterState(menuScreen);
	}
	
	// - - - -
	
	public static void main(String[] args) {
		AppGameContainer appGC;
		try {
			appGC = new AppGameContainer(new MainGame(gameName)); //screen
			appGC.setDisplayMode(640, 480, false); //width, height, fullscreen?
			appGC.start();
		}
		catch(SlickException ex) {
			ex.printStackTrace();
		}
	}
	
}
