package persistent.junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import persistent.classes.Ingredient;

public class IngredientTypeManagerTest extends PersistentManagerTest{
	
	
	@Test
	public void test(){
		assertFalse(hm.addIngredient(new Ingredient(2, 2000, "merda")));
	}
}
