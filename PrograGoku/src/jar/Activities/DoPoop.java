package jar.Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import abstraction.AActivity;
import abstraction.ActivityType;

public class DoPoop extends AActivity{

	public DoPoop() {
		super("DoPoop", ActivityType.TOILET);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.setPoop(0);
		character.setMood(Mood.Happy);
	}

}
