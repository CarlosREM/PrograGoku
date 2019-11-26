package ADT;

public class GardenFactory {
   private ConsumableFactory drugsFactory;
   private ConsumableFactory mealsFactory;
   
   public GardenFactory(ConsumableFactory drugsFactory, ConsumableFactory mealsFactory) {
	   this.drugsFactory = drugsFactory;
	   this.mealsFactory = mealsFactory;
   }

	public ConsumableFactory getDrugsFactory() {
		return drugsFactory;
	}
	
	public void setDrugsFactory(ConsumableFactory drugsFactory) {
		this.drugsFactory = drugsFactory;
	}
	
	public ConsumableFactory getMealsFactory() {
		return mealsFactory;
	}
	
	public void setMealsFactory(ConsumableFactory mealsFactory) {
		this.mealsFactory = mealsFactory;
	}
}
