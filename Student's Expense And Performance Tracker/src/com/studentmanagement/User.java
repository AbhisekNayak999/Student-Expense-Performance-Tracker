package com.studentmanagement;

import java.sql.*;

public class User {
	
	//These variables are created to track states of login or register.
	public static final int LOGIN_SUCCESS = 0;
	public static final int WRONG_PASSWORD = 1;
	public static final int USER_NOT_FOUND = 2;


	//This passwordInDb will store the retrieved password from the database.
	String passwordInDb;
	
	//This rowAffected will store how many rows have been affected after inserting the data into database.
	int rowAffected = 0;
	//Here we will fetch the user entered data with our table in the database,
	//if same user name and password are there in the database, then only allowing the user to login.
	public int isExist(String username, String password) {
		
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try
		{
			//With this method of the JdbcUtil class we will load the Driver class and build the connection.
			connect = JdbcUtil.getConnection();
			
			//Now we will execute the query with the help of the statement  object statement.
			String query = "SELECT password FROM user_authentication WHERE username=?";

			//Now with the help of the connection we will create the statement.
			ps = connect.prepareStatement(query);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if (rs.next()) //rs.next() will return true if anything the query returns.
			{
				//Storing the password saved in the database to the instance variable.
				passwordInDb = rs.getString(1);
				
				if (password.equals(passwordInDb))
				{
					return LOGIN_SUCCESS;
				}
			}
			else
			{
				return USER_NOT_FOUND;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong...");
			e.printStackTrace();
		}
		finally 
		{
			JdbcUtil.closeResources(connect, ps, rs);
		}
		
		return WRONG_PASSWORD;
	}

	
	
	//This method will check first that is there any user exist with the same user name if not then only allowing the user to register.
	public boolean registerUser(String username, String password) {
		
		
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try
		{
			//With this method of the JdbcUtil class we will load the Driver class and build the connection.
			connect = JdbcUtil.getConnection();
			
			//Now we will execute the query with the help of the PrepareStatement  object .
			String query = "SELECT * FROM user_authentication WHERE username=?";
			
			//Now with the help of the connection we will create the statement.
			ps = connect.prepareStatement(query);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			//When the if condition is true that means there exist a user with the same user name so it should not allow the user to register.
			if (!rs.next()) //rs.next() will return true if anything the query returns.
			{
				String sql = "INSERT INTO user_authentication (username, password) VALUES (?,?);";
				
				ps = connect.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				
				
				rowAffected = ps.executeUpdate();
				
				if(rowAffected != 0)
				{
					return true;
				}
			}
			else
			{
				System.out.println("User already exist with same user name...");
				return false;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong...");
			e.printStackTrace();
		}
		finally 
		{
			JdbcUtil.closeResources(connect, ps, rs);
		}
		return false;
	}
}
