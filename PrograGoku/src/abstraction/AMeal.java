package abstraction;

import ADT.ExtendedCharacter;

public abstract class AMeal implements IConsumable {
	protected String name;
	protected int nutriment;
	
	public AMeal(String name,int nutriment) {
			this.name=name;
			this.nutriment=nutriment;		
	}
	
	public void raiseHealth(ExtendedCharacter character) {
		int cHP = character.getCurrentHealthPoints();
		character.setCurrentHealthPoints(cHP+this.nutriment);
	}
	
	public void raisePee(ExtendedCharacter character) {
		int cPee = character.getPee();
		character.setPee(cPee+this.nutriment);
	}
	
	public void raisePoop(ExtendedCharacter character) {
		int cPoop = character.getPoop();
		character.setPoop(cPoop+this.nutriment);
	}	
}
