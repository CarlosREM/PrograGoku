package jar.Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Obesity extends ASickness{

	public Obesity() {
		super("Obesity", 15);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(character.getMusculature() <= 20) {
				if(GenerateSicknessChance.applySickness(100 - character.getMusculature()))
					character.getSickness().add(this);
				else
					return;
			}else
				return;
		}
		else {
			if(character.getFatigue() < 85) {
				character.setFatigue(character.getFatigue() + this.getDamage());
				character.setCurrentHealthPoints(character.getCurrentHealthPoints() - this.getDamage());
			}
		}
	}
}
