package persistent.interfaces;

import java.util.List;

import persistent.classes.FavoriteRecipe;
import persistent.classes.Recipe;

public interface FavoriteRecipeManagerInterface {
	
	
	public List<Recipe> findFavoriteRecipe(String username);
	
	public boolean addFavoriteRecipe(int recipeID, String username); 
	
	public boolean addFavoriteRecipe(FavoriteRecipe fr); 
	
}
