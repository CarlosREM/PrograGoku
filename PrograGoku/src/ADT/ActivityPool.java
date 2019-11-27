package ADT;

import java.util.HashMap;
import java.util.Set;

import abstraction.AActivity;

public class ActivityPool {
	
	   public static HashMap<String, AActivity> activities = new HashMap<>();

	   public static void addActivity(String key, AActivity  value) {
		   activities.put(key, value);   
	   }
	   
	   public static AActivity getActivity(String key) {
		   return activities.get(key);
	   }
	   
	   public static Set<String> getKeys() {
		   return activities.keySet();
	   }
}
