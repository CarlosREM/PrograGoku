package ADT;

import java.util.ArrayList;

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
	
	@Override
	public IPrototype deepClone() {
		// TODO Auto-generated method stub
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
