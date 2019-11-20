package Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Pizza extends AMeal{
	
	public Pizza() {
		super("Pizza",15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePee(character);
	}

}