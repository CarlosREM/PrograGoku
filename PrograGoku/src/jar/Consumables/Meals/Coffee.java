package jar.Consumables.Meals;

import ADT.ExtendedCharacter;
import abstraction.AMeal;

public class Coffee extends AMeal{
	
	public Coffee() {
		super("Coffee",10);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		this.raiseHealth(character);
		this.raisePee(character);
	}

}
