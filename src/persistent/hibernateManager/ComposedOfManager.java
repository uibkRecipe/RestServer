package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.ComposedOf;
import persistent.classes.IngredientType;
import persistent.classes.Recipe;
import persistent.classes.RecipeIngredients;
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
				
			if(t != null)
				t.commit();
			
		} catch(Exception  e){
			if(t != null)
				t.rollback();
			success = false;
			e.printStackTrace();
		}
		
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
	public RecipeIngredients getIngredients(int recipeID) {
		RecipeIngredients ri = new RecipeIngredients();
		List<IngredientType> ingredientTypeList = new ArrayList<IngredientType>();
		List<String> quantity;
		Transaction t = null;
		try {
			Session session = sessionFactory.openSession();
			t = session.beginTransaction();
				
			String query = "SELECT i.* from INGREDIENTTYPE as i join COMPOSEDOF as co ON i.ID=co.INGREDIENTTYPEID AND co.RECIPEID='" + recipeID + "'";
			
			ingredientTypeList = (List<IngredientType>) session.createSQLQuery(query).addEntity("i", IngredientType.class).list();
			query = "SELECT co.QUANTITY from INGREDIENTTYPE as i join COMPOSEDOF as co ON i.ID=co.INGREDIENTTYPEID AND co.RECIPEID='" + recipeID + "'";
			quantity = (List<String>) session.createSQLQuery(query).list();
			if(ingredientTypeList.size() == quantity.size()){
				ri.setIngredients(ingredientTypeList);
				ri.setQuantities(quantity);
			}
			else {
				return null;
			}
				
		} catch(Exception  e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return ri;
	}
	
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public List<Recipe> findRecipeByIngredient(List<IngredientType> lz) {
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

	
	@SuppressWarnings("unchecked")
	public List<Recipe> execFindrecipeByIngredientQuery(String query){
		List<Recipe> recipes = new ArrayList<>();
		Transaction t = null;
		try {
			Session session = sessionFactory.openSession();
			t = session.beginTransaction();
				
			
			
			recipes = (List<Recipe>) session.createSQLQuery(query).addEntity("r", Recipe.class).list();
			
			
		} catch(Exception  e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
		return recipes;
		
		
	}
	
	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1) {
		String query = "SELECT DISTINCT r.* FROM COMPOSEDOF AS c JOIN RECIPE r ON c.RECIPEID=r.ID WHERE c.INGREDIENTTYPEID='" + ingredient1 + "'";
		return execFindrecipeByIngredientQuery(query);
	}


	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1, int ingredient2){
		String query = "SELECT DISTINCT r.* FROM COMPOSEDOF AS c JOIN RECIPE r ON c.RECIPEID=r.ID WHERE c.INGREDIENTTYPEID='" + ingredient1 + "' OR  c.INGREDIENTTYPEID='" + ingredient2 + "'";
		return execFindrecipeByIngredientQuery(query);
	}


	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1,
			int ingredient2, int ingredient3) {
		String query = "SELECT DISTINCT r.* FROM COMPOSEDOF AS c JOIN RECIPE r ON c.RECIPEID=r.ID WHERE c.INGREDIENTTYPEID='" + ingredient1 + "' OR  c.INGREDIENTTYPEID='" + ingredient2 + "'" 
				+ " OR c.INGREDIENTTYPE='" + ingredient3 + "'";
		return execFindrecipeByIngredientQuery(query);
	}
	

}
