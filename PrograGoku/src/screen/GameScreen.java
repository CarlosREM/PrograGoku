package screen;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class GameScreen extends BasicGameState {

	public GameScreen(int stateId) {
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Image background = new Image("res/GameMap.png");
		g.drawImage(background, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return 1;
	}

}
