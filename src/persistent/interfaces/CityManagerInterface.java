package persistent.interfaces;

import java.util.List;

import org.hibernate.Session;

import persistent.classes.City;

public interface CityManagerInterface {
	
	
		
	
		
		/**
		 * Find an object by its id.
		 * @param ortId the id of the "ort"
		 * @return Ort on success otherwise null
		 */
		public City findCityByID(int cityID);
		
		/**
		 * Search for an ortname with a LIKE search
		 * @param ortName string to match
		 * @return a list of orts that matches the string ortName
		 */
		public List<City> findCityByName(String cityName);
		/**
		 * Suggestion (JCOMBOBOX)
		 * @param country
		 * @param region
		 * @return
		 */
		public List<City> findCityNameByCountryAndRegion(String country, String region);
}
