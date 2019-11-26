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
		this.mood = Mood.HAPPY;
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
	
	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	@Override
	public IPrototype deepClone() {
		return null;
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
		visitor.visit(this);
	}

}
