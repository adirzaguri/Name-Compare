package utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

class LoadingCSVTEST {

	@Test
	void test() {
		HashMap<String, HashSet<String>> hm=utils.Utilities.LoadCSVtoMap("resources/Nicknames.csv");
		//assertEquals(3,hm.get("abe").size());
		//assertEquals("[ab, abner]", hm.get("abner").toString());
		assertEquals(true,hm.get("bartholomew").contains("mees"));
	}

}
