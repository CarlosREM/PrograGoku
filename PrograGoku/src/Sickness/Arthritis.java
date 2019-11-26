package Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Arthritis extends ASickness{

	public Arthritis() {
		super("Arthritis", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(character.getMusculature() <= 40) {
				if(GenerateSicknessChance.applySicknes(100 - character.getMusculature())) {
					character.getSickness().add(this);
				}
				else {
					return;
				}
			}else {
				return;
			}
		}
		if(character.getMusculature() > 10)
			character.setMusculature(character.getMusculature() - this.getDamage());
	}
}