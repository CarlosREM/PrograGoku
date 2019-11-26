package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;

public class Sleep extends AActivity{

	public Sleep() {
		super("Sleep", ActivityType.TOILET);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.setFatigue(0);
		character.setMood(Mood.Sleeping);
	}

}
