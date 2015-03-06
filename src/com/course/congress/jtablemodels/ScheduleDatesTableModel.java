package com.course.congress.jtablemodels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ScheduleDatesTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<String> dates;
	private final String[] columnNames = new String[] { " " };

	public ScheduleDatesTableModel(List<String> dates) {
		this.dates = dates;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public int getRowCount() {
		return dates.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int colNum) {
		switch (colNum) {
		case 0:
			return String.class;
		default:
			return super.getColumnClass(colNum);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String date = dates.get(rowIndex);
		if (0 == columnIndex) {
			return date;
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (0 == columnIndex) {
			dates.add(rowIndex, (String) aValue);
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

}
