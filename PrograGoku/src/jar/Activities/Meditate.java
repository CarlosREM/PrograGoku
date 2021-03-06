package jar.Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;
import abstraction.ASickness;
import abstraction.AActivity;
import abstraction.ActivityType;

public class Meditate extends AActivity{

	public Meditate() {
		super("Meditate", ActivityType.MEDITATE);
		this.sickness.add(SicknessPool.getSickness("Depression"));
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMentalHealth(20);
		character.setMood(Mood.Meditating);
		removeSicknesses(character);
	}
}
