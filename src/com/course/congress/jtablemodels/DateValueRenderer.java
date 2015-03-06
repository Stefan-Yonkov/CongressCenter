package com.course.congress.jtablemodels;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Rendering dates in jTable.
 */
public class DateValueRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof Date) {
			value = df.format(value);
		}
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}
}