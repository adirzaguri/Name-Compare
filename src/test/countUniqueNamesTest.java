package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.uniqueNamesCounter;

class countUniqueNamesTest {

	@Test
	void testCountUniqueNames() {
		assertEquals(1,uniqueNamesCounter.countUniqueNames("Deborah","Egli","Deborah","Egli","Deborah Egli"),"expected 1");
		assertEquals(1,uniqueNamesCounter.countUniqueNames("Deborah","Egli","Debbie","Egli","Debbie Egli"),"expected 1");
		assertEquals(1,uniqueNamesCounter.countUniqueNames("Deborah","Egni","Deborah","Egli","Deborah Egli"),"expected 1");
		assertEquals(1,uniqueNamesCounter.countUniqueNames("Deborah S","Egli","Deborah","Egli","Egli Deborah"),"expected 1");
		assertEquals(2,uniqueNamesCounter.countUniqueNames("Michele","Egli","Deborah","Egli","Michele Egli"),"expected 2");		
		
	}
	
	@Test
	void AdditionalTestCountUniqueNames() {
		assertEquals(2,uniqueNamesCounter.countUniqueNames("DEBORAH","Egli","Michele","Egli","Deborah Egli"),"expected 2");//Case sensitive
		assertEquals(1,uniqueNamesCounter.countUniqueNames("deB","Egni","DeborAH a","Egli","Deborah Egli"), "expected 1");//Case sensitive
		
		assertEquals(1,uniqueNamesCounter.countUniqueNames("deb b","Egni","Deborah a","Egli","Deborah Egli"), "expected 1");// middle names
		assertEquals(1,uniqueNamesCounter.countUniqueNames("deb a b","Egni","Deborah a","Egli","Deborah Egli"), "expected 1");// middle names
		
		assertEquals(1,uniqueNamesCounter.countUniqueNames("deb","Egni","Deborahr","Egli","Deborah Egli"), "expected 1");// typos
		assertEquals(1,uniqueNamesCounter.countUniqueNames("deb","Egni","Debora","Egli","Egli Deborah"), "expected 1");// typos
		
		assertEquals(3,uniqueNamesCounter.countUniqueNames("Deborah S","Egli","dave","Egli","Egli dan"), "expected 3"); // 3 different names
		
		assertEquals(2,uniqueNamesCounter.countUniqueNames("Michele","Egli","Deborah","Egli","Egli Michele"));//crossed name on card		
	}

	
}
