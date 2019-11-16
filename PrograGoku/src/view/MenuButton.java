package view;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

public class MenuButton implements ButtonAction {

	public static final int STATE_IDLE = 0,
					 		STATE_FOCUSED = 1,
					 		STATE_PRESSED = 2;
	
	private float x, y;
	private int state;
	private List<Sprite> sprites = new ArrayList<>();
	
	public float getX() { return x; }
	public void setX(float x) { this.x = x;	}

	public float getY() { return y; }
	public void setY(float y) {	this.y = y; }

	public float getWidth() { return sprites.get(0).getWidth(); }
	public float getHeight() { return sprites.get(0).getHeight(); }
	
	public int getState() {	return state; }
	public void setState(int state) { this.state = state; }

	
	public MenuButton(float x, float y, String pathIdle, String pathFocused, String pathPressed) throws SlickException {
		this.x = x;
		this.y = y;
		
		state = STATE_IDLE;
		
		sprites.add(new Sprite(pathIdle, x, y));
		sprites.add(new Sprite(pathFocused, x, y));
		sprites.add(new Sprite(pathPressed, x, y));
	}
	
	public void draw() {
		sprites.get(state).draw();
	}
	
	public boolean checkFocus(float mouseX, float mouseY) {
		boolean checkX = getX() < mouseX && mouseX < getX()+getWidth(),
				checkY = getY() < mouseY && mouseY < getY()+getHeight();
		
		return checkX & checkY;
		
	}
	
	@Override
	public void performAction() {
		System.out.println("Im being pressed!");
	}
	
}
