package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import com.course.congress.datastorage.DataStorage;
import com.course.congress.jtablemodels.ScheduleDatesTableModel;
import com.course.congress.jtablemodels.ScheduleTableModel;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.utils.DateUtils;

public class PAddScheduleForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();
	
	private JLabel hallNameLabel;
	private JLabel dateLabel;
	private JLabel eventNameLabel;
	private JLabel scheduleMonthLabel;
	private JLabel usermessagesLabel;

	private JComboBox<String> hallCombo;
	private JComboBox<Serializable> eventCombo;

	private JButton prevMonth;
	private JButton nextMonth;
	private JButton buttonSave;	

	private JDatePickerImpl datePicker;
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;

	private JTable table;
	private JTable headerTable;
	private JScrollPane scrollPane;

	private int currentMonth;
	private int currentYear;
	private int currentDay;

	private Hall[] halls;
	private Event[] events;
	private HashMap<String, ArrayList<Event>> schedulesMap;
	
	private List<Event> eventsPerHall;
	private List<Event> existingEvent;

	public PAddScheduleForm() {
		setLayout(null);

		currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		events = DataStorage.getEvents();
		halls = DataStorage.getHalls();
		schedulesMap = DataStorage.getSchedule() != null ? DataStorage.getSchedule() : new HashMap<String, ArrayList<Event>>();
		existingEvent = new ArrayList<Event>();
		
		hallNameLabel = new JLabel("Hall");
		hallNameLabel.setBounds(10, 12, 30, 20);
		add(hallNameLabel);
		hallCombo = new JComboBox(new DefaultComboBoxModel(halls));
		hallCombo.insertItemAt("Please choose..", 0);
		hallCombo.setSelectedIndex(0);
		hallCombo.setMaximumRowCount(5);
		hallCombo.setBounds(38, 10, 140, 25);
		add(hallCombo);

		dateLabel = new JLabel("Date");
		dateLabel.setBounds(220, 12, 100, 20);
		add(dateLabel);
		dateModel = new UtilDateModel();
		dateModel.setDate(currentYear, currentMonth, currentDay);
		dateModel.setSelected(false);
		datePanel = new JDatePanelImpl(dateModel);
		datePanel.setEnabled(false);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.setEnabled(false);
		datePicker.setOpaque(false);
		datePicker.setBounds(250, 10, 130, 30);
		add(datePicker);

		eventNameLabel = new JLabel("Event");
		eventNameLabel.setBounds(430, 12, 50, 20);
		add(eventNameLabel);
		eventCombo = new JComboBox(new DefaultComboBoxModel(existingEvent.toArray()));
		eventCombo.setEnabled(false);
		eventCombo.setMaximumRowCount(3);
		eventCombo.setBounds(467, 10, 122, 25);
		add(eventCombo);
		
		buttonSave = new JButton("Edit");
		buttonSave.setBounds(600, 10, 100, 25);
		buttonSave.setEnabled(false);
		add(buttonSave);

		prevMonth = new JButton("<< " + DateUtils.getPrevMonth(currentMonth));
		prevMonth.setBounds(10, 70, 130, 20);
		add(prevMonth);

		scheduleMonthLabel = new JLabel("Schedule : "
				+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
		scheduleMonthLabel.setBounds(290, 70, 180, 20);
		add(scheduleMonthLabel);

		nextMonth = new JButton(DateUtils.getNextMonth(currentMonth) + " >>");
		nextMonth.setBounds(570, 70, 130, 20);
		add(nextMonth);
		
		String label = "";
		ArrayList<Event> assignedEvents = getAllAssignedEvents();
		for (int i = 0; i< events.length; i++){
			if(!assignedEvents.contains(events[i])){
				label +=  "<br>" + events[i].getName() + " - " + new SimpleDateFormat("dd MMM yyyy").format(events[i].getStartDate());
			}
		}
		usermessagesLabel = new JLabel("<html>Unassigned events:<br>"+label+"</html>");
		usermessagesLabel.setBounds(730, 10, 450, 200);
		add(usermessagesLabel);

		table = new JTable(new ScheduleTableModel(halls, schedulesMap,currentMonth,
				currentYear));
		table.setShowGrid(true);
		table.setGridColor(new Color(167,206,221));

		List<String> dates = new ArrayList<String>();
		for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
			dates.add("Date " + (i + 1));
		}
		headerTable = new JTable();
		changeHeaderTableProperties(new ScheduleDatesTableModel(dates));

		scrollPane = new JScrollPane(table);
		scrollPane.setRowHeaderView(headerTable);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		add(scrollPane).setBounds(10, 90, 690, 540);
		
		prevMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentMonth == 0) {
					currentMonth = 11;
					currentYear = currentYear - 1;
				} else {
					currentMonth = currentMonth - 1;
				}
				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
						currentYear));
			}

		});

		nextMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentMonth == 11) {
					currentMonth = 0;
					currentYear = currentYear + 1;
				} else {
					currentMonth = currentMonth + 1;
				}
				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates in the jTable
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
						currentYear));
			}
		});

		hallCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Hall selectedHall = null;
				try {
					selectedHall = (Hall) hallCombo.getSelectedItem();
				} catch (ClassCastException ce) {
					hallCombo.setSelectedIndex(1);;
					selectedHall = (Hall) hallCombo.getSelectedItem();
				}
				String hallName = selectedHall.getName();
				eventsPerHall = schedulesMap.get(hallName);
				
				//clear selected values for the other combos
				dateModel.setSelected(false);
				eventCombo.setSelectedIndex(-1);
				eventCombo.setEnabled(false);
				buttonSave.setEnabled(false);
			}
		});

		datePicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date newDate = (Date) datePicker.getModel().getValue();
				newDate = DateUtils.returnDateWithoutTime(newDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(newDate);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);

				currentMonth = month;
				currentYear = year;

				//if there are events assigned to the selected hall
				if (eventsPerHall != null) {
					for (Event event : eventsPerHall) {
						Date eventStartDate = event.getStartDate();
						//if there are events assigned to the hall on the selected date
						if (eventStartDate.equals(newDate)) {
							eventCombo.removeAllItems();
							eventCombo.addItem(event);
							eventCombo.setSelectedIndex(0);
							eventCombo.setEnabled(false);
							buttonSave.setText("Edit");
							buttonSave.setEnabled(true);
							break;
						} else {
							//Show possible events for the selected date and hall
							getPossibleEventsForDate(newDate);
						}
					}
				} else {
					getPossibleEventsForDate(newDate);
				}

				scheduleMonthLabel.setText("Schedule : "
						+ DateUtils.getCurrentMonth(currentMonth) + " " + currentYear);
				prevMonth.setText("<< " + DateUtils.getPrevMonth(currentMonth));
				nextMonth.setText(DateUtils.getNextMonth(currentMonth) + " >>");

				// change the displayed dates in the jTable
				List<String> dates = new ArrayList<String>();
				for (int i = 0; i < DateUtils.getDaysOfMonth(currentMonth, currentYear); i++) {
					dates.add("Date " + (i + 1));
				}
				changeHeaderTableProperties(new ScheduleDatesTableModel(dates));
				table.setModel(new ScheduleTableModel(halls, schedulesMap,currentMonth,
						currentYear));
			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) datePicker.getModel().getValue();
				selectedDate = DateUtils.returnDateWithoutTime(selectedDate);
				Hall selectedHall = (Hall) hallCombo.getSelectedItem();
				if (buttonSave.getText().equalsIgnoreCase("edit")) {
					getPossibleEventsForDate(selectedDate);
				} else if (buttonSave.getText().equalsIgnoreCase("save")) {
					String hallName = selectedHall.getName();
					Event selectedEvent = null;
					try {
						selectedEvent = (Event) eventCombo.getSelectedItem();
					} catch (ClassCastException ce) {
						//eventsPerHall = DataStorage.get
						for (Event event : eventsPerHall) {
							if(event.getStartDate().equals(selectedDate)){
								selectedEvent = event;
							}
						}
						DataStorage.removeSchedule(hallName, selectedEvent);
						System.out.println("Event detached.");
						table.setModel(new ScheduleTableModel(halls, schedulesMap, currentMonth,
								currentYear));
						ArrayList<Event> assignedEvents = getAllAssignedEvents();
						String label = "";
						for (int i = 0; i< events.length; i++){
							if(!assignedEvents.contains(events[i])){
								label +=  "<br>" + events[i].getName() + " - " + new SimpleDateFormat("dd MMM yyyy").format(events[i].getStartDate());
							}
						}
						usermessagesLabel.setText("<html>Unassigned events:<br>"+label+"</html>");
						return;
					}
					int duration = selectedEvent.getDuration() + 1;
					if (duration > 1) {
						int rowIndex = DateUtils.getDayIndex(selectedDate);
						int colIndex = Arrays.asList(halls).indexOf(selectedHall);
						while (duration > 1) {
							Object cellValue = table.getModel().getValueAt(rowIndex, colIndex);
							if (cellValue == null) {
								duration--;
								rowIndex++;
							} else {
								JOptionPane.showMessageDialog(null, "The hall is occupied for the selected period! Please choose another hall!");
								return;
							}
						} 
					}
					DataStorage.addNewSchedule(hallName, selectedEvent);
					table.setModel(new ScheduleTableModel(halls, DataStorage.getSchedule(), currentMonth, currentYear));
					String label = "";
					ArrayList<Event> assignedEvents = getAllAssignedEvents();
					for (int i = 0; i< events.length; i++){
						if(!assignedEvents.contains(events[i])){
							label +=  "<br>" + events[i].getName() + " - " + new SimpleDateFormat("dd MMM yyyy").format(events[i].getStartDate());
						}
					}
					usermessagesLabel.setText("<html>Unassigned events:<br>"+label+"</html>");
					eventCombo.setEnabled(false);
					buttonSave.setEnabled(false);
				}
			}
		});
	}

	private void changeHeaderTableProperties(
			ScheduleDatesTableModel scheduleDatesTableModel) {
		headerTable.setModel(scheduleDatesTableModel);
		headerTable.setShowGrid(false);
		headerTable.setOpaque(false);
		headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		headerTable.setPreferredScrollableViewportSize(new Dimension(60, 0));
		headerTable.getColumnModel().getColumn(0).setPreferredWidth(60);
		headerTable.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(JTable x,
							Object value, boolean isSelected, boolean hasFocus,
							int row, int column) {

						Component component = table
								.getTableHeader()
								.getDefaultRenderer()
								.getTableCellRendererComponent(table, value,
										false, false, -1, -2);
						((JLabel) component)
								.setHorizontalAlignment(SwingConstants.CENTER);
						return component;
					}
				});
	}
	
	/**
	 * Fills the combobox with the possible events for the selected date and hall.
	 * @param selectedDate
	 */
	private void getPossibleEventsForDate(Date selectedDate){
		Hall selectedHall = (Hall) hallCombo.getSelectedItem();
		String selectedHallName = selectedHall.getName();
		ArrayList<Event> allAssignedEvents = new ArrayList<Event>();
		allAssignedEvents = getAllAssignedEvents();
		eventCombo.removeAllItems();
		
		for (int i = 0; i < events.length; i++) {
			if (events[i].getStartDate().equals(selectedDate)) {
				if (!schedulesMap.isEmpty()) {
					for (Map.Entry<String, ArrayList<Event>> entry : schedulesMap.entrySet()) {
						String hallName = entry.getKey();
						ArrayList<Event> assignedEventsPerHall = entry.getValue();
						
						// if event is assigned to a hall, don't add it to the
						// possible events
						if (!allAssignedEvents.contains(events[i])) {
							eventCombo.addItem(events[i]);
							break;
						} else if (assignedEventsPerHall.contains(events[i])
								&& hallName.equalsIgnoreCase(selectedHallName)) {
							eventCombo.addItem(events[i]);
						}
					}
					
				} else {
					eventCombo.addItem(events[i]);
				}
			}
		}
		if (eventCombo.getItemCount() > 0) {
			eventCombo.insertItemAt("Please choose..", 0);
			eventCombo.setSelectedIndex(0);
			eventCombo.setEnabled(true);
			buttonSave.setText("Save");
			buttonSave.setEnabled(true);
		} else {
			eventCombo.insertItemAt("No events..", 0);
			eventCombo.setSelectedIndex(0);
			eventCombo.setEnabled(false);
			buttonSave.setEnabled(false);
		}
	}
	
	private ArrayList<Event> getAllAssignedEvents(){
		ArrayList<Event> allAssignedEvents = new ArrayList<Event>();
		if (!schedulesMap.isEmpty()) {
			for (Map.Entry<String, ArrayList<Event>> entry : schedulesMap.entrySet()) {
				ArrayList<Event> assignedEventsPerHall = entry.getValue();
				for (Event event : assignedEventsPerHall) {
					allAssignedEvents.add(event);
				}
			}
		}
		return allAssignedEvents;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
