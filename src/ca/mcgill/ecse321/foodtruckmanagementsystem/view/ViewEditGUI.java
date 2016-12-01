/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.FoodTruckManagementSystemController;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Food;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.Ingredient;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class ViewEditGUI extends JFrame{

	private static final long serialVersionUID = 7922212941394523180L;
	
	private JLabel errorMessage;
	private String error = null;
	
	private JButton saveAndClose;
	private JButton remove;
	private JButton cancel;
	
	private JLabel nameLabel;
	private JTextField name;
	private JLabel quantityLabel;
	private JTextField quantity;
	private JLabel priceLabel;
	private JTextField price;
	
	FoodTruckManager ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
	FoodTruckManagementSystemController ftmsc; 
	private String itemType;
	private int index;
	private boolean isNew;
	
	/**
	 * Constructor takes String identifying the item being edited, its index in the saved data,
	 * and a boolean isNew stating whether the item is a new addition.
	 * 
	 * @param item
	 * @param index
	 * @param isNew
	 */
	public ViewEditGUI(String item, int index, boolean isNew){
		this.itemType = item;
		this.index = index;
		this.isNew = isNew;
		
		if(isNew){
			setTitle("Add " + itemType);
		}
		else{
			setTitle("Edit " + itemType);
		}
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		initComponents();		
	}

	/**
	 * Initiate new window components
	 */
	private void initComponents() {
		
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		remove = new JButton();
		saveAndClose = new JButton();
		saveAndClose.setText("Save and Quit");
		cancel = new JButton();
		cancel.setText("Quit without Saving");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		ftmsc = new FoodTruckManagementSystemController();
		
		if(isNew){
			if(itemType.equals("Food")){
				foodWindow(new Food("", 0, 0));
			}
			else if(itemType.equals("Employee")){
				employeeWindow(new Employee(""));
			}
			else if(itemType.equals("Ingredient")){
				ingredientWindow(new Ingredient("", 0));
			}
			else{	//Equipment
				equipmentWindow(new Equipment("", 0));
			}
			
			
		}
		
		else{				
			if(itemType.equals("Food")){
				foodWindow(ftm.getFood(index));
			}
			else if(itemType.equals("Employee")){
				employeeWindow(ftm.getEmployee(index));
			}
			else if(itemType.equals("Ingredient")){
				ingredientWindow(ftm.getIngredient(index));
			}
			else{	//Equipment
				equipmentWindow(ftm.getEquipment(index));
			}			
		}		
	}
	
	/**
	 * Opens up the window to edit a food item.
	 * 
	 * @param food
	 */
	private void foodWindow(Food food){
		nameLabel = new JLabel("Food Name (No special characters):");
		quantityLabel = new JLabel("Add number of orders (1, 2, 3, ...):");
		priceLabel = new JLabel("Price ($0.00):");
		
		name = new JTextField();
		name.setText(food.getName());
		quantity = new JTextField();
		quantity.setText("0");
		price = new JTextField();
		price.setText(Double.toString(food.getPrice()));
		
		saveAndClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				if(isNew){
					try {
						ftmsc.createFood(name.getText(), Double.parseDouble(price.getText()), 
								Integer.parseInt(quantity.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				else{
					try {
						ftmsc.editOrder(ftm.getFood(index), Integer.parseInt(quantity.getText()));
						ftmsc.editFood(ftm.getFood(index), name.getText(), 
								Double.parseDouble(price.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				if(error == null || error.length() == 0){
					dispose();
				}
				errorMessage.setText(error);
				//resize the window in case an error message needs to be shown
				pack();
			}
		});
		
		remove.setText("Remove this item");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				//checks that user isn't trying to remove an item that doesn't exist
				if(!isNew){
					ftmsc.removeFood(ftm.getFood(index));
					dispose();
				}
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createSequentialGroup()
						.addComponent(priceLabel)
						.addComponent(price))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createSequentialGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {saveAndClose, remove, cancel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createParallelGroup()
						.addComponent(priceLabel)
						.addComponent(price))
				.addGroup(layout.createParallelGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createParallelGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		pack();
	}
	
	/**
	 * Create a window to edit a specified employee (or create a new one)
	 * @param employee
	 */
	private void employeeWindow(Employee employee){
		nameLabel = new JLabel("Employee Name (No special characters):");		
		name = new JTextField();
		name.setText(employee.getName());
		
		saveAndClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				if(isNew){
					try {
						ftmsc.createEmployee(name.getText());
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				else{
					try {
						ftmsc.editEmployeeName(ftm.getEmployee(index), name.getText());
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				if(error == null || error.length() == 0){
					dispose();
				}
				errorMessage.setText(error);
				//resize the window in case an error message needs to be shown
				pack();
			}
		});
		
		remove.setText("Remove this employee's record");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				//checks that the user isn't trying to delete a non-existant employee
				if(!isNew){
					ftmsc.removeEmployee(ftm.getEmployee(index));
					dispose();
				}
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createSequentialGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {saveAndClose, remove, cancel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createParallelGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		pack();
	}
	
	/**
	 * Create a window to edit a specified ingredient
	 * @param ingredient
	 */
	private void ingredientWindow(Ingredient ingredient){
		nameLabel = new JLabel("Ingredient Name (No special characters):");
		quantityLabel = new JLabel("Quantity:");
		
		name = new JTextField();
		name.setText(ingredient.getName());
		quantity = new JTextField();
		quantity.setText(""+ingredient.getQuantity());
		
		saveAndClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				if(isNew){
					try {
						ftmsc.createIngredient(name.getText(), Integer.parseInt(quantity.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				else{
					try {
						ftmsc.editIngredient(ftm.getIngredient(index), name.getText(), 
								Integer.parseInt(quantity.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				if(error == null || error.length() == 0){
					dispose();
				}
				errorMessage.setText(error);
				//resize the window in case an error message needs to be shown
				pack();
			}
		});
		
		remove.setText("Remove this ingredient");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				//checks that the ingredient exists before deleting it
				if(!isNew){
					ftmsc.removeIngredient(ftm.getIngredient(index));
					dispose();
				}
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createSequentialGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {saveAndClose, remove, cancel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createParallelGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createParallelGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		pack();
	}
	
	/**
	 * Create a window to edit a specified equipment item.
	 * @param equipment
	 */
	private void equipmentWindow(Equipment equipment){
		nameLabel = new JLabel("Ingredient Name (No special characters):");
		quantityLabel = new JLabel("Quantity:");
		
		name = new JTextField();
		name.setText(equipment.getName());
		quantity = new JTextField();
		quantity.setText(""+equipment.getQuantity());
		
		saveAndClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				if(isNew){
					try {
						ftmsc.createEquipment(name.getText(), Integer.parseInt(quantity.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				else{
					try {
						ftmsc.editEquipment(ftm.getEquipment(index), name.getText(), 
								Integer.parseInt(quantity.getText()));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				if(error == null || error.length() == 0){
					dispose();
				}
				errorMessage.setText(error);
				//resize the window in case an error message needs to be shown
				pack();
			}
		});
		
		remove.setText("Remove this equipment");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!isNew){
					ftmsc.removeEquipment(ftm.getEquipment(index));
					dispose();
				}
			}
		});
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createSequentialGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {saveAndClose, remove, cancel});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(nameLabel)
						.addComponent(name))
				.addGroup(layout.createParallelGroup()
						.addComponent(quantityLabel)
						.addComponent(quantity))
				.addGroup(layout.createParallelGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);
		pack();
	}
}