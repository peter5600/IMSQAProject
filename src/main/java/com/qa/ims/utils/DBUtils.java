package com.qa.ims.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DBUtils {

	private static final Logger LOGGER = LogManager.getLogger();

	private String dbUrl;//removed final so that i can modify the url and set it so that the script makes the db for me

	private final String dbUser;

	private final String dbPassword;

	private DBUtils(String properties) {
		Properties dbProps = new Properties();
		try (InputStream fis = ClassLoader.getSystemResourceAsStream(properties)) {
			dbProps.load(fis);
		} catch (Exception e) {  
			LOGGER.error(e);
		}
		this.dbUrl = dbProps.getProperty("db.url", "");
		this.dbUser = dbProps.getProperty("db.user", "");
		this.dbPassword = dbProps.getProperty("db.password", "");
		if(this.dbUrl.indexOf("jdbc:mysql") != -1) {//check for test mode
			this.init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");//call this to create the db
			this.dbUrl += "/ims";
		}//this code creates a database and changes the url i dont want to mess with it if its in test mode
	}

	public DBUtils() {
		this("db.properties");
	}

	public int init(String... paths) {
		int modified = 0;

		for (String path : paths) {
			modified += executeSQLFile(path);
		}

		return modified;
	}

	public int executeSQLFile(String file) {
		int modified = 0;
		try (Connection connection = this.getConnection();
				BufferedReader br = new BufferedReader(new FileReader(file));) {
			String fileAsString = br.lines().reduce((acc, next) -> acc + next).orElse("");
			String[] queries = fileAsString.split(";");
			modified += Stream.of(queries).map(string -> {
				try (Statement statement = connection.createStatement();) {
					return statement.executeUpdate(string);
				} catch (Exception e) {
					LOGGER.debug(e);
					return 0;
				}
			}).reduce((acc, next) -> acc + next).orElse(0);
		} catch (Exception e) {
			LOGGER.debug(e);
		}
		return modified;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}

	private static DBUtils instance;

	public static DBUtils connect() {
		instance = new DBUtils();
		return instance;
	}

	public static DBUtils connect(String properties) {
		instance = new DBUtils(properties);
		return instance;
	}

	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
		}
		return instance;
	}

}
