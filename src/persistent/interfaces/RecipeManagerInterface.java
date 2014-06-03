package persistent.interfaces;

import java.util.List;

import persistent.classes.Category;
import persistent.classes.Recipe;

public interface RecipeManagerInterface {
	public List<Recipe> findRecipeByAutor(String username);
	
	/**
	 * Add a Recipe in the DB.
	 * @param r the recipe to add
	 * @return true on success; false otherwise;
	 */
	public boolean addRecipe(Recipe r);
	/**
	 * Remove a recipe whose author is identified by username
	 * and whose ID is recipe id
	 * @param recipeID
	 * @param username
	 * @return true on success; false otherwise;
	 */
	public boolean removeRecipe(String username, int recipeID);
	/**
	 * 
	 * @param C
	 * @return
	 */
	public List<Recipe> getRezeptByCategory(Category C);

	/**
	 * 
	 * @return
	 */
	public List<Recipe> getRezeptByCategory();

}
