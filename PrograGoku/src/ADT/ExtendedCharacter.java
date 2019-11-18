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

}
