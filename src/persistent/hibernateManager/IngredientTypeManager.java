package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.City;
import persistent.classes.Ingredient;
import persistent.classes.IngredientType;
import persistent.classes.Recipe;
import persistent.interfaces.IngredientTypeManagerInterface;

public class IngredientTypeManager extends PersistentManager 
implements IngredientTypeManagerInterface{
	/**
	 * Constructor
	 * @param sessionFactory
	 */
	public IngredientTypeManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllIngredientType(){
		List<String> lz = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			SQLQuery query = session.createSQLQuery("SELECT NAME FROM INGREDIENTTYPE");
			lz = query.list();
		} catch(Exception e){
			t.rollback();
		}
		t.commit();
		return lz;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<IngredientType> findIngredientByName(String name){
		
		List<IngredientType> lz = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			String toSearch = "'" + name + "%'";
			lz = (List<IngredientType>) session.createSQLQuery("SELECT {i.*} FROM "
					+ "INGREDIENTTYPE {i} WHERE NAME LIKE " + toSearch).
					addEntity("i", IngredientType.class)
					.list();
			
			
			
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		}
	
		return lz;
	}
	
	
	public boolean addIngredientType(IngredientType ingredientType) {
		
		
			boolean ret = true;
			Session session = sessionFactory.openSession();
			Transaction t = null;
			
			try{
				t = session.beginTransaction();
			
				session.save(ingredientType);
				
			} catch(Exception e){
				t.rollback();
				System.out.println("Error " + e.getMessage());
				ret = false;
				t.rollback();
			}
			t.commit();
			return ret;
			
		
		
	}
}
