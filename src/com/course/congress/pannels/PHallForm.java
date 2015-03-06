package com.course.congress.pannels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.course.congress.datastorage.DataStorage;
import com.course.congress.jtablemodels.HallTableModel;
import com.course.congress.objects.Hall;

public class PHallForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image bg = new ImageIcon("img\\background.jpeg").getImage();
	
	private JLabel name;
	private JLabel capacity;
	private JLabel floor;

	private JTextField hallName;
	private JFormattedTextField hallCapacity;
	private JComboBox<Integer> hallFloor;

	private JButton addHall;
	private JButton removeHall;

	private JTable hallsTable;
	private HallTableModel hallTableModel;
	private JScrollPane scrollPane;
	
	private List<Hall> hallList;

	private static final Integer[] floors = { 1, 2, 3, 4, 5 };

	public PHallForm() {
		setLayout(null);
		
		name = new JLabel("Name");
		name.setBounds(10, 12, 40, 20);
		add(name);
		hallName = new JTextField();
		hallName.setBounds(48, 10, 130, 25);
		hallName.setVisible(true);
		add(hallName);

		capacity = new JLabel("Capacity");
		capacity.setBounds(200, 12, 50, 20);
		add(capacity);
		hallCapacity = new JFormattedTextField();
		hallCapacity.setValue(new Integer(150));
		hallCapacity.setColumns(10);
		hallCapacity.setBounds(250, 10, 80, 25);
		add(hallCapacity);

		floor = new JLabel("Floor");
		floor.setBounds(365, 12, 50, 20);
		add(floor);
		hallFloor = new JComboBox<Integer>(floors);
		hallFloor.setMaximumRowCount(5);
		hallFloor.setBounds(400, 10, 80, 27);
		add(hallFloor);

		// Creating editable jTable for halls with data from file
		// build the list
		hallList = new ArrayList<Hall>();
		Hall[] halls = DataStorage.getHalls();
		if(halls != null) {
			for (int i = 0; i < halls.length; i++) {
				hallList.add(halls[i]);
			}			
		}

		// create the model
		hallTableModel = new HallTableModel(hallList);
		// create the table
		hallsTable = new JTable(hallTableModel);
		hallsTable.setShowGrid(true);
		hallsTable.setGridColor(new Color(167,206,221));
		setTableAlignment(hallsTable);
		hallsTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(hallsTable);
		add(scrollPane).setBounds(10, 60, 470, 500);

		addHall = new JButton("Add Hall");
		addHall.setBounds(500, 10, 110, 40);
		add(addHall);

		removeHall = new JButton("Remove Hall");
		removeHall.setBounds(500, 520, 110, 40);
		removeHall.setEnabled(false);
		add(removeHall);
		
		// events and actions
		addHall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String hallNameText = hallName.getText();
				int capacityText = ((Number)hallCapacity.getValue()).intValue();
				int floorText = (Integer) hallFloor.getSelectedItem();
				//validate hall fields
				if (hallNameText == "" || hallNameText.isEmpty() || hallNameText == null) {
					JOptionPane.showMessageDialog(null, "Please enter hall name.");
				} else if (capacityText <= 0 ) {
					JOptionPane.showMessageDialog(null, "Hall capacity must be bigger than 0.");
				} else {
					int max = 0;
					if(DataStorage.getHalls() != null) {
						for(int i = 0; i < DataStorage.getHalls().length; i++) {
							if(DataStorage.getHalls()[i].getID() > max) {
								max = DataStorage.getHalls()[i].getID();
							}
						} 
					}
					Hall hall = new Hall(++max, hallNameText, capacityText, floorText);
					for (Hall existingHall : hallList) {
						if (existingHall.getName().equalsIgnoreCase(hallNameText) && existingHall.getCapacity() == capacityText && existingHall.getFloor() == floorText) {
							JOptionPane.showMessageDialog(null, "This hall already exists! Create new one!");
							return;
						} 
					}
					
					hallTableModel.addRow(hall);
					// add to data repository
					DataStorage.addNewHall(hall);							
					
					//clear fields
					hallName.setText("");
					hallCapacity.setValue(new Integer(150));
					hallFloor.setSelectedIndex(0);
				}
			}
		});
		
		removeHall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (hallsTable.getSelectedRow() != -1) {
					 DataStorage.removeHall(hallTableModel.getRowObject(hallsTable.getSelectedRow()).getID());
					 hallTableModel.removeRow(hallsTable.getSelectedRow());
			        } else {
			        	JOptionPane.showMessageDialog(null, "Please select hall from the table first.");
			        }
				removeHall.setEnabled(false);	
			}
		});
		
		hallsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
		        	removeHall.setEnabled(true);				
		        } 
			}
		});
	}

	private void setTableAlignment(JTable hallsTable2) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		hallsTable.setDefaultRenderer(Integer.class, centerRenderer);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}
