package Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Milk extends AMeal{
	
	public Milk() {
		super("Milk",10);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePee(character);
	}

}
