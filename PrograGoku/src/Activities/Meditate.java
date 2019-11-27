package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;
import abstraction.AActivity;
import abstraction.ActivityType;

public class Meditate extends AActivity{

	public Meditate() {
		super("Meditate", ActivityType.MEDITATE);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMentalHealth(20);
		character.setMood(Mood.Meditating);
		if(character.getSickness().contains(SicknessPool.getSickness("Depression"))){
			character.getSickness().remove(SicknessPool.getSickness("Depression"));
		}
		
	}

}
