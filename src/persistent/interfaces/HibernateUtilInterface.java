package persistent.interfaces;

import persistent.classes.City;


/**
 * My Hibernate Motto: give the function the minimum as possible returns the 
 * maximum! 
 * @author mirko
 *
 */
public interface HibernateUtilInterface 
extends 
CityManagerInterface, 
ComposedOfManagerInterface,
CountryManagerInterface,
FriendManagerInterface,
IngredientManagerInterface, 
IngredientTypeManagerInterface, 
RatingManagerInterface, 
RecipeManagerInterface, 
RegionManagerInterface, 
UserManagerInterface
{
	
	/**
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	public City getUserPosition(String username);
	
	

	 
}
