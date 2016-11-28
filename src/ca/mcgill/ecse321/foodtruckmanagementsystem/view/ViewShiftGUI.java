package ca.mcgill.ecse321.foodtruckmanagementsystem.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;

public class ViewShiftGUI extends JFrame{
	
	private String itemType;
	private JButton cancel;
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
	private String[] shiftsColumns = new String[] {"Work Date", "Work Start Time", "Work End Time"};
	private HashMap<Integer, Employee> employeeMap;
	private JTable employeeTable;
	private Object[][] employees;
	private Object[][] shifts;
	private JTable shiftsTable;
	private JTabbedPane functions;
	
	public ViewShiftGUI(String item, int index) {
		this.itemType = item;
		this.index = index;
		setTitle(itemType + "Shifts");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		functions = new JTabbedPane();
		initComponents();
		setVisible(true);
	}
	
	private void initComponents() {
		cancel.setText("Quit without Saving");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		ftmsc = new FoodTruckManagementSystemController();
		ftm = FoodTruckManager.getInstance();
		shiftsWindow(ftm.getEmployee(index));
		add(functions);
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
		
		shifts = new Object[e.numberOfWorkStartTime()][3];
		for (int i = 0; i < workDates.size(); i++) {
			shifts[i][0] = workDates.get(i);								
			shifts[i][1] = workStartTimes.get(i);
			shifts[i][2] = workEndTimes.get(i);
		}
		
		shiftsTable = new JTable(shifts, shiftsColumns);
//		@SuppressWarnings("unused")
//		ButtonColumn editColumn = new ButtonColumn(employeeTable, edit, 2);
//		@SuppressWarnings("unused")
//		ButtonColumn shiftColumn = new ButtonColumn(employeeTable, shifts, 1);
		functions.setComponentAt(0, new JScrollPane(shiftsTable));
	}
}
