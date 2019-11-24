package view;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import management.FixedActivityCoord;

public class AnimatedSprite {

	private float x, y;
	private int width, height;
	private int animSpeed;
	
	private List<Animation> animations = new ArrayList<>();
	private int currentAnimation = 0;
	private boolean idle = true;
	
	public float getX() { return x; }
	public void setX(float x) {	this.x = x; }

	public float getY() { return y; }
	public void setY(float y) {this.y = y; }
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public float getAnimSpeed() { return animSpeed; }
	public Animation getAnimation(int animID) { return animations.get(animID); }
	public Image getAnimationFrame(int animID, int frame) { return animations.get(animID).getImage(frame); }
	
	public int getCurrentAnimation() { return currentAnimation; }
	public void setCurrentAnimation(int animID) { currentAnimation = animID; }
	
	public boolean isIdle() { return idle;}
	public void setIdle(boolean idle) { this.idle = idle; }
	
	public AnimatedSprite(String path, int sprWidth, int sprHeight, int animSpeed) throws SlickException {
		this.x = 0;
		this.y = 0;
		this.width = sprWidth;
		this.height = sprHeight;
		this.animSpeed = animSpeed;
		
		SpriteSheet sheet = new SpriteSheet(path, sprWidth, sprHeight);
		sheet.startUse();
		for (int i = 0; i < sheet.getVerticalCount(); i++) {
			Animation anim = new Animation();
			for (int j = 0; j < sheet.getHorizontalCount(); j++) {
				anim.addFrame(sheet.getSprite(j, i), animSpeed);
			}
			animations.add(anim);
		}
	}
	
	public void draw() {
		if (idle)
			animations.get(currentAnimation).getImage(0).draw(x, y);
		else
			animations.get(currentAnimation).draw(x, y);
	}
	public void setPosition(FixedActivityCoord activityLocation) {
		this.x = activityLocation.x;
		this.y = activityLocation.y;
	}
}
