package ca.mcgill.ecse321.foodtruckmanagementsystem.application;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManagementSystem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.foodtruckmanagementsystem.view.FoodTruckManagerPage;

public class FoodTruckManager {
	
	public static void main (String args[]){
		
		PersistenceFoodTruckManagementSystem.loadFoodTruckManagerModel();
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run(){
				new FoodTruckManagerPage();
			}
		});
	}

}
