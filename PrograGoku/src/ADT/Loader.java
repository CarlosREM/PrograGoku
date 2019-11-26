package ADT;

public class Loader {
	  private static jarLoader.Loader jloader;
      
	  public static void load() {
    	   jloader = new jarLoader.Loader();
      }
	  
      public static GardenFactory getGarden() {
    	  return jloader.garden;
      }
}
