/*
 * This controller is solely used for the JUnit tests!
 * Only one line is changed, where instead of loading the FTM from XML,
 * this one gets an instance of it.
 * 
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import java.util.Calendar;
import java.util.Date;

import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
public class Controller {

	public Controller() {}
	
	/**
	 * Create an employee object with the string attribute name,
	 * and store in xml file. name must only contain alphabetical letters
	 * and numerical numbers (0 to 9). Store in memory.
	 *  
	 * @param name
	 * @throws InvalidInputException
	 */
	public void createEmployee(String name) throws InvalidInputException {
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
	 * Creates a new Ingredient with (name, quantity) and stores it into the xml file.
	 * name must only contain alphabetical letters and numerical numbers (0 to 9).
	 * quantity must be greater than 0. Store in memory.
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
	
	/**
	 * Edit an ingredient object specified, edit its name to newName, and its quantity to newQuantity.
	 * newName must only contain alphabetical letters and numerical numbers (0 to 9).
	 * newQuantity must be greater than 0. Store in memory.
	 *  
	 * @param ingredient
	 * @param newName
	 * @param newQuantity
	 * @throws InvalidInputException
	 */
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
	 * Creates a new Equipment with (name, quantity) and stores it into the xml file.
	 * name must only contain alphabetical letters and numerical numbers (0 to 9).
	 * quantity must be greater than 0. Store in memory.
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
	 * Creates a new Food with (name, price, popularity rating) and stores it into the xml file.
	 * name must only contain alphabetical letters and numerical numbers (0 to 9).
	 * price must be greater than 0. popularity must be greater than 0. Store in memory.
	 * 
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
	
	/**
	 * Edit the food object specified, changing its name attribute to newName,
	 * and changing its price attribute to newPrice.
	 * newName must only contain alphabetical letters and numerical numbers (0 to 9).
	 * newPrice must be greater than 0. Store in memory.
	 * 
	 * @param food
	 * @param newName
	 * @param newPrice
	 * @throws InvalidInputException
	 */
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
	/**
	 * Edit the popularity attribute of a specified food food. 
	 * Order must be greater than 0. Store in memory.
	 * 
	 * @param food
	 * @param order
	 * @throws InvalidInputException
	 */
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
	 * Edit the name attribute of an specified employee employee.
	 * newName must only contain alphabetical letters and numerical numbers (0 to 9).
	 * Store in memory.
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

	/**
	 * Edit the quantity, to newQuantity, and name, to newName, attribute of an specified equipment e.
	 * newQuantity must be greater than 0.newName must only contain alphabetical letters and numerical
	 * numbers (0 to 9). Store in memory.
	 * 
	 * @param e
	 * @param newName
	 * @param newQuantity
	 * @throws InvalidInputException
	 */
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
	
	/**
	 * Adding a workStartTime and a workEndTime attribute to an specified employee e.
	 * workStartTime and workEndTime both contain information about the year, month,
	 * date, hours and minutes of the working shift. Store in memory.
	 * workEndTime must be after workStartTime.
	 * 
	 * @param e
	 * @param workStartTime
	 * @param workEndTime
	 * @throws InvalidInputException
	 */
	public void addShift(Employee e, Date workStartTime, Date workEndTime) throws InvalidInputException {
		String error = "";
		Date empty= new Date();
		if (workStartTime == null || (workStartTime.compareTo(empty) == 0)) {
			error = error + "Start time cannot be empty! ";
		}
		if (workEndTime == null || (workEndTime.compareTo(empty) == 0)) {
			error = error + "End time cannot be empty! ";
		}
		if (workStartTime != null && workEndTime != null && (workStartTime.compareTo(workEndTime) > 0)) {
			if (!(workEndTime.compareTo(empty) == 0)) {
				error = error + "End time cannot be before start time! ";
			}			
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			if (ftm.getEmployee(i).getName().equals(e.getName())) {
				ftm.getEmployee(i).addWorkStartTime(workStartTime);
				ftm.getEmployee(i).addWorkEndTime(workEndTime);
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Editing the workStartTime and the workEndTime attribute of a specified employee e.
	 * newStartTime must be before newEndTime. oldStartTime and oldEndTime must be of the same
	 * index in the lists of startTime and lists of endTime in employee e. Store in memory.
	 * 
	 * @param e
	 * @param oldStartTime
	 * @param oldEndTime
	 * @param newStartTime
	 * @param newEndTime
	 * @throws InvalidInputException
	 */
	public void editShift(Employee e, Date oldStartTime, Date oldEndTime, Date newStartTime, Date newEndTime) throws InvalidInputException {
		String error = "";
		Date empty = new Date();
		if (newStartTime == null || newStartTime.compareTo(empty) == 0) {
			error = error + "New start time cannot be empty! ";
		}
		if (newEndTime == null || newEndTime.compareTo(empty) == 0) {
			error = error + "New end time cannot be empty! ";
		}
		if (newEndTime.before(newStartTime) && newStartTime != null && newEndTime != null ) {
			if (!(newEndTime.compareTo(empty) == 0)) {
				error = error + "New end time cannot be before new start time! ";
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		Calendar c = Calendar.getInstance();
		int i = 0;
		int j = 0;
		Employee et;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			et = ftm.getEmployee(i);
			if (et.getName().equals(e.getName())) {
				for (j = 0; j < et.numberOfWorkStartTime(); j++) {
					if (et.getWorkStartTime(j).equals(oldStartTime) && (et.getWorkEndTime(j).equals(oldEndTime))) {
						
						if (!(et.getWorkStartTime(j).equals(newStartTime))) {
							c.setTime(newStartTime);
							et.getWorkStartTime(j).setTime(c.getTimeInMillis());
						}
						if (!(et.getWorkEndTime(j).equals(newEndTime))) {
							c.setTime(newEndTime);
							et.getWorkEndTime(j).setTime(c.getTimeInMillis());
						}
						break;
					}
				}
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Removing a startTime and endTime from the lists of startTime and endTime
	 * of the specified employee e from memory.
	 * 
	 * @param e
	 * @param startTime
	 * @param endTime
	 */
	public void removeShift(Employee e, Date startTime, Date endTime) {
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		int j = 0;
		int k = 0;
		Employee et;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			et = ftm.getEmployee(i);
			if (et.getName().equals(e.getName())) {
				for (j = 0; j < et.numberOfWorkStartTime(); j++) {
					if (et.getWorkStartTime(j).equals(startTime)) {
						break;
					}
				}
				for (k = 0; k < et.numberOfWorkEndTime(); k++) {
					if (et.getWorkEndTime(k).equals(endTime)) {
						break;
					}
				}
				if (j == k) {
					et.removeWorkStartTime(et.getWorkStartTime(j));
					et.removeWorkEndTime(et.getWorkEndTime(k));
					break;
				}
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Remove the food object f from memory.
	 * 
	 * @param f
	 */
	public void removeFood(Food f) {
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		for (int i = 0; i < ftm.getFoods().size(); i++) {
			if (ftm.getFood(i).getName().equals(f.getName())) {
				ftm.removeFood(ftm.getFood(i));
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Remove the ingredient object i from memory.
	 * 
	 * @param i
	 */
	public void removeIngredient(Ingredient i) {
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		for (int j = 0; j < ftm.getIngredients().size(); j++) {
			if (ftm.getIngredient(j).getName().equals(i.getName())) {
				ftm.removeIngredient(ftm.getIngredient(j));
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Remove the equipment object e from memory.
	 * 
	 * @param e
	 */
	public void removeEquipment(Equipment e) {
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		for (int i = 0; i < ftm.getEquipment().size(); i++) {
			if (ftm.getEquipment(i).getName().equals(e.getName())) {
				ftm.removeEquipment(ftm.getEquipment(i));
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
	
	/**
	 * Remove the employee object e from memory.
	 * @param e
	 */
	public void removeEmployee(Employee e) {
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			if (ftm.getEmployee(i).getName().equals(e.getName())){
				ftm.removeEmployee(ftm.getEmployee(i));
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(ftm);
	}
}