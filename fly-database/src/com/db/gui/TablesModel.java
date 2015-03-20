package com.db.gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.DefaultTableModel;

import com.db.stucture.Database;
import com.db.stucture.Table;

public class TablesModel extends DefaultTableModel implements Observer {
	private static final long serialVersionUID = -1352333392366435145L;

	@Override
	public void update(Observable subject, Object table) {
		if (getRowCount() < ((Database) subject).tables.size()) {
			addRow(new Object[] { ((Table) table).name });
		} else if (getRowCount() > ((Database) subject).tables.size()) {
			for (int row = 0; row < getRowCount(); row++) {
				if (((String) getValueAt(row, 0)).equals(((Table) table).name)) {
					removeRow(row);
				}
			}
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
