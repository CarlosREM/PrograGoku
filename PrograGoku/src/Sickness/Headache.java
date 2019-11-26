package Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Headache extends ASickness{

	public Headache() {
		super("Headache", 5);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(character.getFatigue() >= 50) {
				if(GenerateSicknessChance.applySicknes(character.getFatigue())) {
					character.getSickness().add(this);
				}
				else {
					return;
				}
			}else {
				return;
			}
		}
		if(character.getFatigue() < 95)
			character.setFatigue(character.getFatigue() + this.getDamage());
		character.setCurrentHealthPoints(character.getCurrentHealthPoints() - this.getDamage());
		
	}

}