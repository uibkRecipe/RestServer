package rest;

import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<Recipe> getCO2FriendlyRec(String username){
		HibernateUtil hu = HibernateUtil.getInstance();
		
		List<Recipe> recipes = hu.getAllRecipes();
		List<Recipe> retRecipes = new ArrayList<Recipe>();
		for(int i=0; i<recipes.size(); i++){
			Recipe r = recipes.get(i);
			Recipe rNew = calculateCO2(r.getID(), username);
			recipes.set(i, rNew);
		}
		Collections.sort(recipes, new CO2FriendlyComporator());
		for (int i= 0; i<10 && i<recipes.size(); i++){
			retRecipes.add(recipes.get(i));
		}
		return recipes;
	}
	
}
