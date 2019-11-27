package jar.Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import abstraction.AActivity;
import abstraction.ActivityType;

public class DoPee extends AActivity{

	public DoPee() {
		super("DoPee", ActivityType.TOILET);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.setPee(0);
		character.setMood(Mood.Happy);
	}

}
