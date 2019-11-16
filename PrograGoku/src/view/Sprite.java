package view;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image {

	private float x, y;
	
	public float getX() { return x; }
	public void setX(float x) {	this.x = x; }

	public float getY() { return y; }
	public void setY(float y) {this.y = y; }
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Sprite(String path) throws SlickException {
		super(path);
		this.x = 0;
		this.y = 0;
	}
	
	public Sprite(String path, float x, float y) throws SlickException {
		super(path);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw() {
		super.draw(x, y);
	}

}
