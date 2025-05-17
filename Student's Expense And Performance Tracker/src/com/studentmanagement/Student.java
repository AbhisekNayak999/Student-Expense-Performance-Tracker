package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student 
{

	//Creating reference variable of the scanner class to take the user input
	static Scanner sc = UserLogin.sc;

	
	/* Creating these variable as instance variable to access them through out the class.*/
	//Creating the reference variable of Connection to build connection with the server.
	static Connection connect = null;
	//Creating the reference variable of Prepared statement to create the statement and pass the query.
	static PreparedStatement ps = null;
	//Creating the reference variable of ResultSet to store the data from the database.
	static ResultSet rs = null;
	
	//addStudent() method will add the student information to the database.
	public static void addStudent() 
	{
		
		//Initializing the phone and email to null, so if the user does not want to add we should not bother about it.
		String email = null;
		String phone = null;
		
		System.out.println("Whan you want to add a new student, you need to enter the name compulsory but there is no restriction on you to add phone or email "
				+ "Which you can add later...");
		
		System.out.print("Please enter the full name: ");
		String name = sc.nextLine();
		System.out.println();
		System.out.print("If you want to add email please enter else you can skip by just pressing enter once : ");
		email = sc.nextLine();
		System.out.println();
		System.out.println("If you want to add phone please enter else you can skip by just pressing enter once : ");
		phone = sc.nextLine();
		System.out.println();
		
		try
		{
			System.out.print("Please enter your id: ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String query = "INSERT INTO students(student_id, name, email, phone) VALUES(?,?,?,?)";
			ps = connect.prepareStatement(query );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phone);
			
			//Executing the query
			int rowAffected = ps.executeUpdate();		//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
			
			//if rowAffected is not 0 that means our operation executed successfully.
			if(rowAffected != 0)
			{
				System.out.println("Congratulation, Your details have been successfully added...");
			}
			else
			{
				System.out.println("Sorry, id mismathced so we were not able to add your information ! Please try again later...");
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

	//ViewAllStudent() method will retrieve all the students name available on the database
	public static void viewAllStudent() 
	{
		
		try
		{			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String query = "SELECT name FROM students;";
			ps = connect.prepareStatement(query );
			
			//Executing the query
			ResultSet rs = ps.executeQuery();
			
			//Creating an array list to store the names temporarily.
			List<String> names = new ArrayList<>();
			
			//rs.next() method will return true if any data we retrieve from database.
			while(rs.next())
			{
				names.add(rs.getString("name"));
			}
			
			//Now checking if there are any names present or not.
			if (!names.isEmpty()) {
				System.out.println();
				System.out.println("----------------------------------------------");
				System.out.println(" The name of all registered students are:   ");
				System.out.println();
				System.out.printf("%-15s\n ", " === Name === ");
				System.out.println("---------------");
				
				for(String  nm : names)
				{
					System.out.println("- "+nm);
				}
				System.out.println("----------------------------------------------");
				rs.close();
			}
			else
			{
				System.out.println("No students are registered yet!");
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

	//searchStudent() method will search any student with their student id.
	public static void searchStudent() 
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
			String query = "SELECT s.name, p.subject, p.mark FROM performance AS p INNER JOIN students AS s ON p.student_id=s.student_id WHERE s.student_id=?;";
			ps = connect.prepareStatement(query );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			
			//Executing the query
			rs = ps.executeQuery();
			
			boolean recordFound = false;

	        while (rs.next()) {
	            if (!recordFound) {
	            	System.out.println("----------------------------------------------");
					System.out.println("The record of students with id "+id+"is :");
					System.out.println();
	                System.out.printf("%-20s %-15s %-10s \n", "Name", "Subject", "Mark");
	                System.out.println("---------------------------------------------");
	                recordFound = true;
	            }
	            System.out.printf("%-20s %-15s %-10d \n", rs.getString(1), rs.getString(2), rs.getInt(3));
	        }
	        if(recordFound)
	        {
	        	System.out.println("----------------------------------------------");
	        }
	        if (!recordFound) {
	            System.out.println("No student found with the provided ID.");
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
	
	//updateStudent() method will update any registered students details
	public static void updateStudent() 
	{
		
		try
		{
			System.out.println("Please enter your id: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			System.out.println("Please select the option which data are you willing to update...");
			System.out.println("1. Name\n2. Email\n3. Phone");
			
			int choice = sc.nextInt();
			sc.nextLine(); //This is used to consume the left over which is after entering the id the user pressed enter which is \n behind the scene.
			
			switch(choice)
			{
				case 1:
				{
					System.out.print("Enter the new name: ");
					String newName = sc.nextLine();
					System.out.println();
					
					String query = "UPDATE students SET name=? WHERE student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setString(1, newName);
					ps.setInt(2, id);
					
					//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
					int rowAffected = ps.executeUpdate();
					
					//if rowAffected is not 0 that means our operation executed successfully.
					if (rowAffected != 0) 
					{
						System.out.println("Hoorah! Name successfully updated");
					}
					else
					{
						System.out.println("There is no student present with the entered id.\nPlease check the id first");
					}
					break;
				}
				case 2:
				{
					System.out.print("Enter the new email: ");
					String email = sc.nextLine();
					System.out.println();
					
					String query = "UPDATE students SET email=? WHERE student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setString(1, email);
					ps.setInt(2, id);
					
					//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
					int rowAffected = ps.executeUpdate();
					
					//if rowAffected is not 0 that means our operation executed successfully.
					if (rowAffected != 0) 
					{
						System.out.println("Hoorah ! Email successfully updated");
					}
					else
					{
						System.out.println("There is no student present with the entered id.\nPlease check the id first");
					}
					break;
				}
				case 3:
				{
					System.out.print("Enter the new phone: ");
					String phone = sc.nextLine();
					System.out.println();
					
					String query = "UPDATE students SET phone=? WHERE student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setString(1, phone);
					ps.setInt(2, id);
					
					//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
					int rowAffected = ps.executeUpdate();
					
					//if rowAffected is not 0 that means our operation executed successfully.
					if (rowAffected != 0) 
					{
						System.out.println("Hoorah ! Phone successfully updated");
					}
					else
					{
						System.out.println("There is no student present with the entered id.\nPlease check the id first");
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

	//deleteStudent() method will delete all the informations about a student.
	public static void deleteStudent() 
	{
		
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
			if (rs.next())
			{
				String sql = "DELETE FROM students WHERE student_id=?";
				ps = connect.prepareStatement(sql);
				
				//Setting the value in the place of placeholder
				ps.setInt(1, id);
				
				//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
				int rowAffected = ps.executeUpdate();
				
				//if rowAffected is not 0 that means our operation executed successfully.
				if(rowAffected != 0)
				{
					System.out.println("Student's details with id: "+id+" has been deleted successfully.");
				}
				rs.close();
			}
			else
			{
				System.out.println("No student's data available with the entered student id.");
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