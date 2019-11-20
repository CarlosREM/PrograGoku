package ADT;

import java.util.HashMap;

public class AbilitiesPool {
	   public static HashMap<String, Ability> abilities = new HashMap<>();

	   public static void addSickness(String key,Ability  value) {
		   abilities.put(key, value);   
	   }
	   public static Ability getSickness(String key) {
		   return abilities.get(key);
	   }
}
