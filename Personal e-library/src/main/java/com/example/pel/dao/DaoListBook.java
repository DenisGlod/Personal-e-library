package com.example.pel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.pel.db.DB;
import com.example.pel.entity.ListBook;
import com.mysql.jdbc.PreparedStatement;

public class DaoListBook {
	private DB db;

	public DaoListBook(DB db) {
		this.db = db;
	}

	public void addBook(ListBook ob) throws SQLException {
		PreparedStatement ps = (PreparedStatement) db.getCn()
				.prepareStatement("INSERT INTO " + ob.getClass().getSimpleName()
						+ " (name, year, author, pages, isbn, description, url, status) VALUES(?,?,?,?,?,?,?,?)");
		ps.setString(1, ob.getName());
		ps.setInt(2, ob.getYear());
		ps.setString(3, ob.getAuthor());
		ps.setInt(4, ob.getPages());
		ps.setString(5, ob.getIsbn());
		ps.setString(6, ob.getDescription());
		ps.setString(7, ob.getUrl());
		ps.setString(8, ob.getStatus());
		ps.execute();
	}

	public void deleteBook(Integer id) throws SQLException {
		db.update("DELETE FROM ListBook WHERE id=" + id);
	}

	public void updateBook(ListBook ob) throws SQLException {
		PreparedStatement ps = (PreparedStatement) db.getCn()
				.prepareStatement("UPDATE " + ob.getClass().getSimpleName()
						+ " SET name=?, year=?, author=?, pages=?, isbn=?,  description=?, url=?, status=? WHERE id="
						+ ob.getId());
		ps.setString(1, ob.getName());
		ps.setInt(2, ob.getYear());
		ps.setString(3, ob.getAuthor());
		ps.setInt(4, ob.getPages());
		ps.setString(5, ob.getIsbn());
		ps.setString(6, ob.getDescription());
		ps.setString(7, ob.getUrl());
		ps.setString(8, ob.getStatus());
		ps.execute();
	}

	public ResultSet getListBook() throws SQLException {
		ResultSet rs = db.query("SELECT * FROM ListBook");
		return rs;
	}

	public ResultSet getBook(int idBook) throws SQLException {
		ResultSet rs = db.query("SELECT * FROM ListBook WHERE id = " + idBook + ";");
		return rs;
	}
}
