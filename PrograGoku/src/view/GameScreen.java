package view;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.MainGame;
import management.ActionSpotManager;
import management.ClockManager;
import management.DialogManager;
import management.FixedActivityCoord;
import management.GardenManager;
import management.MapCollisionManager;
import management.CharacterViewManager;
import management.SoundManager;

public class GameScreen extends BasicGameState {
	private int stateId;
	
	private static GameScreen instance = null; 
	
	private StateBasedGame sbg;
	private CharacterViewManager playerViewManager;
	
	// DEBUG
	private String strMouse = "No input",
			   	   strCam = "No input",
			   	   strPosition = "No input",
			   	   strMoving = "No input";
	
	// INTERRUPTIONS
	private boolean paused,
				    dialogPause,
				    inBattle,
				    dead;
	private static final Color pausedTint = new Color(0, 0, 0, 175); //r, g, b, alpha

	public boolean isPaused() { return paused; } 
	public void setPaused(boolean state, boolean isDialog) {
		clockManager.setPause(state);
		paused = state;
		dialogPause = isDialog;
		playerViewManager.setLockPosition(state);
		playerViewManager.setCharacterIdle(state);
		musicVolume = (paused) ? 0.1f : 0.3f;
		SoundManager.setVolume(musicVolume);
	}
	public boolean isDead() { return dead; }
	
	public boolean isInBattle() { return inBattle; }
	public void setInBattle(boolean state) {
		inBattle = state;
		if (state) {
			previousMusicState = musicState;
			musicState = 2;
		}
		else {
			musicState = previousMusicState;
			previousMusicState = -1;
		}
	}
	
	// CAMERA & BACKGROUND
	private BigImage background;
	
	private float camX, camY,
				  maxCamX, maxCamY,
				  camMoveSpeed = 1;
	
	public float getCamX() { return camX; }
	public float getCamY() { return camY; }
	
	// LOGIC
	private ClockManager clockManager;
	private Thread clockThread;
	
	// MISC
	public static final int MUSIC_NIGHT = 0,
							MUSIC_DAY = 1,
							MUSIC_BATTLE = 2;
	
	private int musicState = MUSIC_NIGHT,
				previousMusicState = -1;
	private float musicVolume = 0.3f;
	private String[] musicTracks;
	public void setMusicState(int musicState) {
		if (!inBattle)
			this.musicState = musicState;
		else
			this.previousMusicState = musicState;
	}	
	
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
		setPaused(false, false);
		dead = false;
		musicState = 0;
		
		camX = -(FixedActivityCoord.ACTION_BED.x) + MainGame.screenWidth/2;
		camY = -(FixedActivityCoord.ACTION_BED.y + 32) + MainGame.screenHeight/2;
		
		playerViewManager.startingPos();
		
		if (clockThread.isAlive()) {
			clockManager.stop();
			clockManager = new ClockManager();
			clockThread = new Thread(clockManager);
		}
		clockThread.start();
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;
		
		background = new BigImage("res/images/game/GameMap.png");
		
		maxCamX = -1*background.getWidth() + MainGame.screenWidth;
		maxCamY = -1*background.getHeight() + MainGame.screenHeight - (GameOverlay.getHeight()-10);
		
		playerViewManager = CharacterViewManager.getInstance();
		MapCollisionManager.init();
		ActionSpotManager.init();
		GardenManager.init();
		
		musicTracks = new String[] {"nighttime", "daytime", "battle"};
				
		clockManager = new ClockManager();
		clockThread = new Thread(clockManager);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		// SPRITES
		background.draw(camX, camY);
		
		if (MainGame.debug)
			MapCollisionManager.draw(g, camX, camY);
		
		// ACTION SPOTS
		ActionSpotManager.draw(g, camX, camY);

		// GARDEN MANAGER
		GardenManager.draw(g);
		
		// MOVE POSITION
		if (!playerViewManager.isCharacterIdle() && !playerViewManager.isPlayingAnimation()) {
			g.setColor(Color.red);
			g.fillOval(playerViewManager.getMoveX() - 8, playerViewManager.getMoveY() - 4, 16, 8);
		}
		

		
		// CHARACTER
		playerViewManager.drawCharacter(g);

