package Activities;

import java.util.ArrayList;

import abstraction.ASickness;
import abstraction.IVisitor;

public abstract class AActivity implements IVisitor {
	
	public Types type;
	public String name;
	protected ArrayList<ASickness> sickness;
	
	public AActivity (String name, Types type) {
		this.type = type;
		this.name = name;
		sickness = new ArrayList<>();
	}

	public static enum Types {
		POOL (0, 0), RING(0, 0), FIELD(0, 0), BED(0, 0), BATHROOM(0, 0), LIVINGROOM(0, 0), FRIDGE(0,0), TABLE(0,0); 
		
		private int x;
		private int y;
		
		public int getX() 
	    { 
	        return x; 
	    } 
		public int getY() 
	    { 
	        return y; 
	    } 
	  
	    
	    private Types(int x, int y) 
	    { 
	        this.x = x;
	        this.y = y;
	    } 
	}
	
	
	
}
