package jar.Sickness;

import ADT.ExtendedCharacter;
import ADT.GenerateSicknessChance;
import abstraction.ASickness;

public class Cancer extends ASickness{

	public Cancer() {
		super("Cancer", 30);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().contains(this)) {
			if(character.getLevel() >= 15) {
				if(GenerateSicknessChance.applySickness(character.getLevel())) {
					character.getSickness().add(this);
				}else {
					return;
				}
			}
			else {
				return;
			}
		}
		else
			character.setCurrentHealthPoints(character.getCurrentHealthPoints() - this.getDamage());
	}
}