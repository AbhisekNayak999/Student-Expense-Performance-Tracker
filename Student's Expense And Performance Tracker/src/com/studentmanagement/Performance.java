package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Performance {

	//Creating reference variable of the scanner class to take the user input
	static Scanner sc = UserLogin.sc;

		
	/* Creating these variable as instance variable to access them through out the class.*/
	
	//Creating the reference variable of Connection to build connection with the server.
	static Connection connect = null;
	//Creating the reference variable of Prepared statement to create the statement and pass the query.
	static PreparedStatement ps = null;
	//Creating the reference variable of ResultSet to store the data from the database.
	static ResultSet rs = null;

	
	public static void addMark() 
	{
	
		System.out.print("Enter the student id: ");
		int id = sc.nextInt();
		System.out.println();
		
		System.out.println("Now you need to enter the subject name and respective marks");
		System.out.print("Enter your mark: ");
		int mark = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		System.out.println("Enter the subject: ");
		String subject = sc.nextLine();
		
		try
		{
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String sql = "INSERT INTO performance(student_id, subject, mark) VALUES(?,?,?)";
			ps = connect.prepareStatement(sql );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			ps.setString(2, subject);
			ps.setInt(3, mark);
			
			//Executing the query
			int rowAffected = ps.executeUpdate();	//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
			
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
			//Closing the resources that have been opened.
			JdbcUtil.closeResources(connect, ps, rs);
		}
	}

	public static void viewPerformance() 
	{
		
		//Using this method we can view the performance of a student with his/her student id.
		Student.searchStudent();		
	}

	public static void viewAllPerformance() 
	{
		
		
		try
		{			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			//Creating the statement and passing the query.
			String query = "SELECT name, subject, mark FROM students AS s INNER JOIN performance AS p ON s.student_id=p.student_id;";
			ps = connect.prepareStatement(query );
			
			//Executing the query
			rs = ps.executeQuery();
			
			int count = 0;
			
			//isExist variable will track only once that is there anything retrieved from the database which will make it true only once in the database.
			boolean isExist = false;
			
			while(rs.next())
			{
				if(count == 0)
				{
					System.out.println("==========================================");
					System.out.println("The name of all registered students are: ");
					System.out.println("==========================================");
					System.out.printf("%-20s %-15s %-10s \n", "Name" , "Subject" , "Mark");
			        System.out.println("---------------------------------------------");
			        isExist = true;
					count++;
				}
				System.out.printf("%-20s %-15s %-10d \n", rs.getString(1) , rs.getString(2) , rs.getInt(3));
			}
			
			if(!isExist) {
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

	public static void calculateAvgMark() 
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
			String query = "SELECT * FROM students WHERE student_id=?;";
			ps = connect.prepareStatement(query );
			
			//Setting the values to the placeholder.
			ps.setInt(1, id);
			
			//Executing the query
			rs = ps.executeQuery();
			
			//rs.next() method will return true if any data we retrieve from database.
			if(rs.next())
			{	
				//Creating the statement and passing the query.
				String sql = "SELECT AVG(mark) FROM performance WHERE student_id=?;";
				ps = connect.prepareStatement(sql);
				
				//Setting the values to the placeholder.
				ps.setInt(1, id);
				
				//Executing the query
				rs = ps.executeQuery();
				
				if(rs.next())
				{
					System.out.println("Average marks of the student with id: "+id+" is : "+rs.getDouble(1));
				}
				else
				{
					System.out.println("Marks have not been uploaded");
				}
				
			}
			else
			{
				System.out.println("No student present with the given id.");
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

	public static void updateMark() 
	{

		try
		{
			System.out.println("Please enter your id: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			//Loading and registering the Driver class.
			connect = JdbcUtil.getConnection();
			
			System.out.println("Please enter the option which data are you willing to update...");
			System.out.println("1. Subject\n2. Mark");
			
			int choice = sc.nextInt();
			sc.nextLine(); //This is used to consume the left over which is after entering the id the user pressed enter which is \n behind the scene.
			
			switch(choice)
			{
				case 1:
				{
					System.out.print("Enter the new subject name: ");
					String newName = sc.nextLine();
					System.out.println();
					
					String query = "UPDATE performance SET subject=? WHERE student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setString(1, newName);
					ps.setInt(2, id);
					
					//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
					int rowAffected = ps.executeUpdate();
					
					//if rowAffected is not 0 that means our operation executed successfully.
					if (rowAffected != 0) 
					{
						System.out.println("Hoorah! Subject successfully updated");
					}
					else
					{
						System.out.println("There is no student present with the entered id.\nPlease check the id first");
					}
					break;
				}
				case 2:
				{
					System.out.print("Enter the updated mark: ");
					int mark = sc.nextInt();
					System.out.println();
					
					String query = "UPDATE performance SET mark=? WHERE student_id=?";
					ps = connect.prepareStatement(query);
					
					//Setting the value at the placeholder
					ps.setInt(1, mark);
					ps.setInt(2, id);
					
					//executeUpdate() method will return integer other than 0 if any if the row affected during insert, update or delete operation.
					int rowAffected = ps.executeUpdate();
					
					//if rowAffected is not 0 that means our operation executed successfully.
					if (rowAffected != 0) 
					{
						System.out.println("Hoorah ! Mark successfully updated");
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

}
