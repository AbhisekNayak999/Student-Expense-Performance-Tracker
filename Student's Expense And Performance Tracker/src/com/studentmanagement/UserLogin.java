package com.studentmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserLogin 
{
	//Creating a static scanner class to get the user input throughout this program.
	static Scanner sc  = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		
		//Creating the object of the LoginOrRegister class to get its method studentLogin() and studentRegister().
		LoginOrRegister log = new LoginOrRegister();
		
		
		try
		{
			System.out.println("============================================================");
			System.out.println("|| Welcome To The Student Expense And Performance Tracker ||");
			System.out.println("============================================================\n");
			System.out.println("Please enter your choice to proceed...");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.print("Enter your choice : ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			System.out.println();
			switch (choice) {
			case 1: 
			{
				log.studentLogin();
				break;
			}
			case 2:
			{
				log.studentRegister();
				break;
			}
			default:
				System.out.println("Invalid Input \nPlease enter from the given options...");
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("\n Invalid input.\nPlease enter a number...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			sc.close();
		}
	}
}
