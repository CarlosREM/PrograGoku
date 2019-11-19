package ADT;
import java.util.ArrayList;

import abstraction.*;

public class Drug implements IConsumable{
	
	public String name;
	private ArrayList<ASickness> sickness;	
	
	public Drug(String name) {
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
	public void use(ExtendedCharacter character) {
		for(ASickness s : character.getSickness()) {
			if(this.sickness.contains(s)) {
				character.getSickness().remove(s);
			}
		}
	}
}
