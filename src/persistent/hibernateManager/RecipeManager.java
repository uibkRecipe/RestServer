package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Category;
import persistent.classes.Recipe;
import persistent.interfaces.RecipeManagerInterface;

public class RecipeManager extends PersistentManager implements RecipeManagerInterface{
	
	public RecipeManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByAutor(String username){
		List<Recipe> lr = new ArrayList<Recipe>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT {Recipe.*} FROM RECIPE {Recipe} WHERE AUTOR='" + username + "'")
				    .addEntity("Recipe", Recipe.class).list();
		} catch (Exception e){
			
			lr = null;
			t.rollback();
		}
		
		session.close();
		
		return lr;
		
	}
	
	
	/**
	 * Add a Recipe in the DB.
	 * @param r the recipe to add
	 * @return true on success; false otherwise;
	 */
	public boolean addRecipe(Recipe r){
		boolean ret = true;
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
		
			session.save(r);
			
		} catch(Exception e){
			t.rollback();
			System.out.println("Error " + e.getMessage());
			ret = false;
			t.rollback();
		}
		t.commit();
		return ret;
		
	}
	/**
	 * ... Only the admin & the authors could remove recipes
	 */
	public boolean removeRecipe(String username, int recipeID){
		Transaction t = null;
		boolean isSuccessful = true;
		Session session = sessionFactory.openSession();
		try {
			t = session.beginTransaction();
			Recipe r = new Recipe();
			r.setID(recipeID);
			
			session.delete(r);
		} catch(Exception e){
			t.rollback();
			isSuccessful = false;
		}
		t.commit();
		return isSuccessful;
	}

	
	public List<Recipe> getRezeptByCategory(Category C) {
	
		return null;
	}

	
	public List<Recipe> getRezeptByCategory() {
		
		return null;
	}
	public byte [] getFoto(){
		// TO DO
		return new byte[0];
	}
	/*public String getTitle(){
		//TO DO
		
	}
	public String getDescriptionShort(){
		//Erster Satz 50 character
	}*/
}
