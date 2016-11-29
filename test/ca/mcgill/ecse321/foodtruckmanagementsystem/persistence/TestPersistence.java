/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence" + File.separator + "data.xml");
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("food", Food.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		
		Employee e1 = new Employee("Michael");
		Employee e2 = new Employee("Steve");
		ftm.addEmployee(e1);
		ftm.addEmployee(e2);
		
		Equipment eq1 = new Equipment("Fork", 3);
		Equipment eq2 = new Equipment("Napkin", 20);
		ftm.addEquipment(eq1);
		ftm.addEquipment(eq2);
		
		Ingredient i1 = new Ingredient("Lettuce", 25);
		Ingredient i2 = new Ingredient("Tomato", 50);
		ftm.addIngredient(i1);
		ftm.addIngredient(i2);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	@Test
	public void testEmployeePersistence() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
//		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence" + File.separator + "data.xml");
//		PersistenceXStream.setAlias("employee", Employee.class);
//		PersistenceXStream.setAlias("equipment", Equipment.class);
//		PersistenceXStream.setAlias("food", Food.class);
//		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
//		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		if (!PersistenceXStream.saveToXMLwithXStream(ftm))
		{
			fail("Could not save file");
		}
		// clear the model in memory
		ftm.delete();
		assertEquals(0, ftm.getEmployees().size());
		
		// load model
		ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		if (ftm == null)
			fail("Could not load file");
		
		// check participants
		assertEquals(2, ftm.getEmployees().size());
		assertEquals("Michael", ftm.getEmployee(0).getName());
		assertEquals("Steve", ftm.getEmployee(1).getName());
		
		ftm.getEmployee(0).setName("Mike");
		ftm.getEmployee(1).setName("Steven");
		
		assertEquals(2, ftm.getEmployees().size());
		assertEquals("Mike", ftm.getEmployee(0).getName());
		assertEquals("Steven", ftm.getEmployee(1).getName());
	}

	@Test
	public void testEquipmentPersistence() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
//		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence" + File.separator + "data.xml");
//		PersistenceXStream.setAlias("employee", Employee.class);
//		PersistenceXStream.setAlias("equipment", Equipment.class);
//		PersistenceXStream.setAlias("food", Food.class);
//		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
//		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		if (!PersistenceXStream.saveToXMLwithXStream(ftm))
		{
			fail("Could not save file");
		}
		// clear the model in memory
		ftm.delete();
		assertEquals(0, ftm.getEquipment().size());
		
		// load model
		ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		if (ftm == null)
			fail("Could not load file");
		
		// check participants
		assertEquals(2, ftm.getEquipment().size());
		assertEquals("Fork", ftm.getEquipment(0).getName());
		assertEquals(3, ftm.getEquipment(0).getQuantity());
		assertEquals("Napkin", ftm.getEquipment(1).getName());
		assertEquals(20, ftm.getEquipment(1).getQuantity());
		
		ftm.getEquipment(0).setName("Cup");
		ftm.getEquipment(0).setQuantity(50);
		ftm.getEquipment(1).setName("Grill");
		ftm.getEquipment(1).setQuantity(1);
		
		assertEquals(2, ftm.getEquipment().size());
		assertEquals("Cup", ftm.getEquipment(0).getName());
		assertEquals(50, ftm.getEquipment(0).getQuantity());
		assertEquals("Grill", ftm.getEquipment(1).getName());
		assertEquals(1, ftm.getEquipment(1).getQuantity());
	}
	
	@Test
	public void testIngredientPersistence() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
//		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence" + File.separator + "data.xml");
//		PersistenceXStream.setAlias("employee", Employee.class);
//		PersistenceXStream.setAlias("equipment", Equipment.class);
//		PersistenceXStream.setAlias("food", Food.class);
//		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
//		PersistenceXStream.setAlias("ingredient", Ingredient.class);
		if (!PersistenceXStream.saveToXMLwithXStream(ftm))
		{
			fail("Could not save file");
		}
		// clear the model in memory
		ftm.delete();
		assertEquals(0, ftm.getIngredients().size());
		
		// load model
		ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		if (ftm == null)
			fail("Could not load file");
		
		// check participants
		assertEquals(2, ftm.getIngredients().size());
		assertEquals("Lettuce", ftm.getIngredient(0).getName());
		assertEquals(25, ftm.getIngredient(0).getQuantity());
		assertEquals("Tomato", ftm.getIngredient(1).getName());
		assertEquals(50, ftm.getIngredient(1).getQuantity());
		
		ftm.getIngredient(0).setName("Carrot");
		ftm.getIngredient(0).setQuantity(1);
		ftm.getIngredient(1).setName("Mustard");
		ftm.getIngredient(1).setQuantity(10);
		
		assertEquals(2, ftm.getIngredients().size());
		assertEquals("Carrot", ftm.getIngredient(0).getName());
		assertEquals(1, ftm.getIngredient(0).getQuantity());
		assertEquals("Mustard", ftm.getIngredient(1).getName());
		assertEquals(10, ftm.getIngredient(1).getQuantity());
	}
}
