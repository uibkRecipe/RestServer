package persistent.interfaces;

import java.io.File;
import java.util.List;

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
	public List<Recipe> findRecipeByCategory(String category);

	/**
	 * 
	 * @return
	 */
	public List<Recipe> findRecipeByCategory();
	
	
	/**
	 * Set the File f as recipe foto of the user u
	 * 
	 * @param u
	 *            user, that want to set the foto
	 * @param f
	 *            file, that contains the foto
	 * @return true on success false otherwise
	 */
	public boolean setRecipeFoto(String username, int recipeID, File f);
	
	
	
	/**
	 * Find a recipe by its ID
	 * @param recipeID
	 * @return the Recipe if the ID was found, null otherwise
	 */
	public Recipe findRecipeById(int recipeID);
}
