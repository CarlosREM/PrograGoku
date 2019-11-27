package jar.Consumables.Drugs;

import ADT.SicknessPool;
import abstraction.ADrug;

public class Ibuprofen extends ADrug{

	public Ibuprofen() {
		super("Ibuprofen");
		sickness.add(SicknessPool.getSickness("Headache"));
		sickness.add(SicknessPool.getSickness("Arthritis"));
	}
	
}
