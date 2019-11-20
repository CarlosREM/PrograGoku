package Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Tea extends AMeal{
	
	public Tea() {
		super("Tea",10);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePee(character);
	}

}