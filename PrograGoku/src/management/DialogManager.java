package management;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import main.MainGame;
import view.GameOverlay;
import view.GameScreen;

public class DialogManager extends UIManager {
	
	public static final int TYPE_MESSAGE = 0,
							TYPE_OK = 1,
							TYPE_YES_NO = 2,
							TYPE_LIST = 3;
	
	private static Dialog currentDialog = null;
	
	public static void createDialog(int type, String message) {
		SoundManager.playSound("dialogPopup");

		List<String> responses = new ArrayList<>();
		if (type == TYPE_OK) {
			responses.add("OK");
		}
		else if (type == TYPE_YES_NO) {
			responses.add("NO");
			responses.add("YES");
		}
		
		currentDialog = new Dialog(type, message, responses);
		
		if (type != TYPE_MESSAGE)
			GameScreen.getInstance().setPaused(true, true);
	}
	public static void createDialog(int type, String message, List<String> responses) {
		SoundManager.playSound("dialogPopup");

		currentDialog = new DialogManager.Dialog(type, message, responses);
		GameScreen.getInstance().setPaused(true, true);
	}
	
	
	public static String getDialogResponse() throws InterruptedException {
		while (currentDialog.responseSelected.isEmpty()) {
			Thread.sleep(100);
		}
		String response = currentDialog.responseSelected;
		closeDialog();
		return response;
	}
	
	public static void closeDialog() {
		GameScreen.getInstance().setPaused(false, false);
		currentDialog = null;
	}
	
	public static void checkDialogInput(Input input) {
		switch(currentDialog.type) {
		case TYPE_MESSAGE:
			break;
		case TYPE_OK:
			currentDialog.checkOkInput(input);
			break;
		case TYPE_YES_NO:
			currentDialog.checkYesNoInput(input);
			break;
		case TYPE_LIST:
			currentDialog.checkListInput(input);
			break;
		}
	}
	
	public static void drawDialog(Graphics g) {
		if (currentDialog == null)
			return;
		g.setLineWidth(10);
		switch(currentDialog.type) {
			case TYPE_MESSAGE:
				currentDialog.drawMessageDialog(g);
				break;
			case TYPE_OK:
				currentDialog.drawOkDialog(g);
				break;
			case TYPE_YES_NO:
				currentDialog.drawYesNoDialog(g);
				break;
			case TYPE_LIST:
				currentDialog.drawListDialog(g);
				break;
		}
		g.resetLineWidth();
		g.setColor(Color.white);
	}
	
	private static class Dialog {
		
		int type;
		String message;
		List<String> responses;
		int responseHovered = 0;
		String responseSelected = "";
		
		int optionMinIndex = 0;
		int optionListSize = 16;
		
		float width = 0, height = 0;
		
		Dialog(int type, String message, List<String> responses) {
			this.type = type;
			this.message = message;
			this.responses = responses;
			
			calculateMsgDims();
		}

		private void calculateMsgDims() {
			String[] lines = message.split("\n");
			
			height = 26 + lines.length*16 + 8; //upper offset + number of lines * 16 + lower offset
			switch(type) {
				case TYPE_LIST:
					height += optionListSize*16 + 5*32; // option list size + available commands
					break;
				case TYPE_OK: case TYPE_YES_NO:
					height += 32;

			}
			float tempWidth;
			for (String line : lines) {
				tempWidth = 16 + line.length()*10 + 16; // left offset + number of chars + right offset;
				if (tempWidth > width)
					width = tempWidth;
			}
			
			if (type == TYPE_LIST) {
				for (String option : responses) {
					tempWidth = 16 + option.length()*10 + 16; // left offset + number of chars + right offset;
					if (tempWidth > width)
						width = tempWidth;
				}
			}			
		}
		
		// INPUT CHECK
		
		void checkOkInput(Input input) {
			if (input.isKeyPressed(Input.KEY_ENTER)) {
				responseSelected = responses.get(responseHovered);
				SoundManager.playSound("dialogSelect");
			}
		}
		
		void checkYesNoInput(Input input) {
			boolean keyPress = false;
			if (input.isKeyPressed(Input.KEY_N)) {
				keyPress = true;
				responseHovered = 0;
				SoundManager.playSound("dialogDismiss");
			}
			else if (input.isKeyPressed(Input.KEY_Y)) {
				keyPress = true;
				responseHovered = 1;
				SoundManager.playSound("dialogSelect");
			}
			
			if (keyPress)
				responseSelected = responses.get(responseHovered);
		}
		
