package persistent.hibernateManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Rating;
import persistent.interfaces.RatingManagerInterface;

public class RatingManager extends PersistentManager implements RatingManagerInterface{

	public RatingManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public boolean addRating(Rating rating){
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.save(rating);
			
		} catch (Exception e){
			if(t != null)
				t.rollback();
		}
		t.commit();
		
		return true;
	}
	
}
