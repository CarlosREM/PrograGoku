package management;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import view.ColliderRect;

//import main.MainGame;

public class MapCollisionManager {

	// each tile = 32 x 32
	// map tile size = 60 x 40
	
	private static final Color renderColor = new Color(255, 0, 0, 135); //r, g, b, alpha
	
	//rectangle = float [4] {x, y, h, w} 
	private static List<ColliderRect> rectangles;
	
	public static void init() {
		rectangles = new ArrayList<>();
		
		rectangles.add(new ColliderRect(0, 0, 32*60, 32*12)); 			//map top
		rectangles.add(new ColliderRect(0, 32*35, 32*60, 32*5)); 		//map bottom
		rectangles.add(new ColliderRect(0, 32*12, 32*2, 32*23)); 		//map left
		rectangles.add(new ColliderRect(32*42, 32*12, 32*18, 32*14)); 	//map right - top
		rectangles.add(new ColliderRect(32*42, 32*28, 32*18, 32*7)); 	//map right - bottom
		
		rectangles.add(new ColliderRect(32*2, 32*12, 32*9, 32*7)); 		//pool
		
		rectangles.add(new ColliderRect(32*9, 32*19, 32*2, 32*1 + 8)); 	//backyard sign
		
		rectangles.add(new ColliderRect (32*2, 32*24, 32*3, 32*11)); 	//arena - left 
		rectangles.add(new ColliderRect (32*5, 32*24, 32*3, 32*2)); 	//arena - top left
		rectangles.add(new ColliderRect (32*5, 32*31, 32*3, 32*4)); 	//arena - bottom left 
		rectangles.add(new ColliderRect (32*11, 32*24, 32*4, 32*2)); 	//arena - top right
		rectangles.add(new ColliderRect (32*11, 32*31, 32*4, 32*2)); 	//arena - bottom right
		rectangles.add(new ColliderRect (32*14, 32*26, 32*1, 32*5)); 	//arena - right 

		rectangles.add(new ColliderRect (32*17, 32*20, 32*2, 32*6)); 	//house - leftmost
		rectangles.add(new ColliderRect (32*19, 32*20, 32*4, 32*3)); 	//house - backdoor
		rectangles.add(new ColliderRect (32*22, 32*12, 32*20, 32*4-8));	//house - top
		rectangles.add(new ColliderRect (32*22, 32*16-8, 32*1, 32*4+8));//house - bathroom left
		rectangles.add(new ColliderRect (32*23, 32*17, 32*2-16, 32*3)); //house - bathroom bottom left
		rectangles.add(new ColliderRect (32*26+16, 32*17, 32*3-16, 32*3)); //house - bathroom bottom right
		rectangles.add(new ColliderRect (32*28, 32*16-8, 32*1, 32*1+8)); //house - bathroom right
		rectangles.add(new ColliderRect (32*25, 32*21+6, 32*3, 32*2-12));//house - sofa
		rectangles.add(new ColliderRect (32*31, 32*17, 32*5, 32*2)); 	//house - table
		rectangles.add(new ColliderRect (32*31, 32*19, 32*11, 32*3)); 	//house - bedroom top & frontdoor
		rectangles.add(new ColliderRect (32*31, 32*22, 32*1, 32*2)); 	//house - bedroom left
		rectangles.add(new ColliderRect (32*34, 32*22, 32*1, 32*1)); 	//house - nightstand
		rectangles.add(new ColliderRect (32*35, 32*22, 32*2, 32*2)); 	//house - bed
		rectangles.add(new ColliderRect (32*37, 32*22, 32*2, 32*4)); 	//house - frontdoor left
		rectangles.add(new ColliderRect (32*17, 32*26, 32*22, 32*4)); 	//house - bottom
		
		rectangles.add(new ColliderRect (32*16, 32*31, 32*9, 32*4)); 	//garden - left 
		rectangles.add(new ColliderRect (32*28, 32*32, 32*1, 32*3)); 	//garden - middle fence left 
		rectangles.add(new ColliderRect (32*32, 32*32, 32*1, 32*3)); 	//garden - middle fence right 
		rectangles.add(new ColliderRect (32*36, 32*31, 32*6, 32*4)); 	//garden - right
	}
	
	public static boolean checkCollision(ColliderRect playerRect, float xOffset, float yOffset) {
		boolean collision = false;
		float rectX, rectY;
		int i = 1;
		for (ColliderRect rect : rectangles) {
			rectX = rect.getX() + xOffset;
			rectY =  rect.getY() + yOffset;
			collision = playerRect.checkCollision(rectX, rectX + rect.getW(), rectY, rectY + rect.getH());
			if (collision)
				break;
			i++;
		}
		//System.out.println(i);
		return collision;
	}
	
	public static void draw(Graphics g, float xOffset, float yOffset) {
		g.setColor(renderColor);
		
		boolean onScreen = true;
		float rectX, rectY;
		for (ColliderRect rect : rectangles) {
			rectX = rect.getX() + xOffset;
			rectY = rect.getY() + yOffset;
			if (onScreen)
				g.fillRect(rectX, rectY, rect.getW(), rect.getH());
		}
		
		g.setColor(Color.white);
	}
	
}
