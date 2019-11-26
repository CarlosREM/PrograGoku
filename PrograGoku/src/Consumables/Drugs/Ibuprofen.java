package Consumables.Drugs;

import ADT.SicknessPool;
import Sickness.Arthritis;
import Sickness.Headache;
import abstraction.ADrug;

public class Ibuprofen extends ADrug{

	public Ibuprofen() {
		super("Ibuprofen");
		sickness.add(SicknessPool.getSickness("Headache"));
		sickness.add(SicknessPool.getSickness("Arthritis"));
	}
	
}
