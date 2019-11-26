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
			character.setCurrentHealthPoints(100);	
		}
		decreaseHunger(character);
	}
	public void decreaseHunger(ExtendedCharacter character) {
		character.decreaseHunger(this.nutriment);
		int fat = character.getHunger();
		if(fat<0) {
			character.decreaseMusculature(-fat);
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
