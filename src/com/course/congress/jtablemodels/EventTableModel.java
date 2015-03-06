package com.course.congress.jtablemodels;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.course.congress.objects.Event;

/**
 * This class is used for validating data in events jTable.
 *
 */
public class EventTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private final List<Event> eventList;
	private final String[] columnNames = new String[] {"Name", "Duration (# of days)", "Start Date", "End Date", "Type", "Description", "Equipment", "Arrangement"};
	private final Class[] columnClass = new Class[] { String.class, Integer.class, Date.class, Date.class, String.class,  String.class, Boolean.class, Boolean.class};

	public EventTableModel(List<Event> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClass[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return eventList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Event row = eventList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getName();
		} else if (1 == columnIndex) {
			return (row.getDuration() + 1);
		} else if (2 == columnIndex) {
			return row.getStartDate();
		} else if (3 == columnIndex) {
			return row.getEndDate();
		} else if(4 == columnIndex){
			return row.getType();
		} else if(5 == columnIndex){
			return row.getDescription();
		} else if (6 == columnIndex){
			if(row.getEquipments() != null){
				return true;
			} else {
				return false;
			}
		} else if (7 == columnIndex){
			if(row.getArrangement() != null){
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex==1){
			return false;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Event row = eventList.get(rowIndex);
		if (0 == columnIndex) {
			row.setName((String) aValue);
		} else if (1 == columnIndex) {
			int duration = (Integer) aValue;
			row.setDuration(duration);
		} else if (2 == columnIndex) {
			row.setStartDate((Date) aValue);
		} else if (3 == columnIndex) {
			row.setEndDate((Date) aValue);
		} else if (4 == columnIndex){
			row.setType((String) aValue);
		} else if (5 == columnIndex){
			row.setDescription((String) aValue);
		}
		 fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addRow(Event event) {
		eventList.add(event);
		fireTableRowsInserted(eventList.size() - 1, eventList.size() - 1);
	}
	
	public void removeRow(int rowIndex) {
	    eventList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public Event getRowObject(int rowIndex) {
		return eventList.get(rowIndex);
	}
}
