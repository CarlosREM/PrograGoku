package view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import main.MainGame;

public class GameOverlay {
	
	private static final Color transpBlackColor = new Color(50, 50, 50, 100),
							   borderColor = new Color(104, 8, 40), 	//#63411C
							   mainColor = new Color(248, 248, 192), 	//#F8D088
							   textColor = new Color(177, 85, 46), 		//#461715
							   barColor = new Color(13, 158, 54), 		//#0B9F36
							   fitGreenColor = new Color(6, 194, 69),
							   fitYellowColor = new Color(243, 234, 44),
							   fitRedColor = new Color(232, 8, 24);
	
	private static final String res = "res/images/game/";
	
	private static final int height = 160;
	public static int getHeight() { return height; }
	
	private static final int cornerRadius = 10;
	public static int getCornerRadius() { return cornerRadius; }

	private static final int borderWidth = 10;
	
	//STATS - - -
	private static String time = "23:00", date = "Day 31, Year 10";

	private static String statFeeling = "Happy?";
	private static int statEnergy = 50,
					   statSleep = 50,
					   statToilet = 50,
					   statHunger = 50,
					   statMental = 50,
					   statFitness = 50;
	
	private static List<String> sicknesses;
	
	
	public static void init() throws SlickException {
		sicknesses = new ArrayList<>();
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
		
		// FEELING PORTRAIT
		g.setColor(transpBlackColor);
		g.fillRect(offsetX += borderWidth*2, offsetY += borderWidth*2,
				   height, height - borderWidth*6);
		g.setColor(textColor);
		g.drawString("Feeling: " + statFeeling, offsetX, offsetY + height - borderWidth*5 - 8);
		
		// div
		g.setColor(borderColor);
		offsetX += height + 32;
		g.fillRect(offsetX, offsetY, 4, height - borderWidth*5);
		offsetX += 4 + 32;

		// STAT TEXT
		g.setColor(textColor);
		g.drawString("Energy:", offsetX, offsetY);
		g.drawString("Sleep:", offsetX, offsetY + 44);
		g.drawString("Toilet:", offsetX, offsetY + 44 + 32);
		g.drawString("Hunger:", offsetX + 76 + 150 + 16, offsetY + 44);
		g.drawString("Mental:", offsetX + 76 + 150 + 16, offsetY + 44 + 32);
		
		// STAT BARS
		g.setColor(transpBlackColor);
		g.fillRoundRect(offsetX + 76, offsetY, 392, 20, 5);
		g.fillRoundRect(offsetX + 76, offsetY + 44, 150, 20, 5);
		g.fillRoundRect(offsetX + 76, offsetY + 44 + 32, 150, 20, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76, offsetY + 44, 150, 20, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76, offsetY + 44 + 32, 150, 20, 5);
		
		g.setColor(barColor);
		g.fillRoundRect(offsetX + 76 + 2, offsetY + 2,
					   (392f*((float) statEnergy/100)) - 4, 20 - 4, 5);
		g.fillRoundRect(offsetX + 76 + 2, offsetY + 44 + 2,
					   (150f*((float) statSleep/100)) - 4, 20 - 4, 5);
		g.fillRoundRect(offsetX + 76 + 2, offsetY + 44 + 32 + 2,
					   (150f*((float) statToilet/100)) - 4, 20 - 4, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44+ 2,
					   (150f*((float) statHunger/100)) - 4, 20 - 4, 5);
		g.fillRoundRect(offsetX + 76 + 150 + 16 + 76 + 2, offsetY + 44 + 32 + 2,
					   (150f*((float) statMental/100)) - 4, 20 - 4, 5);
		
		
		// div
		g.setColor(borderColor);
		offsetX += 76 + 392 + 32;
		g.fillRect(offsetX, offsetY, 4, height - borderWidth*5);
		offsetX += 4 + 32;
		
		// ENFERMEDADES
		if (sicknesses.isEmpty()) {
			for (int n = 0; n < 6; n++)
				sicknesses.add("AIDS");
		}
		g.setColor(textColor);
		g.drawString("Sicknesses: ", offsetX, offsetY);
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
		g.drawString("Fitness:", offsetX, offsetY);
		
		g.setColor(transpBlackColor);
		g.fillRoundRect(offsetX, offsetY + 32, 180, 40, 5);
		g.setColor(fitGreenColor);
		g.fillRoundRect(offsetX + 5, offsetY + 32 + 5, 180 - 10, 40 - 10, 5);
		g.setColor(fitRedColor);
		g.fillRoundRect(offsetX + 5, offsetY + 32 + 5, 65, 40 - 10, 5);
		g.setColor(fitYellowColor);
		g.fillRect(offsetX + 5 + 60, offsetY + 32 + 5, 55, 40 - 10);
		
		g.setColor(Color.white);
		g.fillRect(offsetX + 172f*((float) statFitness/100), offsetY + 32, 8, 40);
		
		g.setColor(textColor);
		g.drawString("Fat", offsetX, offsetY + 32 + 40);
		g.drawString("Fit", offsetX + 150, offsetY + 32 + 40);
		
		g.setColor(Color.white);
	}
}
