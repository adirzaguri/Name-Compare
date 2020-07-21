package uniqueNames;

import java.util.HashMap;
import java.util.HashSet;

public class uniqueNamesCounter {

	static HashMap<String, HashSet<String>> Nicknames=utils.Utilities.LoadCSVtoMap("resources/Nicknames.csv");
	
	
	public static int countUniqueNames(String billFirstName,String billLastName,String shipFirstName,String shipLastName,String billNameOnCard) {
		
		
	}
	
	
	
	public static boolean IsSameNameButTypo(String name1,String name2) {
		return utils.Utilities.LevenshteinDistance(name1, name2)<=1;	
	}
	
	
	
	// checks if the names are same or Nicknames or Nickname but typo  ( the nicknames hashset of each name contains the name itself) 
	public static boolean IsSame(String name1,String name2) {
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
	
	
	
}
