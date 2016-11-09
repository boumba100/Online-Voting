package com.tej.voteEnligne.implement;

import com.tej.voteEnligne.models.Act;

import com.tej.voteEnligne.services.ActDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteActDBImplementation implements ActDB {
	private static final String ACTS_DB_FIELD = "acts.db";
	private static final String ACTS_TABLE = "actsTable";
	private static final String ACT_NAME_FIELD = "actName";
	private static final String NAMES_FIELD = "names";
	private static final String SCORE_FIELD = "score";

	public SqliteActDBImplementation() {
		try {
			Class.forName("org.sqlite.JDBC");
			initDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAct(Act act) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(connection != null) {
				try {
					statement.close();
					connection.commit();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void initDB() {
		System.out.println("test 123");
		Connection connection = null;
		Statement statement = null;
		String sqlString = "CREATE TABLE IF NOT EXISTS " + ACTS_TABLE + " (" + ACT_NAME_FIELD + " TEXT,"
				+ NAMES_FIELD + " TEXT," + SCORE_FIELD + " INT)";
		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate(sqlString);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + ACTS_DB_FIELD);
	}
	
	private String namesArrayToString(String[] namesArray) {
		String namesString = "";
		for(String name: namesArray) {
			namesString += name + ",";
		}
		return namesString;
	}
	
}
















