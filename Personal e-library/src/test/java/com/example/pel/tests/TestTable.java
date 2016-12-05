package com.example.pel.tests;

import java.sql.SQLException;

import org.junit.Test;

import com.example.pel.dao.DaoListBook;
import com.example.pel.db.DB;
import com.example.pel.table.Table;

import junit.framework.TestCase;

public class TestTable extends TestCase {

	@Test
	public void testTable() {
		try {
			DB db = new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString());
			db.update("USE PELibrary");
			Table table = new Table(new DaoListBook(db).getListBook());
			assertNotNull(table);
		} catch (SQLException | ClassNotFoundException e) {
			fail();
		}
	}

	@Test
	public void testIsCellEditable() {
		try {
			DB db = new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString());
			db.update("USE PELibrary");
			Table table = new Table(new DaoListBook(db).getListBook());
			assertFalse(table.isCellEditable(1, 1));
		} catch (SQLException | ClassNotFoundException e) {
			fail();
		}
	}

}
