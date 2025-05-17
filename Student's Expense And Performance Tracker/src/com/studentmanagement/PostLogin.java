package com.studentmanagement;

import java.sql.*;

public class PostLogin {
	
	//Creating the object of the MainMenuFunction to let the user go ahead towards the options they will have..
	MainMenuFunction main = new MainMenuFunction();
	
	//This method should redirect the user to the all the functions.
	public void loginSuccessful() 
	{
		main.mainMenu();
	}

	//This method should redirect the user to the login page to let them login with user name and password. And also it should return the student id to themselves.
	public void registrationSuccessful(String username) {
		
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			//With this method of the JdbcUtil class we will load the Driver class and build the connection.
			connect = JdbcUtil.getConnection();
			
			//Now we will execute the query with the help of the PrepareStatement  object .
			String query = "SELECT student_id FROM user_authentication WHERE username=?";
			
			//Now with the help of the connection we will create the statement.
			ps = connect.prepareStatement(query);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				int id = rs.getInt(1);
				System.out.println(" *** Please remember this id as with this only you will be able to do something.\nYour student id is : "+id);
			}
			
			System.out.println("\nPlease login now with your username and password.");
			
			//Creating the object of the LoginOrRegister class to get its method studentLogin() and studentRegister().
			LoginOrRegister log = new LoginOrRegister();
			log.studentLogin();
		}
		catch(Exception e)
		{
			System.out.println("Something went wrong..."+e);
		}
		finally
		{
			JdbcUtil.closeResources(connect, ps, rs);
		}
	}
}
