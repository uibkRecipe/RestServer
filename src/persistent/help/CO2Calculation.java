package persistent.help;

import java.util.List;

import persistent.classes.Ingredient;
import persistent.classes.IngredientType;
import persistent.classes.Recipe;
import persistent.classes.User;
import persistent.hibernateManager.HibernateUtil;

public class CO2Calculation {
	
	
	private static CO2Calculation instance = null;
	private CO2Calculation() {
	
	}
	
	

	public static CO2Calculation getInstance() {
		if (instance == null) {
			instance = new CO2Calculation();
		}

		return instance;
	}
	private double getNearestIngredient(int ingredientTypeID, int cityID){
		HibernateUtil hu = HibernateUtil.getInstance();
		double minDistance = Double.MAX_VALUE;
		double tmp = 0;
		
		List<Ingredient> l = hu.findIngredientsByIngredientType(ingredientTypeID);
		int cityID1;
		for (Ingredient ingredient : l) {
			System.out.println("ICH BIN DRIN");;
			cityID1 = ingredient.getCity();
			tmp = CityDistanceCalculator.getInstance().calcDistance(cityID, cityID1);
			if(tmp < minDistance)
				minDistance = tmp;
		}
		
		
		return minDistance;
	}
	
	public Recipe calculateCO2(int recipeID, String username){
		HibernateUtil hu = HibernateUtil.getInstance();
		Recipe r = hu.findRecipeById(recipeID);
		if(r == null)
			return null;
		User u = hu.findUserById(username);
		if(u == null)
			return null;
		int cityID = u.getCity();
		List<IngredientType> ingredientList = hu.getIngredients(recipeID).getIngredients();
		System.out.println(ingredientList);
		double distance = 0.0;
		for (IngredientType ingredientType : ingredientList) {
			distance += getNearestIngredient(ingredientType.getID(), cityID);
		}
		r.setDistance(distance);
		return r;
	}
	
}
