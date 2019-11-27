package jar.Consumables.Drugs;

import ADT.SicknessPool;
import abstraction.ADrug;

public class Ativian extends ADrug{

	public Ativian() {
		super("Ativian");
		sickness.add(SicknessPool.getSickness("Arthritis"));
	}
	
}
