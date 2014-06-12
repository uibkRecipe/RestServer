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

	@Override
	public Region findRegionByCountryAndRegionName(String code, String name) {
		List<Region> regions = findRegionByCountryCode(code);
		String n = name.toUpperCase();
		for (Region region : regions) {
			String tmp = region.getName().toUpperCase();
			if(tmp.length() >= n.length()){
				tmp = tmp.substring(0, n.length());
				if(n.equals(tmp)){
					System.out.println("YES");
					return region;
				}
			}
		}
		return null;
	}
}
