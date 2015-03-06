package com.course.congress.jtablemodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.utils.DateUtils;

public class ScheduleTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private int currentMonth;
	private int currentYear;
	private int daysInMonth;
	private HashMap<String, ArrayList<Event>> schedulesMap;
	private Hall[] halls;
	private final String[] columnNames;

	public ScheduleTableModel(Hall[] halls, HashMap<String, ArrayList<Event>> schedulesMap, int currentMonth,
			int currentYear) {
		this.halls = halls;
		this.schedulesMap = schedulesMap;
		this.currentMonth = currentMonth;
		this.currentYear = currentYear;
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, 1);
		this.daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		this.columnNames = new String[halls.length];
	}

	@Override
	public String getColumnName(int column) {
		for (int i = 0; i < halls.length; i++) {
			columnNames[i] = halls[i].getName();
		}
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.daysInMonth;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Event> eventsPerHall = schedulesMap.get(columnNames[columnIndex]);
		if (eventsPerHall != null) {
			for (int i = 0; i <= this.daysInMonth; i++) {
				if(rowIndex == i){
					for (Event event : eventsPerHall) {
						Date eventStartDate = event.getStartDate();
						Date eventEndDate = event.getEndDate();
						Date currentCalendarDate = DateUtils.getDateByDayMonthYear((rowIndex + 1), currentMonth, currentYear);
						
						int dateMarginStart = DateUtils.returnDateWithoutTime(eventStartDate).compareTo(DateUtils.returnDateWithoutTime(currentCalendarDate));
						int dateMarginEnd = DateUtils.returnDateWithoutTime(eventEndDate).compareTo(DateUtils.returnDateWithoutTime(currentCalendarDate));
						
						if (dateMarginStart <= 0 && dateMarginEnd >= 0) {
							return event.getName();
						}
					}
				} 
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		fireTableCellUpdated(rowIndex, columnIndex);
	}

}
