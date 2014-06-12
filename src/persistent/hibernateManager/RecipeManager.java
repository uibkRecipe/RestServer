package persistent.hibernateManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Recipe;
import persistent.interfaces.RecipeManagerInterface;

public class RecipeManager extends PersistentManager implements RecipeManagerInterface{
	
	public RecipeManager(SessionFactory sessionFactory) {
		super(sessionFactory);
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

	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByCategory(String category) {
		if(category.equals("") || category == null)
			return findRecipeByCategory();
		List<Recipe> lr = new ArrayList<Recipe>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t  = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT * FROM RECIPE  WHERE CATEGORY='" + category + "'").addEntity(Recipe.class).list();
		} catch (Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return lr;
	}

	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByCategory() {
		List<Recipe> lr = new ArrayList<Recipe>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT r.* FROM RECIPE r").addEntity("r", Recipe.class).list();
		} catch (Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return lr;
	}
	


	public List<Recipe> getAllRecipes(){
		return findRecipeByCategory();
	}


	@Override
	public boolean setRecipeFoto(String username, int recipeID, File f) {
	
		if(f == null){
			return false;
		}
		Recipe r = findRecipeById(recipeID);
		
		Session session = sessionFactory.openSession();
		boolean success = true;
		byte [] binaryFile = new byte [(int)f.length()];
		Transaction t = null; 
		try {
			t = session.beginTransaction();
			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(binaryFile);
			fileInputStream.close();
		} catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		r.setFoto(binaryFile);
		
		session.saveOrUpdate(r);
		session.getTransaction().commit();
			
		return success;
		}




	@Override
	public Recipe findRecipeById(int recipeID) {
		Session session = sessionFactory.openSession();
		Transaction t = null;
		Recipe r = null;
		try {
			
			t = session.beginTransaction();
			r  = (Recipe) session.get(Recipe.class, recipeID);
		} catch(Exception e){
			if(t != null)
				t.rollback();
		}
		
		return r;
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByTime(int time){
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Recipe> lr  = new ArrayList<>();
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT * FROM RECIPE WHERE TIME <= '" + time + "'");
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return lr;
	}
	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByTime(int mintime, int maxtime){
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Recipe> lr  = new ArrayList<>();
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT * FROM RECIPE WHERE TIME >= '" + mintime + "' AND TIME <= '" + maxtime + "'").addEntity(Recipe.class).list();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return lr;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Recipe> findRecipeByName(String name){
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Recipe> lr  = new ArrayList<>();
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery("SELECT * FROM RECIPE WHERE NAME LIKE '%" + name + "%'").addEntity(Recipe.class).list();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return lr;
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
			e.printStackTrace();
			lr = null;
			t.rollback();
		}
		
		session.close();
		
		return lr;
		
	}








	@Override
	public boolean addCooked(int recipeID) {
		boolean success = true;
		Session s = null;
		Transaction t = null;
		try {
			Recipe r = findRecipeById(recipeID);
			s = sessionFactory.openSession();
			t = s.beginTransaction();
			int cooked = r.getCooked();
			r.setCooked(cooked+1);
			s.saveOrUpdate(r);
			if(t != null)
				t.commit();
		} catch(Exception e) {
			if(t != null)
				t.rollback();
			e.printStackTrace();
			success = false;
		}
		return success;
	}


	
}
