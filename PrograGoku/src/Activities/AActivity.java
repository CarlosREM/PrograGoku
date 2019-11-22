package Activities;

import java.util.ArrayList;

import ADT.FixedActivityCoord;
import abstraction.ASickness;
import abstraction.IVisitor;

public abstract class AActivity implements IVisitor {
	
	public FixedActivityCoord location;
	public String name;
	protected ArrayList<ASickness> sickness;
	
	public AActivity (String name, FixedActivityCoord location) {
		this.location = location;
		this.name = name;
		sickness = new ArrayList<>();
	}
	
}
