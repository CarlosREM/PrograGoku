package Consumables.Drugs;

import ADT.ExtendedCharacter;
import Sickness.Arthritis;
import Sickness.Cancer;
import Sickness.Cold;
import Sickness.Depression;
import Sickness.Headache;
import Sickness.Obesity;
import abstraction.ADrug;
import abstraction.ASickness;

public class Senzu_Bean extends ADrug{

	public Senzu_Bean() {
		super("Senzu Bean");
	}
	
	@Override
    public void visit(ExtendedCharacter character) {
			character.getSickness().clear();
			character.setCurrentHealthPoints(100);
			character.setMentalHealth(100);	
			character.setMusculature(50);
	}
}
