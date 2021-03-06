package ADT;

import abstraction.IVisitor;

public class MoodVisitor implements IVisitor{
	private static MoodVisitor instance;
	private MoodVisitor (){
		
	}
	public static MoodVisitor getInstance() {
		if(instance == null)
			instance = new MoodVisitor();
		return instance;
	}

	@Override
	public void visit(ExtendedCharacter character) {
		if(!character.getSickness().isEmpty()) {
			character.setMood(Mood.Sick);
			return;
		}
		if(character.getMentalHealth() <= 40 || character.getHunger() >= 60) {
			character.setMood(Mood.Sad);
			return;
		}
		if(character.getMentalHealth() == 69 || character.getCurrentHealthPoints() == 69) {
			character.setMood(Mood.Delicioso);
			return;
		}
		character.setMood(Mood.Happy);
	}

}
