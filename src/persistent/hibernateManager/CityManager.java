package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.City;
import persistent.classes.Country;
import persistent.interfaces.CityManagerInterface;
/**
 * 
 * @author mirko
 *
 */
public class CityManager extends PersistentManager implements CityManagerInterface
  {
	
	
	/**
	 * Constructor. Create a OrtManager
	 * @param session
	 */
	public CityManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	/**
	 * Please don't use this method.. there are all the cities
	 * in the database.
	 * Add a city into the table "CITY" of the database
	 * @param city the city to add
	 * @return true on success false otherwise
	 */
	public boolean addCity(City city){
		boolean success = true;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/** If the object is not already contained **/
		try {
			session.save(city);
			
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());;
			success = false;
			session.getTransaction().rollback();
		} 
		session.getTransaction().commit();
		session.close();
		return success;
	}
	/**
	 * Find an object by its id.
	 * @param ortId the id of the "ort"
	 * @return Ort on success otherwise null
	 */
	public City findCityByID(int cityID){
		City city = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			city = (City) session.get(City.class, cityID);
		} catch (Exception e) {
			System.out.println("City " + city + "could not be found");
		} finally {
			session.close();
		}
		return city;
	}
	/**
	 * Search for an ortname with a LIKE search
	 * @param ortName string to match
	 * @return a list of orts that matches the string ortName
	 */
	@SuppressWarnings("unchecked")
	public List<City> findCityByName(String cityName){
		List<City> lo = new ArrayList<City>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		String toSearch = "'" + cityName + "%'";
		try {
			t = session.beginTransaction();
			lo = (List<City>) session.createSQLQuery("SELECT {City.*} FROM CITY {City} WHERE NAME LIKE " + toSearch)
				    .addEntity("City", City.class).list();
				
		} catch (Exception e){
			if(t != null)
				t.rollback();
		} finally {
			session.close();
		}
		return lo;
	}
	@Override
	public List<City> findCityNameByCountryAndRegion(String country,
			String region) {
		List<City> cityList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			cityList = (List<City>) session.createSQLQuery("SELECT c.* FROM CITY c WHERE c.COUNTRY='" + country +"' AND c.REGION='" + region + "'").addEntity("c", City.class).list();
		} catch (Exception e){
			if(t != null){
				t.rollback();
			}
			e.printStackTrace();
					
		}
		return cityList;
		
	}
	
	
	
	
}
