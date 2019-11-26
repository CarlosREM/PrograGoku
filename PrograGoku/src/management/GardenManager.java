package management;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ADT.Fridge;
import ADT.GardenFactory;
import abstraction.AConsumable;
import view.GameScreen;

public class GardenManager extends UIManager {

	private static GardenFactory factory;
	private static AConsumable[] gardenSpots;
	
	public static void init() {
		factory = ADT.Loader.getGarden();
		gardenSpots = new AConsumable[] {null, null, null};
	}
	
	public static void loadGardenSpot(int pos) {
		AConsumable newConsumable = null;
		switch(pos) {
			case 0: case 1:
				newConsumable = factory.getMealsFactory().getRandomConsumable();
				break;
				
			case 2:
				newConsumable = factory.getDrugsFactory().getRandomConsumable();
				break;
			
			default:
				System.out.println("FUCK, que haces!?!");
				break;
		}
		if (newConsumable != null) {
			gardenSpots[pos] = newConsumable;
		}
	}
	
	public static void pickupConsumable(int pos) {
		try { 
			if (gardenSpots[pos] == null) {
				DialogManager.createDialog(DialogManager.TYPE_MESSAGE, "Nothing to harvest here.\nI'll come back later");
			}
			else {
				DialogManager.createDialog(DialogManager.TYPE_MESSAGE, "Harvested some " + gardenSpots[pos].getName());
				Fridge.addConsumable(gardenSpots[pos].getName(), gardenSpots[pos]);
				gardenSpots[pos] = null;
			}
			Thread.sleep(1500);
			DialogManager.closeDialog();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void draw(Graphics g) {
		float x = 0,
			  y = FixedActivityCoord.GARDEN1.y + GameScreen.getInstance().getCamY();
			 
		Color currentColor = null;
		for (int i= 0; i < 3; i++) {
			switch(i) {
			case 0:
				currentColor = greenColor;
				x = FixedActivityCoord.GARDEN1.x;
				break;
			case 1:
				currentColor = redColor;
				x = FixedActivityCoord.GARDEN2.x;
				break;
			case 2:
				currentColor = blueColor;
				x = FixedActivityCoord.GARDEN3.x;
				break;
			}
			if (gardenSpots[i] != null) {
				g.setColor(currentColor);
				g.fillRect(x - 10 + GameScreen.getInstance().getCamX(), y - 8, 21, 17);
				g.setColor(currentColor.darker(0.5f));
				g.setLineWidth(2);
				g.drawRect(x - 10 + GameScreen.getInstance().getCamX(), y - 8, 21, 17);
				g.resetLineWidth();
			}
		}
	}
}
