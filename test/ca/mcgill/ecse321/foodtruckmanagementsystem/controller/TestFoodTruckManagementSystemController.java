package ca.mcgill.ecse321.foodtruckmanagementsystem.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TestFoodTruckManagementSystemController {

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

	@Test
	public void testCreateEmptyFood() {
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
	
	@Test
	public void testCreateFreeFood() {
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
	
	@Test
	public void testCreateFood() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getFoods().size());
				
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createFood("Burger", 12.95, 0);
		} catch (InvalidInputException e) {
			fail();
		}
		
		assertEquals(1, ftm.getFoods().size());
	}
	
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

	@Test
	public void testCreateEmptyEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee("");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee name cannot be empty! ", error);		
		assertEquals(0, ftm.getEmployees().size());
	}

	@Test
	public void testCreateInvalidEmployeeName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee("A!@#");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Employee name is not valid! ", error);		
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testCreateEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEmployee("Michael");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(1, ftm.getEmployees().size());
		assertEquals("Michael", ftm.getEmployee(0).getName());
	}
	
	@Test
	public void testCreateEmptyIngredient() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient("", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient name cannot be empty! Ingredient quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	@Test
	public void testCreateInvalidIngredientName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createIngredient("Lettuce!", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Ingredient name is not valid! Ingredient quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	@Test
	public void testCreateEmptyEquipment() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name cannot be empty! Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
	
	@Test
	public void testCreateInvalidEquipmenttName() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getIngredients().size());
		
		String error = "";
		FoodTruckManagementSystemController ftmsc = new FoodTruckManagementSystemController();
		try {
			ftmsc.createEquipment("Fork!", 0);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Equipment name is not valid! Equipment quantity is not valid! ", error);
		assertEquals(0, ftm.getIngredients().size());
	}
}
