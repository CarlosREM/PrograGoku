package management;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import main.MainGame;
import view.ColliderRect;

public class ActionSpotManager extends UIManager {
	
	private static final List<ActionSpot> actionSpots = new ArrayList<>();
	
	public static void init() {
		loadActivities();
		loadDoors();
		loadSigns();
		loadGarden();
		loadLists();
		loadExit();
		
		if (MainGame.debug) {
			loadAnimationSpots();
		}
	}
	
	private static void loadActivities() {
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_BED, ActionSpot.Type.ACTIVITY) {
			@Override
			public void action() {
				DialogManager.createDialog(DialogManager.TYPE_YES_NO, "This is a reeeeeeeeally loooooong Activity!\nDo you wish to do it or are you gonna chicken out?");
				Thread dialogThread = new Thread() {
					@Override
					public void run() {
						try {
							String response = DialogManager.getDialogResponse();
							System.out.println(response);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				dialogThread.start();
			}
		});
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_TOILET, ActionSpot.Type.ACTIVITY));
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_MEDITATE, ActionSpot.Type.ACTIVITY));
	}
	
	private static void loadDoors() {
		actionSpots.add(new ActionSpot(FixedActivityCoord.FRONTDOOR_IN, FixedActivityCoord.FRONTDOOR_OUT.y + 8));
		actionSpots.add(new ActionSpot(FixedActivityCoord.FRONTDOOR_OUT, FixedActivityCoord.FRONTDOOR_IN.y - 16));
		actionSpots.add(new ActionSpot(FixedActivityCoord.BACKDOOR_IN, FixedActivityCoord.BACKDOOR_OUT.y - 16));
		actionSpots.add(new ActionSpot(FixedActivityCoord.BACKDOOR_OUT, FixedActivityCoord.BACKDOOR_IN.y + 8));
	}
	
	private static void loadSigns() {
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BATHROOM, ActionSpot.Type.SIGN) {
			@Override
			public void action() {
				DialogManager.createDialog(DialogManager.TYPE_OK, "I'm a sign!\nRead me thoroughly~");
				Thread dialogThread = new Thread() {
					@Override
					public void run() {
						try {
							String response = DialogManager.getDialogResponse();
							System.out.println(response);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				dialogThread.start();
			}
		});
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BEDROOM, ActionSpot.Type.SIGN) {
			@Override
			public void action() {
				DialogManager.createDialog(DialogManager.TYPE_OK, "This is a really long text message!\nI'm a big ass sign now, dumbass.\nPiss off, will ya?");
				Thread dialogThread = new Thread() {
					@Override
					public void run() {
						try {
							String response = DialogManager.getDialogResponse();
							System.out.println(response);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				dialogThread.start();
			}
		});
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_GARDEN, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_FRONTYARD, ActionSpot.Type.SIGN));
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_KITCHEN, ActionSpot.Type.SIGN));
	}
	
	private static void loadGarden() {
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN1, ActionSpot.Type.PICKUP));
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN2, ActionSpot.Type.PICKUP));
		actionSpots.add(new ActionSpot(FixedActivityCoord.GARDEN3, ActionSpot.Type.PICKUP));
	}
	
	private static void loadLists() {
		actionSpots.add(new ActionSpot(FixedActivityCoord.ACTION_FRIDGE, ActionSpot.Type.LIST) {
			@Override
			public void action() {
				ArrayList<String> options = new ArrayList<>();
				for (int i = 0; i < 24; i++)
					options.add("Option "+i);
				
				DialogManager.createDialog(DialogManager.TYPE_LIST, "Select a consumable:", options);
				Thread dialogThread = new Thread() {
					@Override
					public void run() {
						try {
							String response = DialogManager.getDialogResponse();
							System.out.println(response);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				dialogThread.start();
			}
		});
		actionSpots.add(new ActionSpot(FixedActivityCoord.SIGN_BACKYARD, ActionSpot.Type.LIST) {
			@Override
			public void action() {
				ArrayList<String> options = new ArrayList<>();
				for (int i = 0; i < 10; i++)
					options.add("Option "+i);
				
				DialogManager.createDialog(DialogManager.TYPE_LIST, "Select a training activity:", options);
				Thread dialogThread = new Thread() {
					@Override
					public void run() {
						try {
							String response = DialogManager.getDialogResponse();
							System.out.println(response);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				};
				dialogThread.start();
			}
		});
	}
	
	private static void loadExit() {
		actionSpots.add(new ActionSpot() {
			@Override
			public void action() {
				DialogManager.createDialog(DialogManager.TYPE_MESSAGE, "I think I have what I\nneed in the house...");
			}
		});
	}
	
	private static void loadAnimationSpots() {
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
	
	public static boolean checkInteraction(ColliderRect playerCollider, float xOffset, float yOffset) throws SlickException {
		ColliderRect playerInteractionCollider = new ColliderRect(playerCollider.getCenterX() - 7,
																  playerCollider.getCenterY() - 3,
																  15, 7);
		boolean collision = false;
		float spotX, spotY;
		for (ActionSpot spot : actionSpots) {
			spotX = spot.getX() + xOffset - spot.getW()/2;
			spotY = spot.getY() + yOffset - spot.getH()/2;
			if (spot.type == ActionSpot.Type.EXIT) {
				spotX += spot.getW()/2;
				spotY += spot.getH()/2;
			}
			collision = playerInteractionCollider.checkCollision(spotX, spotX + spot.getW(), spotY, spotY + spot.getH());
			
			if (collision) {
				if (!spot.hasInteracted)
					spot.triggerAction(playerCollider, xOffset, yOffset);
				else
					collision = false;
				break;
			}
			else if (spot.hasInteracted) {
				spot.hasInteracted = false;
				if (spot.type == ActionSpot.Type.EXIT)
					DialogManager.closeDialog();
			}
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
			
				DOOR	(new Color(redColor).multiply(transparentMultiplierColor)),
				ACTIVITY(new Color(yellowColor).multiply(transparentMultiplierColor)),
				SIGN	(new Color(mainColor).multiply(transparentMultiplierColor)),
				PICKUP	(new Color(greenColor).multiply(transparentMultiplierColor)),
				LIST	(new Color(blueColor).multiply(transparentMultiplierColor)),
				EXIT	(new Color(Color.black).multiply(transparentMultiplierColor));
			
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
		
		public void triggerAction(ColliderRect playerCollider, float xOffset, float yOffset) throws SlickException {
			hasInteracted = true;
			switch(type) {
				case DOOR:
					playerCollider.setY(oppositeY + yOffset);
					SoundManager.playSound("steps");
					break;
				default:
					action();
					break;
			}
		}
		
		public void action() {
			System.out.println(this.type.name());
		}
		
		public void draw(Graphics g, float xOffset, float yOffset) {
			float spotRenderX = this.getX() + xOffset - this.getW(),
				  spotRenderY = this.getY() + yOffset - this.getH();
			
			type.renderColor.multiply(transparentMultiplierColor);
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
