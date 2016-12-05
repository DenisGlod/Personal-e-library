package com.example.pel.tests;

import org.junit.Test;

import com.example.pel.entity.ListBook;

import junit.framework.TestCase;

public class TestListBook extends TestCase {

	@Test
	public void testSet() {
		ListBook listBook = new ListBook(1, "name", 2016, "author", 11, "isbn", "description", "url", "status");
		listBook.setId(2);
		listBook.setName("name2");
		listBook.setYear(2012);
		listBook.setAuthor("author2");
		listBook.setPages(12);
		listBook.setIsbn("isbn2");
		listBook.setDescription("description2");
		listBook.setUrl("url2");
		listBook.setStatus("status2");
		if (listBook.getId() == 2 && listBook.getName().equals("name2") && listBook.getYear() == 2012
				&& listBook.getAuthor().equals("author2") && listBook.getPages() == 12
				&& listBook.getIsbn().equals("isbn2") && listBook.getDescription().equals("description2")
				&& listBook.getUrl().equals("url2") && listBook.getStatus().equals("status2")) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	@Test
	public void testGet() {
		ListBook listBook = new ListBook(1, "name", 2016, "author", 11, "isbn", "description", "url", "status");
		if (listBook.getId() == 1 && listBook.getName().equals("name") && listBook.getYear() == 2016
				&& listBook.getAuthor().equals("author") && listBook.getPages() == 11
				&& listBook.getIsbn().equals("isbn") && listBook.getDescription().equals("description")
				&& listBook.getUrl().equals("url") && listBook.getStatus().equals("status")) {
			assertTrue(true);
		} else {
			fail();
		}
	}

}
