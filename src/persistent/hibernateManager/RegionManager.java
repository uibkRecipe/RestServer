package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Region;
import persistent.interfaces.RegionManagerInterface;

public class RegionManager extends PersistentManager implements RegionManagerInterface{
	public RegionManager(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Region> findRegionByCountryCode(String Code){
		Session session = sessionFactory.openSession();
		Transaction t  = null;
		List<Region> regionList = new ArrayList<>();
		try {
			t = session.beginTransaction();
			regionList = (List<Region>) session.createSQLQuery("SELECT r.* FROM REGION r  WHERE r.COUNTRY='" + Code +"'").addEntity("r", Region.class).list();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return regionList;
	}
}
