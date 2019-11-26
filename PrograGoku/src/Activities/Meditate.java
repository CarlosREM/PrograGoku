package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;

public class Meditate extends AActivity{

	public Meditate() {
		super("Meditate", ActivityType.TOILET);
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMentalHealth(20);
		character.setMood(Mood.Meditanding);
		if(character.getSickness().contains(SicknessPool.getSickness("Depression"))){
			character.getSickness().remove(SicknessPool.getSickness("Depression"));
		}
		
	}

}
