package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

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
	/**
	 * Unit test case for empty food name (INVALID)
	 */
	@Test
	public void testCreateEmptyFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
		try {
			ftmsc.createFood("        !", -0.1, 2132143);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! Food price is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for invalid popularity and invalid name and invalid price (INVALID)
	 */	
	@Test
	public void testCreateInvalidFoodNamePricePopularity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		Controller ftmsc = new Controller();
		try {
			ftmsc.createFood("        !", -0.1, -12);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Food name is not valid! Food price is not valid! Food popularity is not valid! ", error);		
		assertEquals(0, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for empty name, invalid popularity and invalid price (INVALID)
	 */
	@Test
	public void testCreateEmptyInvalidFoodNamePricePopularity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
		
		String error = "";
		Controller ftmsc = new Controller();
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
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createFood(name, 12.95, 0);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
	}
	
	/**
	 * Unit test case for food removal (VALID)
	 */
	@Test
	public void testRemoveFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createFood(name, 12.95, 0);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getFoods().size());
		assertEquals(name, ftm2.getFood(0).getName());
		assertEquals(0, ftm2.getFood(0).getPopularity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getFoods().size());
		
		Food f = new Food (name, 12.95, 0);
		ftmsc.removeFood(f);
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(0, ftm3.getFoods().size());
	}
	
	
	/**
	 * Unit test case for editing food (VALID)
	 */
	@Test
	public void testEditFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing name to invalid name (INVALID)
	 */
	@Test
	public void testEditInvalidFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing food name to empty name (INVALID)
	 */
	@Test
	public void testEditEmptyFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing price to invalid price (INVALID)
	 */
	@Test
	public void testEditInvalidFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing name and price to invalid name and invalid price (INVALID)
	 */
	@Test
	public void testEditInvalidFoodNamePrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing food and price to empty name and invalid price (INVALID)
	 */
	@Test
	public void testEditEmptyInvalidFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing food name (VALID)
	 */
	@Test
	public void testEditFoodName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing price (VALID)
	 */
	@Test
	public void testEditFoodPrice() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		Food f = new Food(name, price, 0);
		Controller ftmsc = new Controller();
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
		assertEquals(1, ftm2.getFoods().size());
		
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createIngredient(name, 1);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getIngredients().size());
		assertEquals(name, ftm2.getIngredient(0).getName());
		assertEquals(1, ftm2.getIngredient(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getIngredients().size());
	}
	
	
	/**
	 * Unit test case for removing existing ingredient(VALID)
	 */
	@Test
	public void testRemoveIngredient() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createIngredient(name, 1);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getIngredients().size());
		assertEquals(name, ftm2.getIngredient(0).getName());
		assertEquals(1, ftm2.getIngredient(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getIngredients().size());
		
		Ingredient i = new Ingredient(name, 1);
		ftmsc.removeIngredient(i);
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(0, ftm3.getIngredients().size());
	}
	
	
	/**
	 * Unit test case for editing name to invalid name(INVALID)
	 */
	@Test
	public void testEditInvalidIngredientName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing name and quantity to empty name and invalid quantity (INVALID)
	 */
	@Test
	public void testEditEmptyInvalidIngredientNameQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing quantity to invalid quantity(INVALID)
	 */
	@Test
	public void testEditInvalidIngredientQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing name to empty name (INVALID)
	 */	
	@Test
	public void testEditEmptyIngredientName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing name and quantity to invalid name and invalid quantity (INVALID)
	 */
	@Test
	public void testEditInvalidIngredientNameQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing name (VALID)
	 */
	@Test
	public void testEditIngredientName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
		
	/**
	 * Unit test case for editing quantity (VALID)
	 */
	@Test
	public void testEditIngredientQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing name and quantity (VALID)
	 */
	@Test
	public void testEditIngredientNameQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		String name = "Lettuce";
		
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
		try {
			ftmsc.createEquipment("Fork!", -1);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name is not valid! Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getEquipment().size());
	}
	
	
	/**
	 * Unit test case for empty name and invalid quantity(INVALID)
	 */
	@Test
	public void testCreateEmptyInvalidEquipmentNameQuantity(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		Controller ftmsc = new Controller();
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
	public void testCreateEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createEquipment(name, 1);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEquipment().size());
		assertEquals(name, ftm2.getEquipment(0).getName());
		assertEquals(1, ftm2.getEquipment(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEquipment().size());
	}
	
	
	/**
	 * Unit test case for removing existing equipment(VALID)
	 */
	@Test
	public void testRemoveEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		
		Controller ftmsc = new Controller();
		try {
			ftmsc.createEquipment(name, 1);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEquipment().size());
		assertEquals(name, ftm2.getEquipment(0).getName());
		assertEquals(1, ftm2.getEquipment(0).getQuantity());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEquipment().size());
		
		Equipment e = new Equipment(name, 1);
		ftmsc.removeEquipment(e);
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(0, ftm3.getEquipment().size());
	}
	
	
	/**
	 * Unit test case for editing name to empty name (INVALID)
	 */
	@Test
	public void testEditEmptyEquipmentName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		Controller ftmsc = new Controller();
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
			ftmsc.editEquipment(eq, "", 1);
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
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing quantity to invalid quantity (INVALID)
	 */
	@Test
	public void testEditInvalidEquipmentQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing name and quantity to invalid name and invalid quantity (INVALID)
	 */
	@Test
	public void testEditInvalidEquipmentNameQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing name and quantity to empty name and invalid quantity (INVALID)
	 */
	@Test
	public void testEditEmptyInvalidEquipmentNameQuantity() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		Controller ftmsc = new Controller();
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
	
	
	/**
	 * Unit test case for editing equipment (VALID)
	 */
	@Test
	public void testEditEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEquipment().size());
		
		String error = "";
		String name = "Fork";
		Controller ftmsc = new Controller();
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
		assertEquals("Spoon", ftm3.getEquipment(0).getName());
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
		
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing popularity (VALID)
	 */
	@Test
	public void testEditOrder() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		String error = "";
		String name = "Burger";
		double price = 12.95;
		
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
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
		Controller ftmsc = new Controller();
		try {
			ftmsc.createEmployee(name);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEmployees().size());
		assertEquals(name, ftm2.getEmployee(0).getName());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEmployees().size());		
	}
	
	/**
	 * Unit test case for removing employee (VALID)
	 */
	@Test
	public void testRemoveEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "BobbyJoe";
		Controller ftmsc = new Controller();
		try {
			ftmsc.createEmployee(name);
		} catch (InvalidInputException e) {
			fail();
		}
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm2.getEmployees().size());
		assertEquals(name, ftm2.getEmployee(0).getName());
		
		assertEquals("", error);
		assertEquals(1, ftm.getEmployees().size());
		Employee e = new Employee(name);
		ftmsc.removeEmployee(e);
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(0, ftm3.getEmployees().size());
	}
	
	/**
	 * Unit test case for editing name (VALID)
	 */
	@Test
	public void testEditEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "BobbyJoe";
		Controller ftmsc = new Controller();
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
		
	/**
	 * Unit test case for editing name to empty name (INVALID)
	 */
	@Test
	public void testEditEmptyEmployeeName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "BobbyJoe";
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for editing name to invalid name (INVALID)
	 */
	@Test
	public void testEditInvalidEmployeeName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "BobbyJoe";
		Controller ftmsc = new Controller();
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
	
	/**
	 * Unit test case for adding empty start time to employee (INVALID)
	 */
	@Test
	public void testAddEmptyEmployeeStartTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date startTime = null;
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! ", error);
		
		startTime = new Date();
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! ", error);
	}
	
	/**
	 * Unit test case for adding empty end time to employee (INVALID)
	 */
	@Test
	public void testAddEmptyEmployeeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		Date endTime = null;
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
		
		endTime = new Date();
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
	}
	
	/**
	 * Unit test case for adding empty start time and empty end time to employee (INVALID)
	 */
	@Test
	public void testAddEmptyEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Date startTime = new Date();
		Date endTime = new Date();
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
		
		startTime = null;
		endTime = new Date();
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
		
		startTime = new Date();
		endTime = null;
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
		
		startTime = null;
		endTime = null;
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Start time cannot be empty! End time cannot be empty! ", error);
		assertEquals(0, emp.numberOfWorkStartTime());
		assertEquals(0, emp.numberOfWorkEndTime());
	}
	
	/**
	 * Unit test case for adding invalid start time and end time to employee (INVALID)
	 */
	@Test
	public void testAddInvalidEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("End time cannot be before start time! ", error);
	}
	
	/**
	 * Unit test case for adding start time and end time to employee (VALID)
	 */
	@Test
	public void testAddEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
	}
	
	/**
	 * Unit test case for removing start time and end time from employee (VALID)
	 */
	@Test
	public void testRemoveEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
		
		ftmsc.removeShift(emp, startTime, endTime);
		FoodTruckManager ftm4 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm4.getEmployees().size());
		assertEquals(name, ftm4.getEmployee(0).getName());
		assertEquals(0, ftm4.getEmployee(0).getWorkStartTime().length);
		assertEquals(0, ftm4.getEmployee(0).getWorkEndTime().length);
	}
	
	/**
	 * Unit test case for editing start time to empty start time (INVALID)
	 */
	@Test
	public void testEditEmptyEmployeeStartTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
		
		Date newStartTime = new Date();
		try {
			ftmsc.editShift(emp, startTime, endTime, newStartTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New start time cannot be empty! ", error);
	}	
	
	/**
	 * Unit test case for editing end time to empty end time (INVALID)
	 */
	@Test
	public void testEditEmptyEmployeeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
		
		Date newEndTime = new Date();
		try {
			ftmsc.editShift(emp, startTime, endTime, startTime, newEndTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New end time cannot be empty! ", error);
	}
	
	/**
	 * Unit test case for editing start time and end time to invalid start time and invalid end time (INVALID)
	 */
	@Test
	public void testEditInvalidEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
		
		c.set(2016, Calendar.DECEMBER, 3, 20, 0);
		Date newStartTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 3, 18, 0);
		Date newEndTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.editShift(emp, startTime, endTime, newStartTime, newEndTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("New end time cannot be before new start time! ", error);
	}
	
	/**
	 * Unit test case for editing start time and end time (VALID)
	 */
	@Test
	public void testEditEmployeeStartTimeEndTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
				
		String error = "";
		String name = "Michael";
		Controller ftmsc = new Controller();
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
		
		Employee emp = new Employee(name);
		Calendar c = Calendar.getInstance();
		c.set(2016, Calendar.DECEMBER, 1, 22, 0);
		Date startTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 1, 23, 0);
		Date endTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.addShift(emp, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm3 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm3.getEmployees().size());
		assertEquals(name, ftm3.getEmployee(0).getName());
		assertEquals(1, ftm3.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm3.getEmployee(0).getWorkEndTime().length);
		assertEquals(startTime, ftm3.getEmployee(0).getWorkStartTime(0));
		assertEquals(endTime, ftm3.getEmployee(0).getWorkEndTime(0));
		
		c.set(2016, Calendar.DECEMBER, 3, 22, 0);
		Date newStartTime = new Date(c.getTimeInMillis());
		c.set(2016, Calendar.DECEMBER, 3, 23, 0);
		Date newEndTime = new Date(c.getTimeInMillis());
		try {
			ftmsc.editShift(emp, startTime, endTime, newStartTime, newEndTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		FoodTruckManager ftm4 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		assertEquals(1, ftm4.getEmployees().size());
		assertEquals(name, ftm4.getEmployee(0).getName());
		assertEquals(1, ftm4.getEmployee(0).getWorkStartTime().length);
		assertEquals(1, ftm4.getEmployee(0).getWorkEndTime().length);
		assertEquals(newStartTime, ftm4.getEmployee(0).getWorkStartTime(0));
		assertEquals(newEndTime, ftm4.getEmployee(0).getWorkEndTime(0));
	}	
}
