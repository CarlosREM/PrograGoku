package Consumables.Drugs;

import Sickness.Obesity;
import abstraction.ADrug;

public class Ativian extends ADrug{

	public Ativian() {
		super("Ativian");
		sickness.add(new Obesity());
	}
	
}
