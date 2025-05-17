package com.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.PreparedStatement;

public class JdbcUtil {
	
	
	static
	{
		//Loading the Driver Class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		//Establishing Connection with the server
		String url = "jdbc:mysql://localhost:3306/student_tracker";
		String user = "root";
		String password = "root1234";
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void closeResources(Connection connect, Statement ps, ResultSet rs)
	{
		
		//Closing the resource
		try 
		{
			if (rs != null) rs.close();
	        if (ps != null) ps.close();
	        if (connect != null) connect.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
