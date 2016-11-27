package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Food;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Ingredient;

public class FoodTruckManagerPage extends JFrame{

	private static final long serialVersionUID = -8062635784771606869L;
	
	private JFrame frame;
	private JLabel errorMessage;
	private JTabbedPane functions;
	
	private String[] foodColumns = new String[] {"Dish", "Price", "Popularity", "Edit", "Add/Remove"};
	private Object[][] foods;
	private JTable foodTable;
	private JButton editFood;
	private JButton addFood;
	private JButton removeFood;
	
	private String[] equipmentColumns = new String[] {"Item", "Quantity", "Edit", "Add/Remove"};
	private Object[][] equipment;
	private JTable equipmentTable;
	private JButton editEquipment;
	private JButton addEquipment;
	private JButton removeEquipment;
	
	private String[] ingredientColumns = new String[] {"Ingredient", "Quantity", "Edit", "Add/Remove"};
	private Object[][] ingredients;
	private JTable ingredientTable;
	private JButton editIngredient;
	private JButton addIngredient;
	private JButton removeIngredient;
	
	private String[] employeeColumns = new String[] {"Employee", "Check Shifts", "Edit", "Add/Remove"};
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
		refreshFood();
		
		equipmentTable = new JTable();
		refreshEquipment();
		
		ingredientTable = new JTable();
		refreshIngredient();
		
		employeeTable = new JTable();
		refreshEmployee();
		
		functions = new JTabbedPane();
		functions.addTab(FOODTAB, new JScrollPane(foodTable));
		functions.addTab(EMPLOYEETAB, new JScrollPane(employeeTable));
		functions.addTab(INGREDIENTTAB, new JScrollPane(ingredientTable));
		functions.addTab(EQUIPMENTTAB, new JScrollPane(equipmentTable));
		
		frame.add(functions);
		
	}
	
	
	private void refreshFood(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			foodMap = new HashMap<Integer, Food>();
			foodTable.removeAll();
			foods = new Object[ftm.getFoods().size() + 1][5];
			Iterator<Food> fIt = ftm.getFoods().iterator();
			
			int i = 0;
			while (fIt.hasNext()){
				editFood = new JButton();
				removeFood = new JButton();
				
				Food f = fIt.next();
				foodMap.put(i, f);
				foods[i][0] = f.getName();
				foods[i][1] = f.getPrice();
				foods[i][2] = f.getPopularity();
				
				editFood.setText("Edit " + f.getName());
				
				removeFood.setText("Remove " + f.getName());
				
				
				foods[i][3] = editFood;
				foods[i][4] = removeFood;
				i++;
			}
			addFood = new JButton();
			foods[i][4] = addFood;
			
			foodTable = new JTable(foods, foodColumns);
			
		}
	}
	
	
	private void refreshEquipment(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			equipmentMap = new HashMap<Integer, Equipment>();
			equipmentTable.removeAll();
			equipment = new Object[ftm.getEquipment().size() + 1][4];
			Iterator<Equipment> eIt = ftm.getEquipment().iterator();
			
			int i = 0;
			while(eIt.hasNext()){
				editEquipment = new JButton();
				removeEquipment = new JButton();
				
				Equipment e = eIt.next();
				equipmentMap.put(i, e);
				equipment[i][0] = e.getName();
				equipment[i][1] = e.getQuantity();
				equipment[i][2] = editEquipment;
				equipment[i][3] = removeEquipment;
				i++;
			}
			addEquipment = new JButton();
			equipment[i][3] = addEquipment;
			
			equipmentTable = new JTable(equipment, equipmentColumns);
			
		}
	}
	
	
	private void refreshIngredient(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			ingredientMap = new HashMap<Integer, Ingredient>();
			ingredientTable.removeAll();
			ingredients = new Object[ftm.getIngredients().size() + 1][4];
			Iterator<Ingredient> iIt = ftm.getIngredients().iterator();
			
			int i = 0;
			while(iIt.hasNext()){
				editIngredient = new JButton();
				removeIngredient = new JButton();
				
				Ingredient ing = iIt.next();
				ingredientMap.put(i, ing);
				ingredients[i][0] = ing.getName();
				ingredients[i][1] = ing.getQuantity();
				ingredients[i][2] = editIngredient;
				ingredients[i][3] = removeIngredient;
				i++;
			}
			addIngredient = new JButton();
			ingredients[i][3] = addIngredient;
			
			ingredientTable = new JTable (ingredients, ingredientColumns);
			
		}
	}
	
	
	private void refreshEmployee(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		
		errorMessage.setText(error);
		if(error == null || error.length() == 0) {
			
			employeeMap = new HashMap<Integer, Employee>();
			employeeTable.removeAll();
			
			String shift;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			
			employees = new Object[ftm.getEmployees().size() + 1][4];
			Iterator<Employee> emIt = ftm.getEmployees().iterator();
			
			int i = 0;
			while(emIt.hasNext()){
				editEmployee = new JButton();
				removeEmployee = new JButton();
				
				employeeShifts = new JComboBox<String>();
				Employee e = emIt.next();
				employeeMap.put(i, e);
				employees[i][0] = e.getName();
				
				for (int j = 0; e.numberOfWorkstarttime() > j; j++){
					shift = dateFormat.format(e.getWorkstarttime(j));
					shift = shift + " to " + dateFormat.format(e.getWorkendtime(j));
					employeeShifts.addItem(shift);
				}
				
				employees[i][1] = employeeShifts;
				employees[i][2] = editEmployee;
				employees[i][3] = removeEmployee;
				i++;
			}
			addEmployee = new JButton();
			employees[i][3] = addEmployee;
			
			employeeTable = new JTable(employees, employeeColumns);
			
		}
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer{

	private static final long serialVersionUID = -8062635784771606870L;
	
	private String action;
	
	public ButtonRenderer(String action){
		this.action = action;
		setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object button,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}
	
}









