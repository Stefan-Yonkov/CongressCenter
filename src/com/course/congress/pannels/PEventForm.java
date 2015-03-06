package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import org.jdesktop.swingx.table.DatePickerCellEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import com.course.congress.controlers.DataFlowControler;
import com.course.congress.controlers.PPanelControler;
import com.course.congress.datastorage.DataStorage;
import com.course.congress.jtablemodels.DateValueRenderer;
import com.course.congress.jtablemodels.EventTableModel;
import com.course.congress.objects.Event;
import com.course.congress.utils.DateUtils;

public class PEventForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();
	
	private JScrollPane scrollPane;

	private JLabel name;
	private JLabel startDate;
	private JLabel endDate;
	private JLabel type;
	private JLabel description;

	private JTextField eventName;
	private JTextField eventType;
	private JTextArea eventDescription;

	private JButton addEvent;
	private JButton removeEvent;
	private JButton equipment;
	private JButton hallArrangement;
	
	private JDatePickerImpl datePickerStartDate;
	private UtilDateModel dateModelStartDate;
	private JDatePanelImpl datePanelStartDate;
	
	private JDatePickerImpl datePickerEndDate;
	private UtilDateModel dateModelEndDate; 
	private JDatePanelImpl datePanelEndDate; 

	private JTable eventsTable;
	private EventTableModel eventTableModel;
	
	private int currentMonth;
	private int currentYear;
	private int currentDay;

	public PEventForm() {
		final PEventForm currentPanel = this;
		setLayout(null);
		
		Calendar cal = Calendar.getInstance();
		currentMonth = cal.get(Calendar.MONTH);
		currentYear = cal.get(Calendar.YEAR);
		currentDay = cal.get(Calendar.DAY_OF_MONTH);

		// name
		name = new JLabel("Name");
		name.setBounds(10, 10, 40, 20);
		add(name);
		eventName = new JTextField();
		eventName.setBounds(50, 10, 130, 25);
		add(eventName);

		startDate = new JLabel("Start Date");
		startDate.setBounds(195, 12, 60, 20);
		add(startDate);
		dateModelStartDate = new UtilDateModel();
		dateModelStartDate.setDate(currentYear, currentMonth, currentDay);
		dateModelStartDate.setSelected(true);
		datePanelStartDate = new JDatePanelImpl(dateModelStartDate);
		datePickerStartDate = new JDatePickerImpl(datePanelStartDate);
		datePickerStartDate.setOpaque(false);
		datePickerStartDate.setBounds(255, 10, 130, 30);
		add(datePickerStartDate);

		endDate = new JLabel("End Date");
		endDate.setBounds(395, 12, 60, 20);
		add(endDate);
		dateModelEndDate = new UtilDateModel();
		dateModelEndDate.setDate(currentYear, currentMonth, currentDay);
		dateModelEndDate.setSelected(true);
		datePanelEndDate = new JDatePanelImpl(dateModelEndDate);
		datePickerEndDate = new JDatePickerImpl(datePanelEndDate);
		datePickerEndDate.setOpaque(false);
		datePickerEndDate.setBounds(450, 10, 130, 30);
		add(datePickerEndDate);

		type = new JLabel("Type");
		type.setBounds(590, 12, 40, 20);
		add(type);
		eventType = new JTextField();
		eventType.setBounds(620, 10, 145, 25);
		add(eventType);

		description = new JLabel("Description");
		description.setBounds(10, 40, 70, 20);
		add(description);
		eventDescription = new JTextArea();
		JScrollPane sp = new JScrollPane(eventDescription); 
		sp.setBounds(80, 40, 685, 40);
		add(sp);

		addEvent = new JButton("Add Event");
		addEvent.setBounds(800, 10, 120, 40);
		add(addEvent);

		removeEvent = new JButton("Remove Event");
		removeEvent.setBounds(800, 460, 120, 40);
		removeEvent.setEnabled(false);
		add(removeEvent);

		equipment = new JButton("Add Equipment");
		equipment.setBounds(800, 100, 120, 40);
		equipment.setEnabled(false);
		add(equipment);

		hallArrangement = new JButton("Arangement");
		hallArrangement.setBounds(800, 150, 120, 40);
		hallArrangement.setEnabled(false);
		add(hallArrangement);

		// build the list
		List<Event> eventList = new ArrayList<Event>();
		
		Event[] events = DataStorage.getEvents();
		if(events != null) {
			for (int i = 0; i < events.length; i++) {
				eventList.add(events[i]);
			}
		}
		
		// create the model
		eventTableModel = new EventTableModel(eventList);
		// create the table
		eventsTable = new JTable(eventTableModel);
		eventsTable.setShowGrid(true);
		eventsTable.setGridColor(new Color(167,206,221));
		eventsTable.setColumnSelectionAllowed(false);
		eventsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setTableAlignment(eventsTable);
		eventsTable.setFillsViewportHeight(true);
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		eventsTable.setDefaultRenderer(Date.class, new DateValueRenderer());
		eventsTable.setDefaultEditor(Date.class, new DatePickerCellEditor(df));
		scrollPane = new JScrollPane(eventsTable);
		add(scrollPane).setBounds(10, 100, 755, 400);

		// events and actions
		equipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PEquipment pEquipment = new PEquipment();
				PPanelControler.showNextPanel(pEquipment);
			}
		});
		
		addEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eventNameText = eventName.getText();
				Date startDateText = (Date) datePickerStartDate.getModel().getValue();
				startDateText = DateUtils.returnDateWithoutTime(startDateText);
				Date endDateText = (Date) datePickerEndDate.getModel().getValue();
				endDateText = DateUtils.returnDateWithoutTime(endDateText);
				int durationText = (int) ((endDateText.getTime() - startDateText.getTime()) / (1000 * 60 * 60 * 24));
				String typeText = eventType.getText();
				String description = eventDescription.getText();
				//checking if end date is after start date
				if (endDateText.before(startDateText)){
					JOptionPane.showMessageDialog(null, "End date must be after start date!");
				} else if (eventNameText=="" || eventNameText == null || eventNameText.isEmpty()){
					JOptionPane.showMessageDialog(null, "Please enter event name!");
				} else if (typeText == "" || typeText == null || typeText.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter event type!");
				} else {
					int maxID = 0;
					Event event;
					if(DataStorage.getEvents() == null) {
						event = new Event(1, eventNameText, durationText, startDateText, endDateText, typeText, description, null, null, null);
					} else {
						for (int i = 1; i < DataStorage.getEvents().length; i++) {
							if(DataStorage.getEvents()[i].getID() > DataStorage.getEvents()[maxID].getID()) {
								maxID = i;
							}
						}						
						event = new Event(DataStorage.getEvents()[maxID].getID() + 1, eventNameText, durationText, startDateText, endDateText, typeText, description, null, null, null);
					}
					eventTableModel.addRow(event);
					//add new event in data storage
					DataStorage.addNewEvent(event);
					
					eventName.setText("");
					dateModelStartDate.setDate(currentYear, currentMonth, currentDay);
					dateModelEndDate.setDate(currentYear, currentMonth, currentDay);
					eventType.setText("");
					eventDescription.setText("");
				}
			}

		});
		
		removeEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check for selected row first
		        if (eventsTable.getSelectedRow() != -1) {
		        	DataStorage.removeEvent(eventTableModel.getRowObject(eventsTable.getSelectedRow()).getID());
		        	eventTableModel.removeRow(eventsTable.getSelectedRow());
		        } 
		        removeEvent.setEnabled(false);	
		        equipment.setEnabled(false);
		        hallArrangement.setEnabled(false);
		        currentPanel.repaint();
			}
		});
		
		eventsTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					System.out.println("Cell "
							+ e.getFirstRow()
							+ ", "
							+ e.getColumn()
							+ " changed. The new value: "
							+ eventsTable.getModel().getValueAt(
									e.getFirstRow(), e.getColumn()));
					int row = e.getFirstRow();
					int column = e.getColumn();
					TableModel model = eventsTable.getModel();
					if (column == 2 || column == 3) {
						if (eventsTable.getModel().getValueAt(e.getFirstRow(), e.getColumn()) != null) {
							Date startDate = ((Date) model.getValueAt(row, 2));
							Date endDate = ((Date) model.getValueAt(row, 3));
							int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
							if (days < 0 && column == 3) {
								//JOptionPane.showMessageDialog(null,	"End date must be after start date!");
								Date dateToSet = (Date) model.getValueAt(row, 2);
								model.setValueAt(dateToSet, row, column);
							} else if (days < 0 && column == 2) {
								//JOptionPane.showMessageDialog(null, "End date must be after start date!");
								Date dateToSet = (Date) model.getValueAt(row, 3);
								model.setValueAt(dateToSet, row, column);
							} else {
								model.setValueAt(days, row, 1);
							}
						} else {
							JOptionPane.showMessageDialog(null, "This value can not be empty!");
							model.setValueAt(datePickerStartDate.getModel().getValue(), row, column);
						}
					}
				}
			}
		});
		
		eventsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					
		        	removeEvent.setEnabled(true);
		        	equipment.setEnabled(true);
		        	hallArrangement.setEnabled(true);
		        	if(DataStorage.getEvents() != null) {
		        		for(int i=0; i<DataStorage.getEvents().length; i++) {
		        			if(DataStorage.getEvents()[i].getID() == eventTableModel.getRowObject(((DefaultListSelectionModel)e.getSource()).getLeadSelectionIndex()).getID())  {
		        				DataFlowControler.setCurrentEvent(DataStorage.getEvents()[i]);	
		        				break;
		        			}
		        		}		        		
		        	}
		        } 
			}
		});
		
		hallArrangement.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (DataFlowControler.getCurrentEvent() != null) {
					PArangement arangement = new PArangement();
					PPanelControler.showNextPanel(arangement);
				}
			}
		});
	}
	
	/**
	 * Sets alignment to the headers and the content in each cell.
	 * @param table
	 */
	public void setTableAlignment(JTable table) {
		// table content alignment
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		//sets the alignment for integer columns only
		table.setDefaultRenderer(Integer.class, centerRenderer);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
	
}