		// PAUSED
		if (paused) {
			g.setColor(pausedTint);
			g.fillRect(0, 0, MainGame.screenWidth, MainGame.screenHeight);
			
			if (!dialogPause) {
				g.setColor(Color.white);
				g.drawString("PAUSED", MainGame.screenWidth/2 - 27, MainGame.screenHeight/3);
				g.drawString("[ENTER] - Resume", MainGame.screenWidth/2 - 76, MainGame.screenHeight/3 + 32);
				g.drawString("[ESC] - Exit", MainGame.screenWidth/2 - 58, MainGame.screenHeight/3 + 64);
			}
		}
		
		// UI OVERLAY 
		if (playerViewManager.isPlayingAnimation()) {
			float offsetY = MainGame.screenHeight - GameOverlay.getHeight() - 18;
			g.setColor(pausedTint);
			g.fillRect(0, offsetY - 2, MainGame.screenWidth, 32);
			g.setColor(Color.white);
			g.drawString("Press [SPACE] to exit action.", 0, offsetY);
		}
		
		if (!dead) {
			GameOverlay.drawTime(g);
			GameOverlay.drawPlayerStats(g);
		}
		else 
			GameOverlay.drawDeathScreen(g);
		
		// DIALOGS
		DialogManager.drawDialog(g);
		
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
			strCam = "[CAM] X:" + (-camX) + " - Y:" + (-camY);
			strPosition = "[CHAR POS] X:" + playerViewManager.getCharX() + " - Y:" + playerViewManager.getCharY();
			strMoving = "[MOVING TO] X:" + playerViewManager.getMoveX() + " - Y: " + playerViewManager.getMoveY();
		}
				
		if (!paused){
			moveCamera(mouseX, mouseY);
			checkCharacterMove(input);
			playerViewManager.moveCharacter(camX, camY);
			playerViewManager.checkMapCollision(camX, camY);
			playerViewManager.checkActionSpotTrigger(camX, camY);
		}
		else if (dialogPause)
			DialogManager.checkDialogInput(input);
		
		checkKeyboardInteraction(input);
	}

	private void moveCamera(int mouseX, int mouseY) {
		if (mouseX < 30 && camX < 0) {
			camX += camMoveSpeed;
			playerViewManager.addCharX(camMoveSpeed);
			playerViewManager.addMoveX(camMoveSpeed);
		}
		else if (mouseX > MainGame.screenWidth-30 && camX > maxCamX) {
			camX -= camMoveSpeed;
			playerViewManager.addCharX(-camMoveSpeed);
			playerViewManager.addMoveX(-camMoveSpeed);
		}
		
		if (mouseY < 30 && camY < 0) {
			camY += camMoveSpeed;
			playerViewManager.addCharY(camMoveSpeed);
			playerViewManager.addMoveY(camMoveSpeed);
		}
		else if (mouseY > MainGame.screenHeight-30 && camY > maxCamY) {
			camY -= camMoveSpeed;
			playerViewManager.addCharY(-camMoveSpeed);
			playerViewManager.addMoveY(-camMoveSpeed);
		}
	}
	
	private void checkCharacterMove(Input input) {
		if (!playerViewManager.isPlayingAnimation() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			playerViewManager.setMovePosition(input.getMouseX(), input.getMouseY());
		}
	}

	private void checkKeyboardInteraction(Input input) {
		if (input.isKeyPressed(Input.KEY_ENTER) && !dialogPause) {
			setPaused(!paused, false);
		}
		
		if (input.isKeyPressed(Input.KEY_SPACE) && playerViewManager.isPlayingAnimation() && !paused) {
			playerViewManager.stopPlayingAnimation();
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE) && paused && !dialogPause) {
			sbg.enterState(MainGame.menuScreen, new FadeOutTransition(), new FadeInTransition());
		}
		
		if (input.isKeyPressed(Input.KEY_M) && MainGame.debug) {
			musicState++;
			if (musicState == 3)
				musicState = 0;
		}
	}

	public void triggerDeath() {
		dead = true;
		
		ArrayList<String> saves = new ArrayList<>();
		DialogManager.createDialog(DialogManager.TYPE_LIST, "Select a day to restart from:", saves);
		
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					String response = DialogManager.getDialogResponse();
					
					if (response.equals("Cancel"))
						sbg.enterState(MainGame.menuScreen, new FadeOutTransition(), new FadeInTransition());
					else
						System.out.println("Well, fuck");
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	
	@Override
	public int getID() {
		return stateId;
	}

}
