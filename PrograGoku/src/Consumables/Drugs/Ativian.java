package Consumables.Drugs;

import Sickness.Obesity;
import abstraction.ADrug;

public class Ativian extends ADrug{

	public Ativian(String name) {
		super(name);
		sickness.add(new Obesity());
	}
	
}
