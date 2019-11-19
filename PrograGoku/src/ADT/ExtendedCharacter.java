package ADT;

import java.util.ArrayList;

import abstraction.ACharacter;
import abstraction.ASickness;
import abstraction.AWeapon;
import abstraction.IPrototype;

public class ExtendedCharacter extends DefaultCharacter {
	private Mood mood;
	private int pee;
	private int poop;
	private boolean isHurt;
	private int mentalHealth;
	private int musculature;
	private int fatigue;
	private ArrayList<ASickness> sickness;	
	
	@Override
	public IPrototype deepClone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWeapon(AWeapon weapon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWeapon(AWeapon weapon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWeapon(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelDownWeapons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelUpWeapons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeHealth(int healthPoints) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useWeapon(AWeapon weapon, ACharacter character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void useWeapon(int index, ACharacter character) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
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

}
