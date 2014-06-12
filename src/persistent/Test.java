package persistent;

import persistent.classes.Ingredient;
import persistent.classes.IngredientType;
import persistent.classes.RecipeIngredients;
import persistent.help.CO2Calculation;
import persistent.hibernateManager.HibernateUtil;


public class Test {
	
	/**
	 * Use mock for the DB test
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		HibernateUtil hm = HibernateUtil.getInstance();
		hm.addCooked(3);
	
		hm.closeSession();
	}
}