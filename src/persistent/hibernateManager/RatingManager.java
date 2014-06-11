package persistent.hibernateManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Rating;
import persistent.classes.Recipe;
import persistent.interfaces.RatingManagerInterface;

public class RatingManager extends PersistentManager implements RatingManagerInterface{

	public RatingManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public boolean addRating(Rating rating){
		if(rating == null)
			return false;
		int star = rating.getStar();
		int recipeID = rating.getRecipeID();
		boolean success = true;
		Transaction t = null;
		Session s = null;
		try {
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			Recipe recipe = HibernateUtil.getInstance().findRecipeById(recipeID);
			if(recipe == null)
				throw new Exception();
			int numberOfRatings = recipe.getNumberOfRatings();
			float averageRating = recipe.getAverageRating();
			recipe.setAverageRating((averageRating * numberOfRatings + star)/(numberOfRatings + 1));
			recipe.setNumberOfRatings(numberOfRatings + 1);
			s.saveOrUpdate(recipe);
			s.saveOrUpdate(rating);
			t.commit();
		} catch(Exception e){
			success = false;
			t.rollback();
		} finally {
			s.close();
		}
		return success;
	}

	
	
	
	
}
