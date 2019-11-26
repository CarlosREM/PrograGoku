package Consumables.Drugs;

import ADT.SicknessPool;
import Sickness.Obesity;
import abstraction.ADrug;

public class Ativian extends ADrug{

	public Ativian() {
		super("Ativian");
		sickness.add(SicknessPool.getSickness("Arthritis"));
	}
	
}
