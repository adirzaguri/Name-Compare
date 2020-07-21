package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Utilities {

	
	//calculates the Levenshtein distance between two strings 
	public static int LevenshteinDistance(String x, String y) {
	    int[][] dp = new int[x.length() + 1][y.length() + 1];
	 
	    for (int i = 0; i <= x.length(); i++) {
	        for (int j = 0; j <= y.length(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }
	 
	    return dp[x.length()][y.length()];
	}
	
	private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
 
	
	 private static int min(int... numbers) {
	        return Arrays.stream(numbers)
	          .min().orElse(Integer.MAX_VALUE);
	    }
	 
	 
	 // load the csv file to Hashmap - K:name , V:HashSet that contains the nicknames
	 public static HashMap<String , HashSet<String>> LoadCSVtoMap(String Path){
		 
		  HashMap<String, HashSet<String>> namesToNicknames = new HashMap<String, HashSet<String>>();
		  

		  try {
			  BufferedReader input = new BufferedReader(new FileReader(Path));
			  String line = null;
			  while ((line = input.readLine()) != null) {
				String[] names=line.split(",");
				String key=names[0];  
				HashSet<String> values = new HashSet<String>();
				for(String s:names) {
					values.add(s);
				}
				namesToNicknames.put(key, values);
			
			  }
			  
		  } catch (IOException ex) {
			  ex.printStackTrace();	
			  }

		        
		 return  namesToNicknames;
		 
	 }
	 
}

