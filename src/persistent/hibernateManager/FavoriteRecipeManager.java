package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.FavoriteRecipe;
import persistent.classes.IngredientType;
import persistent.classes.Recipe;
import persistent.interfaces.FavoriteRecipeManagerInterface;

public class FavoriteRecipeManager extends PersistentManager implements FavoriteRecipeManagerInterface   {
	
	public FavoriteRecipeManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> findFavoriteRecipe(String username) {
		Transaction t = null;
		Session session = null;
		List<Recipe> lr = new ArrayList<Recipe>();
		try {
			session = sessionFactory.openSession();
			t = session.beginTransaction();
			String query = "SELECT DISTINCT r.* FROM FAVORITERECIPE AS fr JOIN RECIPE r ON fr.RECIPEID=r.ID WHERE USER='" + username + "'";
			lr = (List <Recipe>) session.createSQLQuery(query).addEntity("r", Recipe.class).list();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
			lr = null;
		}
		return lr;
	}


	@Override
	public boolean addFavoriteRecipe(int recipeID, String username) {
		return addFavoriteRecipe(new FavoriteRecipe(recipeID, username));
	}


	@Override
	public boolean addFavoriteRecipe(FavoriteRecipe fr) {
		Transaction t = null;
		Session session = null;
		boolean success  = true;
		try {
			session = sessionFactory.openSession();
			t = session.beginTransaction();
			session.save(fr);
			t.commit();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
			success  = false;
		}
		return success;
	}
	
	

}
