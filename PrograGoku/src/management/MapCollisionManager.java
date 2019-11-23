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
		
/*1*/	rectangles.add(new ColliderRect(0, 0, 32*60, 32*12)); 			//map top
/*2*/	rectangles.add(new ColliderRect(0, 32*35, 32*60, 32*5)); 		//map bottom
/*3*/	rectangles.add(new ColliderRect(0, 32*12, 32*2, 32*23)); 		//map left
/*4*/	rectangles.add(new ColliderRect(32*42, 32*12, 32*18, 32*14)); 	//map right - top
/*5*/	rectangles.add(new ColliderRect(32*42, 32*28, 32*18, 32*7)); 	//map right - bottom
		
/*6*/	rectangles.add(new ColliderRect(32*2, 32*12, 32*9, 32*7)); 		//pool
		
/*7*/	rectangles.add(new ColliderRect(32*9, 32*19, 32*2, 32*1 + 8)); 	//backyard sign
		
/*8*/	rectangles.add(new ColliderRect (32*2, 32*24, 32*3, 32*11)); 	//arena - left 
/*9*/	rectangles.add(new ColliderRect (32*5, 32*24, 32*3, 32*2)); 	//arena - top left
/*10*/	rectangles.add(new ColliderRect (32*5, 32*31, 32*3, 32*4)); 	//arena - bottom left 
/*11*/	rectangles.add(new ColliderRect (32*11, 32*24, 32*4, 32*2)); 	//arena - top right
/*12*/	rectangles.add(new ColliderRect (32*11, 32*31, 32*4, 32*2)); 	//arena - bottom right
/*13*/	rectangles.add(new ColliderRect (32*14, 32*26, 32*1, 32*5)); 	//arena - right 

/*14*/	rectangles.add(new ColliderRect (32*17, 32*20, 32*2, 32*6)); 	//house - leftmost
/*15*/	rectangles.add(new ColliderRect (32*19, 32*20, 32*4, 32*3)); 	//house - backdoor
/*16*/	rectangles.add(new ColliderRect (32*22, 32*12, 32*20, 32*4-8));	//house - top
/*17*/	rectangles.add(new ColliderRect (32*22, 32*16-8, 32*1, 32*4+8));//house - bathroom left
/*18*/	rectangles.add(new ColliderRect (32*23, 32*17, 32*2-16, 32*3)); //house - bathroom bottom left
/*19*/	rectangles.add(new ColliderRect (32*26+16, 32*17, 32*3-16, 32*3)); //house - bathroom bottom right
/*20*/	rectangles.add(new ColliderRect (32*28, 32*16-8, 32*1, 32*1+8)); //house - bathroom right
/*21*/	rectangles.add(new ColliderRect (32*25, 32*21+6, 32*3, 32*2-12));//house - sofa
/*22*/	rectangles.add(new ColliderRect (32*31, 32*17, 32*5, 32*2)); 	//house - table
/*23*/	rectangles.add(new ColliderRect (32*31, 32*19, 32*11, 32*3)); 	//house - bedroom top & frontdoor
/*24*/	rectangles.add(new ColliderRect (32*31, 32*22, 32*1, 32*2)); 	//house - bedroom left
/*25*/	rectangles.add(new ColliderRect (32*34, 32*22, 32*1, 32*1)); 	//house - nightstand
/*26*/	rectangles.add(new ColliderRect (32*35, 32*22, 32*2, 32*2)); 	//house - bed
/*27*/	rectangles.add(new ColliderRect (32*37, 32*22, 32*2, 32*4)); 	//house - frontdoor left
/*28*/	rectangles.add(new ColliderRect (32*17, 32*26, 32*22, 32*4)); 	//house - bottom
	
/*29*/	rectangles.add(new ColliderRect (32*16, 32*31, 32*9, 32*4)); 	//garden - left 
/*30*/	rectangles.add(new ColliderRect (32*28, 32*32, 32*1, 32*3)); 	//garden - middle fence left 
/*31*/	rectangles.add(new ColliderRect (32*32, 32*32, 32*1, 32*3)); 	//garden - middle fence right 
/*32*/	rectangles.add(new ColliderRect (32*36, 32*31, 32*6, 32*4)); 	//garden - right
	}
	
	public static boolean checkCollision(ColliderRect playerRect, float xOffset, float yOffset) {
		boolean collision = false;
		float rectX1, rectX2, rectY2, rectY1;
		
		for (ColliderRect rect : rectangles) {
			rectX1 = rect.getX() + xOffset;
			rectX2 = rectX1 + rect.getW();
			rectY1 = rect.getY() + yOffset;
			rectY2 = rectY1 + rect.getH();
			
			collision = playerRect.checkCollision(rectX1, rectX2, rectY1, rectY2);
			if (collision)
					break;
		}
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
