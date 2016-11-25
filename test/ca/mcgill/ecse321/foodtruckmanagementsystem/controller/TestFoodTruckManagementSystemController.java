package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TestFoodTruckManagementSystemController {

	// create a file for testing purposes
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruckmanagementsystem"+File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("food", Food.class);
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);		
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	/**
	 * Unit test case for empty food name and free price (INVALID)
	 */
	@Test
	public void testNoNameFreeFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("", 0, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name cannot be empty! Food price is not valid! ", error);
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for free food (valid name) (INVALID)
	 */
	@Test
	public void testFreeFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger", 0, 0);
		}
		catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Food price is not valid! ", error);
		
		//	check no change in memory
		assertEquals(0, ftm.getFoods().size());	
	}
	
	/**
	 * Unit test case for food name containing invalid ascii symbols (INVALID)
	 */
	@Test
	public void testInvalidNameFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger!@#", 12.95, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for negative food popularity (INVALID)
	 */
	@Test
	public void testInvalidFoodPopularity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger", 12.95, -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for valid name and invalid price & popularity (INVALID)
	 */
	@Test
	public void testBadPriceAndPopFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger", 0.0, -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food price is not valid! Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	/**
	 * Unit test case for valid price and invalid name & popularity (INVALID)
	 */
	@Test
	public void testBadNameAndPopFood(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger!@#", 12.95, -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for valid popularity and invalid name & price (INVALID)
	 */
	@Test
	public void testBadNameAndPriceFood(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("        !", -0.1, 2132143);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! Food price is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for valid food item creation (VALID)
	 */
	@Test
	public void testValidFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, 12.95, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
	}
	
	@Test
	public void testValidOrder() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger", 12.95, 12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(12, ftm.getFood(0).getPopularity());
		assertEquals("", error);
		
		try {
			ftmsc.editOrder(ftm.getFood(0), 12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals("Burger", ftm2.getFood(0).getName());
		assertEquals(24, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
	}

	/**
	 * Unit test case for empty employee name (INVALID)
	 */
	@Test
	public void testEmptyNameEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee(" ");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee name cannot be empty! ", error);		
		assertEquals(0, ftm.getEmployees().size());
	}

	/**
	 * Unit test case for employee name input with invalid ascii symbols (INVALID)
	 */
	@Test
	public void testInvalidNameEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee("Albert!@#");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Employee name is not valid! ", error);		
		assertEquals(0, ftm.getEmployees().size());
	}
	
	/**
	 * Unit test case for valid Employee input (VALID)
	 */
	@Test
	public void testValidEmployee(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "BobbyJoe";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEmployees().size());
		assertEquals(name, ftm2.getEmployee(0).getName());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEmployees().size());
	}
	
	/**
	 * Unit test case for empty ingredient name with valid quantity(INVALID)
	 */
	@Test
	public void testEmptyNameIngredient() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient(" ", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient name cannot be empty! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	/**
	 * Unit test case for ingredient name containing invalid ascii symbols with valid quantity (INVALID)
	 */
	@Test
	public void testInvalidNameIngredient() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient("Lettuce!", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient name is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	/**
	 * Unit test case for ingredient with invalid quantity and valid name (INVALID)
	 */
	@Test
	public void testInvalidQuantityIngredient(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient("Lettuce", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	/**
	 * Unit test case for ingredient with invalid quantity and name (INVALID)
	 */
	@Test
	public void testBadNameAndQuantityIngredient(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient("Lettuce!", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient name is not valid! Ingredient quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	/**
	 * Unit test case for ingredient with valid name and quantity (VALID)
	 */
	@Test
	public void testValidIngredient() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient(name, 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getIngredients().size());
		assertEquals(name, ftm2.getIngredient(0).getName());
		assertEquals(1, ftm2.getIngredient(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getIngredients().size());
	}
	
	/**
	 * Unit test case for empty equipment name with valid quantity (INVALID)
	 */
	@Test
	public void testEmptyNameEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("  ", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name cannot be empty! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	/**
	 * Unit test case for equipment name containing invalid ascii symbols with valid quantity (INVALID)
	 */
	@Test
	public void testInvalidNameEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("Fork!", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name is not valid! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	/**
	 * Unit test case for equipment with invalid quantity and valid name (INVALID)
	 */
	@Test
	public void testInvalidQuantityEquipment(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("Fork", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	/**
	 * Unit test case for equipment with invalid name and quantity (INVALID)
	 */
	@Test
	public void testBadNameAndQuantityEquipment(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("Fork!", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name is not valid! Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	/**
	 * Unit test case for equipment with valid name and quantity (VALID)
	 */
	@Test
	public void testValidEquipment(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment(name, 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEquipment().size());
		assertEquals(name, ftm2.getEquipment(0).getName());
		assertEquals(1, ftm2.getEquipment(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEquipment().size());
	}
	
	
	@Test
	public void testEditFoodNameAndQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		double price = 1.5;
		String name = "Hot Dog";
		int pop = 0;
		Food f = new Food(name, price, pop);
		ftm.addFood(f);
		assertEquals(1, ftm.getFoods().size());
		assertEquals(name, ftm.getFood(0).getName());
		assertEquals(true, Double.compare(ftm.getFood(0).getPrice(), price) == 0);
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.editFood(ftm.getFood(0), "Burger", 5);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, ftm.getFoods().size());
		assertEquals("Burger", ftm.getFood(0).getName());
		assertEquals(true, Double.compare(ftm.getFood(0).getPrice(), 5) == 0);
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();		
	}
}
