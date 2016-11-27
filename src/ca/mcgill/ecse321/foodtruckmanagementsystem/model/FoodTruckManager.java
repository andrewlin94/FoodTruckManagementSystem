/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.util.*;

// line 3 "../../../../../FoodTruckManagementSystem.ump"
// line 35 "../../../../../FoodTruckManagementSystem.ump"
public class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private List<Food> foods;
  private List<Employee> employees;
  private List<Equipment> equipment;
  private List<Ingredient> ingredients;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManager()
  {
    foods = new ArrayList<Food>();
    employees = new ArrayList<Employee>();
    equipment = new ArrayList<Equipment>();
    ingredients = new ArrayList<Ingredient>();
  }

  public static FoodTruckManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FoodTruckManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Food getFood(int index)
  {
    Food aFood = foods.get(index);
    return aFood;
  }

  public List<Food> getFoods()
  {
    List<Food> newFoods = Collections.unmodifiableList(foods);
    return newFoods;
  }

  public int numberOfFoods()
  {
    int number = foods.size();
    return number;
  }

  public boolean hasFoods()
  {
    boolean has = foods.size() > 0;
    return has;
  }

  public int indexOfFood(Food aFood)
  {
    int index = foods.indexOf(aFood);
    return index;
  }

  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }

  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }

  public static int minimumNumberOfFoods()
  {
    return 0;
  }

  public boolean addFood(Food aFood)
  {
    boolean wasAdded = false;
    if (foods.contains(aFood)) { return false; }
    foods.add(aFood);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFood(Food aFood)
  {
    boolean wasRemoved = false;
    if (foods.contains(aFood))
    {
      foods.remove(aFood);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFoodAt(Food aFood, int index)
  {  
    boolean wasAdded = false;
    if(addFood(aFood))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoods()) { index = numberOfFoods() - 1; }
      foods.remove(aFood);
      foods.add(index, aFood);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodAt(Food aFood, int index)
  {
    boolean wasAdded = false;
    if(foods.contains(aFood))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoods()) { index = numberOfFoods() - 1; }
      foods.remove(aFood);
      foods.add(index, aFood);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodAt(aFood, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEmployees()
  {
    return 0;
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    employees.add(aEmployee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    if (employees.contains(aEmployee))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEquipment()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfIngredients()
  {
    return 0;
  }

  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    ingredients.add(aIngredient);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    if (ingredients.contains(aIngredient))
    {
      ingredients.remove(aIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    foods.clear();
    employees.clear();
    equipment.clear();
    ingredients.clear();
  }

}