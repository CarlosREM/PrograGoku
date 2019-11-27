package jar.Consumables.Drugs;

import ADT.ExtendedCharacter;
import abstraction.ADrug;

public class Senzu_Bean extends ADrug{

	public Senzu_Bean() {
		super("Senzu Bean");
	}
	
	@Override
    public void visit(ExtendedCharacter character) {
			character.getSickness().clear();
			character.setCurrentHealthPoints(100);
			character.setMentalHealth(100);	
			character.increaseMusculature(50);
	}
}
