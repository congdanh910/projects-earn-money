package com.dwsj.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.dwsj.utils.ConfigurationService;

public class ConnectMysql {
	private static ConfigurationService config = ConfigurationService.getInstance();
	private static ConnectMysql instance = null;
	private static final String DRIVER = "jdbc.driverClassName";
	private static final String USER = "jdbc.username";
	private static final String PASSWORD = "jdbc.password";
	private static final String URL = "jdbc.url";
	private Connection connection = null;

	public static ConnectMysql getInstance() {
		if (instance == null) {
			instance = new ConnectMysql();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Class.forName(config.getString(DRIVER));
			connection = DriverManager.getConnection(config.getString(URL),
					config.getString(USER), config.getString(PASSWORD));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}
