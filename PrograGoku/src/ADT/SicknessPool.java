package ADT;
import java.util.HashMap;

import abstraction.*;
public class SicknessPool {
   public static HashMap<String, ASickness> sickness = new HashMap<>();

   public static void addSickness(String key,ASickness  value) {
	   sickness.put(key, value);   
   }
   public static ASickness getSickness(String key) {
	   return sickness.get(key);
   }
}
