package management;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.MainGame;
import view.ColliderRect;

public class ActionSpotManager {
		
	private static final List<ActionSpot> actionSpots = new ArrayList<>();
	
	public static void init() {
		// ACTIVITIES - - -
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_BED, ActionSpot.Type.ACTIVITY));
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_TOILET, ActionSpot.Type.ACTIVITY));
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_FRIDGE, ActionSpot.Type.ACTIVITY));
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_MEDITATE, ActionSpot.Type.ACTIVITY));
		
		// DOORS 	- - -
		actionSpots.add(new ActionSpot(FixedActivityCoord.FRONTDOOR_IN, FixedActivityCoord.FRONTDOOR_OUT.y + 16));
		actionSpots.add(new ActionSpot(FixedActivityCoord.FRONTDOOR_OUT, FixedActivityCoord.FRONTDOOR_IN.y - 16));
		actionSpots.add(new ActionSpot(FixedActivityCoord.BACKDOOR_IN, FixedActivityCoord.BACKDOOR_OUT.y - 16));
		actionSpots.add(new ActionSpot(FixedActivityCoord.BACKDOOR_OUT, FixedActivityCoord.BACKDOOR_IN.y + 16));
		
		// SIGNS 	- - -
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BACKYARD, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BATHROOM, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BEDROOM, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_GARDEN, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_FRONTYARD, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_KITCHEN, ActionSpot.Type.SIGN));

		// GARDEN 	- - -
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN1, ActionSpot.Type.PICKUP));
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN2, ActionSpot.Type.PICKUP));
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN3, ActionSpot.Type.PICKUP));

		// FRIDGE 	- - -
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_FRIDGE, ActionSpot.Type.FRIDGE));

		// EXIT 	- - -
		actionSpots.add(new ActionSpot());
		
		if (MainGame.debug) {
			actionSpots.add(new ActionSpot(FixedActivityCoord.SLEEP, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.TABLE1, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.TABLE2, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.TOILET, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.POOL1, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.POOL2, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.FIELD1, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.FIELD2, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.FIELD3, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.ARENA1, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.ARENA2, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.SOFA1, ActionSpot.Type.SIGN));
			actionSpots.add(new ActionSpot(FixedActivityCoord.SOFA2, ActionSpot.Type.SIGN));
		}
	}
	
	public static boolean checkInteraction(ColliderRect playerCollider, float xOffset, float yOffset) throws SlickException {
		ColliderRect playerInteractionCollider = new ColliderRect(playerCollider.getCenterX() - 7,
																  playerCollider.getCenterY() - 3,
																  15, 7);
		boolean collision = false;
		float spotX, spotY;
		for (ActionSpot spot : actionSpots) {
			spotX = spot.getX() + xOffset - spot.getW()/2;
			spotY = spot.getY() + yOffset - spot.getH()/2;
			collision = playerInteractionCollider.checkCollision(spotX, spotX + spot.getW(), spotY, spotY + spot.getH());
			
			if (collision) {
				if (!spot.hasInteracted)
					spot.interact(playerCollider, xOffset, yOffset);
				else
					collision = false;
				break;
			}
			else if (spot.hasInteracted)
				spot.hasInteracted = false;
		}
		return collision;
	}
	
	public static void draw(Graphics g, float xOffset, float yOffset) {
		g.setLineWidth((float) 1.5);
		for (ActionSpot spot : actionSpots)
			spot.draw(g, xOffset, yOffset);
		
		g.resetLineWidth();
		g.setColor(Color.white);
	}
	

	private static class ActionSpot extends ColliderRect {

		static enum Type {
			
				DOOR	(new Color(232, 8, 24, 223)),
				ACTIVITY(new Color(243, 234, 44, 223)),
				SIGN	(new Color(248, 248, 192, 223)),
				PICKUP	(new Color(6, 194, 69, 223)),
				FRIDGE	(new Color(128, 128, 248, 223)),
				EXIT	(new Color(0, 0, 0, 127));
			
			public final Color renderColor;
			
			private Type(Color color) {
				renderColor = color;
			}
		}
		
		private Type type;
		private float oppositeY;
		private boolean hasInteracted = false;
		
		public ActionSpot(FixedActivityCoord activity, Type type) {
			super(activity.x, activity.y, 8, 4);
			this.type = type;
			oppositeY = 0;
		}
		
		public ActionSpot(FixedActivityCoord activity, float oppositeY) {
			super(activity.x, activity.y, 8, 4);
			this.type = Type.DOOR;
			this.oppositeY = oppositeY;
		}
		
		public ActionSpot() {
			super(1856, 832, 64, 64);
			this.type = Type.EXIT;
		}
		
		public void interact(ColliderRect playerCollider, float xOffset, float yOffset) throws SlickException {
			System.out.println(this.type.name());
			hasInteracted = true;
			switch(type) {
				case DOOR:
					playerCollider.setY(oppositeY + yOffset);
					SoundManager.playSound("steps");
					break;
				case ACTIVITY:
					break;
				case SIGN:
					break;
				case PICKUP:
					break;
				case FRIDGE:
					break;

				case EXIT:
					break;
					
			}
		}
		
		public void draw(Graphics g, float xOffset, float yOffset) {
			float spotRenderX = this.getX() + xOffset - this.getW(),
				  spotRenderY = this.getY() + yOffset - this.getH();
			g.setColor(type.renderColor);
			
			if (type != Type.EXIT) {
				if (type == Type.PICKUP && !MainGame.debug)
					return;
				g.fillRoundRect(spotRenderX, spotRenderY, this.getW()*2, this.getH()*2, 1);
				g.setColor(type.renderColor.darker());
				g.drawRoundRect(spotRenderX, spotRenderY, this.getW()*2, this.getH()*2, 1);
			}
			else if (MainGame.debug) {
				spotRenderX += this.getW();
				spotRenderY += this.getH();
				g.fillRect(spotRenderX, spotRenderY, this.getW(), this.getH());
			}
		}
	}
}
