package com.example.pel.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.pel.dao.DaoListBook;
import com.example.pel.db.DB;
import com.example.pel.db.DBCreate;
import com.example.pel.entity.ListBook;

public class TestDaoListBook {
	private static DB db;
	private static DaoListBook daoListBook;

	@BeforeClass
	public static void before() {
		try {
			db = new DB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(), PropertiesDB.PASSWORD.toString());
			DBCreate.createDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString(), "test");
			db.query("USE test;");
			daoListBook = new DaoListBook(db);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void after() {
		try {
			DBCreate.deleteDB(PropertiesDB.URL.toString(), PropertiesDB.USER.toString(),
					PropertiesDB.PASSWORD.toString(), "test");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddBook() {
		try {
			daoListBook.addBook(new ListBook("name", 2016, "author", 11, "isbn", "description", "url", "status"));
			assertTrue(true);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testDeleteBook() {
		try {
			daoListBook.addBook(new ListBook("name", 2016, "author", 11, "isbn", "description", "url", "status"));
			daoListBook.deleteBook(3);
			assertTrue(true);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testUpdateBook() {
		try {
			ResultSet book = new DaoListBook(db).getBook(1);
			daoListBook.updateBook(new ListBook(1, "name", 2016, "author", 11, "isbn", "description", "url", "status"));
			ResultSet updatebook = new DaoListBook(db).getBook(1);
			assertTrue(!book.equals(updatebook));
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetListBook() {
		try {
			ResultSet listbook = new DaoListBook(db).getListBook();
			assertNotNull(listbook);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testGetBook() {
		try {
			ResultSet book = new DaoListBook(db).getBook(1);
			assertNotNull(book);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

}
