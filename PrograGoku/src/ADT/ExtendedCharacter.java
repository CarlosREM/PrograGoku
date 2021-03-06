package ADT;

import java.util.ArrayList;
import java.util.TreeMap;

import abstraction.ACharacter;
import abstraction.ASickness;
import abstraction.AWeapon;
import abstraction.IPrototype;
import abstraction.IVisitor;

public class ExtendedCharacter extends DefaultCharacter {
	private Mood mood;
	private int pee;
	private int poop;
	private boolean isHurt;
	private int mentalHealth;
	private int musculature;
	private int fatigue;
	private ArrayList<ASickness> sickness;	
	private boolean immortal;
	private int hunger;
	
	public ExtendedCharacter(String name) {		
		super(name, new TreeMap<>(), 100, 100, 0, 1, 0, 0, 0, new ArrayList<>() , 0, 0);
		this.mood = Mood.Happy;
		this.pee = 0;
		this.poop = 0;
		this.isHurt = false;
		this.mentalHealth = 100;
		this.musculature = 50;
		this.fatigue = 0;
		this.sickness = new ArrayList<>();
		this.immortal = false;
		this.hunger = 0;
	}
	
	public ExtendedCharacter(ExtendedCharacter oldChar) {		
		super(oldChar.getName(), new TreeMap<>(),  oldChar.getCurrentHealthPoints(), oldChar.getMaxHealthPoints(), 0, 1, 0, 0, 0, new ArrayList<>() , 0, 0);
		this.mood = oldChar.getMood();
		this.pee = oldChar.getPee();
		this.poop = oldChar.getPoop();
		this.isHurt = oldChar.isHurt();
		this.mentalHealth = oldChar.getMentalHealth();
		this.musculature = oldChar.getMusculature();
		this.fatigue = oldChar.getFatigue();
		this.sickness = new ArrayList<>();
		sickness.addAll(oldChar.getSickness());
		this.immortal = oldChar.isImmortal();
		this.hunger = oldChar.getHunger();
	}
	
	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	@Override
	public IPrototype deepClone() {
		return new ExtendedCharacter(this);
	}

	public Mood getMood() {
		return mood;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public int getPee() {
		return pee;
	}

	public void setPee(int pee) {
		this.pee = pee;
	}

	public int getPoop() {
		return poop;
	}

	public void setPoop(int poop) {
		this.poop = poop;
	}

	public boolean isHurt() {
		return isHurt;
	}

	public void setHurt(boolean isHurt) {
		this.isHurt = isHurt;
	}

	public int getMentalHealth() {
		return mentalHealth;
	}

	public void setMentalHealth(int mentalHealth) {
		this.mentalHealth = mentalHealth;
	}

	public int getMusculature() {
		return musculature;
	}

	public void setMusculature(int musculature) {
		this.musculature = musculature;
	}

	public int getFatigue() {
		return fatigue;
	}

	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}

	public ArrayList<ASickness> getSickness() {
		return sickness;
	}

	public void setSickness(ArrayList<ASickness> sickness) {
		this.sickness = sickness;
	}
	
	public boolean isImmortal() {
		return immortal;
	}

	public void setImmortal(boolean immortal) {
		this.immortal = immortal;
	}

	public void visit(IVisitor visitor) {
		String visitorName = visitor.getClass().getName();
		Proxy.log.append("Visited by: " + visitorName + "\n");
		Proxy.log.append("Previous State: " + this.getFullState() + "\n");
		visitor.visit(this);
		Proxy.log.append("Result State: " + this.getFullState() + "\n");
	}
	
	public void increasePee(int amount) {
		this.pee += amount;
		if(pee>100) {
			this.pee = 100;
		}
	}
	
	public void decreasePee(int amount) {
		this.pee -= amount;
		if(pee<0) {
			this.pee = 0;
		}
	}
	
	public void increasePoop(int amount) {
		this.poop += amount;
		if(poop>100) {
			this.poop = 100;
		}		
	}
	
	public void decreasePoop(int amount) {
		this.poop -= amount;
		if(poop<0) {
			this.poop = 0;
		}				
	}

	public void increaseMentalHealth(int amount) {
		this.mentalHealth += amount;
		if(mentalHealth>100) {
			this.mentalHealth = 100;
		}			
	}
	
	public void decreaseMentalHealth(int amount) {
		this.mentalHealth -= amount;
		if(mentalHealth<0) {
			this.mentalHealth = 0;
		}		
	}
	
	public void increaseMusculature(int amount) {
		this.musculature += amount;
		if(musculature>100) {
			this.musculature = 100;
		}			
	}
	
	public void decreaseMusculature(int amount) {
		this.musculature -= amount;
		if(musculature<0) {
			this.musculature = 0;
		}				
	}
	
	public void increaseFatigue(int amount) {
		this.fatigue += amount;
		if(fatigue>100) {
			this.fatigue = 100;
		}		
	}
	
	public void decreaseFatigue(int amount) {
		this.fatigue -= amount;
		if(fatigue<0) {
			this.fatigue = 0;
		}		
	}
	
	public void increaseHunger(int amount) {
		this.hunger += amount;
		if(hunger>100) {
			this.hunger = 100;
		}
	}
	
	public void decreaseHunger(int amount) {
		this.hunger -= amount;
		if(hunger<0) {
				increaseMusculature(hunger);
				this.hunger = 0;
			}
		}			
	
	public void increaseHealthPoints(int amount) {
		super.setCurrentHealthPoints(super.getCurrentHealthPoints() + amount);
		if(super.getCurrentHealthPoints() > 100)
			super.setCurrentHealthPoints(100);
	}
	
	public void decreaseHealthPoints(int amount) {
		super.setCurrentHealthPoints(super.getCurrentHealthPoints() - amount);
		if(super.getCurrentHealthPoints() < 0)
			super.setCurrentHealthPoints(0);
	}
	
	private String getFullState() {
		StringBuilder state = new StringBuilder();
		state.append("Health: " + this.getCurrentHealthPoints() + "\n");
		state.append("Mental Health: " + this.getMentalHealth() + "\n"); 
		state.append("Mood: " + this.getMood().name() + "\n"); 
		state.append("Hunger: " + this.hunger + "\n"); 
		state.append("Fatigue: " + this.fatigue + "\n"); 
		state.append("Musculature: " + this.getMusculature() + "\n"); 
		state.append("Immortal: " + this.immortal + "\n"); 
		state.append("Hurt: " + this.isHurt + "\n"); 
		state.append("Poop: " + this.poop + "\n"); 
		state.append("Pee: " + this.pee + "\n"); 
		state.append("Sicknesses:\n");
		for(ASickness s : this.getSickness()) {
			state.append(s.getName() + "\n"); 
		}
		state.append("Abilities:\n");
		for(AWeapon s : this.getWeapons()) {
			state.append(s.getName() + "\n"); 
		}
		state.append("-------------------------------------------------\n");
		return state.toString();
	}
	
}
