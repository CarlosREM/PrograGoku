package view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.MainGame;
import management.ActionSpotManager;
import management.FixedActivityCoord;
import management.MapCollisionManager;
import management.SoundManager;

public class GameScreen extends BasicGameState {
	private int stateId;
	
	private static GameScreen instance = null;
	
	// DEBUG
	private String strMouse = "No input",
			   strCam = "No input",
			   strPosition = "No input",
			   strMoving = "No input";
	
	// PAUSE
	private static boolean paused = false;
	public static void setPaused(boolean state) {
		paused = state;
		musicVolume = (paused) ? 0.1f : 0.3f ;
	}
	private static Color pausedTint = new Color(0, 0, 0, 175); //r, g, b, alpha
	
	// CAMERA & BACKGROUND
	private BigImage background;
	
	private float camX, camY,
				  maxCamX, maxCamY,
				  camMoveSpeed = 0.8f;
	
	// CHARACTER INFO
	private AnimatedSprite character;
	private ColliderRect playerCollider;
	private float charMoveX, charMoveY,
				  charMoveSpeed = 0.5f;
	
	private boolean lockPosition = false;
	public void setLockPosition(boolean state) { lockPosition = state; }
	
	// MISC
	private static int musicState = 0;
	private static float musicVolume = 0.3f;
	private static String[] musicTracks;
	
	private static final String res = "res/images/game/";
	
	
	private GameScreen() {
		this.stateId = MainGame.gameScreen;
	}
	
	public static GameScreen getInstance() {
		if (instance == null)
			instance = new GameScreen();
		return instance;
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) {
		setPaused(false);
		setLockPosition(false);
		musicState = 0;
		
		camX = -(FixedActivityCoord.ACTION_BED.x) + MainGame.screenWidth/2;
		camY = -(FixedActivityCoord.ACTION_BED.y + 32) + MainGame.screenHeight/2;
		
		playerCollider.setPosition(MainGame.screenWidth/2 - playerCollider.getW()/2,
								   MainGame.screenHeight/2 - playerCollider.getH()/2);
		
		character.setIdle(true);
		character.setPosition(playerCollider.getCenterX() - character.getWidth()/2,
	  			  			  playerCollider.getY2() - character.getHeight());
		
		charMoveX = playerCollider.getCenterX();
		charMoveY = playerCollider.getCenterY();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new BigImage(res + "GameMap.png");
		
		maxCamX = -1*background.getWidth() + MainGame.screenWidth;
		maxCamY = -1*background.getHeight() + MainGame.screenHeight - (GameOverlay.getHeight()-10);

		character = new AnimatedSprite(res + "spr_character.png", 32, 48, 120);
		playerCollider = new ColliderRect(28, 14);
				
		MapCollisionManager.init();
		ActionSpotManager.init();
		
		musicTracks = new String[] {"daytime", "nighttime", "battle"};
		
		GameOverlay.init();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		// SPRITES
		background.draw(camX, camY);
		
		if (MainGame.debug)
			MapCollisionManager.draw(g, camX, camY);
		
		// ACTION SPOTS
		ActionSpotManager.draw(g, camX, camY);

		if (!character.isIdle()) {
			g.setColor(Color.red);
			g.fillOval(charMoveX-8, charMoveY-4, 16, 8);
			g.setColor(Color.white);
		}
		
		playerCollider.draw(g);
		character.draw();

		// PAUSED
		if (paused) {
			g.setColor(pausedTint);
			g.fillRect(0, 0, MainGame.screenWidth, MainGame.screenHeight);
			
			g.setColor(Color.white);
			g.drawString("PAUSED", MainGame.screenWidth/2 - 27, MainGame.screenHeight/3);
			g.drawString("[ENTER] - Resume", MainGame.screenWidth/2 - 76, MainGame.screenHeight/3 + 32);
			g.drawString("[ESC] - Exit", MainGame.screenWidth/2 - 58, MainGame.screenHeight/3 + 64);
		}
		
		// UI OVERLAY 
		GameOverlay.drawTime(g);
		GameOverlay.drawPlayerStats(g);
		
		
		// DEBUG INFO
		if (MainGame.debug) {
			g.setColor(pausedTint);
			g.fillRect(0, 0, 360, 160);
			g.setColor(Color.white);
			
			g.drawString("Current State: " + getID(), 10, 30);
			g.drawString("[MUSIC] : \"" + musicTracks[musicState] + "\" - V:" + musicVolume, 10, 50);
			g.drawString(strMouse, 10, 70);
			g.drawString(strCam, 10, 90);
			g.drawString(strPosition, 10, 110);
			g.drawString(strMoving, 10, 130);
		}

	
		// AUDIO
		if (SoundManager.getTrackName() != musicTracks[musicState]) {
			SoundManager.playMusic(musicTracks[musicState]);
			SoundManager.setVolume(musicVolume);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();

		int mouseX = input.getMouseX(),
			mouseY = input.getMouseY();
		
		if (MainGame.debug) {
			strMouse = "[MOUSE] X:" + mouseX + " - Y:" + mouseY;
			strCam = "[CAM] X:" + camX + " - Y:" + camY;
			strPosition = "[CHAR POS] X:" + playerCollider.getCenterX() + " - Y:" + playerCollider.getCenterY();
			strMoving = "[MOVING TO] X:" + charMoveX + " - Y: " + charMoveY;
		}

		checkKeyboardInteraction(input, sbg);
				
		if (!paused){
			moveCamera(mouseX, mouseY);
			checkCharacterMove(input);
			moveCharacter();
			checkCharacterCollision();
			checkCharacterInteraction();
		}
	}

	private void moveCamera(int mouseX, int mouseY) {
		if (mouseX < 30 && camX < 0) {
			camX += camMoveSpeed;
			playerCollider.setX(playerCollider.getX() + camMoveSpeed);
			charMoveX += camMoveSpeed;
		}
		else if (mouseX > MainGame.screenWidth-30 && camX > maxCamX) {
			camX -= camMoveSpeed;
			playerCollider.setX(playerCollider.getX() - camMoveSpeed);
			charMoveX -= camMoveSpeed;
		}
		
		if (mouseY < 30 && camY < 0) {
			camY += camMoveSpeed;
			playerCollider.setY(playerCollider.getY() + camMoveSpeed);
			charMoveY += camMoveSpeed;
		}
		else if (mouseY > MainGame.screenHeight-30 && camY > maxCamY) {
			camY -= camMoveSpeed;
			playerCollider.setY(playerCollider.getY() - camMoveSpeed);
			charMoveY -= camMoveSpeed;
		}
	}
	
	private void checkCharacterMove(Input input) {
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			charMoveX = input.getMouseX();
			charMoveY= input.getMouseY();
		}
	}
	
