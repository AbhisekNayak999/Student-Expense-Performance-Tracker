package com.studentmanagement;

import java.util.Scanner;

public class MainMenuFunction 
{
	//Creating the reference variable of the scanner class to get the input from user.
	Scanner sc1 = UserLogin.sc;
	
	public void mainMenu() 
	{
		
		//This boolean variable will control that how many time the loop should run and this completely depends on the user.
		boolean exit = false;
		
		//This loop will run till the user choose option 6 i.e: exit.
		while(!exit)
		{
			System.out.println();
			System.out.println("---------------------------------------------------");
			System.out.println("| === Student Expense And Performance Tracker === |");
			System.out.println("---------------------------------------------------");
			System.out.println();
			System.out.println("Please select any of these option to proceed");
			System.out.println("Menu ===>");
			System.out.println("1. Student Management.");
			System.out.println("2. Performance Management.");
			System.out.println("3. Expense Management.");
			System.out.println("4. View all report of a student.");
			System.out.println("5. Logout !");
			System.out.print("Enter your choice: ");
			int choice = sc1.nextInt();
			sc1.nextLine();
			
			System.out.println();

			//This menu is controlled by this switch case.
			switch(choice)
			{
				case 1:
				{
					studentMenu();
					break;
				}
				case 2:
				{
					performanceMenu();
					break;
				}
				case 3:
				{
					expenseMenu();
					break;
				}
				case 4:
				{
					viewFullReport();
					break;
				}
				case 5:
				{
					System.out.println();
					System.out.println("Thanks for using Student Expense And Performance Tracker | See you soon...");
					exit = true;
					break;
				}
				default:
					System.out.println("Invalid Input");
					System.out.println("Please choose any given option...");
			}
		}
	}
	

	private void studentMenu() 
	{
		boolean exit = false;
		
		while(!exit)
		{
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println("|     === Welcome to the student section ===     |");
			System.out.println("--------------------------------------------------"); 
			System.out.println();
			System.out.println("Please select any of these option to proceed");
			System.out.println("Options ===>");
			System.out.println("1. Add new student. ");
			System.out.println("2. View all student.");
			System.out.println("3. Search any student.");
			System.out.println("4. Update student detail.");
			System.out.println("5. Delete student detail.");
			System.out.println("6. Exit Student section.");
			System.out.print("Enter your choice: ");
			
			int choice = sc1.nextInt();
			sc1.nextLine();
			
			//This switch case will control the operations user wants to perform ahead.
			switch (choice) 
			{
			case 1: 
			{
				Student.addStudent();
				break;
			}
			case 2: 
			{
				Student.viewAllStudent();
				break;
			}
			case 3: 
			{
				Student.searchStudent();
				break;
			}
			case 4: 
			{
				Student.updateStudent();
				break;
			}
			case 5: 
			{
				Student.deleteStudent();
				break;
			}
			case 6:
			{
				System.out.println("Going back to Mainmenu...");
				exit = true;
				break;
			}
			default:
				System.out.println("Invalid Input.\nPlease choose any given options");
			}
		}
	}

	private void performanceMenu() 
	{
		boolean exit = false;
		
		while(!exit)
		{
			System.out.println();
			System.out.println("----------------------------------------------------");
			System.out.println("|    === Welcome to the performance section ===    |");
			System.out.println("----------------------------------------------------"); 
			System.out.println();
			System.out.println("Please select any of these option to proceed");
			System.out.println("Options ===>");
			System.out.println("1. Add marks of student. ");
			System.out.println("2. View performance of student.");
			System.out.println("3. View performance of all student.");
			System.out.println("4. Update student marks.");
			System.out.println("5. Calculate average mark.");
			System.out.println("6. Exit Performance section.");
			System.out.print("Enter your choice: ");
			
			int choice = sc1.nextInt();
			sc1.nextLine();
			
			//This switch case will control the operations user wants to perform ahead.
			switch (choice) 
			{
			case 1: 
			{
				Performance.addMark();
				break;
			}
			case 2: 
			{
				Performance.viewPerformance();
				break;
			}
			case 3: 
			{
				Performance.viewAllPerformance();
				break;
			}
			case 4:
			{
				Performance.updateMark();
				break;
			}
			case 5: 
			{
				Performance.calculateAvgMark();
				break;
			}
			case 6:
			{
				System.out.println("Going back to Mainmenu...");
				exit = true;
				break;
			}
			default:
				System.out.println("Invalid Input.\nPlease choose any given options");
			}
		}
	}

	private void expenseMenu() 
	{
		
		boolean exit = false;
		
		while(!exit)
		{
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println("|     === Welcome to the expense section ===     |");
			System.out.println("--------------------------------------------------"); 
			System.out.println();
			System.out.println("Please select any of these option to proceed");
			System.out.println("Options ===>");
			System.out.println("1. Add expenses of student. ");
			System.out.println("2. View expenses of student.");
			System.out.println("3. Sort expenses by amount. ");
			System.out.println("4. Search student expense.");
			System.out.print("Enter your choice: ");
			
			int choice = sc1.nextInt();
			sc1.nextLine();
			
			//This switch case will control the operations user wants to perform ahead.
			switch (choice) 
			{
			case 1: 
			{
				Expense.addExpense();
				break;
			}
			case 2: 
			{
				Expense.viewExpense();
				break;
			}
			case 3: 
			{
				Expense.sortExpense();
				break;
			}
			case 4: 
			{
				Expense.searchExpense();
				break;
			}
			case 5:
			{
				System.out.println("Going back to Mainmenu...");
				exit = true;
				break;
			}
			default:
				System.out.println("Invalid Input.\nPlease choose any given options");
			}
		}
	}

	private void viewFullReport() 
	{
		Report.viewReport();
	}
	
}