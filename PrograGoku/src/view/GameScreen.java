package view;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import main.MainGame;
import management.SoundManager;

public class GameScreen extends BasicGameState {

	private int stateId;
	private String mouse = "No input";

	private static final String res = "res/images/game/";
	
	public GameScreen(int stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (MainGame.debug) {
			g.drawString(mouse, 10, 30);
			g.drawString("Current State: " + getID(), 10, 50);
		}
		
		Image background = new Image(res + "GameMap.png");
		g.drawImage(background, 0, 0);
		
		if (SoundManager.getTrackName() != "daytime")
			SoundManager.playMusic("daytime");
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (MainGame.debug) {
			int mouseX = Mouse.getX(),
				mouseY = MainGame.screenHeight - Mouse.getY();
			mouse = "[MOUSE] X: " + mouseX + " - Y: " + mouseY;
		}
		
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
				sbg.enterState(MainGame.menuScreen);
		}
	}

	@Override
	public int getID() {
		return stateId;
	}

}
