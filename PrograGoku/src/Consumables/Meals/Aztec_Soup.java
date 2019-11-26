package Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Aztec_Soup extends AMeal{
	
	public Aztec_Soup() {
		super("Aztec Soup",10);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePoop(character);
		this.raisePee(character);
	}

}