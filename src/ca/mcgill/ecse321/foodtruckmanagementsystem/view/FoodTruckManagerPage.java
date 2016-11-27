package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Food;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Ingredient;

public class FoodTruckManagerPage extends JFrame{

	private static final long serialVersionUID = 1860279074122772938L;
	
	private JFrame frame;
	private JLabel errorMessage;
	private JTabbedPane functions;
	
	private String[] foodColumns = new String[] {"Dish", "Price", "Popularity", "Edit/Add/Remove"};
	private Object[][] foods;
	private JTable foodTable;
	private JButton editFood;
	private JButton addFood;
	private JButton removeFood;
	
	private String[] equipmentColumns = new String[] {"Item", "Quantity", "Edit/Add/Remove"};
	private Object[][] equipment;
	private JTable equipmentTable;
	private JButton editEquipment;
	private JButton addEquipment;
	private JButton removeEquipment;
	
	private String[] ingredientColumns = new String[] {"Ingredient", "Quantity", "Edit/Add/Remove"};
	private Object[][] ingredients;
	private JTable ingredientTable;
	private JButton editIngredient;
	private JButton addIngredient;
	private JButton removeIngredient;
	
	private String[] employeeColumns = new String[] {"Employee", "Check Shifts", "Edit/Add/Remove"};
	private Object[][] employees;
	private JTable employeeTable;
	private JComboBox<String> employeeShifts;
	private JButton editEmployee;
	private JButton addEmployee;
	private JButton removeEmployee;
	
	private String error = null;
	private HashMap<Integer, Food> foodMap;
	private HashMap<Integer, Ingredient> ingredientMap;
	private HashMap<Integer, Equipment> equipmentMap;
	private HashMap<Integer, Employee> employeeMap;
	
	final static String FOODTAB = "Food";
	final static String EMPLOYEETAB = "Employees";
	final static String EQUIPMENTTAB = "Equipment";
	final static String INGREDIENTTAB = "Ingredients";
	
	
	public FoodTruckManagerPage(){
		
		frame = new JFrame("Food Truck Manager");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(600, 400);	
		
		initComponents();
		
		frame.setVisible(true);
		
	}
	
	
	private void initComponents(){
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		foodTable = new JTable();		
		equipmentTable = new JTable();		
		ingredientTable = new JTable();		
		employeeTable = new JTable();
		
		functions = new JTabbedPane();
		functions.addTab(FOODTAB, new JScrollPane(foodTable));
		functions.addTab(EMPLOYEETAB, new JScrollPane(employeeTable));
		functions.addTab(INGREDIENTTAB, new JScrollPane(ingredientTable));
		functions.addTab(EQUIPMENTTAB, new JScrollPane(equipmentTable));
		
		refreshFood();
		refreshEquipment();
		refreshIngredient();
		refreshEmployee();
		
		frame.add(functions);
		
	}
	
	
	private void refreshFood(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			foodMap = new HashMap<Integer, Food>();
			foodTable.removeAll();
			foods = new Object[ftm.getFoods().size() + 1][4];
			Iterator<Food> fIt = ftm.getFoods().iterator();
			
			int i = 0;
			while (fIt.hasNext()){
				
				Food f = fIt.next();
				foodMap.put(i, f);
				foods[i][0] = f.getName();
				foods[i][1] = f.getPrice();
				foods[i][2] = f.getPopularity();
				
				foods[i][3] = "Edit/Remove " + f.getName();
				i++;
			}
			
			foods[i][3] = "Add new food";
			
			foodTable = new JTable(foods, foodColumns);
			
			functions.setComponentAt(0, new JScrollPane(foodTable));
			
		}
	}
	
	
	private void refreshEquipment(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			equipmentMap = new HashMap<Integer, Equipment>();
			equipmentTable.removeAll();
			equipment = new Object[ftm.getEquipment().size() + 1][3];
			Iterator<Equipment> eIt = ftm.getEquipment().iterator();
			
			int i = 0;
			while(eIt.hasNext()){
				
				Equipment e = eIt.next();
				equipmentMap.put(i, e);
				equipment[i][0] = e.getName();
				equipment[i][1] = e.getQuantity();
				equipment[i][2] = "Edit/Remove " + e.getName();
				i++;
			}
			equipment[i][2] = "Add new equipment";
			
			equipmentTable = new JTable(equipment, equipmentColumns);
			
			functions.setComponentAt(3, new JScrollPane(equipmentTable));
		}
	}
	
	
	private void refreshIngredient(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			ingredientMap = new HashMap<Integer, Ingredient>();
			ingredientTable.removeAll();
			ingredients = new Object[ftm.getIngredients().size() + 1][3];
			Iterator<Ingredient> iIt = ftm.getIngredients().iterator();
			
			int i = 0;
			while(iIt.hasNext()){
				
				Ingredient ing = iIt.next();
				ingredientMap.put(i, ing);
				ingredients[i][0] = ing.getName();
				ingredients[i][1] = ing.getQuantity();
				ingredients[i][2] = "Edit/Remove " +ing.getName();
				i++;
			}
			ingredients[i][2] = "Add new ingredient";
			
			ingredientTable = new JTable (ingredients, ingredientColumns);
			
			functions.setComponentAt(2, new JScrollPane(ingredientTable));			
		}
	}
	
	
	private void refreshEmployee(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			employeeMap = new HashMap<Integer, Employee>();
			employeeTable.removeAll();
			
			//String shift;
			//DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			
			employees = new Object[ftm.getEmployees().size() + 1][3];
			Iterator<Employee> emIt = ftm.getEmployees().iterator();
			
			int i = 0;
			while(emIt.hasNext()){
				
				Employee e = emIt.next();
				employeeMap.put(i, e);
				employees[i][0] = e.getName();
				
				/*
				for (int j = 0; e.numberOfWorkstarttime() > j; j++){
					shift = dateFormat.format(e.getWorkstarttime(j));
					shift = shift + " to " + dateFormat.format(e.getWorkendtime(j));
					employeeShifts.addItem(shift);
				}
				*/
				
				employees[i][1] = "Check Shifts";
				employees[i][2] = "Edit/Remove " + e.getName();
				i++;
			}
			employees[i][2] = "Add new employee";
			
			employeeTable = new JTable(employees, employeeColumns);
			
			functions.setComponentAt(1, new JScrollPane(employeeTable));
			
		}
	}
}









