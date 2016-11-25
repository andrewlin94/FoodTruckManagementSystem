package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
public class FoodTruckManagementSystemController {

	public FoodTruckManagementSystemController() {}
	
	/**
	 * Method takes a string as input that only contains alphabetical and
	 * numerical ascii characters (otherwise throws an error).
	 * Thus, special characters with accents cannot be input.
	 * 
	 * Creates a new Employee with (name) and saves their name into the xml file.
	 * 
	 * @param name
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * Method used to change an existing employee's name into a different name.
	 * Takes two String inputs (old name, new name).
	 * Names can only contain ascii alphabetical and numerical values and spaces,
	 * any other inputs will throw errors.
	 * 
	 * @param oldName
	 * @param newName
	 * @throws InvalidInputException
	 */
	public void editEmployee(Employee employee, String newName) throws InvalidInputException{
		
	}
	
	/**
	 * Method takes one string and one integer as input. String must contain only alphabetical
	 * and numerical ascii values (throws an error otherwise). Thus, special characters and 
	 * accents cannot be given as valid input.
	 * Integer must be >=1 to be valid.
	 * 
	 * Creates a new Ingredient with (name, quantity) and stores it into the xml file.
	 * 
	 * @param name
	 * @param quantity
	 * @throws InvalidInputException
	 */
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
	
	public void editIngredient(Ingredient ingredient, String newName, int newQuant){
		
	}
	
	/**
	 * Method takes one string and one integer as input. String must contain only alphabetical
	 * and numerical ascii values (throws an error otherwise). Thus, special characters and 
	 * accents cannot be given as valid input.
	 * Integer must be >=1 to be valid.
	 * 
	 * Creates a new Equipment with (name, quantity) and stores it into the xml file.
	 * 
	 * @param name
	 * @param quantity
	 * @throws InvalidInputException
	 */
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
	
	public void editEquipment(Equipment equipment, String newName, int newQuant){
		
	}
	
	/**
	 * Method takes one string, one double and one integer as input. String must contain only alphabetical
	 * and numerical ascii values (throws an error otherwise). Thus, special characters and 
	 * accents cannot be given as valid input.
	 * Double must be > 0.0 to be valid.
	 * Integer must be >=1 to be valid.
	 * 
	 * Creates a new Food with (name, price, popularity rating) and stores it into the xml file.
	 * 
	 * @param name
	 * @param price
	 * @param popularity
	 * @throws InvalidInputException
	 */
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
			
		if (Double.compare(price, (double)0.0) <= 0) {
			error = error + "Food price is not valid! ";
		}	
		if (popularity < 0) {
			error = error + "Food popularity is not valid! ";
		}		
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		Food aFood = new Food(name, price, popularity);
		ftm.addFood(aFood);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void editFood(Food food, String newName, double newPrice) throws InvalidInputException{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		String error = "";
		if (newName == null || newName.trim().length() == 0) {
			error = error + "Food name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Food name is not valid! ";
					break;
				}
			}
		}
			
		if (Double.compare(newPrice, (double)0.0) <= 0) {
			error = error + "Food price is not valid! ";
		}			
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		int index = ftm.indexOfFood(food);
		ftm.getFood(index).setName(newName);
		ftm.getFood(index).setPrice(newPrice);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void editOrder(Food food, int order) throws InvalidInputException{
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		String error = "";
		int index = 0;
		for (int i = 0; i < ftm.getFoods().size(); i++) {
			if (ftm.getFood(i).getName().equals(food.getName()) && Double.compare(ftm.getFood(i).getPrice(), food.getPrice()) == 0 && ftm.getFood(i).getPopularity() == food.getPopularity()) {
				index = i;
				break;
			}
			if (i == ftm.getFoods().size()-1) {
				error = error + "Food item is not on the list! ";
			}
		}
		if (order <= 0){
			error = error + "Order must be greater than 0! ";
		}
		if (error.length() > 0){
			throw new InvalidInputException(error);
		}
		int newPop = ftm.getFood(index).getPopularity() + order;
		ftm.getFood(index).setPopularity(newPop);
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
}
