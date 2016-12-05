package com.example.pel.entity;

public class ListBook {
	private int id;
	private String name;
	private int year;
	private String author;
	private int pages;
	private String isbn;
	private String description;
	private String url;
	private String status;

	public ListBook(int id, String name, int year, String author, int pages, String isbn, String description,
			String url, String status) {
		this.id = id;
		this.name = name;
		this.year = year;
		this.author = author;
		this.pages = pages;
		this.isbn = isbn;
		this.description = description;
		this.url = url;
		this.status = status;
	}

	public ListBook(String name, int year, String author, int pages, String isbn, String description, String url,
			String status) {
		this.name = name;
		this.year = year;
		this.author = author;
		this.pages = pages;
		this.isbn = isbn;
		this.description = description;
		this.url = url;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
