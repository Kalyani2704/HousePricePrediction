//we write here all database logics//
package org.house.predict.config;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConfig {
	private static Connection conn;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	private static DBConfig db = null;
	private int areaid = 0;     // repeat declear in city master repo

	private DBConfig() {
		try {

			Properties p = new Properties();
			//System.out.println("variable Fin:"+PathHelper.fin);
			p.load(PathHelper.fin);
     		String DriverClassName = p.getProperty("driver.classname");
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			String url = p.getProperty("db.url");
			
			//System.out.println(DriverClassName + "\n" + username + "\n" + password + "\n" + url);
			Class.forName(DriverClassName);
			conn = DriverManager.getConnection(url, username, password);
			
		//	System.out.println("Conn is:"+conn);
			if (conn != null) {
				System.out.println("database is connected");
			} else {
				System.out.println("database is not connected");
			}
			System.err.println("===============================");
		} catch (Exception ex) {
			//System.out.println(DriverClassName + "\n" + username + "\n" + password + "\n" + url);
			//System.err.println("When we Connect database error is-->" + ex);
		}
	}

	public static DBConfig getDBInstance() {
		if (db == null) {
			return new DBConfig();
		}
		return db;
	}

	public static Connection getConnection() {
		return conn;
	}

	public static PreparedStatement getStatement() {
		return stmt;
	}

	public static ResultSet getResult() {
		return rs;
	}

}
