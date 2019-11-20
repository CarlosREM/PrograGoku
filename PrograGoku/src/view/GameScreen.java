package view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.MainGame;
import management.SoundManager;

public class GameScreen extends BasicGameState {

	private int stateId;
	private String mouse = "No input",
				   cam = "No input";

	private static final String res = "res/images/game/";
	
	private float camX, camY;
	private float maxX, maxY;
	private final float startingX = 1088,
					    startingY = 714;
	
	private Sprite background;
	
	private AnimatedSprite character;
	private float charMoveX, charMoveY,
				  charMoveSpeed = 0.2f;
	
	private int musicState = 0;
	
	public GameScreen(int stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Sprite(res + "GameMap.png", 0, 0);
		
		maxX = -1*background.getWidth() + MainGame.screenWidth;
		maxY = -1*background.getHeight() + MainGame.screenHeight;
		
		camX = -startingX + MainGame.screenWidth/2;
		camY = -startingY + MainGame.screenHeight/2;
		
		character = new AnimatedSprite(res + "spr_character.png", 32, 48, 120);
		character.setPosition(startingX, startingY);
		charMoveX = character.getX();
		charMoveY = character.getY();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.translate(camX, camY);
		
		background.draw();
		character.draw();
		
		if (MainGame.debug) {
			g.drawString("Current State: " + getID(), 10, -1*camY - 30);
			g.drawString(mouse, 10, -1*camY - 50);
			g.drawString(cam, 10, -1*camY - 70);
		}
		
		// AUDIO
		switch(musicState) {
		case 0:
			if (SoundManager.getTrackName() != "daytime") {
				SoundManager.playMusic("daytime");
				SoundManager.setVolume(0.3f);
			}
			break;
			
		case 1:
			if (SoundManager.getTrackName() != "nighttime") {
				SoundManager.playMusic("nighttime");
				SoundManager.setVolume(0.3f);
			}
			break;
			
		case 2:
			if (SoundManager.getTrackName() != "battle") {
				SoundManager.playMusic("battle");
				SoundManager.setVolume(0.3f);
			}
			break;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int mouseX = input.getMouseX(),
			mouseY = input.getMouseY();
		
		if (MainGame.debug) {
			mouse = "[MOUSE] X: " + mouseX + " - Y: " + mouseY;
			cam = "[CAM] X:" + camX + " - Y:" + camY;
		}

		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
				sbg.enterState(MainGame.menuScreen, new FadeOutTransition(), new FadeInTransition());
		}
		
		else if (input.isKeyPressed(Input.KEY_SPACE)) {
			musicState++;
			if (musicState > 3)
				musicState = 0;
		}
		
		else {
			moveCamera(mouseX, mouseY);
			moveCharacter(input);
		}
	}

	private void moveCamera(int mouseX, int mouseY) {
		if (mouseX < 30 && camX < 0) {
			camX += 0.5f;
		}
		else if (mouseX > MainGame.screenWidth-30 && camX > maxX) {
			camX -= 0.5f;
		}
		
		if (mouseY < 30 && camY < 0) {
			camY += 0.5f;
		}
		else if (mouseY > MainGame.screenHeight-30 && camY > maxY) {
			camY -= 0.5f;
		}
	}
	
	private void moveCharacter(Input input) {
		boolean idle = true;
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			charMoveX = -camX + input.getMouseX() - character.getWidth()/2;
			charMoveY= -camY + input.getMouseY() - character.getHeight()/2;
		}
		
		float charX = character.getX(),
			  charY = character.getY(),
			  distX = Math.abs(charMoveX - charX),
			  distY = Math.abs(charMoveY - charY);
		if (distX > charMoveSpeed || distY > charMoveSpeed) {
			idle = false;
			
			if (charY < charMoveY) {
				character.setY(charY + charMoveSpeed);
				if (distY > distX)
					character.setCurrentAnimation(0); // look down
			}
			else if (charY > charMoveY) {
				character.setY(charY - charMoveSpeed);
				if (distY > distX)
					character.setCurrentAnimation(1); // look up
			}
			
			if (charX > charMoveX) {
				character.setX(charX - charMoveSpeed);
				if (distX > distY)
					character.setCurrentAnimation(2); // look left
			}
			else if (charX < charMoveX) {
				character.setX(charX + charMoveSpeed);
				if (distX > distY)
					character.setCurrentAnimation(3); // look right
			}
		}
		
		character.setIdle(idle);
	}
	
	@Override
	public int getID() {
		return stateId;
	}

}
