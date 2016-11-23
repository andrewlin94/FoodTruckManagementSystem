package ca.mcgill.ecse321.foodtruckmanagementsystem.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;

public class PersistenceFoodTruckManagementSystem{
	
	private static void initializeXStream()
	{
		PersistenceXStream.setFilename("eventregistration.xml");
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("food", Food.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
	}

	public static void loadEventRegistrationModel()
	{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		PersistenceFoodTruckManagementSystem.initializeXStream();
		FoodTruckManager ftm2 = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		if(ftm2 != null) {
			
			Iterator<Food> fIt = ftm2.getFoods().iterator();
			while (fIt.hasNext()) {
				ftm.addFood(fIt.next());
			}
			
			Iterator<Equipment> eIt = ftm2.getEquipment().iterator();
			while (eIt.hasNext()) {
				ftm.addEquipment(eIt.next());
			}
			
			Iterator<Employee> emIt = ftm2.getEmployees().iterator();
			while(emIt.hasNext()) {
				ftm.addEmployee(emIt.next());
			}
			
			Iterator<Ingredient> iIt = ftm2.getIngredients().iterator();
			while(iIt.hasNext()) {
				ftm.addIngredient(iIt.next());
			}
		}
	}
}
