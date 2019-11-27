package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import abstraction.AActivity;
import abstraction.ActivityType;

public class Sleep extends AActivity{

	public Sleep() {
		super("Sleep", ActivityType.BED);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.setFatigue(0);
		character.setMood(Mood.Sleeping);
	}

}
