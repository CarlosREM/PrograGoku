package abstraction;

import ADT.ExtendedCharacter;

public abstract class AMeal extends AConsumable {
	protected int nutriment;
	
	public AMeal(String name, int nutriment) {
			super(name);
			this.nutriment=nutriment;		
	}
	
	public void raiseHealth(ExtendedCharacter character) {
		int cHP = character.getCurrentHealthPoints();
		if (cHP+this.nutriment<100) {
			character.setCurrentHealthPoints(cHP+this.nutriment);
		}else {
			character.setCurrentHealthPoints(cHP+this.nutriment);	
		}
	}
	
	public void raisePee(ExtendedCharacter character) {
		character.increasePee(nutriment);
	}
	
	public void raisePoop(ExtendedCharacter character) {
		character.increasePoop(nutriment);
	}

	public int getNutriment() {
		return nutriment;
	}

	public void setNutriment(int nutriment) {
		this.nutriment = nutriment;
	}	
	
}
