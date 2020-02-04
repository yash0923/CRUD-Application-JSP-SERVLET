package com.codingraja.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/customer_master";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "yash";
	
	private static Connection connection = null;
	
	public static Connection getConnection(){
		if(connection==null){
			try{
				//Loading The Driver Class
				Class.forName(DRIVER);
				
				//Getting the connection Object
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		return connection;
	}
}

/*CREATE TABLE customer_master(
	customer_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(50) UNIQUE NOT NULL,
	mobile VARCHAR(50),
	PRIMARY KEY(customer_id)
);*/
