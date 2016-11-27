/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.util.Date;
import java.util.*;

// line 28 "../../../../../FoodTruckManagementSystem.ump"
// line 55 "../../../../../FoodTruckManagementSystem.ump"
public class Employee
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private String name;
  private List<Date> workStartTime;
  private List<Date> workEndTime;

  //Autounique Attributes
  private int id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aName)
  {
    name = aName;
    workStartTime = new ArrayList<Date>();
    workEndTime = new ArrayList<Date>();
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

  public boolean addWorkStartTime(Date aWorkStartTime)
  {
    boolean wasAdded = false;
    wasAdded = workStartTime.add(aWorkStartTime);
    return wasAdded;
  }

  public boolean removeWorkStartTime(Date aWorkStartTime)
  {
    boolean wasRemoved = false;
    wasRemoved = workStartTime.remove(aWorkStartTime);
    return wasRemoved;
  }

  public boolean addWorkEndTime(Date aWorkEndTime)
  {
    boolean wasAdded = false;
    wasAdded = workEndTime.add(aWorkEndTime);
    return wasAdded;
  }

  public boolean removeWorkEndTime(Date aWorkEndTime)
  {
    boolean wasRemoved = false;
    wasRemoved = workEndTime.remove(aWorkEndTime);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public Date getWorkStartTime(int index)
  {
    Date aWorkStartTime = workStartTime.get(index);
    return aWorkStartTime;
  }

  public Date[] getWorkStartTime()
  {
    Date[] newWorkStartTime = workStartTime.toArray(new Date[workStartTime.size()]);
    return newWorkStartTime;
  }

  public int numberOfWorkStartTime()
  {
    int number = workStartTime.size();
    return number;
  }

  public boolean hasWorkStartTime()
  {
    boolean has = workStartTime.size() > 0;
    return has;
  }

  public int indexOfWorkStartTime(Date aWorkStartTime)
  {
    int index = workStartTime.indexOf(aWorkStartTime);
    return index;
  }

  public Date getWorkEndTime(int index)
  {
    Date aWorkEndTime = workEndTime.get(index);
    return aWorkEndTime;
  }

  public Date[] getWorkEndTime()
  {
    Date[] newWorkEndTime = workEndTime.toArray(new Date[workEndTime.size()]);
    return newWorkEndTime;
  }

  public int numberOfWorkEndTime()
  {
    int number = workEndTime.size();
    return number;
  }

  public boolean hasWorkEndTime()
  {
    boolean has = workEndTime.size() > 0;
    return has;
  }

  public int indexOfWorkEndTime(Date aWorkEndTime)
  {
    int index = workEndTime.indexOf(aWorkEndTime);
    return index;
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
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}