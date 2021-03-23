package com.simplilearn.lockedme;

import java.util.Scanner;

public class LockedMe {
	
	private static Scanner keyboard;
	private static Scanner input;
	
	public static void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORED CREDENTIALS ");
		int option = keyboard.nextInt();
		switch(option) {
			case 1 : 
			//	fetchCredentials(inpUsername);
				break;
			case 2 :
			//	storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
	//	lockerInput.close();
	}

}
