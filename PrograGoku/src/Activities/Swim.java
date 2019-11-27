package Activities;

import ADT.ExtendedCharacter;
import ADT.SicknessPool;
import abstraction.AActivity;
import abstraction.ASickness;
import abstraction.ActivityType;

public class Swim extends AActivity{

	public Swim() {
		super("Swim", ActivityType.POOL);
		this.sickness.add(SicknessPool.getSickness("Arthritis"));
		this.sickness.add(SicknessPool.getSickness("Obesity"));
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMusculature(15);
		removeSicknesses(character);
	}
}
