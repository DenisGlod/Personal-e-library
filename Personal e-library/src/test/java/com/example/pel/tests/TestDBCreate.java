package com.example.pel.tests;

import java.sql.SQLException;

import org.junit.Test;

import com.example.pel.db.DBCreate;

import junit.framework.TestCase;

public class TestDBCreate extends TestCase {

	@Test
	public void testCreateDB() {
		try {
			DBCreate.createDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString(), "test");
			assertTrue(true);
		} catch (SQLException | ClassNotFoundException e) {
			fail();
		} finally {
			try {
				DBCreate.deleteDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
						PropertiesDB.PASSWORD.toString(), "test");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testDeleteDB() {
		try {
			DBCreate.createDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString(), "test");
			DBCreate.deleteDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString(), "test");
			assertTrue(true);
		} catch (SQLException | ClassNotFoundException e) {
			fail();
		}
	}

}
