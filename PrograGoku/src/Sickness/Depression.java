package Sickness;

import ADT.ExtendedCharacter;
import abstraction.ASickness;

public class Depression extends ASickness{

	public Depression() {
		super("Depression", 20);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if (character.getMentalHealth()<30) {
			int cHP = character.getCurrentHealthPoints();
			character.setCurrentHealthPoints(cHP-this.damage);
		}
	}

}