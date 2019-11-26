package ADT;

import java.util.HashMap;
import java.util.Random;

import abstraction.ADrug;
import abstraction.AMeal;
import abstraction.AConsumable;

public  class ConsumableFactory {
	private  HashMap<String, AConsumable> consumables = new HashMap<>();
	
	public void addConsumable(String key,AConsumable value){
	    consumables.put(key, value);
	}
	
	public AConsumable getConsumable(String key) {
		return consumables.get(key);
	}
	
	public AConsumable getRandomConsumable(){
		int qKeys = consumables.size();
		int num = new Random().nextInt(qKeys);
		return consumables.get(consumables.keySet().toArray()[num]);
	}
	
}
