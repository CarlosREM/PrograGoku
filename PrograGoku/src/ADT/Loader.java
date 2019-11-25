package ADT;

public class Loader {
	  private jarLoader.Loader jloader;
      public Loader () {
    	   jloader = new jarLoader.Loader();
      }
      public Garden getGarden() {
    	  return jloader.garden;
      }
  
}
