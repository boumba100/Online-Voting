package com.tej.voteEnligne.implement;

import com.tej.voteEnligne.models.Act;
import com.tej.voteEnligne.services.ActDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteActDBImplementation implements ActDB{
	private Connection connection = null;
	
	public SqliteActDBImplementation() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addAct(Act act) {
		
	}
}
