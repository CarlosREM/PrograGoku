package ADT;

import java.util.HashMap;

import abstraction.IConsumable;

public class Fridge {
	private HashMap<String,IConsumable> consumables;
	private HashMap<String,Integer> consumablesQuant;

	public Fridge() {
		this.consumables = new HashMap<String, IConsumable>();
		this.consumablesQuant = new HashMap<String, Integer>();
	}
	
	public void addConsumable(String key, IConsumable value, int quantity) {
		if(!this.consumables.containsKey(key)) {
			this.consumables.put(key, value);
			this.consumablesQuant.put(key, quantity);
		}else {
			quantity += this.consumablesQuant.get(key);
			this.consumablesQuant.put(key, quantity);
		}
		
	}
	
	public IConsumable getConsumable(String key) {
		if(this.consumablesQuant.get(key)>0) {
			return this.consumables.get(key);
		}else {
			return null;
		}
	}
	
	public HashMap<String,Integer> getConsumablesQuantity() {
		return this.consumablesQuant;
	}
}
