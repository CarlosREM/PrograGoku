package ADT;

import java.util.HashMap;
import java.util.Set;

public class RecoveryPool {
	private static HashMap<String,GameState> gameStates = new HashMap<>();
	public static GameState getState(String key) {
		return gameStates.get(key);
	}
	
	public static GameState addState(String key,GameState value) {
		return gameStates.put(key, value);
	}	
	
	public static Set<String> getKeys() {
		return gameStates.keySet();
	}
}
