package Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Cold extends ASickness{

	public Cold() {
		super("Cold", 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(GenerateSicknessChance.applySicknes(20)) {
				character.getSickness().add(this);
			}
			else {
				return;
			}
		}
		character.setCurrentHealthPoints(character.getCurrentHealthPoints() - this.getDamage());
	}

}
