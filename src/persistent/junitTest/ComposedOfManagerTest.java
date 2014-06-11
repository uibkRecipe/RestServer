package persistent.junitTest;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import persistent.classes.IngredientType;

public class ComposedOfManagerTest extends PersistentManagerTest{

	@Test
	public void getIngredients() {
		List<IngredientType> l = hm.getIngredients(2);
		assertEquals(l.size(), 1);
	}

	@Test
	public void findRezeptByIngredient() {
	}

	@Test
	public void addIngredientToRecipe() {
		
		
	}

}
