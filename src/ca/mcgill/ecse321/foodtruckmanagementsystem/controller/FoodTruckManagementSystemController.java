/*
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
public class FoodTruckManagementSystemController {

	public FoodTruckManagementSystemController() {}
	
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
		//check if name is empty
		if (name == null || name.trim().length() == 0) {
			error = error + "Employee name cannot be empty! ";	// add error message
		}
		
		// name is not empty, check if name is valid
		else {	
			int ascii;	
			for (int i = 0; i < name.trim().length(); i++) {	// for each character in the name	
				ascii = (int) name.trim().charAt(i);	// get ascii value of the character
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {	
					
					error = error + "Employee name is not valid! ";		// add error message						
					break;	// stop loop when one character is not valid
				}
			}
		}
		
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// load from memory, write, then save
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
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
		
		// check if name is empty
		if (name == null || name.trim().length() == 0) {
			error = error + "Ingredient name cannot be empty! ";	// add error message
		}
		
		// name is not empty, check if name is valid
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {	// for each character in the name
				ascii = (int) name.trim().charAt(i);
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					
					error = error + "Ingredient name is not valid! ";	// add error message
					break;	// stop loop when there is one invalid character
				}
			}
		}
		
		if (quantity < 1) {	// quantity must be greater than 0
			error = error + "Ingredient quantity is not valid! ";	// add error message
		}
		
		if (error.length() > 0) {	// if there was an error
			throw new InvalidInputException(error);
		}
		
		// load from memory, write, then save
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
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
		
		// check if name is empty
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New ingredient name cannot be empty! ";	// add error message
		}
		
		// name is not empty, check if name is valid
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);	// get ascii value of character
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					
					error = error + "New ingredient name is not valid! ";	// add error message
					break;
				}
			}
		}
		
		// check if newQuantity is valid
		if (newQuantity <= 0) { 
			error = error + "New ingredient quantity must be greater than 0! ";	// add error message
		}
		
		// check if there was an error
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// load from memory
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// search for the corresponding ingredient
		int i = 0;
		for (i = 0; i < ftm2.getIngredients().size(); i++) {
			// name and quantity must match
			if (ftm2.getIngredient(i).getName().equals(ingredient.getName())
					&& ftm2.getIngredient(i).getQuantity() == ingredient.getQuantity()) {
				
				// set to newName iif name is not equal the newName
				if (!(ftm2.getIngredient(i).getName().equals(newName))) {
					ftm2.getIngredient(i).setName(newName);
				}
				
				// set to newQuantity iif quantity != newQuantity
				if (!(ftm2.getIngredient(i).getQuantity() == newQuantity)) {
					ftm2.getIngredient(i).setQuantity(newQuantity);
				}
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm2);
				break;	// stop the loop
			}
		}
		
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
		
		// check if name is empty
		if (name == null || name.trim().length() == 0) {
			error = error + "Equipment name cannot be empty! ";	// add error message
		}
		
		// name is not empty, check if name is valid
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					
					error = error + "Equipment name is not valid! ";	// add error message
					break;	// stop the loop if there is an invalid character
				}
			}
		}
		if (quantity < 1) {
			error = error + "Equipment quantity is not valid! ";	// add error message
		}
		
		// check if there was any error
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// load from memory, write, save to memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
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
		
		// check if name is empty
		if (name == null || name.trim().length() == 0) {
			error = error + "Food name cannot be empty! ";
		}
		
		// name is not empty, check if name is valid
		else {
			int ascii;
			for (int i = 0; i < name.trim().length(); i++) {
				ascii = (int) name.trim().charAt(i);	// get the current character ascii value
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "Food name is not valid! ";	// add error message
					break;	// stop the loop when a character is not valid
				}
			}
		}
		
		// check if price is greater than 0
		if (Double.compare(price, (double)0.0) <= 0) {
			error = error + "Food price is not valid! ";
		}
		
		// check if popularity is valid
		if (popularity < 0) {
			error = error + "Food popularity is not valid! ";
		}
		
		// check if there was any error
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// load from memory, write, then save in memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
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
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		String error = "";
		
		// check if newName is empty
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New food name cannot be empty! ";
		}
		// newName is not empty, check if newName is valid
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);	// get the ascii value of the character
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New food name is not valid! ";	// add error message
					break;	// stop the loop when a character is invalid
				}
			}
		}
		
		// check if newPrice is valid	
		if (Double.compare(newPrice, (double)0.0) <= 0) {
			error = error + "New food price is not valid! ";
		}			
		
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// search for the index of the food
		int i = 0;
		for (i = 0; i < ftm.getFoods().size(); i++) {
			
			// check if the name, price, and popularity are equal to the food we want to change
			if (ftm.getFood(i).getName().equals(food.getName())
					&& (Double.compare(ftm.getFood(i).getPrice(), food.getPrice()) == 0)
					&& ftm.getFood(i).getPopularity() == food.getPopularity()) {
				
				// if current name is not the newName, set to newName
				if (!(ftm.getFood(i).getName().equals(newName))) {
					ftm.getFood(i).setName(newName);
				}
				
				// if current price is not the newPrice, set to newPrice
				if (!(Double.compare(ftm.getFood(i).getPrice(), newPrice) == 0)) {
					ftm.getFood(i).setPrice(newPrice);
				}
				
				// save in memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
			}
		}
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
		
		// check if new order is valid
		if (order < 0) {
			error = error + "Order must be greater than 0! ";
		}
		
		// check if there was an error
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// search food in memory
		int i = 0;
		for (i = 0; i < ftm.getFoods().size(); i++) {
			
			// check if the food is the one we want to change
			if (ftm.getFood(i).getName().equals(food.getName())
					&& (Double.compare(ftm.getFood(i).getPrice(), food.getPrice()) == 0)
					&& ftm.getFood(i).getPopularity() == food.getPopularity()) {
				break; // stop the loop
			}
		}
		// increment the popularity count
		int p = ftm.getFood(i).getPopularity();
		ftm.getFood(i).setPopularity(p + order);
		
		// save to memory
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
		
		// check if newName is empty
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New employee name cannot be empty! ";	// add error message
		}
		
		// newName is not empty, check if newName is valid
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);	// get the ascii value of the character 
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New employee name is not valid! ";	// add error message
					break;	// stop the loop
				}
			}
		}
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// search for the employee we want to change
		int i = 0;
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			
			// compare the names of the employees
			if (ftm.getEmployee(i).getName().equals(employee.getName())) {
				
				// set employee name to newName if it is not already newName
				if (!(ftm.getEmployee(i).getName().equals(newName))) {
					ftm.getEmployee(i).setName(newName);
				}
				
				// save to memory
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
		
		// check if newName is empty
		if (newName == null || newName.trim().length() == 0) {
			error = error + "New equipment name cannot be empty! ";	// add error message
		}
		
		// newName is not empty, check if newName is valid
		else {
			int ascii;
			for (int i = 0; i < newName.trim().length(); i++) {
				ascii = (int) newName.trim().charAt(i);	// get ascii value of the character
				
				// check if the characters are valid by ascii values (space, &, a-z, A-Z, 0-9)
				if ((ascii < 32) || (ascii > 32 && ascii < 38) || (ascii > 38 && ascii < 48) || 
						(ascii > 57 && ascii < 65) || (ascii > 90 && ascii < 97) || (ascii > 122)) {
					error = error + "New equipment name is not valid! ";	// add error message
					break;	// stop the loop
				}
			}
		}
		// check if newQuantity is greater than 0
		if (newQuantity < 0) {
			error = error + "New equipment quantity must be greater than 0! ";	// add error message
		}
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// search for equipment in memory
		int i = 0;
		for (i = 0; i < ftm.getEquipment().size(); i++) {
			
			// check if equipment is the one we want to edit
			if (ftm.getEquipment(i).getName().equals(e.getName())
					&& ftm.getEquipment(i).getQuantity() == e.getQuantity()) {
				
				// change name to newName if name is not already newName
				if (!(ftm.getEquipment(i).getName().equals(newName))) {
					ftm.getEquipment(i).setName(newName);
				}
				
				// change quantity to newQuantity if quantity is not already newQuantity
				if (!(ftm.getEquipment(i).getQuantity() == newQuantity)) {
					ftm.getEquipment(i).setQuantity(newQuantity);
				}
				
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
		
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
		Date empty = new Date();
		
		// check if start time is valid	or null
		if (workStartTime == null || (workStartTime.compareTo(empty) == 0)) {
			error = error + "Start time cannot be empty! ";	// add error message
		}
		
		// check if end time is valid or null
		if (workEndTime == null || (workEndTime.compareTo(empty) == 0)) {
			error = error + "End time cannot be empty! ";	// add error message
		}
		
		// check if end time is before start time if they are both valid
		if (workStartTime != null && workEndTime != null && (workStartTime.compareTo(workEndTime) > 0)) {
			
			// check if end time is null, so that it would not return the following error message when end time is empty
			if (!(workEndTime.compareTo(empty) == 0)) {
				error = error + "End time cannot be before start time! ";	// add error message
			}			
		}
		
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		int i = 0;
		
		// search for employee in memory
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			
			// check if the employee is the one we want to assign a shift to
			if (ftm.getEmployee(i).getName().equals(e.getName())) {
				
				// add start time and end time
				ftm.getEmployee(i).addWorkStartTime(workStartTime);
				ftm.getEmployee(i).addWorkEndTime(workEndTime);
				
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
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
		
		// check if new start time is empty or null
		if (newStartTime == null || newStartTime.compareTo(empty) == 0) {
			error = error + "New start time cannot be empty! ";	// add error message
		}
		
		// check if new end time is empty or null
		if (newEndTime == null || newEndTime.compareTo(empty) == 0) {
			error = error + "New end time cannot be empty! ";	// add error message
		}
		
		// check if new end time is before new start time if they are both valid
		if (newEndTime.before(newStartTime) && newStartTime != null && newEndTime != null ) {
			
			// check if new end time is null, so that the error message will not be added if end time is empty
			if (!(newEndTime.compareTo(empty) == 0)) {
				error = error + "New end time cannot be before new start time! ";	// add error message
			}
		}
		// check if there were any errors
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		// variables for search
		Calendar c = Calendar.getInstance();
		int i = 0;
		int j = 0;
		Employee et;
		
		// search for employee in memory
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			et = ftm.getEmployee(i);
			
			// check if name is same as the name we want to change
			if (et.getName().equals(e.getName())) {
				
				// search for start time and end time in memory of that employee
				for (j = 0; j < et.numberOfWorkStartTime(); j++) {
					
					// check if employee has the oldStartTime and oldEndTime
					if (et.getWorkStartTime(j).equals(oldStartTime) && (et.getWorkEndTime(j).equals(oldEndTime))) {
						
						// set workStartTime to newStartTime if not the same
						if (!(et.getWorkStartTime(j).equals(newStartTime))) {
							c.setTime(newStartTime);
							et.getWorkStartTime(j).setTime(c.getTimeInMillis());
						}
						
						// set workEndTime to newEndTime if not the same
						if (!(et.getWorkEndTime(j).equals(newEndTime))) {
							c.setTime(newEndTime);
							et.getWorkEndTime(j).setTime(c.getTimeInMillis());
						}
						PersistenceXStream.saveToXMLwithXStream(ftm);
						break;
					}
				}
			}
		}
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
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		// variables for search
		int i = 0;
		int j = 0;
		int k = 0;
		Employee et;
		
		// search for employee in memory
		for (i = 0; i < ftm.getEmployees().size(); i++) {
			et = ftm.getEmployee(i);
			
			// check if employee is the one we want to remove a shift from
			if (et.getName().equals(e.getName())) {
				
				// search for the start time shift we want to remove
				for (j = 0; j < et.numberOfWorkStartTime(); j++) {
					if (et.getWorkStartTime(j).equals(startTime)) {
						break;
					}
				}
				
				// search for the end time shift we want to remove
				for (k = 0; k < et.numberOfWorkEndTime(); k++) {
					if (et.getWorkEndTime(k).equals(endTime)) {
						break;
					}
				}
				
				// check if start time and end time are the same index (meaning this is the same shift)
				if (j == k) {
					
					// remove shifts
					et.removeWorkStartTime(et.getWorkStartTime(j));
					et.removeWorkEndTime(et.getWorkEndTime(k));
					
					// save to memory
					PersistenceXStream.saveToXMLwithXStream(ftm);
					break;
				}
			}
		}
	}
	
	/**
	 * Remove the food object f from memory.
	 * 
	 * @param f
	 */
	public void removeFood(Food f) {
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		// search for food we want to remove
		for (int i = 0; i < ftm.getFoods().size(); i++) {
			
			// check if the food is the one we want to remove
			if ( (ftm.getFood(i).getName().equals(f.getName()))
					&& (Double.compare(ftm.getFood(i).getPrice(), f.getPrice()) == 0)
					&& (ftm.getFood(i).getPopularity() == f.getPopularity())) {
				
				// remove the food
				ftm.removeFood(ftm.getFood(i));
				
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
	}
	
	/**
	 * Remove the ingredient object i from memory.
	 * 
	 * @param i
	 */
	public void removeIngredient(Ingredient i) {
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		// search for ingredient in memory
		for (int j = 0; j < ftm.getIngredients().size(); j++) {
			
			// check if the ingredient is the one we want to remove
			if (ftm.getIngredient(j).getName().equals(i.getName())
					&& (ftm.getIngredient(j).getQuantity() == i.getQuantity())) {
				
				// remove the ingredient
				ftm.removeIngredient(ftm.getIngredient(j));
				
				// save in memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
	}
	
	/**
	 * Remove the equipment object e from memory.
	 * 
	 * @param e
	 */
	public void removeEquipment(Equipment e) {
		
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		// search for equipment in memory
		for (int i = 0; i < ftm.getEquipment().size(); i++) {
			
			// check if the equipment is the one we want to remove
			if (ftm.getEquipment(i).getName().equals(e.getName())
					&& ftm.getEquipment(i).getQuantity() == e.getQuantity()) {
				
				// remove equipment
				ftm.removeEquipment(ftm.getEquipment(i));
				
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
		
	}
	
	/**
	 * Remove the employee object e from memory.
	 * @param e
	 */
	public void removeEmployee(Employee e) {
		// load from memory
		FoodTruckManager ftm = (FoodTruckManager)PersistenceXStream.loadFromXMLwithXStream();
		
		// search for employee in memory
		for (int i = 0; i < ftm.getEmployees().size(); i++) {
			if (ftm.getEmployee(i).getName().equals(e.getName())){
				
				// remove employee
				ftm.removeEmployee(ftm.getEmployee(i));
				
				// save to memory
				PersistenceXStream.saveToXMLwithXStream(ftm);
				break;
			}
		}
	}
}
