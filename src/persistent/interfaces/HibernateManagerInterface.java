package persistent.interfaces;

/**
 * My Hibernate Motto: give the function the minimum as possible returns the
 * maximum!
 * 
 * @author mirko
 * 
 */
public interface HibernateManagerInterface extends CityManagerInterface,
		ComposedOfManagerInterface, CountryManagerInterface,
		FriendManagerInterface, IngredientManagerInterface,
		IngredientTypeManagerInterface, RatingManagerInterface,
		RecipeManagerInterface, RegionManagerInterface, UserManagerInterface {

}
