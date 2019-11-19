package ADT;

import abstraction.AMeal;

public class Food extends AMeal{

	@Override
	public void use(ExtendedCharacter character) {
		int cHP = character.getCurrentHealthPoints();
		int cPoop = character.getPoop();
		character.setCurrentHealthPoints(cHP+this.nutriment);
		character.setPoop(cPoop+this.nutriment);
		
		character.setMood(Mood.HAPPY);		
	}

}
