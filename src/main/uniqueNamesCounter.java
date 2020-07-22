package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
//import java.util.Scanner;

public class uniqueNamesCounter {

	public static final HashMap<String, HashSet<String>> Nicknames=utils.Utilities.LoadCSVtoMap("resources/Nicknames.csv");
	
	
	public static int countUniqueNames(String billFirstName,String billLastName,String shipFirstName,String shipLastName,String billNameOnCard) {
		
		//" Decision tree"

		if(IsBillShipSame(billFirstName,billLastName,shipFirstName,shipLastName)) {
			if(IsOnCardNameSameBillOrShip(billFirstName, billLastName, billNameOnCard))
				if(IsOnCardNameSameBillOrShip(shipFirstName, shipLastName, billNameOnCard))
					return 1;
		}else {
			if(IsOnCardNameSameBillOrShip(billFirstName, billLastName, billNameOnCard)
					||IsOnCardNameSameBillOrShip(shipFirstName, shipLastName, billNameOnCard))
				return 2;
			else {
				return 3;
			}
		}
		return 2; //in any other case
	}
	
	
	//Checks if the Strings are the same but one of them with typo
	public static boolean IsSameNameButTypo(String name1,String name2) {
		return utils.Utilities.LevenshteinDistance(name1, name2)<=1;	
	}
	
	
	
	// Checks if the names are same or Nicknames or Nickname but typo  ( the nicknames hashset of each name contains the name itself) 
	public static boolean IsSame(String name1,String name2) {
		if(name1.equals(name2))
			return true;
		
		if(Nicknames.containsKey(name1))
			for(String s:Nicknames.get(name1))
				if(IsSameNameButTypo(s, name2))
				return true;
		
		if(Nicknames.containsKey(name2))
			for(String s:Nicknames.get(name2))
				if(IsSameNameButTypo(s, name1))
				return true;
		
		return false;
	}
	
	// "Compares"(by the conditions) billfirstname with shipfirstname, and billLastName with shipLastName
	public static boolean IsBillShipSame(String billFirstName,String billLastName,String shipFirstName,String shipLastName) {
		
		ArrayList<String> MiddleNamesBill=new ArrayList<String>();
		ArrayList<String> MiddleNamesShip=new ArrayList<String>();
		
		String[] namesArrayBill=billFirstName.split("\\s+");
		String[] namesArrayShip=shipFirstName.split("\\s+");
		
		String billFirst=namesArrayBill[0];
		String shipFirst=namesArrayShip[0];
		for(String s:namesArrayBill) {
			if(s.equals(billFirst))
				continue;
			MiddleNamesBill.add(s.toLowerCase());
		}
		
		for(String s:namesArrayShip) {
			if(s.equals(shipFirst))
				continue;
			MiddleNamesBill.add(s.toLowerCase());
		}
		
			
		
		if(!IsSame(shipFirst.toLowerCase(), billFirst.toLowerCase())) // comparing the first names
			return false;
		
		if(!IsSameNameButTypo(billLastName.toLowerCase(), shipLastName.toLowerCase()))//comparing the last names
			return false;
		
		if(MiddleNamesBill.size()==0||MiddleNamesShip.size()==0)
			return true;
		
		
		
		int len=Math.min(MiddleNamesBill.size(), MiddleNamesShip.size());
		//comparing the middle names
		for (int i = 0; i < len; i++) {
			if(!IsSameNameButTypo(MiddleNamesBill.get(i), MiddleNamesShip.get(i)))
				return false;
		}
		
		return true;
				
		}

	
	
	// Exports the first and last name from billNameOnCard - assumes the last name at the end of the String  
	public static String[] DivideOnCardNameFirstOption(String billNameOnCard,int lastNameNumberOfWords) {
		
		ArrayList<String> FirstAndLastOfOnCard=new ArrayList<String>();
		
		String[] NameOnCardArray=billNameOnCard.split("\\s+");
		
		StringBuilder sbLName = new StringBuilder(NameOnCardArray[NameOnCardArray.length-lastNameNumberOfWords]);
		int i = billNameOnCard.length()-lastNameNumberOfWords+1;
		while(i<billNameOnCard.length()) {
			sbLName.append(" ");
			sbLName.append(NameOnCardArray[i]);
			i++;
		}
		String OnCardLastName=sbLName.toString(); 
		
		
		StringBuilder sbFName = new StringBuilder(NameOnCardArray[0]);
		int it = billNameOnCard.length()-lastNameNumberOfWords;
		while(it<(billNameOnCard.length()-lastNameNumberOfWords)) {
			sbFName.append(" ");
			sbFName.append(NameOnCardArray[it]);
			it++;
		}
		String OnCardFirstName=sbFName.toString(); 
		
		FirstAndLastOfOnCard.add(OnCardFirstName);
		FirstAndLastOfOnCard.add(OnCardLastName);
		
		
		return (String[])FirstAndLastOfOnCard.toArray(new String[FirstAndLastOfOnCard.size()]);
	}
	
	// Exports the first and last name from billNameOnCard - assumes the last name at the start of the String
	public static String[] DivideOnCardNameSecondOption(String billNameOnCard,int lastNameNumberOfWords) {
		
		ArrayList<String> FirstAndLastOfOnCard=new ArrayList<String>();
		
		String[] NameOnCardArray=billNameOnCard.split("\\s+");
		
		StringBuilder sbLName = new StringBuilder(NameOnCardArray[0]);
		int i =1;
		while(i<lastNameNumberOfWords) {
			sbLName.append(" ");
			sbLName.append(NameOnCardArray[i]);
			i++;
		}
		String OnCardLastName=sbLName.toString(); 
		
		
		StringBuilder sbFName = new StringBuilder(NameOnCardArray[lastNameNumberOfWords]);
		int it = lastNameNumberOfWords;
		while(it<NameOnCardArray.length-1) {
			sbFName.append(" ");
			sbFName.append(NameOnCardArray[it]);
			it++;
		}
		String OnCardFirstName=sbFName.toString(); 
		
		FirstAndLastOfOnCard.add(OnCardFirstName);
		FirstAndLastOfOnCard.add(OnCardLastName);
		
		return (String[])FirstAndLastOfOnCard.toArray(new String[FirstAndLastOfOnCard.size()]);
	}

	
	// "Compares"(by the conditions) bill/ship first and last name  with billNameOnCard
	public static boolean IsOnCardNameSameBillOrShip(String FirstName,String LastName,String billNameOnCard) {		
		
		int lastNameNumberOfWords = LastName.split("\\s+").length;

		// returns String array of first and last name [firstName,lastName] with the first possibility
		String[] OnCardNameFirst=DivideOnCardNameFirstOption(billNameOnCard, lastNameNumberOfWords); 
		
		// returns String array of first and last name [firstName,lastName] with the second possibility
		String[] OnCardNameSecond=DivideOnCardNameSecondOption(billNameOnCard, lastNameNumberOfWords);
		
		//checks the 2 possibilities (once assumes that the first name is at the start and the last name at the end of billNameOnCard,
		// and once  assumes that the first name is at the end and the last name at the start of billNameOnCard).
		if(IsBillShipSame(FirstName, LastName, OnCardNameFirst[0], OnCardNameFirst[1]) // 
				||IsBillShipSame(FirstName, LastName, OnCardNameSecond[0], OnCardNameSecond[1])) {
			return true;
		}
		return false;
		
	}

}