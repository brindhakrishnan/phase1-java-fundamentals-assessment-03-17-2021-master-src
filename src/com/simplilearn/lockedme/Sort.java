package com.simplilearn.lockedme;

public class Sort {
	
	public static String temp;
	
	public static String[] stringSort(String[] sortString) {
		
		for (int i = 0; i < sortString.length; i++) 
        {
            for (int j = i + 1; j < sortString.length; j++) { 
                if (sortString[i].compareTo(sortString[j])>0) 
                {
                    temp = sortString[i];
                    sortString[i] = sortString[j];
                    sortString[j] = temp;
                }
            }
        }
		
		return sortString;
	}

}
