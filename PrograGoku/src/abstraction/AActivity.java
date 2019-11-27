package abstraction;

import java.util.ArrayList;

import management.FixedActivityCoord;

public abstract class AActivity implements IVisitor {
	
	public ActivityType location;
	private String name;
	protected ArrayList<ASickness> sickness;
	
	
	public ActivityType getLocation() {
		return location;
	}
	public void setLocation(ActivityType location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public AActivity (String name, ActivityType location) {
		this.location = location;
		this.name = name;
		sickness = new ArrayList<>();
	}
	
}
