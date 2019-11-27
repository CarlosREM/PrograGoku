package view;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ADT.ExtendedCharacter;
import ADT.GameState;
import main.MainGame;
import management.SoundManager;

public class MenuScreen extends BasicGameState {
	private final int stateId;
	
	private String mouse = "No input";
	
	// RESOURCES
	private BigImage background,
				  gameTitle;
	
	private List<MenuButton> buttons = new ArrayList<>();
		
	private static final String res = "res/images/menu/";
	
	public MenuScreen(int stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new BigImage(res + "MenuBG.png");
		gameTitle = new BigImage(res + "MenuTitle.png");
		
		//btnStart
		buttons.add(new MenuButton(MainGame.screenWidth/16, MainGame.screenHeight/9*6,
								  res + "Menu_Start.png",
								  res + "Menu_StartFocused.png",
								  res + "Menu_StartPressed.png") {
			@Override
			public void performAction() {
				GameState.getInstance().setCharacter(new ExtendedCharacter("Phillip"));
				sbg.enterState(MainGame.gameScreen, new FadeOutTransition(), new FadeInTransition());
			}
		});
		//btnLoad
		buttons.add(new MenuButton(MainGame.screenWidth/16, MainGame.screenHeight/9*7,
				  				 res + "Menu_Load.png",
				  				 res + "Menu_LoadFocused.png",
				  				 res + "Menu_LoadPressed.png"));
		
		//btnExit
		buttons.add(new MenuButton(MainGame.screenWidth/16, MainGame.screenHeight/9*8,
				  				 res + "Menu_Exit.png",
				  				 res + "Menu_ExitFocused.png",
				  				 res + "Menu_ExitPressed.png") {
			@Override
			public void performAction() {
				SoundManager.stopMusic();
				System.exit(0);
			}
		});
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		background.draw(0, 0);	
		gameTitle.draw(MainGame.screenWidth/16, MainGame.screenHeight/9);

		for(MenuButton button : buttons)
			button.draw();
		
		if (MainGame.debug) {
			g.drawString(mouse, 10, 30);
			g.drawString("Current State: " + getID(), 10, 50);
		}
		
		//AUDIO
		
		if (SoundManager.getTrackName() != "menu") {
			SoundManager.playMusic("menu");
			SoundManager.setVolume(1);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (MainGame.debug) {
			int mouseX = Mouse.getX(),
				mouseY = MainGame.screenHeight - Mouse.getY();
			mouse = "[MOUSE] X: " + mouseX + " - Y: " + mouseY;
		}
		
		if (gc.getInput().isKeyPressed(Input.KEY_LSHIFT)) {
			MainGame.debug = !MainGame.debug;
			gc.setShowFPS(MainGame.debug);
		}
		
		for (MenuButton button : buttons)
			checkButton(gc, button);
		
	}
	
	private void checkButton(GameContainer gc, MenuButton button) throws SlickException {
		Input input = gc.getInput();
		int mouseX = Mouse.getX(),
			mouseY = MainGame.screenHeight - Mouse.getY();
		
		boolean isFocused = button.checkFocus(mouseX, mouseY);
		if (isFocused) {
			if (button.getState() == MenuButton.STATE_IDLE)
				SoundManager.playSound("btnFocus");

			if (button.getState() == MenuButton.STATE_FOCUSED &&
				input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				button.setState(MenuButton.STATE_PRESSED);
				SoundManager.playSound("btnClick");
				button.performAction();
			}
			else if (button.getState() != MenuButton.STATE_FOCUSED &&
					!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				button.setState(MenuButton.STATE_FOCUSED);
		}
		else if (button.getState() != MenuButton.STATE_IDLE)
			button.setState(MenuButton.STATE_IDLE);
	}
	
	
	@Override
	public int getID() {
		return stateId;
	}

}
