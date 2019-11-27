package jar;

import ADT.ActivityPool;
import ADT.ConsumableFactory;
import ADT.GardenFactory;
import ADT.SicknessPool;

import jar.Sickness.*;
import jar.Consumables.Drugs.*;
import jar.Consumables.Meals.*;
import jar.Activities.*;

public class Loader {
	
	public Loader() {
		load();
	}
	
	public void load() {
		loadSicknesses();
		loadConsummables();
		loadActivities();
		// -- NO IMPLEMENTADO
		loadAbilities();
	}
	
	private void loadSicknesses() {
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
	}
	
	private void loadConsummables() {
		ConsumableFactory meals = loadMeals(),
						  drugs = loadDrugs();
		
		GardenFactory.init(drugs, meals);
	}
	
	private ConsumableFactory loadMeals() {
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
		
		return meals;
	}
	private ConsumableFactory loadDrugs() {
		Ativian ativian = new Ativian();
		Ibuprofen ibuprofen = new Ibuprofen();
		Senzu_Bean senzu_Bean = new Senzu_Bean();
		
		ConsumableFactory drugs =  new ConsumableFactory(); 
		
		drugs.addConsumable(ativian.getName(), ativian);
		drugs.addConsumable(ibuprofen.getName(), ibuprofen);
		drugs.addConsumable(senzu_Bean.getName(), senzu_Bean);
		
		return drugs;
	}
	
	private void loadActivities() {
		DoPee pee = new DoPee();
		DoPoop poop = new DoPoop();
		Meditate meditate = new Meditate();
		Sleep sleep = new Sleep();
		Swim swim = new Swim();
		Run run = new Run();
		Play play = new Play();
		
		ActivityPool.addActivity(pee.getName(), pee);
		ActivityPool.addActivity(poop.getName(), poop);
		ActivityPool.addActivity(meditate.getName(), meditate);
		ActivityPool.addActivity(sleep.getName(), sleep);
		ActivityPool.addActivity(swim.getName(), swim);
		ActivityPool.addActivity(run.getName(), run);
		ActivityPool.addActivity(play.getName(), play);
	}
	
	private void loadAbilities() {
		
	}
}
