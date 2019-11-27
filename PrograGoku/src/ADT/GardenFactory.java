package ADT;

public class GardenFactory {
   private static ConsumableFactory drugsFactory;
   private static ConsumableFactory mealsFactory;
   
   public static void init(ConsumableFactory drugsFactory, ConsumableFactory mealsFactory) {
	   GardenFactory.drugsFactory = drugsFactory;
	   GardenFactory.mealsFactory = mealsFactory;
   }

	public static ConsumableFactory getDrugsFactory() {
		return drugsFactory;
	}
	
	public static void setDrugsFactory(ConsumableFactory drugsFactory) {
		GardenFactory.drugsFactory = drugsFactory;
	}
	
	public static ConsumableFactory getMealsFactory() {
		return mealsFactory;
	}
	
	public static void setMealsFactory(ConsumableFactory mealsFactory) {
		GardenFactory.mealsFactory = mealsFactory;
	}
}
