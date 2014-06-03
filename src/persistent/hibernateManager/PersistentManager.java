package persistent.hibernateManager;

import org.hibernate.SessionFactory;

public abstract class PersistentManager {
	
	protected SessionFactory sessionFactory;
	/**
	 * Constructor of the persistent manager classes
	 * @param sessionFactory
	 */
	public PersistentManager(SessionFactory sessionFactory){
		
		this.sessionFactory = sessionFactory;
	}
}
