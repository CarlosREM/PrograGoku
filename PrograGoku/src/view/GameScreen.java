package view;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ADT.FixedActivityCoord;
import main.MainGame;
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
		musicVolume = (paused) ? 0.1f : 0.0f ;
	}
	private static Color pausedTint = new Color(0, 0, 0, 175); //r, g, b, alpha
	
	// CAMERA & BACKGROUND
	private BigImage background;
	
	private float camX, camY,
				  maxCamX, maxCamY,
				  camMoveSpeed = 0.5f;
	private final float camStartingX = 1088,
					    camStartingY = 714;
	
	// CHARACTER INFO
	private AnimatedSprite character;
	private ColliderRect playerCollider;
	private BigImage moveCursor;
	private float charMoveX, charMoveY,
				  charMoveSpeed = 0.2f;
	
	
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
		
		camX = -camStartingX + MainGame.screenWidth/2;
		camY = -camStartingY + MainGame.screenHeight/2;
		
		character.setPosition(MainGame.screenWidth/2, MainGame.screenHeight/2);
		
		charMoveX = character.getX();
		charMoveY = character.getY();
		
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new BigImage(res + "GameMap.png");
		
		maxCamX = -1*background.getWidth() + MainGame.screenWidth;
		maxCamY = -1*background.getHeight() + MainGame.screenHeight;

		character = new AnimatedSprite(res + "spr_character.png", 32, 48, 120);
		character.setPosition(FixedActivityCoord.ACTION_BED);

		playerCollider = new ColliderRect(character.getX() + 2, character.getY() + character.getHeight() - 16, 28, 14);
		
		//moveCursor = new BigImage(res + "movePointer.png");
		
		MapCollisionManager.init();
		
		musicTracks = new String[] {"daytime", "nighttime", "battle"};
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		// SPRITES
		background.draw(camX, camY);
		
		if (MainGame.debug)
			MapCollisionManager.draw(g, camX, camY);
		
		if (!character.isIdle()) {
			//System.out.println("POINTER AT X:"+charMoveX+" - Y:"+charMoveY);
			//g.drawImage(moveCursor, charMoveX, charMoveY);
		}
		
		playerCollider.draw(g);
		character.draw();
		
		// UI OVERLAY 
		
		
		// PAUSED
		if (paused) {
			g.setColor(pausedTint);
			g.fillRect(0, 0, MainGame.screenWidth, MainGame.screenHeight);
			g.setColor(Color.white);
		}
		
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
			strPosition = "[CHAR POS] X:" + character.getX() + " - Y:" + character.getY();
			strMoving = "[MOVING TO] X:" + charMoveX + " - Y: " + charMoveY;
		}

		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
				sbg.enterState(MainGame.menuScreen, new FadeOutTransition(), new FadeInTransition());
		}
		
		else if (input.isKeyPressed(Input.KEY_SPACE)) {
			musicState++;
			if (musicState == 3)
				musicState = 0;
		}
		
		else if (input.isKeyPressed(Input.KEY_ENTER)) {
			setLockPosition(!paused);
			setPaused(!paused);
			SoundManager.setVolume(musicVolume);
		}
		
		if (!paused){
			moveCamera(mouseX, mouseY);
			checkCharacterMove(input);
			checkCharacterCollision();
			moveCharacter();
			playerCollider.setX(character.getX() + 2);
			playerCollider.setY(character.getY() + character.getHeight() - 16);
		}
	}

	private void moveCamera(int mouseX, int mouseY) {
		if (mouseX < 30 && camX < 0) {
			camX += camMoveSpeed;
			character.setX(character.getX() + camMoveSpeed);
			charMoveX += camMoveSpeed;
		}
		else if (mouseX > MainGame.screenWidth-30 && camX > maxCamX) {
			camX -= camMoveSpeed;
			character.setX(character.getX() - camMoveSpeed);
			charMoveX -= camMoveSpeed;
		}
		
		if (mouseY < 30 && camY < 0) {
			camY += camMoveSpeed;
			character.setY(character.getY() + camMoveSpeed);
			charMoveY += camMoveSpeed;
		}
		else if (mouseY > MainGame.screenHeight-30 && camY > maxCamY) {
			camY -= camMoveSpeed;
			character.setY(character.getY() - camMoveSpeed);
			charMoveY -= camMoveSpeed;
		}
	}
	
	private void checkCharacterMove(Input input) {
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			charMoveX = input.getMouseX() - character.getWidth()/2;
			charMoveY= input.getMouseY() - character.getHeight()/2;
			
		}
	}
	
	private void checkCharacterCollision() {
		boolean collision = MapCollisionManager.checkCollision(playerCollider, camX, camY);
		
		if (collision) {
			float charY = character.getY(),
				  charX = character.getX();
			if (charMoveY < charY) { //UP
				charY += charMoveSpeed*10;
				character.setY(charY);
				charMoveY = charY;
			}
			else if (charY < charMoveY) { // DOWN
				charY -= charMoveSpeed*10;
				character.setY(charY);
				charMoveY = charY;
			}
			
			else if (charMoveX < charX) { // LEFT
				charX += charMoveSpeed*10;
				character.setX(charX);
				charMoveX = charX;
			}
			else if (charX < charMoveX) { // RIGHT
				charX -= charMoveSpeed*10;
				character.setX(charX);
				charMoveX = charX;
			}
			

		}
	}
	
	private void moveCharacter() {
		boolean idle = true;

		float charX = character.getX(),
			  charY = character.getY(),
			  distX = Math.abs(charMoveX - charX),
			  distY = Math.abs(charMoveY - charY);
		if (!lockPosition && (distX > charMoveSpeed || distY > charMoveSpeed)) {
			idle = false;
			

			if (charY > charMoveY) {
				character.setY(charY - charMoveSpeed);
				if (distY > distX)
					character.setCurrentAnimation(1); // look up
			}
			else if (charY < charMoveY) {
				character.setY(charY + charMoveSpeed);
				if (distY > distX)
					character.setCurrentAnimation(0); // look down
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
