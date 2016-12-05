package com.example.pel.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class Table extends JTable {
	private static final long serialVersionUID = 1L;

	public Table(ResultSet rs) throws SQLException {
		DefaultTableModel dtm = new DefaultTableModel();
		ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			dtm.addColumn(rsmd.getColumnName(i));
		}
		while (rs.next()) {
			Vector<String> vector = new Vector<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				vector.add(rs.getString(i));
			}
			dtm.addRow(vector);
		}
		setModel(dtm);
		setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
		getTableHeader().setReorderingAllowed(false);
		setAutoCreateRowSorter(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
