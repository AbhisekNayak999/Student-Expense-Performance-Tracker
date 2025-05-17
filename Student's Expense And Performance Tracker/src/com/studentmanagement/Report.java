package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Report {

	//Creating reference variable of the scanner class to take the user input
	static Scanner sc = UserLogin.sc;
		
	/* Creating these variable as instance variable to access them through out the class.*/
	
	//Creating the reference variable of Connection to build connection with the server.
	static Connection connect = null;
	//Creating the reference variable of Prepared statement to create the statement and pass the query.
	static PreparedStatement ps = null;
	//Creating the reference variable of ResultSet to store the data from the database.
	static ResultSet rs = null;
	
	public static void viewReport() 
	{
		
		try
		{
			System.out.print("Please enter the student id: ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String query = "SELECT s.name, p.subject, p.mark, e.category, e.amount, e.expense_date "
					+ "FROM students AS s "
					+ "INNER JOIN performance p ON s.student_id=p.student_id "
					+ "INNER JOIN expense AS e ON s.student_id=e.student_id WHERE s.student_id=?;";
			ps = connect.prepareStatement(query );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			
			//Executing the query
			rs = ps.executeQuery();
			
			//Displaying the details of the student.
			boolean hasResults = false;
			while (rs.next()) {
			    if (!hasResults) {
			    	System.out.println("==============================================================");
			    	System.out.println("||  The complete report of the student with id "+id+" is:   ||");
			    	System.out.println("==============================================================");
			        System.out.printf("%-20s %-15s %-10s %-15s %-10s %-10s \n", "Name", "Subject", "Mark", "Category", "Amount", "Date");
			        System.out.println("--------------------------------------------------------------------------------------");
			        hasResults = true;
			    }
			    System.out.printf("%-20s %-15s %-10d %-15s %-10.2f %-10s \n",
			                      rs.getString("name"),
			                      rs.getString("subject"),
			                      rs.getInt("mark"),
			                      rs.getString("category"),
			                      rs.getDouble("amount"),
			                      rs.getDate("expense_date").toString());
			}

			if (!hasResults) {
			    System.out.println("No records found for the entered student id.");
			}

		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong..." + e);
		}
		finally
		{
			JdbcUtil.closeResources(connect, ps, rs);
		}
		
	}


}
