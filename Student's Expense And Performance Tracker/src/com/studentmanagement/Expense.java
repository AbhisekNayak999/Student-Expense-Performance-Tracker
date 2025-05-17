
package com.studentmanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Expense {


	//Creating reference variable of the scanner class to take the user input
	static Scanner sc = UserLogin.sc;
		
	/* Creating these variable as instance variable to access them through out the class.*/
	
	//Creating the reference variable of Connection to build connection with the server.
	static Connection connect = null;
	//Creating the reference variable of Prepared statement to create the statement and pass the query.
	static PreparedStatement ps = null;
	//Creating the reference variable of ResultSet to store the data from the database.
	static ResultSet rs = null;

	
	public static void addExpense() {
		
		System.out.println("You can add your expenses with your unique student id...");
		
		try
		{
			System.out.print("Please enter your id: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.println();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Executing the query
			String query = "SELECT * FROM students WHERE student_id=?;";
			//Creating the statement and passing the query.
			ps = connect.prepareStatement(query);
			
			//Setting the value at the placeholder
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			//rs.next() method will return true if any data we retrieve from database.
			if (!rs.next())
			{
				System.out.println("No student exists with the entered student id.");
			}
			else
			{
				System.out.println("To add your expenses you need to enter the amount , category (i.e: food, travel etc) and the date.");
				
				System.out.print("Enter the amount: ");
				double amount = sc.nextDouble();
				sc.nextLine();
				
				System.out.println("Enter the category: ");
				String category = sc.nextLine();
				
				System.out.println("Enter the date (yyyy-mm-dd): ");
				String dateInput = sc.nextLine();
				
				//Converting String to Date
				Date date = Date.valueOf(dateInput);
				
				//Executing the query
				String sql = "INSERT INTO expense (student_id, category, amount, expense_date) VALUES(?, ?, ?, ?);";
				//Creating the statement and passing the query.
				ps = connect.prepareStatement(sql);
				
				//Setting the value at the placeholder
				ps.setInt(1, id);
				ps.setString(2, category);
				ps.setDouble(3, amount);
				ps.setDate(4, date);
				
				//Executing the query
				int rowAffected = ps.executeUpdate();	//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
				
				//if rowAffected is not 0 that means our operation executed successfully.
				if(rowAffected != 0)
				{
					System.out.println("Congratulations, Your details have been successfully added...");
				}
				else
				{
					System.out.println("Sorry, id mismatched so we were not able to add your information ! Please try again later...");
				}
			}
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

	public static void viewExpense() {

		System.out.println("To view all the expenses please enter the student id: ");
		int id = sc.nextInt();
		sc.nextLine();

		try
		{
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String sql = "SELECT category, amount, expense_date FROM expense WHERE student_id=?;";
			ps = connect.prepareStatement(sql );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			
			//Executing the query
			rs = ps.executeQuery();
			
			boolean hasResults = false;

			while (rs.next()) {
			    if (!hasResults) {
			    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println("The expenses of the student are : ");
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			        System.out.printf("%-20s %-15s %-10s \n", "Category", "Amount", "Date");
			        System.out.println("---------------------------------------------");
			        hasResults = true;
			    }
			    System.out.printf("%-20s %-15.2f %-10s \n",
			                      rs.getString("category"),
			                      rs.getDouble("amount"),
			                      rs.getDate("expense_date").toString());
			}

			if (!hasResults) {
			    System.out.println("No expenses found for the entered student id!");
			}

		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong..." + e);
		}
		finally
		{
			//Closing the resources that have been opened.
			JdbcUtil.closeResources(connect, ps, rs);
		}
		
	}

	public static void sortExpense() {

		System.out.println("To sort all the expenses please enter the student id: ");
		int id = sc.nextInt();
		sc.nextLine();

		try
		{
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String sql = "SELECT category, amount, expense_date FROM expense WHERE student_id=? ORDER BY amount ASC;";
			ps = connect.prepareStatement(sql );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			
			//Executing the query
			rs = ps.executeQuery();
			
			boolean hasResults = false;

			while (rs.next()) {
			    if (!hasResults) {
			    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					System.out.println("The expenses of the student are : ");
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			        System.out.printf("%-20s %-15s %-10s \n", "Category", "Amount", "Date");
			        System.out.println("---------------------------------------------");
			        hasResults = true;
			    }
			    System.out.printf("%-20s %-15.2f %-10s \n",
			                      rs.getString("category"),
			                      rs.getDouble("amount"),
			                      rs.getDate("expense_date").toString());
			}

			if (!hasResults) {
			    System.out.println("No expenses found for the entered student id!");
			}

		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong..." + e);
		}
		finally
		{
			//Closing the resources that have been opened.
			JdbcUtil.closeResources(connect, ps, rs);
		}
		
	}

	public static void searchExpense() {
		
		try
		{
			System.out.println("Please enter your id: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			System.out.println("Please select the option which data are you willing to search...");
			System.out.println("1. Amount\n2. Date\n3. Category");
			
			int choice = sc.nextInt();
			sc.nextLine(); //This is used to consume the left over which is after entering the id the user pressed enter which is \n behind the scene.
			
			switch(choice)
			{
				case 1:
				{
					System.out.print("Enter the searching amount: ");
					double amount = sc.nextDouble();
					System.out.println();
					
					String query = "SELECT category, amount, expense_date FROM expense WHERE amount=? AND student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setDouble(1, amount);
					ps.setInt(2, id);
					
					//Executing the query
					rs = ps.executeQuery();
					
					boolean hasResults = false;

					while (rs.next()) {
					    if (!hasResults) {
					    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							System.out.println("The expenses of the student are : ");
							System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					        System.out.printf("%-20s %-15s %-10s \n", "Category", "Amount", "Date");
					        System.out.println("---------------------------------------------");
					        hasResults = true;
					    }
					    System.out.printf("%-20s %-15.2f %-10s \n",
					                      rs.getString("category"),
					                      rs.getDouble("amount"),
					                      rs.getDate("expense_date").toString());
					}

					if (!hasResults) {
					    System.out.println("No expenses found for the entered student id!");
					}

					break;
				}
				case 2:
				{
					System.out.println("Enter the date (yyyy-mm-dd): ");
					String dateInput = sc.nextLine();
					
					//Converting String to Date
					Date date = Date.valueOf(dateInput);
					
					String query = "SELECT category, amount, expense_date FROM expense WHERE expense_date=? AND student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setDate(1, date);
					ps.setInt(2, id);
					
					//Executing the query
					rs = ps.executeQuery();
					
					boolean hasResults = false;

					while (rs.next()) {
					    if (!hasResults) {
					    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							System.out.println("The expenses of the student are : ");
							System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					        System.out.printf("%-20s %-15s %-10s \n", "Category", "Amount", "Date");
					        System.out.println("---------------------------------------------");
					        hasResults = true;
					    }
					    System.out.printf("%-20s %-15.2f %-10s \n",
					                      rs.getString("category"),
					                      rs.getDouble("amount"),
					                      rs.getDate("expense_date").toString());
					}

					if (!hasResults) {
					    System.out.println("No expenses found for the entered student id!");
					}

					break;
				}
				case 3:
				{
					System.out.println("Enter the category: ");
					String category = sc.nextLine();
					
					String query = "SELECT category, amount, expense_date FROM expense WHERE category=? AND student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setString(1, category);
					ps.setInt(2, id);
					
					//Executing the query
					rs = ps.executeQuery();
					
					boolean hasResults = false;

					while (rs.next()) {
					    if (!hasResults) {
					    	System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
							System.out.println("The expenses of the student are : ");
							System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
					        System.out.printf("%-20s %-15s %-10s \n", "Category", "Amount", "Date");
					        System.out.println("---------------------------------------------");
					        hasResults = true;
					    }
					    System.out.printf("%-20s %-15.2f %-10s \n",
					                      rs.getString("category"),
					                      rs.getDouble("amount"),
					                      rs.getDate("expense_date").toString());
					}

					if (!hasResults) {
					    System.out.println("No expenses found for the entered student id!");
					}

					break;
				}
				default:
					System.out.println("Invalid Input");
			}
		}
		catch (Exception e) 
		{
			System.out.println("Something went wrong..."+e);
		}
		finally
		{
			JdbcUtil.closeResources(connect, ps, rs);
		}
		
	}

}
