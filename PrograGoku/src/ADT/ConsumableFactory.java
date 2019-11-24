package ADT;

import java.util.HashMap;
import java.util.Random;

import abstraction.ADrug;
import abstraction.AMeal;
import abstraction.IConsumable;

public class ConsumableFactory {
	private  HashMap<String, IConsumable> consumables = new HashMap<>();
	
	public void addConsumable(String key,IConsumable value){
	    consumables.put(key, value);
	}
	
	public IConsumable getConsumable(String key) {
		return consumables.get(key);
	}
	
	public IConsumable getRandomConsumable(){
		int qKeys = consumables.size();
		int num = new Random().nextInt(qKeys);
		return consumables.get(consumables.keySet().toArray()[num]);
	}
	
	public AMeal getRandomMeal(){
		int qKeys = consumables.size();
		IConsumable consumable = null;
		int num;
		while(!(consumable instanceof AMeal)) {
			num = new Random().nextInt(qKeys);
			consumable = consumables.get(consumables.keySet().toArray()[num]);
		}
		return (AMeal) consumable;
	}	
	
	public ADrug getRandomDrug(){
		int qKeys = consumables.size();
		IConsumable consumable = null;
		int num;
		while(!(consumable instanceof ADrug)) {
			num = new Random().nextInt(qKeys);
			consumable = consumables.get(consumables.keySet().toArray()[num]);
		}
		return (ADrug) consumable;
	}		
}
