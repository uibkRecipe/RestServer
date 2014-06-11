package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Country;
import persistent.interfaces.CountryManagerInterface;



/**
 * COUNTRY = READ-ONLY
 * @author mirko
 *
 */
public class CountryManager extends PersistentManager implements CountryManagerInterface{
	
	public CountryManager(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Country> getCountryList(){
		List<Country> lc = new ArrayList<Country>();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session.createSQLQuery("SELECT * FROM COUNTRY").addEntity(Country.class);
			lc = (List<Country>)query.list();
		} catch(Exception e){
			e.printStackTrace();
			t.rollback();
		}
		t.commit();
		
		return lc;
	}
	

	
	
	public Country findCountryByCode(String countryCode){
		
		Session session = sessionFactory.openSession();
		Country c = null;
		Transaction t = session.beginTransaction();
		try {
			c = (Country) session.get(Country.class, countryCode);
		} catch (Exception e) {
			System.out.println("Country" + countryCode + "could not be found");
			t.rollback();
		} 
		session.close();
		return c;
	}
	
	public List<String> findCountryByName(String countryName){
		List<String> lz = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session.createSQLQuery("SELECT NAME FROM COUNTRY WHERE"
					+ " name LIKE '" + countryName + "%'");
			lz = query.list();
		} catch(Exception e){
			t.rollback();
		}
		return lz;
	}
	
	public String  findCountryCodeByName(String countryName){
		List<String> lz = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session.createSQLQuery("SELECT CODE FROM COUNTRY WHERE"
					+ " name LIKE '" + countryName + "%'");
			lz = query.list();
		} catch(Exception e){
			t.rollback();
		}
		if(!lz.isEmpty())
			return lz.get(0);
		else 
			return null;
	}



	
	
		
}
	
	
	
	
	

