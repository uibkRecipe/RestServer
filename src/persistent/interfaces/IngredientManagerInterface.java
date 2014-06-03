package persistent.interfaces;

import persistent.classes.Ingredient;

public interface IngredientManagerInterface {
	
	
	/**
	 * Add a concrete ingredient to the table INGREDIENT
	 * @param ingredient the ingredient to add
	 * @return true on success, false otherwise
	 */
	public boolean addIngredient(Ingredient ingredient);
}
