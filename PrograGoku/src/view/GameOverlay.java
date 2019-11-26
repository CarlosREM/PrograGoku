package view;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import ADT.ExtendedCharacter;
import abstraction.ASickness;
import main.MainGame;
import management.UIManager;

public class GameOverlay extends UIManager {
	
	private static final int height = 160;
	public static int getHeight() { return height; }
	
	private static final int cornerRadius = 10;
	public static int getCornerRadius() { return cornerRadius; }

	private static final int borderWidth = 10;
	
	//STATS - - -
	private static String time = "23:00", date = "Day 31, Year 10";

	private static String statMood = "Sleeping";
	private static float statEnergy = 50,
					   	 statSleep = 50,
					   	 statToilet = 50,
					   	 statHunger = 50,
					   	 statMental = 50,
					   	 statMusculature = 50;
	
	private static List<String> sicknesses = new ArrayList<>();
	
	public static void setTime(String time) { GameOverlay.time = time; }
	public static void setDate(String date) { GameOverlay.date = date; }

	public static void update(ExtendedCharacter character) {
		
		statMood = character.getMood().name();
		statEnergy = character.getCurrentHealthPoints();
		statSleep = 100 - character.getFatigue();
		statMental = character.getMentalHealth();
		statHunger = character.getHunger();
		statToilet = (character.getPee()/2) + (character.getPoop()/2);
		statMusculature = character.getMusculature();
		
		sicknesses.clear();
		for (ASickness sickness : character.getSickness())
			sicknesses.add(sickness.getName());

	}
	
	public static void drawTime(Graphics g) {
		float offsetX = MainGame.screenWidth - 160;
		g.setColor(transpBlackColor);
		g.fillRoundRect(offsetX, -cornerRadius,
						200 + cornerRadius, height/2, 2);
		
		g.setLineWidth(5);
		g.setColor(Color.black);
		g.drawRoundRect(offsetX, -cornerRadius,
						200 + cornerRadius, height/2, 2);
		g.resetLineWidth();

		offsetX += 16;
		g.setLineWidth(5);
		g.setColor(Color.white);
		g.drawString(date, offsetX, 12);
		g.drawString(time, offsetX, 40);
		g.resetLineWidth();
	}
	
