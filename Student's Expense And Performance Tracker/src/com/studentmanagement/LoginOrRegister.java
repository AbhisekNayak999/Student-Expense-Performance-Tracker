package com.studentmanagement;





//Forgot password should be an option to retrieve the password.









import java.util.Scanner;

public class LoginOrRegister {

	//Creating the reference variable of the scanner class to take input from the user.
	Scanner sc = UserLogin.sc;
	
	//Defining the attributes of the class which will be used later.
	String username, password;
	
	//Creating the object of the User class which will deal with the database.
	User user = new User();
	
	//Creating the object of PostLogin class which will handle the next steps.
	PostLogin ps = new PostLogin();
	
	//Creating the integer variable to store the final state of the methods of User class.
	int state1;
	boolean state2;
	
	public void studentLogin() {
		
		System.out.println("--- Welcome, Please enter the following information to login ---");
		
		System.out.println();
		
		System.out.print("Enter the username: ");
		username = sc.nextLine();
		
		System.out.print("Enter the password: ");
		password = sc.nextLine();
		
		//Checking whether the user name and password is blank or not. If blank the user should not proceed.
		if(username.isBlank() || password.isBlank())
		{
			System.out.println("Username or Password cannot be empty. Try again.");
		}

		System.out.println();
		//Invoking the isExist() method of the User class with its object and storing in in state variable which will return boolean value.
		state1 = user.isExist(username, password);
		
		if(state1 == 0)
		{
			System.out.println("___ Login Successful ___");
			System.out.println();
			ps.loginSuccessful();
		}
		else if(state1 == 2)
		{
			System.out.println("No user exist with the entered user name !");
		}
		else
		{
			System.out.println("Incorrect Username Or Password... \nPlease try again later");
		}
	}

	public void studentRegister() {
		System.out.println("--- Welcome, Please enter the following information to register ---");
		System.out.println();
		
		System.out.print("Enter the username: ");
		username = sc.nextLine();
		
		System.out.println();
		
		System.out.print("Enter the password: ");
		password = sc.nextLine();
		
		//Invoking the isExist() method of the User class with its object and storing in in state variable which will return boolean value.
		state2 = user.registerUser(username, password);
		
		if(state2)
		{
			System.out.println("___ Registration Successful ___");
			System.out.println();
			ps.registrationSuccessful(username);
		}
		else
		{
			System.out.println("Unable to register at this moment ... \nPlease try again later");
		}
	}

}
