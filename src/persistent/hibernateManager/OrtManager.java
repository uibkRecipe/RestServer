package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import persistent.classes.Ort;
/**
 * 
 * @author mirko
 *
 */
public class OrtManager {
	Session session;
	
	/**
	 * Constructor. Create a OrtManager
	 * @param session
	 */
	public OrtManager(Session session){
		this.session = session;
	}
	
	/**
	 * Add an ort into the table "ort" of the database
	 * @param o the ort to add
	 * @return true on success false otherwise
	 */
	public boolean addOrt(Ort o){
		boolean success = true;
		session.beginTransaction();
		/** If the object is not already contained **/
		try {
			session.save(o);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());;
			success = false;
		} finally {
			session.close();
		}
		return success;
	}
	/**
	 * Find an object by its id.
	 * @param ortId the id of the "ort"
	 * @return Ort on success otherwise null
	 */
	public Ort findOrtById(int ortId){
		Ort o = null;
		session.beginTransaction();
		try {
			o = (Ort) session.get(Ort.class, ortId);
		} catch (Exception e) {
			System.out.println("Ort " + o + "could not be found");
		} finally {
			session.close();
		}
		return o;
	}
	/**
	 * Search for an ortname with a LIKE search
	 * @param ortName string to match
	 * @return a list of orts that matches the string ortName
	 */
	public List<Ort> findOrtByName(String ortName){
		List<Ort> lo = new ArrayList<Ort>();
		session.beginTransaction();
		String toSearch = "%" + ortName + "%";
		try {
			Criteria crit = session.createCriteria(Ort.class);
			crit.add(Restrictions.ilike("OrtName", toSearch));
			lo = (List<Ort>)crit.list();
		} catch (Exception e){
			
		} finally {
			session.close();
		}
		return lo;
	}
	
}
