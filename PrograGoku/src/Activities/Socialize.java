package Activities;

import ADT.ExtendedCharacter;
import ADT.Mood;
import ADT.SicknessPool;
import abstraction.ASickness;

public class Socialize extends AActivity{

	public Socialize() {
		super("Socialize", ActivityType.TOILET);
		this.sickness.add(SicknessPool.getSickness("Depression"));
	}

	@Override
	public void visit(ExtendedCharacter character) {
		character.increaseMentalHealth(30);
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
