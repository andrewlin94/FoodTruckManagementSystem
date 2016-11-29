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

// line 11 "../../../../../FoodTruckManagementSystem.ump"
// line 40 "../../../../../FoodTruckManagementSystem.ump"
public class Food
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Food Attributes
  private String name;
  private double price;
  private int popularity;

  //Autounique Attributes
  private int id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Food(String aName, double aPrice, int aPopularity)
  {
    name = aName;
    price = aPrice;
    popularity = aPopularity;
    id = nextId++;
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

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setPopularity(int aPopularity)
  {
    boolean wasSet = false;
    popularity = aPopularity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public double getPrice()
  {
    return price;
  }

  public int getPopularity()
  {
    return popularity;
  }

  public int getId()
  {
    return id;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "popularity" + ":" + getPopularity()+ "]"
     + outputString;
  }
}