		void checkListInput(Input input) {
			boolean close = false;
			
			if (input.isKeyPressed(Input.KEY_UP)) {
				if (responseHovered > 0) {
					SoundManager.playSound("dialogNavigate");
					responseHovered -= 1;
					if (responseHovered < optionMinIndex)
						optionMinIndex -= 1;
				}
			}
			else if (input.isKeyPressed(Input.KEY_DOWN)) {
				if (responseHovered < (responses.size()-1)) {
					SoundManager.playSound("dialogNavigate");
					responseHovered += 1;
					if (responseHovered == (optionMinIndex + optionListSize))
						optionMinIndex += 1;
				}
			}
			
			else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
				SoundManager.playSound("dialogDismiss");
				responseSelected = "Cancel";
			}
			else if (input.isKeyPressed(Input.KEY_ENTER)) {
				SoundManager.playSound("dialogSelect");
				responseSelected = responses.get(responseHovered);
			}
		}

		// DRAW DIALOG
		
		void drawMessageDialog(Graphics g) {
			if (height < GameOverlay.getHeight())
				height = GameOverlay.getHeight();
						
			float y =  MainGame.screenHeight - height;
			
			g.setColor(borderColor);
			g.fillRoundRect(0, y, width, height + 10, 10);
			g.setColor(mainColor);
			g.fillRoundRect(5, y+5, width - 10, height + 10, 10);
			
			g.setColor(textColor);
			g.drawString(message, 16, y + 16);
		}
		
		void drawOkDialog(Graphics g) {	
			float x = MainGame.screenWidth/2 - width/2,
				  y = MainGame.screenHeight/3 - height/2;
			
			g.setColor(borderColor);
			g.fillRoundRect(x, y, width, height, 10);
			g.setColor(mainColor);
			g.fillRoundRect(x+5, y+5, width - 10, height - 10, 10);
			
			g.setColor(textColor);
			g.drawString(message, x + 16, y + 16);
			g.drawString("[ENTER] OK", x + width/2 - 48, y + height - 32);
		}
		
		void drawYesNoDialog(Graphics g) {
			float x = MainGame.screenWidth/2 - width/2,
				  y = MainGame.screenHeight/3 - height/2;
				
			g.setColor(borderColor);
			g.fillRoundRect(x, y, width, height, 10);
			g.setColor(mainColor);
			g.fillRoundRect(x+5, y+5, width - 10, height - 10, 10);
				
			g.setColor(textColor);
			g.drawString(message, x + 16, y + 16);
			g.drawString("[N] NO", x + 32, y + height - 32);
			g.drawString("[Y] YES", x + width - 96, y + height - 32);
		}
		
		void drawListDialog(Graphics g) {					
			float y =  MainGame.screenHeight - height;
			
			// LAYOUT
			g.setColor(borderColor);
			g.fillRoundRect(0, y, width, height + 10, 10);
			g.setColor(mainColor);
			g.fillRoundRect(5, y+5, width - 10, height, 10);
			
			// MESSAGE
			g.setColor(textColor);
			g.drawString(message, 16, y += 16);
			
			//div
			g.setColor(borderColor);
			g.fillRect(5, y += 24, width - 10, 2);
			
			// OPTIONS
			y += 12;
			g.setColor(textColor);
			String option;
			for (int i = 0; i < responses.size() && i < optionListSize; i++) {
				option = responses.get(optionMinIndex + i);
				g.drawString(option, 32, y + 24*i);
			}
			
			// OPTION HOVERED
			g.setColor(barColor.multiply(transparentMultiplierColor));
			g.fillRoundRect(16, y + 24*(responseHovered - optionMinIndex) - 2, width - 32, 20, 5);
			
			//div
			g.setColor(borderColor);
			g.fillRect(5, y += (24*optionListSize + 8), width - 10, 2);
			
			g.setColor(textColor);
			g.drawString("[^ , v] - Navigate", 16, y += 16);
			g.drawString("[ENTER] - Select", 16, y += 24);
			g.drawString(" [ESC]  - Cancel", 16, y += 24);

		}
		
	}
}
