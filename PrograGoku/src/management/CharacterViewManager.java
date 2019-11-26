package management;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.MainGame;
import view.AnimatedSprite;
import view.ColliderRect;
import view.GameScreen;

public class CharacterViewManager {

	private static CharacterViewManager instance = null;
	
	private static final String res = "res/images/game/";
	
	public static void loadCharacterSprites() {
		
	}
	
	private CharacterViewManager() throws SlickException {
		character = new AnimatedSprite(res + "spr_character.png", 32, 48, 160);
		playerCollider = new ColliderRect(28, 14);
	}
	public static CharacterViewManager getInstance() throws SlickException {
		if (instance == null)
			instance = new CharacterViewManager();
		return instance;
	}
	
	// CHARACTER INFO
	private AnimatedSprite character;
	private ColliderRect playerCollider;
	
	private float moveX, moveY;
	private final float charMoveSpeed = 0.5f;
	
	private boolean lockPosition = false;
	public void setLockPosition(boolean state) { lockPosition = state; }
	public boolean getLockPosition() { return lockPosition; }
	
	private Animation currentAnimation = null;
	private void setCurrentAnimation(Animation anim) { currentAnimation = anim; }
	public boolean isPlayingAnimation() { return currentAnimation != null; }
	public void stopPlayingAnimation() {
		setCharPosition(currentAnimation.exitX, currentAnimation.exitY);
		resetMovePosition();
		currentAnimation = null;
	}
	
	public boolean isCharacterIdle() { return character.isIdle(); }
	public void setCharacterIdle(boolean state) { character.setIdle(state); }
	
	public static final int LOOK_DOWN = 0,
							LOOK_UP = 1,
							LOOK_LEFT = 2,
							LOOK_RIGHT = 3;
	public void setCharacterLookingDirection(int direction) { character.setCurrentAnimation(direction); }
	
	// PLAYER COORDINATES
	public float getCharX() { return playerCollider.getCenterX(); }
	public void setCharX(float x) { playerCollider.setX(x - playerCollider.getW()/2); }
	public void addCharX(float x) { playerCollider.setX(playerCollider.getX() + x); }
	
	public float getCharY() { return playerCollider.getCenterY(); }
	public void setCharY(float y) {  playerCollider.setY(y - playerCollider.getH()/2); }
	public void addCharY(float y) {  playerCollider.setY(playerCollider.getY() + y); }
	
	public void setCharPosition(float x, float y) {
		setCharX(x + GameScreen.getInstance().getCamX());
		setCharY(y + GameScreen.getInstance().getCamY());
	}
	public void setCharPosition(FixedActivityCoord activity) {
		setCharX(activity.x + GameScreen.getInstance().getCamX());
		setCharY(activity.y + GameScreen.getInstance().getCamY());
	}
	
	// MOVE COORDINATES
	public float getMoveX() { return moveX; }
	public void addMoveX(float moveX) { this.moveX += moveX; }

	public float getMoveY() { return moveY; }
	public void addMoveY(float moveY) { this.moveY += moveY; }
	
	public void setMovePosition(float moveX, float moveY) {
		this.moveX = moveX;
		this.moveY = moveY;
	}
	public void setMovePosition(Animation anim) {
		this.moveX = anim.x2 + GameScreen.getInstance().getCamX();
		this.moveY = anim.y2 + GameScreen.getInstance().getCamY();
	}
	public void resetMovePosition() {
		this.moveX = getCharX();
		this.moveY = getCharY();
	}

	
	// PROCEDURES - - - 
	
	public void startingPos() {
		setLockPosition(false);
		
		float initX = MainGame.screenWidth/2 - GameScreen.getInstance().getCamX(),
			  initY = MainGame.screenHeight/2 - GameScreen.getInstance().getCamY();
		setCharPosition(initX, initY);
		
		character.setIdle(true);
		character.setPosition(playerCollider.getCenterX() - character.getWidth()/2,
		 			  		  playerCollider.getY2() - character.getHeight());
		
		resetMovePosition();
		
		currentAnimation = null;
	}
	
