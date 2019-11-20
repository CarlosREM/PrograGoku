package Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Noodles extends AMeal{
	
	public Noodles() {
		super("Noodles",10);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePoop(character);
	}

}
