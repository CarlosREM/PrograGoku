package abstraction;

import java.util.ArrayList;

import ADT.ExtendedCharacter;

public abstract class AActivity implements IVisitor {
	
	private ActivityType location;
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
	
	public void removeSicknesses(ExtendedCharacter character)
	{
		for(ASickness s : this.sickness) {
			if(character.getSickness().contains(s)) {
				character.getSickness().remove(s);
			}
		}
	}
}
