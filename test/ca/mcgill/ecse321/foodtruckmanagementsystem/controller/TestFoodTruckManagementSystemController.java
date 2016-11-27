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

/**************************************************************************************
************************************FOOD TESTS*****************************************	
**************************************************************************************/
	
	@Test
	public void testCreateEmptyFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("", 12, 12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Food name cannot be empty! ", error);
	}
	/**
	 * Unit test case for empty food name and free price (INVALID)
	 */
	@Test
	public void testCreateEmptyFoodNamePrice() {
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
	public void testCreateEmptyFoodPrice() {
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
	public void testCreateInvalidFoodName() {
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
	public void testCreateInvalidFoodPopularity() {
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
	public void testCreateInvalidFoodPricePopularity() {
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
	public void testCreateInvalidFoodNamePopularity(){
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
	public void testCreateInvalidFoodPriceName(){
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
	
	@Test
	public void testCreateInvalidFoodNamePricePopularity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("        !", -0.1, -12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! Food price is not valid! Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	@Test
	public void testCreateEmptyInvalidFoodNamePricePopularity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("", -0.1, -12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name cannot be empty! Food price is not valid! Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for valid food item creation (VALID)
	 */
	@Test
	public void testCreateFood() {
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
	public void testEditFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Hot Dog", 1.5);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getFoods().size());
		assertEquals("Hot Dog", ftm3.getFood(0).getName());
		assertEquals(0, Double.compare(ftm3.getFood(0).getPrice(), 1.5));
	}
	
	@Test
	public void testEditInvalidFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Burger!@#", 1.5);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New food name is not valid! ", error);
	}
	
	@Test
	public void testEditEmptyFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "", 1.5);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New food name cannot be empty! ", error);
	}
	
	@Test
	public void testEditInvalidFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Burger", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New food price is not valid! ", error);
	}
	
	@Test
	public void testEditInvalidFoodNamePrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Burger!@#", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New food name is not valid! New food price is not valid! ", error);
	}
	
	@Test
	public void testEditEmptyInvalidFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New food name cannot be empty! New food price is not valid! ", error);
	}
	
	@Test
	public void testEditFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Cheeseburger", 12.95);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getFoods().size());
		assertEquals("Cheeseburger", ftm3.getFood(0).getName());
		assertEquals(0, Double.compare(ftm3.getFood(0).getPrice(), 12.95));
	}
	
	@Test
	public void testEditFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		try {
			ftmsc.editFood(f, "Burger", 5.95);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getFoods().size());
		assertEquals("Burger", ftm3.getFood(0).getName());
		assertEquals(0, Double.compare(ftm3.getFood(0).getPrice(), 5.95));
	}

/**************************************************************************************
********************************INGREDIENTS TESTS**************************************	
**************************************************************************************/
	/**
	 * Unit test case for empty ingredient name with valid quantity(INVALID)
	 */
	@Test
	public void testCreateEmptyIngredientName() {
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
	public void testCreateInvalidIngredientName() {
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
	public void testCreateInvalidIngredientQuantity(){
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
	public void testCreateInvalidIngredientQuantityName() {
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
	public void testCreateIngredient() {
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
	
	@Test
	public void testEditInvalidIngredientName() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "", 2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New ingredient name cannot be empty! ", error);
	}
	
	@Test
	public void testEditEmptyInvalidIngredientNameQuantity() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New ingredient name cannot be empty! New ingredient quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditInvalidIngredientQuantity() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "Lettuce", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New ingredient quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditEmptyIngredientName() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New ingredient name cannot be empty! ", error);
	}
	@Test
	public void testEditInvalidIngredientNameQuantity() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New ingredient name cannot be empty! New ingredient quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditIngredientName() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "Tomato", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getIngredients().size());
		assertEquals("Tomato", ftm3.getIngredient(0).getName());
		assertEquals(1, ftm3.getIngredient(0).getQuantity());
	}
	
	@Test
	public void testEditIngredientQuantity() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "Lettuce", 2);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getIngredients().size());
		assertEquals("Lettuce", ftm3.getIngredient(0).getName());
		assertEquals(2, ftm3.getIngredient(0).getQuantity());
	}
	
	@Test
	public void testEditIngredientNameQuantity() {
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
		
		Ingredient i = new Ingredient(name, 1);
		try {
			ftmsc.editIngredient(i, "Tomato", 123);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getIngredients().size());
		assertEquals("Tomato", ftm3.getIngredient(0).getName());
		assertEquals(123, ftm3.getIngredient(0).getQuantity());
	}
