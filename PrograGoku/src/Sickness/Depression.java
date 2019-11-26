package Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Depression extends ASickness{

	public Depression() {
		super("Depression", 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(character.getMentalHealth() <= 30) {
				if(GenerateSicknessChance.applySickness(100 - character.getMentalHealth())) {
					character.getSickness().add(this);
				}
				else return;
			}
			else return;
		}
		else {
			if(character.getFatigue() < 95) 
				character.setFatigue(character.getFatigue() + this.getDamage());
			if(character.getMusculature() >= 0)
				character.setMusculature(character.getMusculature() - this.getDamage());
			character.setCurrentHealthPoints(character.getCurrentHealthPoints() - this.getDamage());
		}
	}

}