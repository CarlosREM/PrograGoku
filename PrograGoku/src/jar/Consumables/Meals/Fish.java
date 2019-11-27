package jar.Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Fish extends AMeal{

	public Fish() {
		super("Fish",15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePoop(character);
	}

}
