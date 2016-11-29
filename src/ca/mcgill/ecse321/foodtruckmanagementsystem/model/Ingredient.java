/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;

// line 23 "../../../../../FoodTruckManagementSystem.ump"
// line 50 "../../../../../FoodTruckManagementSystem.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;
  private int quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, int aQuantity)
  {
    name = aName;
    quantity = aQuantity;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "quantity" + ":" + getQuantity()+ "]"
     + outputString;
  }
}