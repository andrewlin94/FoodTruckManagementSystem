/*
 * Team 10 Food Truck Management System
 * Wang, Ying Han	260588337
 * Mircic, Michael	260587925
 * Qian, Carl		260617009
 * Yang, Qing Zhou	260687570
 * Lin, Andrew 		270586060 
 */
package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

//import ca.mcgill.ecse321.foodtruckmanagersystem.view.DateLabelFormatter;
import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;

public class ViewShiftGUI extends JFrame{

	private String itemType;
	private JLabel errorMessage;
	private FoodTruckManagementSystemController ftmsc;
	private FoodTruckManager ftm;
	private int index;
	private JLabel dateLabel;
	private JLabel startTimeLabel;
	private JLabel endTimeLabel;
	private JTextField workDate;
	private JTextField workStartTime;
	private JTextField workEndTime;
	private int year, month, date, startHour, endHour, startMinute, endMinute;
	private String[] shiftsColumns = new String[] {"Work Date", "Work Start Time", "Work End Time", "Add/Remove Shift"};
	private HashMap<Integer, Employee> employeeMap;
	private JTable employeeTable;
	private Object[][] employees;
	private Object[][] shifts;
	private JTable shiftsTable;
	private JButton saveAndClose;
	private JButton remove;
	private JButton cancel;
	private String error;
	private boolean isNew;
	private JDatePickerImpl workDatePicker;
	private JLabel workDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel workStartTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel workEndTimeLabel;
	private JDatePickerImpl shiftDatePicker;
	

	public ViewShiftGUI(int index) {

		this.index = index;
		ftm = FoodTruckManager.getInstance();
		setTitle(ftm.getEmployee(index).getName()+"'s " + "Shifts");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	private void initComponents() {
		cancel = new JButton();
		cancel.setText("Quit without Saving");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});

		shiftsTable = new JTable();
		ftmsc = new FoodTruckManagementSystemController();
		shiftsWindow(ftm.getEmployee(index));
		
//		SqlDateModel model = new SqlDateModel();
//		Properties p = new Properties();
//		p.put("text.today", "Today");
//		p.put("text.month", "Month");
//		p.put("text.year", "Year");
//		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
//		shiftDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//
//		startTimeSpinner = new JSpinner (new SpinnerDateModel());
//		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
//		startTimeSpinner.setEditor(startTimeEditor); // will only show current time
//		startTimeLabel = new JLabel();
//		endTimeSpinner = new JSpinner(new SpinnerDateModel());
//		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
//		endTimeSpinner.setEditor(endTimeEditor); // will only show current time
//		endTimeLabel = new JLabel();
		pack();
	}

	private void shiftsWindow(Employee e) {
		dateLabel = new JLabel("Work date (YYYY/MM/DD):");
		startTimeLabel = new JLabel("Work start time (HH:MM):");
		endTimeLabel = new JLabel("Work end time (HH:MM):");

		ArrayList<String> workDates = new ArrayList<String>();
		ArrayList<String> workStartTimes = new ArrayList<String>();
		ArrayList<String> workEndTimes = new ArrayList<String>();

		Calendar c = Calendar.getInstance();
		for (int i = 0; i < e.numberOfWorkStartTime(); i++) {
			c.setTime(e.getWorkStartTime(i));
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH + 1);
			date = c.get(Calendar.DATE);

			startHour = c.get(Calendar.HOUR);
			startMinute = c.get(Calendar.MINUTE);
			workDates.add(year + "/" + month + "/" + date);
			workStartTimes.add(startHour + ":" + startMinute);
			c.setTime(e.getWorkEndTime(i));
			endHour = c.get(Calendar.HOUR);
			endMinute = c.get(Calendar.MINUTE);
			workEndTimes.add(endHour + ":" + endMinute);
		}

		workDate = new JTextField();
		workStartTime = new JTextField();
		workEndTime = new JTextField();

		employeeMap = new HashMap<Integer, Employee>();
		shiftsTable.removeAll();

		shifts = new Object[e.numberOfWorkStartTime()+1][4];
		int i = 0;
		for (i = 0; i < workDates.size(); i++) {
			shifts[i][0] = workDates.get(i);								
			shifts[i][1] = workStartTimes.get(i);
			shifts[i][2] = workEndTimes.get(i);
			shifts[i][3] = "Edit/Remove shift";
		}
		shifts[i][3] = "Add new shift";

		shiftsTable = new JTable(shifts, shiftsColumns);
//		@SuppressWarnings("unused")
//		ButtonColumn editColumn = new ButtonColumn(shiftsTable, edit, 3);
		add(new JScrollPane(shiftsTable));
	}
//	private void shiftWindow (Employee e) {
//		dateLabel = new JLabel("Work date:");
//		startTimeLabel = new JLabel("Work Start Time:");
//		endTimeLabel = new JLabel("Work End Time");
//		workDate = new JTextField();
//		workDate.setText("");
//		workStartTime = new JTextField();
//		workStartTime.setText("");
//		workStartTime = new JTextField();
//		workStartTime.setText("");
//
//		saveAndClose.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent evt){
//				error = null;
//				if(isNew){
//					try {
//						ftmsc.addShift(e, workStartTime, workEndTime);;
//					} catch (InvalidInputException e) {
//						error = e.getMessage();
//					}
//				}
//				else{
//					try {
//						ftmsc.editShift(e, oldStartTime, oldEndTime, newStartTime, newEndTime);
//					} catch (InvalidInputException e) {
//						error = e.getMessage();
//					}
//				}
//				if(error == null || error.length() == 0){
//					dispose();
//				}
//				errorMessage.setText(error);
//				pack();
//			}
//		});
//
//		remove.setText("Remove this equipment");
//		remove.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent evt){
//				ftmsc.removeIngredient(ftm.getIngredient(index));
//				dispose();
//			}
//		});
//
//		GroupLayout layout = new GroupLayout(getContentPane());
//		getContentPane().setLayout(layout);
//		layout.setAutoCreateGaps(true);
//		layout.setAutoCreateContainerGaps(true);
//		layout.setHorizontalGroup(
//				layout.createParallelGroup()
//				.addComponent(errorMessage)
//				.addGroup(layout.createSequentialGroup()
//						.addComponent(nameLabel)
//						.addComponent(name))
//				.addGroup(layout.createSequentialGroup()
//						.addComponent(quantityLabel)
//						.addComponent(quantity))
//				.addGroup(layout.createSequentialGroup()
//						.addComponent(saveAndClose)
//						.addComponent(remove)
//						.addComponent(cancel))
//				);
//
//		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {nameLabel, name, saveAndClose});
//
//		layout.setVerticalGroup(
//				layout.createSequentialGroup()
//				.addComponent(errorMessage)
//				.addGroup(layout.createParallelGroup()
//						.addComponent(nameLabel)
//						.addComponent(name))
//				.addGroup(layout.createParallelGroup()
//						.addComponent(quantityLabel)
//						.addComponent(quantity))
//				.addGroup(layout.createParallelGroup()
//						.addComponent(saveAndClose)
//						.addComponent(remove)
//						.addComponent(cancel))
//				);
//		pack();
//	}
//	Action edit = new AbstractAction(){
//		private static final long serialVersionUID = 2807908237298004936L;
//
//		@Override
//		public void actionPerformed(ActionEvent evt) {	
//			JTable table = (JTable)evt.getSource();
//			int modelRow = Integer.valueOf(evt.getActionCommand());
//			boolean isNew = false;
//		}
//	};
}