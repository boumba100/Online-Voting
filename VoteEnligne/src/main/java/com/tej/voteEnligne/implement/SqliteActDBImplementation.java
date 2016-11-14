package com.tej.voteEnligne.implement;

import com.tej.voteEnligne.models.Act;

import com.tej.voteEnligne.services.ActDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteActDBImplementation implements ActDB {
	private static final String ACTS_DB_FIELD = "voteSessions.db";
	private static final String VOTE_SESSIONS_TABLE = "sessionsTable";
	private static final String SESSION_CODE = "sessionCode";
	private static final String PASSCODE = "passcode";
	private static final String ACTS_FIELD = "acts";

	public SqliteActDBImplementation() {
		try {
			Class.forName("org.sqlite.JDBC");
			initDB();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initDB() {
		Connection connection = null;
		Statement statement = null;
		String sqlString = "CREATE TABLE IF NOT EXISTS " + VOTE_SESSIONS_TABLE + " (" + SESSION_CODE + " TEXT,"
				+ ACTS_FIELD + " TEXT," + PASSCODE + " TEXT)";
		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
			statement.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void insertVoteSession(String code, String passcode, String actsString) {
		Connection connection = null;
		PreparedStatement prep = null;
		String sqlitePrep = "INSERT	 into " + VOTE_SESSIONS_TABLE + " VALUES (?, ?, ?);";
		try {
			connection = getConnection();
			prep = connection.prepareStatement(sqlitePrep);
			prep.setString(1, code);
			prep.setString(2, actsString);
			prep.setString(3, passcode);
			prep.addBatch();
			prep.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				prep.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean sessionExist(String code) {
		boolean exist = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlquery = "SELECT * FROM " + VOTE_SESSIONS_TABLE + " WHERE " + SESSION_CODE + " = '" + code + "';";

		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlquery);
			if (resultSet.next()) {
				exist = true;
				resultSet.close();
			} else {
				exist = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return exist;
	}

	@Override
	public String getVoteActsString(String code, String passcode) {
		String actsString = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlquery = "SELECT " + ACTS_FIELD + " FROM " + VOTE_SESSIONS_TABLE + " WHERE " + SESSION_CODE + " = '"
				+ code + "'" + " AND " + PASSCODE + "=" + "'" + passcode + "';";

		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlquery);
			if (resultSet.next()) {
				actsString = resultSet.getString(ACTS_FIELD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actsString;

	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + ACTS_DB_FIELD);
	}

	private String namesArrayToString(String[] namesArray) {
		String namesString = "";
		for (String name : namesArray) {
			namesString += name + ",";
		}
		return namesString;
	}
}
