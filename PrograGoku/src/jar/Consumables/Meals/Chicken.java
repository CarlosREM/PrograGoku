package jar.Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Chicken extends AMeal{
	
	public Chicken() {
		super("Chicken",15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePoop(character);
	}

}
