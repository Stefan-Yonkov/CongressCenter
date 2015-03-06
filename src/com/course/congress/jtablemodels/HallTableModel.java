package com.course.congress.jtablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.course.congress.objects.Hall;

/**
 * Manages jTable of Hall
 */
public class HallTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private final List<Hall> hallList;
	private final String[] columnNames = new String[] {"Name", "Capacity", "Floor"};
	private final Class[] columnClass = new Class[] { String.class, Integer.class, Integer.class};
	
	public HallTableModel(List<Hall> hallList) {
		this.hallList = hallList;
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
		return hallList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Hall row = hallList.get(rowIndex);
		if (0 == columnIndex) {
			return row.getName();
		} else if (1 == columnIndex) {
			return row.getCapacity();
		} else if (2 == columnIndex) {
			return row.getFloor();
		} 
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Hall row = hallList.get(rowIndex);
		if (0 == columnIndex) {
			row.setName((String) aValue);
		} else if (1 == columnIndex) {
			row.setCapacity((Integer) aValue);
		} else if (2 == columnIndex) {
			row.setFloor((Integer) aValue);
		} 
		 fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addRow(Hall hall) {
		hallList.add(hall);
		fireTableRowsInserted(hallList.size() - 1, hallList.size() - 1);
	}
	
	public void removeRow(int rowIndex) {
		hallList.remove(rowIndex);
	    fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public Hall getRowObject(int i) {
		return hallList.get(i);
	}

}
