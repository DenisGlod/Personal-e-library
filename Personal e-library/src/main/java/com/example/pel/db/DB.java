package com.example.pel.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DB {
	private Connection cn;
	private Statement st;

	public Connection getCn() {
		return cn;
	}

	public DB(String url, String user, String password) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url + "?useSSL=true", user, password);
		st = (Statement) cn.createStatement();
	}

	public void update(String sql) throws SQLException {
		st.executeUpdate(sql);
	}

	public ResultSet query(String sql) throws SQLException {
		ResultSet rs = null;
		rs = st.executeQuery(sql);
		return rs;
	}

	public void close() throws SQLException {
		st.close();
		cn.close();
	}
}
