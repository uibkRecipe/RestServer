package persistent;

import persistent.hibernateManager.HibernateUtil;


public class Test {
	
	/**
	 * Use mock for the DB test
	 * @param args
	 */
	public static void main(String[] args) {
	
	
		HibernateUtil hm = HibernateUtil.getInstance();
		
	
		hm.addFriend("testUser", "testUser1");
		
		hm.addFriend("testUser", "TEST_ACTIVE_USER");
		
		hm.addFriend("testUser", "TEST_NOT_ACTIVE_USER");
		
		hm.closeSession();
	}
}