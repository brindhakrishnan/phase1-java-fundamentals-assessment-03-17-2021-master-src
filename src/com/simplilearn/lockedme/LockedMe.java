package com.simplilearn.lockedme;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;
import java.io.FileNotFoundException;

public class LockedMe {
	
	private static Scanner appKeyboard;
	private static Scanner appInput;
	private static String path = "/home/brindhakrishnan/eclipse-workspace/phase1-java-fundamentals-assessment-03-17-2021-master-src/database/";
		
	public static void lockerOptions(String inpUsername) {
		
		appInput = new Scanner(System.in);
		System.out.println("1. Retrieve All Details ");
		System.out.println("2. Add Credentials ");
		System.out.println("3. Delete Credentials ");
		System.out.println("4. Search Credentials ");
		System.out.println("5. Exit ");
		int option = appInput.nextInt();
		switch(option) {
			case 1 : 
			//	Function that gets the details of all applications 
				retrieveDetails(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			case 3 : 
				removeCredentials(inpUsername);
				break;
			case 4 :
			//	fetchCredentials(inpUsername);
				break;
			case 5 : 
			//	searchCredentials(inpUsername);
				break;
			
			default :
				System.out.println("Please select 1 through 5");
				break;
		}
//		System.out.println("Closing appInput scanner");
		appInput.close();
	}
	
	//Business Specification - Retrieving the file names in an ascending order
		private static void retrieveDetails(String loggedInUser) {
						
			System.out.println("==========================================");
			System.out.println("*					*");
			System.out.println("*   LOCKEDME.COM - THE SAFEST WAY TO STORE CREDENTIALS	*");
			System.out.println("*					*");
			System.out.println("==========================================");
			
			//Create a file object that points to the user folder
			File foldersearch = new File(path+"/"+loggedInUser);
			
			//get the list of filenames
			String[] filenames = foldersearch.list();
			
			//Sort the filenames in ascending order
			String[] sortedfilenames = Sort.stringSort(filenames);
			
			if(filenames.length > 0) {
			//Print the sorted files
			System.out.println("The sorted list of files under "+loggedInUser+" are ::");				
				for (String files : sortedfilenames) {
					System.out.println(files);
				}
						
			}else System.out.println("No credentials stored yet!");			
			
		}
		
	
	//Business Specification - Option to add a user specified file to the application
		private static void storeCredentials(String loggedInUser) {
			//read data from keyboard
			appKeyboard = new Scanner(System.in);
			File appFile;
						
			System.out.println("==========================================");
			System.out.println("*					*");
			System.out.println("*   LOCKEDME.COM - THE SAFEST WAY TO STORE CREDENTIALS	*");
			System.out.println("*					*");
			System.out.println("==========================================");
			
			//Get the name of the application whose credentials needs to be stored
			System.out.println("Enter the Application name whose credential needs to be stored:");
			String application = appKeyboard.next();
			String credfile = application+".txt";
			
			System.out.println("Enter Username :");
			String username = appKeyboard.next();
		
			System.out.println("Enter Password :");
			String password = appKeyboard.next();
			
			//******* Write a check for file exists with same name case sensitive here
			//1. Search all the files in the folder
			
			File foldersearch = new File(path+"/"+loggedInUser);
			String[] filenames = foldersearch.list();
			
			//2. Compare if the details entered by the user match
			
			for(String f : filenames) {
				if(f.equalsIgnoreCase(credfile)) {
					credfile = f;
				}
			}
			try {
					appFile = new File(path+loggedInUser+"/"+credfile);				
					if(appFile.createNewFile()) {
						System.out.println("New application entry created!");
						//if its an existing application credentials then overwrite existing file	
					}else {
						System.out.println("Overwriting existing credentials!");
					}
	
					// Write Content
					FileWriter writer = new FileWriter(appFile,false);
					writer.write(username+"\n"+password+"\n");
					writer.close();	
					//return true;
		
			}catch(IOException e) {
				System.out.println("Failed to save credentials!");
				e.printStackTrace();
		//		return false;
			}catch(Exception e) {
				e.printStackTrace();
		//		return false;
			}
						
		}
		
		//Business Specification - Option to add a user specified file to the application
		private static boolean removeCredentials(String loggedInUser) {
			//read data from keyboard
			appKeyboard = new Scanner(System.in);
			
			
			System.out.println("==========================================");
			System.out.println("*					*");
			System.out.println("*   LOCKEDME.COM - THE SAFEST WAY TO STORE CREDENTIALS	*");
			System.out.println("*					*");
			System.out.println("==========================================");
			
			//Get the name of the application whose credentials needs to be stored
			System.out.println("Enter the Application name whose credential needs to be deleted:");
			String application = appKeyboard.next();
			String credfile = application+".txt";
			File appFile = new File(path+loggedInUser+"/"+credfile);
			
			//1. Search all the files in the folder
			
			File foldersearch = new File(path+"/"+loggedInUser);
			String[] filenames = foldersearch.list();
			
			//2. Compare if the details entered by the user match

			for(String f : filenames) {
				if(f.equals(credfile)) {
					appFile.delete();
					System.out.println("Credentials deleted!");
					return true;
				}
			
			 }
	
				System.out.println("File Not Found!");
				return false;
		}
	
}
