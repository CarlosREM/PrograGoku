package ADT;

import java.util.HashMap;
import java.util.Stack;

import abstraction.IConsumable;

public class Fridge {
	private HashMap<String,Stack<IConsumable>> consumables;

	public Fridge() {
		this.consumables = new HashMap<>();
	}
	
	public void addConsumable(String key, IConsumable item, int quantity) {
		if(!this.consumables.containsKey(key)) {
			this.consumables.put(key,new Stack<IConsumable>());
			}
		while (quantity>0){
			this.consumables.get(key).push(item);
			quantity--;
		}
	}
	
	public IConsumable getConsumable(String key) {
		return this.consumables.get(key).pop();
	}
	
	public HashMap<String,Stack<IConsumable>> getConsumables() {
		return this.consumables;
	}
}