	public void drawCharacter(Graphics g) {
		playerCollider.draw(g);
		character.draw();
	}
	
	public void moveCharacter(float offsetX, float offsetY) {
		boolean idle = true;

		float charX = playerCollider.getCenterX(),
			  charY = playerCollider.getCenterY(),
			  distX = Math.abs(moveX - charX),
			  distY = Math.abs(moveY - charY);
		
		if (!lockPosition && (distX > charMoveSpeed || distY > charMoveSpeed)) {
			idle = false;
			
			if (distX > charMoveSpeed) {
				if (moveX < charX) { // LEFT
					addCharX(-charMoveSpeed);
					setCharacterLookingDirection(LOOK_LEFT);
				}
				
				else if (charX < moveX) { // RIGHT
					addCharX(charMoveSpeed);
					setCharacterLookingDirection(LOOK_RIGHT);
				}
			}
			
			else if (distY > charMoveSpeed) {
				if (charY < moveY) { // DOWN
					addCharY(charMoveSpeed);
					setCharacterLookingDirection(LOOK_DOWN);
				}
				else if (moveY < charY) { // UP
					addCharY(-charMoveSpeed);
					setCharacterLookingDirection(LOOK_UP);
				}
			}
			
		}
		
		boolean xLock = false, yLock = false;
		if (!idle) {
			if (distX <= charMoveSpeed*2) {
				setCharX(moveX);
				xLock = true;
			}
			if (distY <= charMoveSpeed*2) {
				setCharY(moveY);
				yLock = true;
			}
		}
		character.setPosition(playerCollider.getCenterX() - character.getWidth()/2,
				  			  playerCollider.getY2() - character.getHeight());
		character.setIdle(idle);
		
		if (isPlayingAnimation() && xLock && yLock) {
			currentAnimation.switchPosition();
			setMovePosition(currentAnimation);
		}
	}
	
	public void checkMapCollision(float offsetX, float offsetY) {
		boolean collision = MapCollisionManager.checkCollision(playerCollider, offsetX, offsetY);
		
		if (collision) {
			
			if (playerCollider.getCenterX() > moveX) //LEFT
				addCharX(1);
		
			else if (playerCollider.getCenterX() < moveX) // RIGHT
				addCharX(-1);
		
			if (playerCollider.getCenterY() > moveY) // UP
				addCharY(1);

			else if (playerCollider.getCenterY() < moveY) // DOWN
				addCharY(-1);

			resetMovePosition();
		}
	}
	
	public void checkActionSpotTrigger(float offsetX, float offsetY) throws SlickException {
		if (!isPlayingAnimation() && ActionSpotManager.checkInteraction(playerCollider, offsetX, offsetY)) {
			resetMovePosition();
		}
	}
	
	public void playAnimation(FixedActivityCoord pos1, float exitX, float exitY) {
		Animation anim = new Animation(pos1, pos1, exitX, exitY);
		setCurrentAnimation(anim);
		setCharPosition(pos1);
		resetMovePosition();
	}
	
	public void playAnimation(FixedActivityCoord pos1, FixedActivityCoord pos2, float exitX, float exitY) {
		Animation anim = new Animation(pos1, pos2, exitX, exitY);
		setCurrentAnimation(anim);
		setCharPosition(pos1);
		setMovePosition(anim);
	}
	
	private static class Animation {
		float x1,y1,
			  x2,y2,
			  exitX, exitY;

		Animation(FixedActivityCoord pos1, FixedActivityCoord pos2, float exitX, float exitY) {
			this.x1 = pos1.x;
			this.y1 = pos1.y;
			this.x2 = pos2.x;
			this.y2 = pos2.y;
			this.exitX = exitX;
			this.exitY = exitY;
		}
		
		void switchPosition() {
			float xTemp = x1,
				  yTemp = y1;
			x1 = x2;
			y1 = y2;
			x2 = xTemp;
			y2 = yTemp;
		}
		
	}
}