	private void moveCharacter() {
		boolean idle = true;

		float charX = playerCollider.getCenterX(),
			  charY = playerCollider.getCenterY(),
			  distX = Math.abs(charMoveX - charX),
			  distY = Math.abs(charMoveY - charY);
		
		if (!lockPosition && (distX > charMoveSpeed || distY > charMoveSpeed)) {
			idle = false;
			
			if (distX > charMoveSpeed) {
				if (charMoveX < charX) { // LEFT
					playerCollider.setX(playerCollider.getX() - charMoveSpeed);
					character.setCurrentAnimation(2); // look left
				}
				
				else if (charX < charMoveX) { // RIGHT
					playerCollider.setX(playerCollider.getX() + charMoveSpeed);
					character.setCurrentAnimation(3); // look right
				}
			}
			
			else if (distY > charMoveSpeed) {
				if (charY < charMoveY) { // DOWN
					playerCollider.setY(playerCollider.getY() + charMoveSpeed);
					character.setCurrentAnimation(0); // look down
				}
				else if (charMoveY < charY) { // UP
					playerCollider.setY(playerCollider.getY() - charMoveSpeed);
					character.setCurrentAnimation(1); // look up
				}
			}
			
		}
		
		if (distX <= charMoveSpeed)
			playerCollider.setX(charMoveX - playerCollider.getW()/2);
		if (distY <= charMoveSpeed)
			playerCollider.setY(charMoveY - playerCollider.getH()/2);

		character.setPosition(playerCollider.getCenterX() - character.getWidth()/2,
				  			  playerCollider.getY2() - character.getHeight());
		character.setIdle(idle);
	}

	private void checkCharacterCollision() {
		boolean collision = MapCollisionManager.checkCollision(playerCollider, camX, camY);
		
		if (collision) {
			
			if (playerCollider.getCenterX() > charMoveX) //LEFT
				playerCollider.setX(playerCollider.getX() + 1);
		
			else if (playerCollider.getCenterX() < charMoveX) // RIGHT
				playerCollider.setX(playerCollider.getX() - 1);
		
			if (playerCollider.getCenterY() > charMoveY) // UP
				playerCollider.setY(playerCollider.getY() + 1);

			else if (playerCollider.getCenterY() < charMoveY) // DOWN
				playerCollider.setY(playerCollider.getY() - 1);

			
			charMoveX = playerCollider.getCenterX();
			charMoveY = playerCollider.getCenterY();
		}
	}
	
	private void checkCharacterInteraction() throws SlickException {
		if (ActionSpotManager.checkInteraction(playerCollider, camX, camY)) {
			charMoveX = playerCollider.getCenterX();
			charMoveY = playerCollider.getCenterY();
		}
	}
	
	private void checkKeyboardInteraction(Input input, StateBasedGame sbg) {
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			setLockPosition(!paused);
			character.setIdle(!paused);
			setPaused(!paused);
			SoundManager.setVolume(musicVolume);
		}
		
		if (paused && input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(MainGame.menuScreen, new FadeOutTransition(), new FadeInTransition());
		}
		
		if (MainGame.debug && input.isKeyPressed(Input.KEY_M)) {
			musicState++;
			if (musicState == 3)
				musicState = 0;
		}
	}

	
	@Override
	public int getID() {
		return stateId;
	}

}
