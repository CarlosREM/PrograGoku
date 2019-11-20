package abstraction;
import java.util.ArrayList;

import ADT.ExtendedCharacter;
import abstraction.*;

public abstract class ADrug implements IConsumable{
	
	protected String name;
	protected ArrayList<ASickness> sickness;	
	
	public ADrug(String name) {
		this.name = name;
		this.sickness = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ASickness> getSickness() {
		return sickness;
	}

	public void setSickness(ArrayList<ASickness> sickness) {
		this.sickness = sickness;
	}

	@Override
	public void visit(ExtendedCharacter character) {
		for(ASickness s : character.getSickness()) {
			if(this.sickness.contains(s)) {
				character.getSickness().remove(s);
			}
		}
	}
}
