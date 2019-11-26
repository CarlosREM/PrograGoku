package jarLoader;

import ADT.ConsumableFactory;
import ADT.GardenFactory;
import ADT.SicknessPool;
import Consumables.Drugs.Ativian;
import Consumables.Drugs.Ibuprofen;
import Consumables.Drugs.Senzu_Bean;
import Consumables.Meals.Aztec_Soup;
import Consumables.Meals.Bread;
import Consumables.Meals.Chicken;
import Consumables.Meals.Coffee;
import Consumables.Meals.Fish;
import Consumables.Meals.Meat;
import Consumables.Meals.Milk;
import Consumables.Meals.Noodles;
import Consumables.Meals.Pizza;
import Consumables.Meals.Soda;
import Consumables.Meals.Tea;
import Consumables.Meals.Water;
import Sickness.Arthritis;
import Sickness.Cancer;
import Sickness.Cold;
import Sickness.Depression;
import Sickness.Headache;
import Sickness.Obesity;

public class Loader {
	public GardenFactory garden;
	
	public Loader() {
		load();
	}
	
	public void load () {
		Arthritis arthritis = new Arthritis();
		Cancer cancer = new Cancer();
		Cold cold = new Cold();
		Depression depression = new Depression();
		Headache headache = new Headache();
		Obesity obesity = new Obesity();
		
		SicknessPool.addSickness(arthritis.getName(), arthritis);
		SicknessPool.addSickness(cancer.getName(), cancer);
		SicknessPool.addSickness(cold.getName(), cold);
		SicknessPool.addSickness(depression.getName(), depression);
		SicknessPool.addSickness(headache.getName(), headache);
		SicknessPool.addSickness(obesity.getName(), obesity);
		
		Aztec_Soup aztec = new Aztec_Soup();
		Bread bread = new Bread();
		Chicken chicken = new Chicken();
		Coffee coffee = new Coffee();
		Fish fish = new Fish();
		Meat meat = new Meat();
		Milk milk = new Milk();
		Noodles  noodles = new Noodles();
		Pizza pizza = new Pizza();
		Soda soda = new Soda();
		Tea tea = new Tea();
		Water water = new Water();
		
		Ativian ativian = new Ativian();
		Ibuprofen ibuprofen = new Ibuprofen();
		Senzu_Bean senzu_Bean = new Senzu_Bean();
		
		ConsumableFactory meals =  new ConsumableFactory();
		
		meals.addConsumable(aztec.getName(), aztec);
		meals.addConsumable(bread.getName(), bread);
		meals.addConsumable(chicken.getName(), chicken);
		meals.addConsumable(coffee.getName(), coffee);
		meals.addConsumable(fish.getName(), fish);
		meals.addConsumable(meat.getName(), meat);
		meals.addConsumable(milk.getName(), milk);
		meals.addConsumable(noodles.getName(), noodles);
		meals.addConsumable(pizza.getName(), pizza);
		meals.addConsumable(soda.getName(), soda);
		meals.addConsumable(tea.getName(), tea);
		meals.addConsumable(water.getName(), water);
		
		ConsumableFactory drugs =  new ConsumableFactory(); 
		
		drugs.addConsumable(ativian.getName(), ativian);
		drugs.addConsumable(ibuprofen.getName(), ibuprofen);
		drugs.addConsumable(senzu_Bean.getName(), senzu_Bean);
		
		this.garden = new GardenFactory(drugs, meals);
	}
}