	public static void drawPlayerStats(Graphics g) {
		float offsetX,
			  offsetY;
		
		// FRAME
		g.setColor(borderColor);
		g.fillRoundRect(offsetX = 0, offsetY = MainGame.screenHeight - height,
				        MainGame.screenWidth, height+cornerRadius, cornerRadius);
		g.setColor(mainColor);
		g.fillRoundRect(offsetX += borderWidth, offsetY += borderWidth,
		        	    MainGame.screenWidth - borderWidth*2, height, cornerRadius);
		
		// MOOD PORTRAIT
		offsetX += borderWidth*2;
		offsetY += borderWidth*2;
		drawMoodPortrait(g, offsetX, offsetY);
		g.setColor(textColor);
		g.drawString("MOOD: " + statMood, offsetX, offsetY + height - borderWidth*5 - 8);
		
		// div
		g.setColor(borderColor);
		offsetX += height + 32;
		g.fillRect(offsetX, offsetY, 4, height - borderWidth*5);
		offsetX += 4 + 32;

		// STAT TEXT
		g.setColor(textColor);
		g.drawString("Energy:", offsetX, offsetY);
		g.drawString("Sleep:", offsetX, offsetY + 44);
		g.drawString("Mental:", offsetX, offsetY + 44 + 32);
		g.drawString("Hunger:", offsetX + 76 + 150 + 16, offsetY + 44);
		g.drawString("Toilet:", offsetX + 76 + 150 + 16, offsetY + 44 + 32);
		
		// STAT BARS
		g.setColor(transpBlackColor);
		g.fillRoundRect(offsetX + 76, offsetY, 392, 20, 5);
		g.fillRoundRect(offsetX + 76, offsetY + 44, 150, 20, 5);
		g.fillRoundRect(offsetX + 76, offsetY + 44 + 32, 150, 20, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76, offsetY + 44, 150, 20, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76, offsetY + 44 + 32, 150, 20, 5);
		
		g.setColor(barColor);
		if (statEnergy >= 0)
			g.fillRoundRect(offsetX + 76 + 2, offsetY + 2,
						   (392f*((float) statEnergy/100)) - 4, 20 - 4, 5);
		if (statSleep >= 0)
			g.fillRoundRect(offsetX + 76 + 2, offsetY + 44 + 2,
						   (150f*((float) statSleep/100)) - 4, 20 - 4, 5);
		if (statMental >= 0)
			g.fillRoundRect(offsetX + 76 + 2, offsetY + 44 + 32 + 2,
						   (150f*((float) statMental/100)) - 4, 20 - 4, 5);
		if (statHunger >= 0)
			g.fillRoundRect(offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44+ 2,
						   (150f*((float) statHunger/100)) - 4, 20 - 4, 5);
		if (statToilet >= 0)
			g.fillRoundRect(offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44 + 32 + 2,
						   (150f*((float) statToilet/100)) - 4, 20 - 4, 5);
		
		//debug info
		if (MainGame.debug) {
			g.setColor(Color.white);
			g.drawString(""+statEnergy, offsetX + 76 + 2, offsetY + 2);
			g.drawString(""+statSleep,offsetX + 76 + 2, offsetY + 44 + 2);
			g.drawString(""+statMental,offsetX + 76 + 2, offsetY + 44 + 32 + 2);
			g.drawString(""+statHunger, offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44+ 2);
			g.drawString(""+statToilet, offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44 + 32 + 2);
		}
		
		// div
		g.setColor(borderColor);
		offsetX += 76 + 392 + 32;
		g.fillRect(offsetX, offsetY, 4, height - borderWidth*5);
		offsetX += 4 + 32;
		
		// SICKNESSES
		g.setColor(textColor);
		g.drawString("SICKNESSES: ", offsetX, offsetY);
		if (sicknesses.isEmpty())
			g.drawString("> None :D", offsetX + 16, offsetY + 24);
		else {
			for (int i = 0; i < sicknesses.size(); i++) {
				if (i < 3)
					g.drawString("> "+sicknesses.get(i), offsetX + 16, offsetY + 24*(i+1));
				else {
					g.drawString("> +" + (sicknesses.size() - i), offsetX + 16, offsetY + 24*(i+1));
					break;
				}
			}
		}
		// div
		g.setColor(borderColor);
		offsetX += height + 32;
		g.fillRect(offsetX, offsetY, 4, height - borderWidth*5);
		offsetX += 4 + 32;
		
		// FITNESS
		g.setColor(textColor);
		g.drawString("MUSCULATURE:", offsetX, offsetY);
		
		g.setColor(transpBlackColor);
		g.fillRoundRect(offsetX, offsetY + 32, 180, 40, 5);
		g.setColor(greenColor);
		g.fillRoundRect(offsetX + 5, offsetY + 32 + 5, 180 - 10, 40 - 10, 5);
		g.setColor(redColor);
		g.fillRoundRect(offsetX + 5, offsetY + 32 + 5, 65, 40 - 10, 5);
		g.setColor(yellowColor);
		g.fillRect(offsetX + 5 + 60, offsetY + 32 + 5, 55, 40 - 10);
		
		g.setColor(Color.white);
		g.fillRect(offsetX + 172f*((float) statMusculature/100), offsetY + 32, 8, 40);
		
		g.setColor(textColor);
		g.drawString("Fat", offsetX, offsetY + 32 + 40);
		g.drawString("Fit", offsetX + 150, offsetY + 32 + 40);
		if (MainGame.debug)
			g.drawString("" + statMusculature, offsetX + 75, offsetY + 32 + 40);
		
		g.setColor(Color.white);
		
	}

	private static void drawMoodPortrait(Graphics g, float offsetX, float offsetY) {
		Color currentColor;
		switch(statMood) {
			case "Happy":
				currentColor = greenColor;
				break;
				
			case "Sad":
				currentColor = blueColor;
				break;
				
			case "Sick":
				currentColor = greenColor;
				
			case "Delicioso":
				currentColor = redColor.brighter(5f);
				break;
				
			case "Sleeping":
				currentColor = Color.white;
				break;
				
			default:
				currentColor = yellowColor;
		}
		g.setColor(currentColor);
		g.fillRoundRect(offsetX, offsetY, height, height - borderWidth*6, 5);
		
		g.setColor(transpBlackColor);
		g.fillRect(offsetX + 32, offsetY + 24, 16, 16); // LEFT EYE
		g.fillRect(offsetX + 112, offsetY + 24, 16, 16);// RIGHT EYE
		
		g.fillRect(offsetX + 32, offsetY + 56, 96, 32); // MOUTH

		g.setColor(currentColor);
		switch(statMood) {
			case "Happy":
				g.fillRect(offsetX + 48, offsetY + 56, 64, 16);
				break;
				
			case "Delicioso":
				g.setColor(redColor);
				g.fillRect(offsetX + 32, offsetY + 44, 16, 8); // LEFT BLUSH
				g.fillRect(offsetX + 112, offsetY + 44, 16, 8);// RIGHT BLUSH
				break;
				
			case "Sad": case "Sick":
				g.fillRect(offsetX + 48, offsetY + 72, 64, 16);
				break;
				
			case "Sleeping":
				g.fillRect(offsetX + 32, offsetY + 80, 88, 8);
				
				g.setColor(transpBlackColor);
				g.fillRect(offsetX + 48, offsetY + 24, 16, 16);
				g.fillRect(offsetX + 96, offsetY + 24, 16, 16);
				break;
			
			default:
				g.fillRect(offsetX + 32, offsetY + 56, 96, 16);
		}
	}
	
	public static void drawCombatPrepOverlay(Graphics g) {
		
	}
	
	
	public static void drawCombatOverlay(Graphics g) {
		
	}
}
