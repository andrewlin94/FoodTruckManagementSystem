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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.foodtruckmanagementsystem.controller.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.model.*;
import ca.mcgill.ecse321.foodtruckmanagementsystem.persistence.PersistenceXStream;

public class ViewShiftGUI extends JFrame{

	private static final long serialVersionUID = 4803035983576092263L;

	private Employee employee;
	private JLabel errorMessage;
	private FoodTruckManagementSystemController ftmsc;
	private FoodTruckManager ftm;
	private int index;
	private int shiftIndex;
	private int year, month, date, startHour, endHour, startMinute, endMinute;
	private String[] shiftsColumns = new String[] {"Work Date", "Work Start Time", "Work End Time", "Add/Remove Shift"};
	private Object[][] shifts;
	private JTable shiftsTable;
	private JButton saveAndClose;
	private JButton remove;
	private JButton cancel;
	private String error = null;
	private boolean newShift;
	private JDatePickerImpl workDatePicker;
	private JLabel workDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel workStartTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel workEndTimeLabel;
	private JFrame editShiftFrame;


	public ViewShiftGUI(int index) {
		this.index = index;
		ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		this.employee = ftm.getEmployee(index);
		setTitle(employee.getName()+"'s " + "Shifts");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	private void initComponents() {
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		ftmsc = new FoodTruckManagementSystemController();
		shiftsWindow(employee);
		pack();
	}

	private void shiftsWindow(Employee e) {
		error = null;

		ArrayList<String> workDates = new ArrayList<String>();
		ArrayList<String> workStartTimes = new ArrayList<String>();
		ArrayList<String> workEndTimes = new ArrayList<String>();

		Calendar c = Calendar.getInstance();
		for (int i = 0; i < e.numberOfWorkStartTime(); i++) {
			c.setTime(e.getWorkStartTime(i));
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
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
		@SuppressWarnings("unused")
		ButtonColumn editColumn = new ButtonColumn(shiftsTable, edit, 3);

		this.add(new JScrollPane(shiftsTable), 0);
		this.validate();
	}

	private void editShift(int shift, boolean isNew) {
		this.shiftIndex = shift;
		this.newShift = isNew;
		this.employee = ftm.getEmployee(index);

		editShiftFrame = new JFrame();
		editShiftFrame.setTitle("Editing " + employee.getName()+"'s " + "shift");

		workDateLabel = new JLabel("Work date:");
		workStartTimeLabel = new JLabel("Work start time:");
		workEndTimeLabel = new JLabel("Work end time:");

		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		workDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		startTimeSpinner = new JSpinner(new SpinnerDateModel());		
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(startTimeEditor);
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(endTimeEditor);

		cancel = new JButton();
		cancel.setText("Quit without Saving");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				editShiftFrame.dispose();
			}
		});

		saveAndClose = new JButton();
		saveAndClose.setText("Save and Quit");
		saveAndClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				error = null;
				int year = workDatePicker.getModel().getYear();
				int month = workDatePicker.getModel().getMonth() + 1;
				int date = workDatePicker.getModel().getDay();
				Calendar c = Calendar.getInstance();
				c.setTime(((Date)startTimeSpinner.getValue()));
				int startHour = c.get(Calendar.HOUR_OF_DAY);
				int startMin = c.get(Calendar.MINUTE);
				c.setTime(((Date)endTimeSpinner.getValue()));
				int endHour = c.get(Calendar.HOUR_OF_DAY);
				int endMin = c.get(Calendar.MINUTE);
				c.set(year, month, date, startHour, startMin);
				Date start = new Date(c.getTimeInMillis());
				c.set(year, month, date, endHour, endMin);
				Date end = new Date(c.getTimeInMillis());
				if(newShift) {
					try{
						ftmsc.addShift(employee, start, end);
					} catch (InvalidInputException e){
						error = e.getMessage();
					}
				}

				else{
					try {
						ftmsc.editShift(employee, employee.getWorkStartTime(shiftIndex), 
								employee.getWorkEndTime(shiftIndex), start, end);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				if(error == null || error.length() == 0){
					editShiftFrame.dispose();
				}
				errorMessage.setText(error);
				editShiftFrame.pack();
			}
		});

		remove = new JButton();
		remove.setText("Remove this shift");
		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!newShift){
					ftmsc.removeShift(employee, employee.getWorkStartTime(shiftIndex), 
							employee.getWorkEndTime(shiftIndex));
					editShiftFrame.dispose();
				}
			}
		});

		GroupLayout layout = new GroupLayout(editShiftFrame.getContentPane());
		editShiftFrame.getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addComponent(workDateLabel)
						.addComponent(workDatePicker))
				.addGroup(layout.createSequentialGroup()
						.addComponent(workStartTimeLabel)
						.addComponent(startTimeSpinner))
				.addGroup(layout.createSequentialGroup()
						.addComponent(workEndTimeLabel)
						.addComponent(endTimeSpinner))
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
						.addComponent(workDateLabel)
						.addComponent(workDatePicker))
				.addGroup(layout.createParallelGroup()
						.addComponent(workStartTimeLabel)
						.addComponent(startTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(workEndTimeLabel)
						.addComponent(endTimeSpinner))
				.addGroup(layout.createParallelGroup()
						.addComponent(saveAndClose)
						.addComponent(remove)
						.addComponent(cancel))
				);		
		editShiftFrame.pack();
		editShiftFrame.setVisible(true);
	}
	Action edit = new AbstractAction(){
		private static final long serialVersionUID = 2807908237298004936L;

		@Override
		public void actionPerformed(ActionEvent evt) {	
			JTable table = (JTable)evt.getSource();
			int modelRow = Integer.valueOf(evt.getActionCommand());
			boolean isNew = false;

			if((modelRow+1) == table.getRowCount()){
				isNew = true;
			}

			editShift(modelRow, isNew);
			editShiftFrame.addWindowListener(new WindowListener(){
				@Override
				public void windowClosed(WindowEvent e) {
					ftm = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
					shiftsWindow(ftm.getEmployee(index));
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
	};
}