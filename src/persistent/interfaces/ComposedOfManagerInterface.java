package persistent.interfaces;

import java.util.List;

import persistent.classes.IngredientType;
import persistent.classes.Recipe;

public interface ComposedOfManagerInterface {
	
	/**
	 * This function returns a list containing all 
	 * the ingredient of the recipe r
	 * @param r recipe
	 * @return a list containing all the ingredients
	 */
	public List<IngredientType> getIngredients(int recipeID);
	/**
	 * Return rezept ids
	 * @param lz
	 * @return
	 */
	public List<Recipe> findRezeptByIngredient(List<IngredientType> lz);
	
	/**
	 * This function add the ingredients of a recipe identified by recipeID 
	 * @param recipeID the ID of the recipe
	 * @param ingredients a list containing the ingredienttype
	 * @param quantity 
	 * @return true when all the ingredients are stored correctly, false otherwise
	 */
	public boolean addIngredientToRecipe(int recipeID, List<IngredientType> ingredients,
			List<String> quantity);
	
}
