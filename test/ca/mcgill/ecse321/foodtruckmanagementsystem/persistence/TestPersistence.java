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
		Employee e1 = new Employee("Michael");
		Employee e2 = new Employee("Steve");
		
		ftm.addEmployee(e1);
		ftm.addEmployee(e2);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	@Test
	public void test() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		PersistenceXStream.setFilename("test" + File.separator + "ca" + File.separator + "mcgill" + File.separator + "ecse321" + File.separator + "foodtruckmanagementsystem" + File.separator + "persistence" + File.separator + "data.xml");
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("food", Food.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		PersistenceXStream.setAlias("ingredient", Ingredient.class);
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
	}

}
