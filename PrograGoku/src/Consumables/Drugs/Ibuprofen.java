package Consumables.Drugs;

import Sickness.Arthritis;
import Sickness.Headache;
import abstraction.ADrug;

public class Ibuprofen extends ADrug{

	public Ibuprofen(String name) {
		super(name);
		sickness.add(new Headache());
		sickness.add(new Arthritis());
	}
	
}
