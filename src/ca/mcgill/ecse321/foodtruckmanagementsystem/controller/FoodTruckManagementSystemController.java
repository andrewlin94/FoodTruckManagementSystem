package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import java.util.Iterator;

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
	
	public void editIngredient(Ingredient ingredient, String newName, int newQuantity) throws InvalidInputException {
		String error = "";
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New ingredient name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New ingredient name is not valid! ";
					break;
				}
			}
		}
		if (newQuantity <= 0) {
			error = error + "New ingredient quantity must be greater than 0! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm2.getIngredients().size(); i++) {
			if (ftm2.getIngredient(i).getName().equals(ingredient.getName())) {
				if (!(ftm2.getIngredient(i).getName().equals(newName))) {
					ftm2.getIngredient(i).setName(newName);
				}
				if (!(ftm2.getIngredient(i).getQuantity() == newQuantity)) {
					ftm2.getIngredient(i).setQuantity(newQuantity);
				}
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm2);
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
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		String error = "";
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New food name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New food name is not valid! ";
					break;
				}
			}
		}
			
		if (Double.compare(newPrice, (double)0.0) <= 0) {
			error = error + "New food price is not valid! ";
		}			
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		int i = 0;
		for (i = 0; i < ftm.getFoods().size(); i++) {
			if (ftm.getFood(i).getName().equals(food.getName())) {
				if (!(ftm.getFood(i).getName().equals(newName))) {
					ftm.getFood(i).setName(newName);
				}
				if (!(Double.compare(ftm.getFood(i).getPrice(), newPrice) == 0)) {
					ftm.getFood(i).setPrice(newPrice);
				}
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	public void editOrder(Food food, int order) throws InvalidInputException{
		String error = "";
		if (order <= 0) {
			error = error + "Order must be greater than 0! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm.getFoods().size(); i++) {
			if (ftm.getFood(i).getName().equals(food.getName())) {
				break;
			}
		}	
		int p = ftm.getFood(i).getPopularity();
		ftm.getFood(i).setPopularity(p + order);
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
	public void editEmployeeName(Employee employee, String newName) throws InvalidInputException {
		String error = "";
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New employee name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New employee name is not valid! ";
					break;
				}
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			if (ftm.getEmployee(i).getName().equals(employee.getName())) {
				ftm.getEmployee(i).setName(newName);
				PersistenceXStream.saveToXMLwithXStream(ftm);
			}
		}
	}

	public void editEquipment(Equipment e, String newName, int newQuantity) throws InvalidInputException {
		String error = "";
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New equipment name cannot be empty! ";
		}
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);
				if ((ascii < 32) || (ascii > 32 && ascii < 48) || (ascii > 57 && ascii < 65)
						|| (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New equipment name is not valid! ";
					break;
				}
			}
		}
		if (newQuantity < 0) {
			error = error + "New equipment quantity must be greater than 0! ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm.getEquipment().size(); i++) {
			if (ftm.getEquipment(i).getName().equals(e.getName())) {
				if (!(ftm.getEquipment(i).getName().equals(newName))) {
					ftm.getEquipment(i).setName(newName);
				}
				if (!(ftm.getEquipment(i).getQuantity() == newQuantity)) {
					ftm.getEquipment(i).setQuantity(newQuantity);
				}
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
}
