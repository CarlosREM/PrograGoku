package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;
import abstraction.ASickness;

public class Meditate extends AActivity{

	public Meditate() {
		super("Meditate", ActivityType.TOILET);
		this.sickness.add(SicknessPool.getSickness("Depression"));
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMentalHealth(20);
		character.setMood(Mood.Meditanding);
		removeSicknesses(character);
	}
	
	public void removeSicknesses(ExtendedCharacter character)
	{
		for(ASickness s : this.sickness) {
			if(character.getSickness().contains(s)) {
				character.getSickness().remove(s);
			}
		}
	}
}
