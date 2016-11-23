package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
public class FoodTruckManagementSystemController {

	public FoodTruckManagementSystemController() {
	}
	
	public void createEmployee (String name) throws InvalidInputException {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Employee name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Employee name is not valid! ";
					break;
				}
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Employee aEmployee = new Employee(name);
		ftm.addEmployee(aEmployee);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void removeEmployee (String name) throws InvalidInputException {
		String error = "";
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Employee e;
		for (int i = 0; i < ftm.getEmployees().size(); i++) {
			e = ftm.getEmployee(i);
			if (e.getName() == name) {
				ftm.removeEmployee(e);
			}
		}
	}
	
	public void createIngredient(String name, int quantity) throws InvalidInputException {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Ingredient name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Ingredient name is not valid! ";
					break;
				}
			}
		}
		if (quantity < 1) {
			error = error + "Ingredient quantity is not valid! ";
		}		
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Ingredient i = new Ingredient(name, quantity);
		ftm.addIngredient(i);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void createEquipment(String name, int quantity) throws InvalidInputException {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Equipment name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Equipment name is not valid! ";
					break;
				}
			}
		}
		if (quantity < 1) {
			error = error + "Equipment quantity is not valid! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Equipment e = new Equipment(name, quantity);
		ftm.addEquipment(e);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void createFood(String name, double price, int popularity) throws InvalidInputException {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Food name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Food name is not valid! ";
					break;
				}
			}
		}
		if (popularity < 0) {
			error = error + "Food popularity is not valid! ";
		}		
		if (Double.compare(price, (double)0.0) <= 0) {
			error = error + "Food price is not valid! ";
		}		
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Food aFood = new Food(name, price, popularity);
		ftm.addFood(aFood);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
}
