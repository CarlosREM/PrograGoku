package ADT;

public class Garden {
   private ConsumableFactory drugsFactory;
   private ConsumableFactory mealsFactory;
   
   public Garden(ConsumableFactory drugs,ConsumableFactory meals) {
	   this.drugsFactory = drugs;
	   this.mealsFactory = meals;
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
