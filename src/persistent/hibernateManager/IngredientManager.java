package persistent.hibernateManager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Ingredient;
import persistent.interfaces.IngredientManagerInterface;

public class IngredientManager extends PersistentManager implements IngredientManagerInterface
{

	public IngredientManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean addIngredient(Ingredient ingredient) {
		boolean success = true;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			
			transaction = session.beginTransaction();
			session.save(ingredient);
			
		} catch(Exception e){
			if(transaction != null)
				transaction.rollback();
			e.printStackTrace();
			success = false;
		}
		transaction.commit();
		
		return success;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingredient> findIngredientsByIngredientType(int ingredientTypeID){
		String query = "SELECT * FROM INGREDIENT WHERE INGREDIENTSTYPEID='" + ingredientTypeID + "'";
		Transaction t = null;
		Session session = null;
		List<Ingredient> l = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			t = session.beginTransaction();
			l = (List<Ingredient>) session.createSQLQuery(query).addEntity(Ingredient.class).list();
			t.commit();
		} catch (Exception e){
			t.rollback();
			
		}finally {
			if(session != null)
				session.close();
		}
		return l;
	}



}
