package abstraction;
import java.util.ArrayList;

import ADT.ExtendedCharacter;
import abstraction.*;

public abstract class ADrug extends AConsumable{
	
	protected ArrayList<ASickness> sickness;	
	
	public ADrug(String name) {
		super(name);
		this.sickness = new ArrayList<>();
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
