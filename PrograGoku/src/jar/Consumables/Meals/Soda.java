package jar.Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Soda extends AMeal{
	
	public Soda() {
		super("Soda",15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePee(character);
	}

}
