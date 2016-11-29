/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.application;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceFoodTruckManagementSystem;
import ca.mcgill.ecse321.foodtruckmanagementsystem.view.FoodTruckManagerPage;

public class FoodTruckManagementSystem {
	
	public static void main (String args[]){
		
		PersistenceFoodTruckManagementSystem.loadFoodTruckManagerModel();
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run(){
				new FoodTruckManagerPage();
			}
		});
	}

}
