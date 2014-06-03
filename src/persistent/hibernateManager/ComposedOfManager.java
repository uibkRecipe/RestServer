package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.ComposedOf;
import persistent.classes.IngredientType;
import persistent.classes.Recipe;
import persistent.interfaces.ComposedOfManagerInterface;

public class ComposedOfManager extends PersistentManager implements ComposedOfManagerInterface{
	

	public ComposedOfManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	/**
	 * This help function save a row in the table COMPOSEDOF
	 * @param recipeID
	 * @param ingredientID
	 * @param quantity
	 * @return
	 */
	private boolean saveIngredientToRecipe(int recipeID, int ingredientID, String quantity){
		Transaction t = null;
		boolean success = true;
		try {
			Session session = sessionFactory.openSession();
			t = session.beginTransaction();
			
				
			session.save(new ComposedOf(recipeID, ingredientID, quantity));
				
		} catch(Exception  e){
			if(t != null)
				t.rollback();
			success = false;
			e.printStackTrace();
		}
		if(t != null)
			t.commit();
		return success;
	}
	
	
	public boolean addIngredientToRecipe(int recipeID, List<IngredientType> ingredients,
			List<String> quantity){
		boolean success = true;
		for(int i = 0; i < ingredients.size(); i++){
			success &= saveIngredientToRecipe(recipeID, ingredients.get(i).getID(), quantity.get(i));
		}
		
		return success;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IngredientType> getIngredients(int recipeID) {
		
		List<IngredientType> ingredientTypeList = new ArrayList<IngredientType>();
		Transaction t = null;
		try {
			Session session = sessionFactory.openSession();
			t = session.beginTransaction();
				
			String query = "SELECT i.* from INGREDIENTTYPE as i join COMPOSEDOF as co ON i.ID=co.INGREDIENTTYPEID AND co.RECIPEID='" + recipeID + "'";
			
			ingredientTypeList = (List<IngredientType>) session.createSQLQuery(query).addEntity("i", IngredientType.class).list();
			
			
		} catch(Exception  e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return ingredientTypeList;
	}

	@Override
	public List<Recipe> findRezeptByIngredient(List<IngredientType> lz) {
		if(lz.isEmpty()){
			System.err.println("The given list is empty");
			return null;
		}
		
		StringBuilder query = new StringBuilder("SELECT DISTINCT r.* FROM COMPOSEDOF AS c JOIN RECIPE r ON c.RECIPEID=r.ID WHERE ");
		for(int i = 0; i < lz.size(); i++){
			
			query.append(" c.INGREDIENTTYPEID='" + lz.get(i).getID() + "'");
			if(lz.size() > i+1){
				query.append(" OR ");
			}
		}
		System.out.println(query.toString());
		Session session = sessionFactory.openSession();
		Transaction t = null;
		List<Recipe> lr = new ArrayList<>();
		try {
			t = session.beginTransaction();
			lr = (List<Recipe>) session.createSQLQuery(query.toString()).addEntity("Recipe", Recipe.class).list();
		} catch (Exception e){
			if(t != null)
				t.rollback();
		}
		session.close();
		return lr;
	}
	

}
