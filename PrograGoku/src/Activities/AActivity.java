package Activities;

import java.util.ArrayList;

import abstraction.ASickness;
import abstraction.IVisitor;
import management.FixedActivityCoord;

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
