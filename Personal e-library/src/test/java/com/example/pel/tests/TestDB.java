package com.example.pel.tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.example.pel.db.DB;
import com.mysql.jdbc.Connection;

public class TestDB {

	@Test
	public void testGetCnAndDB() {
		try {
			DB db = new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString());
			Connection connection = db.getCn();
			assertNotNull(connection);
		} catch (ClassNotFoundException | SQLException e) {
			fail("Exception!");
		}
	}

	@Test
	public void testUpdate() {
		try {
			new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString())
					.update("SHOW DATABASES;");
			assertTrue(true);
		} catch (ClassNotFoundException | SQLException e) {
			fail("Exception!");
		}
	}

	@Test
	public void testQuery() {
		try {
			ResultSet resultSet = new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString()).query("SHOW DATABASES;");
			assertNotNull(resultSet);
		} catch (ClassNotFoundException | SQLException e) {
			fail("Exception!");
		}
	}

	@Test
	public void testClose() {
		try {
			new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString()).close();
			assertTrue(true);
		} catch (ClassNotFoundException | SQLException e) {
			fail("Exception!");
		}
	}

}
