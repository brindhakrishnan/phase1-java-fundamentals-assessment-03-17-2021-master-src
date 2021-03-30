package com.simplilearn.lockedme;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Login {
	
	//input data
	private static Scanner keyboard;
	private static Scanner lockerInput;
	//Users data
	protected static Users users;
	public static String path = "/home/brindhakrishnan/eclipse-workspace/phase1-java-fundamentals-assessment-03-17-2021-master-src/database/";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		users = new Users();
		welcomeScreen();
		signInOptions();

	}
	
	//Create a welcome page with the below requirements:
	//	* Code to display the welcome screen. It should display:
	//		** Application name and the developer details 
	//		** The details of the user interface such as options displaying the user interaction information 
	//		** Features to accept the user input to select one of the options listed 
	
	public static void welcomeScreen() {
		System.out.println("=========================================");
		System.out.println("   *                              *");
		System.out.println("   *   Welcome To LockedMe.com    *");
		System.out.println("   * Your Personal Digital Locker *");
		System.out.println("   *                              *");
		System.out.println("=========================================");
		System.out.println("* Developed By : Brindha Krishnan *");
		System.out.println("=========================================");
		
	}
	
	//Display the Sign In Options for the user to select on the Login page
	public static void signInOptions() {
		
		//read data from keyboard
		keyboard = new Scanner(System.in);
		
		System.out.println("1. Register! If you're a New User");
		System.out.println("2. Login! If you're already a Member ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : if(registerUser()) {
				loginUser();
			}
					break;
			case 2 : loginUser();
					break;
			default :
				System.out.println("Please select 1 OR 2");
				break;
		}

		keyboard.close();
		
	}
	
	public static boolean registerUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO REGISTRATION PAGE	*");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		
		System.out.println("Enter Username :");
		String username = keyboard.next();
		users.setUsername(username);
				
		System.out.println("Enter Password :");
		String password = keyboard.next();
		users.setPassword(password);
		
		// Create a New File under the registered user
		if(createLockedMeFolder(users.getUsername())) {
		// Store the LockedMe login creds into a database file
			storeLockedMeCredentials(users.getUsername(),users.getPassword()); 	
			return true;
		}
		return false;
	}
	
	//Function that displays the login page
	public static void loginUser() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		//Get the username from user input
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		users.setUsername(inpUsername);
		
		//Get the password from user input
		System.out.println("Enter Password :");
		String inpPassword = keyboard.next();
		users.setPassword(inpPassword);
		
		
		boolean found = false;
		
		//if the credentials are correct for a registered user, then login is successful
		
		if(validateUserCredential(users.getUsername(),users.getPassword())) {
			found = true;
		}
		else {
			found = false;
		}
		
		if (found) {
			LockedMe.lockerOptions(users.getUsername());
		}
		
	}
	
	//Function that validates the LockedMe app credentials with the database file
	private static boolean validateUserCredential(String username,String password) {
		File dbfile = new File(path+"database.txt");
		
		try {
		lockerInput = new Scanner(dbfile);
		}catch(FileNotFoundException e) {
			System.out.println("404 : File Not Found ");
		}
		
		String credentials = username+","+password;
		while(lockerInput.hasNext()) {
			if(lockerInput.next().equals(credentials)) {
				System.out.println("Login Successful!");
				return true;
				 }
			}
		System.out.println("Login Failed! Invalid Username or Password");
		return false;
		
	}
		
	//Function that stores a database file which contains LockMe app credentials for all registered users
	private static void storeLockedMeCredentials(String username, String password) {
		
		File file = new File(path+"database.txt");
				
		//create new file
		try {
			
			if(file.createNewFile()) {
			
			} 
			// Write Content
			FileWriter writer = new FileWriter(file,true);
			writer.append(username+","+password+"\n");
			writer.close();
		
		} catch (IOException e) {
			System.out.println("Database entry failed!");
		}
			
	}
	
	public static boolean createLockedMeFolder(String filename){
		
		File file = new File(path+filename);
		//create new folder in the path
		boolean folder = file.mkdir();
		
			try {
			
				if(folder) {
					System.out.println("User Registration Successful !");
					return true;
					
				} else {
					System.out.println("User already exists. Try again !");
					throw new IOException("User Registration Failed !");
					//return false;
				}
								
				} catch (IOException e) {
					System.out.println("User Registration Failed !");
					return false;
				}
		}
	
}
