package ADT;

public class GenerateSicknessChance {
	public static boolean applySicknes(double probability){
	    double x = (Math.random()*((100-0)+1));
	    if(x<=probability)
	    	return true;
		return false;
	}
}
