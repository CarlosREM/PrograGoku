package jar.Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Meat extends AMeal{

	public Meat() {
		super("Meat",15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePoop(character);
	}

}