package jar.Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;
import abstraction.AActivity;
import abstraction.ASickness;
import abstraction.ActivityType;

public class Sleep extends AActivity{

	public Sleep() {
		super("Sleep", ActivityType.BED);
		this.sickness.add(SicknessPool.getSickness("Headache"));
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.setFatigue(0);
		character.setMood(Mood.Sleeping);
		removeSicknesses(character);
	}
}