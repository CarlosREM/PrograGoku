package ADT;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import abstraction.AConsumable;

public class Fridge {
	private static HashMap<String,Stack<AConsumable>> consumables = new HashMap<>();
	
	public static void addConsumable(String key, AConsumable item) {
		if(!consumables.containsKey(key)) {
			consumables.put(key, new Stack<AConsumable>());
		}
		consumables.get(key).push(item);
	}
	
	public static int getConsumableQuantity(String key) {
		return consumables.get(key).size();
	}
	
	public static AConsumable getConsumable(String key) {
		return consumables.get(key).pop();
	}
	
	public static Set<String> getKeys() {
		return consumables.keySet();
	}
	
	
}
