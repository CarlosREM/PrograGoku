package ADT;

import java.util.HashMap;

public class RecoveryPool {
	private static HashMap<Integer,GameState> gameStates = new HashMap<>();
	public static GameState getState(Integer key) {
		return gameStates.get(key);
	}
	
	public static GameState addState(Integer key,GameState value) {
		return gameStates.put(key, value);
	}	
}