/**************************************************************************************
**********************************EQUIPMENT TESTS**************************************	
**************************************************************************************/	
	/**
	 * Unit test case for empty equipment name with valid quantity (INVALID)
	 */
	@Test
	public void testCreateEmptyEquipmentName() {
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
	public void testCreateInvalidEquipmentName() {
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
	public void testCreateInvalidEquipmentQuantity(){
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
	public void testCreateInvalidEquipmentNameQuantity(){
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
	
	@Test
	public void testCreateEmptyInvalidEquipmentNameQuantity(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name cannot be empty! Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	/**
	 * Unit test case for equipment with valid name and quantity (VALID)
	 */
	@Test
	public void testCreateEquipment(){
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
	public void testEditEmptyEquipmentName() {
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
		
		Equipment emp = new Equipment("Fork", 1);
		try {
			ftmsc.editEquipment(emp, "", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New equipment name cannot be empty! ", error);
	}
	
	@Test
	public void testEditInvalidEquipmentName() {
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
		
		Equipment eq = new Equipment("Fork", 1);
		try {
			ftmsc.editEquipment(eq, "Spoon!@#", 1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New equipment name is not valid! ", error);
	}
	
	@Test
	public void testEditInvalidEquipmentQuantity() {
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
		
		Equipment eq = new Equipment("Fork", 1);
		try {
			ftmsc.editEquipment(eq, "Fork", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New equipment quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditInvalidEquipmentNameQuantity() {
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
		
		Equipment eq = new Equipment("Fork", 1);
		try {
			ftmsc.editEquipment(eq, "Fork!", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New equipment name is not valid! New equipment quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditEmptyInvalidEquipmentNameQuantity() {
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
		
		Equipment eq = new Equipment("", 1);
		try {
			ftmsc.editEquipment(eq, "", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("New equipment name cannot be empty! New equipment quantity must be greater than 0! ", error);
	}
	
	@Test
	public void testEditEquipment() {
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
		
		Equipment eq = new Equipment("Fork", 1);
		try {
			ftmsc.editEquipment(eq, "Spoon", 123);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEquipment().size());
//		assertEquals("Spoon", ftm3.getEquipment(0).getName());
		assertEquals(123, ftm3.getEquipment(0).getQuantity());
	}
/**************************************************************************************
 *************************************ORDER TESTS**************************************	
 **************************************************************************************/
	
	/**
	 * Unit test case for editing order with invalid number and non-existent food (INVALID)
	 */
	@Test
	public void testEditInvalidOrder() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		assertEquals(0, Double.compare(price, ftm2.getFood(0).getPrice()));
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		Food f = new Food(name, price, 0);
		try {
			ftmsc.editOrder(f, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Order must be greater than 0! ", error);
	}
	
	@Test
	public void testEditOrder() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood(name, price, 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);

		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		assertEquals(0, Double.compare(price, ftm2.getFood(0).getPrice()));
		
		Food f = new Food(name, price, 0);
		try {
			ftmsc.editOrder(f, 10);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(10, ftm3.getFood(0).getPopularity());
	}

/**************************************************************************************
 *************************************EMPLOYEE TEST************************************	
 **************************************************************************************/
	
	/**
	 * Unit test case for empty employee name (INVALID)
	 */
	@Test
	public void testCreateEmptyEmployeeName() {
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
	public void testCreateInvalidEmployeeName() {
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
	public void testCreateEmployee(){
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
	
	@Test
	public void testEditEmployee() {
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
		assertEquals(1, ftm2.getEmployees().size());
		
		try {
			ftmsc.editEmployeeName(ftm2.getEmployee(0), "Bob");
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals("Bob", ftm3.getEmployee(0).getName());
	}
	
	@Test
	public void testEditEmptyEmployeeName() {
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
		assertEquals(1, ftm2.getEmployees().size());
		
		try {
			ftmsc.editEmployeeName(ftm2.getEmployee(0), "");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New employee name cannot be empty! ", error);
	}
	
	@Test
	public void testEditInvalidEmployeeName() {
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
		assertEquals(1, ftm2.getEmployees().size());
		
		try {
			ftmsc.editEmployeeName(ftm2.getEmployee(0), "Bob!@#");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("New employee name is not valid! ", error);
	}
}
