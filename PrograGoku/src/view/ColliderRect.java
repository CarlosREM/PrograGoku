package view;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class ColliderRect {
	
	private static Color renderColor = new Color(0, 0, 0, 135); //r, g, b, alpha

	public static int UP = 0;
	public static int DOWN = 1;
	public static int LEFT = 2;
	public static int RIGHT = 3;

	private float x, y, w, h;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getX2() {
		return x + w;
	}
	
	public float getY2() {
		return y + h;
	}
	
	public float getCenterX() {
		return x + w/2;
	}
	
	public float getCenterY() {
		return y + h/2;
	}
	
	public ColliderRect(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	
	public boolean[] checkCollision(ColliderRect rect) {
		boolean up = rect.getY2() < this.getY(),
				down = this.getY2() < rect.getY(),
				left = rect.getX2() < this.getX(),
				right = this.getX2() < rect.getX();
		
		return new boolean[] { !up, !down, !left, !right};
	}
	
	public boolean checkCollision(float x1, float x2, float y1, float y2) {
		/*
		System.out.println("Collisions:"
				 + "\n\tUP: " + y2 +" < "+ this.getY()
				 + "\n\tDOWN: " + this.getY2() + " < " + y1
				 + "\n\tLEFT: " + x2 + " < " + this.getX()
				 + "\n\tRIGHT: " + this.getX2() + " < " + x1);
		*/
		boolean up = y2 < this.getY(),
				down = this.getY2() < y1,
				left = x2 < this.getX(),
				right = this.getX2() < x1;
		
		return !(up || down || left || right);
	}
	
	public void draw(Graphics g) {
		g.setColor(renderColor);
		g.fillOval(x, y, w, h);
		g.setColor(Color.white);
	}
}
