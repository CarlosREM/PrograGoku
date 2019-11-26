package Consumables.Drugs;

import Sickness.Arthritis;
import Sickness.Cancer;
import Sickness.Cold;
import Sickness.Depression;
import Sickness.Headache;
import Sickness.Obesity;
import abstraction.ADrug;

public class Senzu_Bean extends ADrug{

	public Senzu_Bean() {
		super("Senzu Bean");
		sickness.add(new Cancer());
		sickness.add(new Cold());
		sickness.add(new Obesity());
		sickness.add(new Depression());
		sickness.add(new Arthritis());
		sickness.add(new Headache());
	}

}
