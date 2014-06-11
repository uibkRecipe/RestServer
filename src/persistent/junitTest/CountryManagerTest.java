package persistent.junitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountryManagerTest extends HibernateUtilJUnitTest {

	@Test
	public void getCountryList() {
		assertEquals(hm.getCountryList().size(), 245);
	}

	@Test
	public void findCountry() {
		assertEquals(hm.findCountryByCode("AT").getName(), hm.findCountryByName("AUSTRIA").get(0));
		assertEquals(hm.findCountryByCode("IT").getName(), hm.findCountryByName("ITALY").get(0));
	}

	
	
	
}
