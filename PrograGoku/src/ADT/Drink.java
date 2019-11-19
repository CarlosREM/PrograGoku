package ADT;

import abstraction.AMeal;

public class Drink extends AMeal{

	@Override
	public void use(ExtendedCharacter character) {
		int cHP = character.getCurrentHealthPoints();
		int cPee = character.getPee();
		character.setCurrentHealthPoints(cHP+this.nutriment);
		character.setPee(cPee+this.nutriment);
		
		character.setMood(Mood.HAPPY);		
	}

}
