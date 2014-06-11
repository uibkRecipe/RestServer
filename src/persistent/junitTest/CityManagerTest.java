package persistent.junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import persistent.classes.City;

public class CityManagerTest  extends PersistentManagerTest{
	
	

	@Test 
	/**
	 * This test check if the same city, that in the DB
	 * is saved with two different names (for instance the Italian one 
	 * and the German identifies the same city)
	 */
	public void twoSameCityTest(){
		assertEquals(hm.findCityByName("Bolzano").get(0), hm.findCityByName("Bozen").get(0));
		assertEquals(hm.findCityByName("Bozen"), hm.findCityByName("Bozen"));
	}


	@Test
	public void findCityByIDTest() {
		City c = hm.findCityByName("Bolzano").get(0);
		assertEquals(c, hm.findCityByID(c.getID()));
	}


	


	
	
}
