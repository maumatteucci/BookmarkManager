package com.hype.bookmarkmanager.config.sqldialect;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {

	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	public void initialize(){
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS account");
			statement.executeUpdate(
					"CREATE TABLE account(" +
					"username varchar(30) Primary key not null," +
					"password varchar(30) not null," +
					"role varchar(30) not null )" 
					);
			statement.executeUpdate(
					"INSERT INTO account " +
					"(username,password,role) " +
					"VALUES('maurizio','master123','INSERT')");
			statement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
