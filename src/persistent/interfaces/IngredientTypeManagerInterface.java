package persistent.interfaces;

import java.util.List;

import persistent.classes.IngredientType;

public interface IngredientTypeManagerInterface {
	/**
	 * 
	 * @return a list containing all the names of the ingredients
	 */
	public List<IngredientType> findAllIngredientType();
	/**
	 * Returns a list containing all the ingredients' names, that
	 * matches the given string name
	 * 
	 * @param name
	 * @return
	 */
	public List<IngredientType> findIngredientByName(String name);
	
	/**
	 * Add a ingredienttype to the table INGREDIENTTYPE
	 * 
	 * @param ingredienttype
	 *            the ingredienttype to add
	 * @return true on success, false on failure
	 */
	public boolean addIngredientType(IngredientType ingredientType);
}
