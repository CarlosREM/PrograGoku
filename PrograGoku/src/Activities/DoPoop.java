package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;

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
