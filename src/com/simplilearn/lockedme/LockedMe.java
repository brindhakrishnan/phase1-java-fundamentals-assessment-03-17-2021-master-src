package com.simplilearn.lockedme;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.String;

public class LockedMe {
	
	private static Scanner appKeyboard;
	private static Scanner appInput;
	private static String path = "/home/brindhakrishnan/eclipse-workspace/phase1-java-fundamentals-assessment-03-17-2021-master-src/database/";
	
	public static void lockerOptions(String inpUsername) {
		
		appInput = new Scanner(System.in);
		int mainoption;

		do {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   LOCKEDME.COM - THE SAFEST WAY TO STORE CREDENTIALS	*");
		System.out.println("*					*");
		System.out.println("==========================================");
			
		System.out.println("1. Retrieve All Details ");
		System.out.println("2. Manage Credentials ");
		System.out.println("3. Logout ");
		mainoption = appInput.nextInt();
		switch(mainoption) {
			case 1 : 
				retrieveDetails(inpUsername);
				break;
			case 2 :
				manageCredentials(inpUsername);
				break;
			case 3 : 
				System.out.println("Exiting the Application");
				break;
			
			default :
				System.out.println("Please select 1 to 3");
				break;
		}
		}while(mainoption != 3);
				
		appInput.close();
	}
	
		private static boolean manageCredentials(String loggedInUser) {
			appInput = new Scanner(System.in);
			int suboption;
			
			do {
				
				System.out.println("==========================================");
				System.out.println("*					*");
				System.out.println("*   LOCKEDME.COM - THE SAFEST WAY TO STORE CREDENTIALS	*");
				System.out.println("*					*");
				System.out.println("==========================================");
					

				System.out.println("1. Add Credentials ");
				System.out.println("2. Delete Credentials ");
				System.out.println("3. Search Credentials ");
				System.out.println("4. Main Menu ");
				suboption = appInput.nextInt();
				switch(suboption) {
					case 1 :
						storeCredentials(loggedInUser);
						break;
					case 2 : 
						removeCredentials(loggedInUser);
						break;
					case 3 :
						fetchCredentials(loggedInUser);
						break;
					case 4 : 
						break;
					
					default :
						System.out.println("Please select 1 through 4");
						break;
				}
				}while(suboption != 4);
			return true;
		
		}
	
	//Business Specification - Retrieving the file names in an ascending order
		private static void retrieveDetails(String loggedInUser) {
			
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
			/*
			//File foldersearch = new File(path+"/"+loggedInUser);
			//String[] filenames = foldersearch.list();
			
			//2. Compare if the details entered by the user match
			
			for(String f : filenames) {
				if(f.equalsIgnoreCase(credfile)) {
					credfile = f;
				}
			}
			*/
			try {
					appFile = new File(path+loggedInUser+"/"+credfile);				
					if(appFile.createNewFile()) {
						System.out.println("New application entry created!");
						//if its an existing application credentials then overwrite existing file	
					}else {
						System.out.println("Failed to save credentials!");
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
		
		//Business Specification - Option to delete a user specified file to the application
		private static boolean removeCredentials(String loggedInUser) {
			//read data from keyboard
			appKeyboard = new Scanner(System.in);
	
			//Get the name of the application whose credentials needs to be deleted
			System.out.println("Enter the Application name whose credential needs to be deleted:");
			String application = appKeyboard.next();
			String credfile = application+".txt";
			File appFile = new File(path+loggedInUser+"/"+credfile);
			
			//1. Search all the files in the folder
			
			File foldersearch = new File(path+"/"+loggedInUser);
			String[] filenames = foldersearch.list();
			
			//2. Compare if the details entered by the user match - Linear Search

			for(String f : filenames) {
				if(f.equals(credfile)) {
					appFile.delete();
					System.out.println("Credentials deleted!");
					return true;
				}
			
			 }
	
				System.out.println("404 : File Not Found!");
				return false;
		}
		
		//Business Specification - Option to search a user specified file from the application
		private static void fetchCredentials(String loggedInUser) {
			//read data from keyboard
			appKeyboard = new Scanner(System.in);
			Scanner fileInput;
	
			//Get the name of the application whose credentials needs to be retrieved
			System.out.println("Enter the Application name whose credential needs to be retrieved:");
			String application = appKeyboard.next();
			String credfile = application+".txt";
			File appFile = new File(path+loggedInUser+"/"+credfile);
			
			//1. Search all the files in the folder
			
			File foldersearch = new File(path+"/"+loggedInUser);
			String[] filenames = foldersearch.list();
			
			//2. Compare if the details entered by the user match - Linear Search
			
				for(String f : filenames) {
					//if Filenames match then create a Scanner to read the file
					if(f.equals(credfile)) {
						try {
							fileInput = new Scanner(appFile);
							
							//Print the credentials stored in the file
							System.out.println("Credentials are :: ");
							while(fileInput.hasNext()) {
								System.out.println(fileInput.next());
								}
							fileInput.close();
							//return true;
							
							}catch(FileNotFoundException e) {
								System.out.println("404 : File Not Found ");
							}
					}
				}
				
			}
}
