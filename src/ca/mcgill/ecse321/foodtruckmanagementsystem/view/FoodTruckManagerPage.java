package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Food;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Ingredient;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class FoodTruckManagerPage extends JFrame{

	private static final long serialVersionUID = 1860279074122772938L;
	
	private JTabbedPane functions;
	
	private String[] foodColumns = new String[] {"Dish", "Price", "Popularity", "Edit/Add/Remove"};
	private Object[][] foods;
	private JTable foodTable;
	
	private String[] equipmentColumns = new String[] {"Item", "Quantity", "Edit/Add/Remove"};
	private Object[][] equipment;
	private JTable equipmentTable;
	
	private String[] ingredientColumns = new String[] {"Ingredient", "Quantity", "Edit/Add/Remove"};
	private Object[][] ingredients;
	private JTable ingredientTable;
	
	private String[] employeeColumns = new String[] {"Employee", "Check Shifts", "Edit/Add/Remove"};
	private Object[][] employees;
	private JTable employeeTable;
	
	final static String FOODTAB = "Food";
	final static String EMPLOYEETAB = "Employees";
	final static String EQUIPMENTTAB = "Equipment";
	final static String INGREDIENTTAB = "Ingredients";
	
	
	public FoodTruckManagerPage(){
		
		setTitle("Food Truck Manager");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(700, 400);	
		
		initComponents();
		
		setVisible(true);
		
	}
	
	
	private void initComponents(){
		
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
		
		add(functions);
		
	}
	
	
	private void refreshFood(){
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();		
		
		foodTable.removeAll();
		foods = new Object[ftm.getFoods().size() + 1][4];
		Iterator<Food> fIt = ftm.getFoods().iterator();

		int i = 0;
		while (fIt.hasNext()) {

			Food f = fIt.next();
			foods[i][0] = f.getName();
			foods[i][1] = "$ " + f.getPrice();
			foods[i][2] = f.getPopularity();

			foods[i][3] = "Edit/Remove " + "\"" + f.getName() + "\"";
			i++;
		}

		foods[i][3] = "Add new food";

		foodTable = new JTable(foods, foodColumns);
		@SuppressWarnings("unused")
		ButtonColumn editColumn = new ButtonColumn(foodTable, edit, 3);

		functions.setComponentAt(0, new JScrollPane(foodTable));
		SwingUtilities.updateComponentTreeUI(this);
		invalidate();
		validate();
		repaint();

	}
	
	
	private void refreshEquipment(){
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		equipmentTable.removeAll();
		equipment = new Object[ftm.getEquipment().size() + 1][3];
		Iterator<Equipment> eIt = ftm.getEquipment().iterator();

		int i = 0;
		while (eIt.hasNext()) {

			Equipment e = eIt.next();
			equipment[i][0] = e.getName();
			equipment[i][1] = e.getQuantity();
			equipment[i][2] = "Edit/Remove " + "\"" + e.getName() + "\"";
			i++;
		}
		equipment[i][2] = "Add new equipment";

		equipmentTable = new JTable(equipment, equipmentColumns);
		@SuppressWarnings("unused")
		ButtonColumn editColumn = new ButtonColumn(equipmentTable, edit, 2);

		functions.setComponentAt(3, new JScrollPane(equipmentTable));

	}
	
	
	private void refreshIngredient(){
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		ingredientTable.removeAll();
		ingredients = new Object[ftm.getIngredients().size() + 1][3];
		Iterator<Ingredient> iIt = ftm.getIngredients().iterator();

		int i = 0;
		while (iIt.hasNext()) {

			Ingredient ing = iIt.next();
			ingredients[i][0] = ing.getName();
			ingredients[i][1] = ing.getQuantity();
			ingredients[i][2] = "Edit/Remove " + "\"" + ing.getName() + "\"";
			i++;
		}
		ingredients[i][2] = "Add new ingredient";

		ingredientTable = new JTable(ingredients, ingredientColumns);
		@SuppressWarnings("unused")
		ButtonColumn editColumn = new ButtonColumn(ingredientTable, edit, 2);

		functions.setComponentAt(2, new JScrollPane(ingredientTable));

	}
	
	
	private void refreshEmployee(){
		FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		employeeTable.removeAll();

		employees = new Object[ftm.getEmployees().size() + 1][3];
		Iterator<Employee> emIt = ftm.getEmployees().iterator();

		int i = 0;
		while (emIt.hasNext()) {

			Employee e = emIt.next();
			employees[i][0] = e.getName();
			employees[i][1] = "Check/Edit Shifts";
			employees[i][2] = "Edit/Remove " + "\"" + e.getName() + "\"";
			i++;
		}
		employees[i][2] = "Add new employee";

		employeeTable = new JTable(employees, employeeColumns);
		@SuppressWarnings("unused")
		ButtonColumn editColumn = new ButtonColumn(employeeTable, edit, 2);
		@SuppressWarnings("unused")
		ButtonColumn shiftColumn = new ButtonColumn(employeeTable, shifts, 1);

		functions.setComponentAt(1, new JScrollPane(employeeTable));

	}
	
	Action edit = new AbstractAction(){
		private static final long serialVersionUID = 2807908237298004936L;
		
		ViewEditGUI veg;
		
		public void actionPerformed(ActionEvent evt) {
			JTable table = (JTable)evt.getSource();
			int modelRow = Integer.valueOf(evt.getActionCommand());
			boolean isNew = false;
			
			if ((modelRow+1) == table.getRowCount()){
				isNew = true;
			}
			
			if(table.equals(employeeTable)){
				veg = new ViewEditGUI("Employee", modelRow, isNew);		
				veg.addWindowListener(new WindowListener(){
					@Override
					public void windowClosed(WindowEvent e) {
						refreshEmployee();
					}
					@Override
					public void windowClosing(WindowEvent e) {}
					@Override
					public void windowDeactivated(WindowEvent e) {}
					@Override
					public void windowDeiconified(WindowEvent e) {}
					@Override
					public void windowIconified(WindowEvent e) {}
					@Override
					public void windowOpened(WindowEvent e) {}
					@Override
					public void windowActivated(WindowEvent arg0) {}
				});				
			}
			
			else if(table.equals(foodTable)){
				veg = new ViewEditGUI("Food", modelRow, isNew);
				veg.addWindowListener(new WindowListener(){
					@Override
					public void windowClosed(WindowEvent e) {
						refreshFood();
					}
					@Override
					public void windowClosing(WindowEvent e) {}
					@Override
					public void windowDeactivated(WindowEvent e) {}
					@Override
					public void windowDeiconified(WindowEvent e) {}
					@Override
					public void windowIconified(WindowEvent e) {}
					@Override
					public void windowOpened(WindowEvent e) {}
					@Override
					public void windowActivated(WindowEvent arg0) {}
				});
			}
			
			else if(table.equals(ingredientTable)){
				veg = new ViewEditGUI("Ingredient", modelRow, isNew);	
				refreshIngredient();
			}
			
			else if(table.equals(equipmentTable)){
				veg = new ViewEditGUI("Equipment", modelRow, isNew);				
				refreshEquipment();
			}
			
			else{
				System.out.println("Something went very very wrong");
			}
		}
	};

	Action shifts = new AbstractAction(){
		private static final long serialVersionUID = -2586924078002381328L;

		public void actionPerformed(ActionEvent evt){
			JTable table = (JTable)evt.getSource();
			int modelRow = Integer.valueOf(evt.getActionCommand());
			
			if(table.getRowCount() == (modelRow+1)){}
			
			else{
				@SuppressWarnings("unused")
				ViewShiftGUI vsg = new ViewShiftGUI(modelRow);				
				refreshEmployee();
			}
		}
	};
	
}









