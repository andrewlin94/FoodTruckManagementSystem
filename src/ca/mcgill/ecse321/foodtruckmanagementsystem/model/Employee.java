/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagementsystem.model;
import java.sql.Date;
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
  private List<Date> workstarttime;
  private List<Date> workendtime;

  //Autounique Attributes
  private int id;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(String aName)
  {
    name = aName;
    workstarttime = new ArrayList<Date>();
    workendtime = new ArrayList<Date>();
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

  public boolean addWorkstarttime(Date aWorkstarttime)
  {
    boolean wasAdded = false;
    wasAdded = workstarttime.add(aWorkstarttime);
    return wasAdded;
  }

  public boolean removeWorkstarttime(Date aWorkstarttime)
  {
    boolean wasRemoved = false;
    wasRemoved = workstarttime.remove(aWorkstarttime);
    return wasRemoved;
  }

  public boolean addWorkendtime(Date aWorkendtime)
  {
    boolean wasAdded = false;
    wasAdded = workendtime.add(aWorkendtime);
    return wasAdded;
  }

  public boolean removeWorkendtime(Date aWorkendtime)
  {
    boolean wasRemoved = false;
    wasRemoved = workendtime.remove(aWorkendtime);
    return wasRemoved;
  }

  public String getName()
  {
    return name;
  }

  public Date getWorkstarttime(int index)
  {
    Date aWorkstarttime = workstarttime.get(index);
    return aWorkstarttime;
  }

  public Date[] getWorkstarttime()
  {
    Date[] newWorkstarttime = workstarttime.toArray(new Date[workstarttime.size()]);
    return newWorkstarttime;
  }

  public int numberOfWorkstarttime()
  {
    int number = workstarttime.size();
    return number;
  }

  public boolean hasWorkstarttime()
  {
    boolean has = workstarttime.size() > 0;
    return has;
  }

  public int indexOfWorkstarttime(Date aWorkstarttime)
  {
    int index = workstarttime.indexOf(aWorkstarttime);
    return index;
  }

  public Date getWorkendtime(int index)
  {
    Date aWorkendtime = workendtime.get(index);
    return aWorkendtime;
  }

  public Date[] getWorkendtime()
  {
    Date[] newWorkendtime = workendtime.toArray(new Date[workendtime.size()]);
    return newWorkendtime;
  }

  public int numberOfWorkendtime()
  {
    int number = workendtime.size();
    return number;
  }

  public boolean hasWorkendtime()
  {
    boolean has = workendtime.size() > 0;
    return has;
  }

  public int indexOfWorkendtime(Date aWorkendtime)
  {
    int index = workendtime.indexOf(aWorkendtime);
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