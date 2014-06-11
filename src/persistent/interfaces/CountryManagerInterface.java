package persistent.interfaces;

import java.util.List;

import persistent.classes.Country;

/**
 * 
 * @author Mirko Bez
 *
 */
public interface CountryManagerInterface {

	/**
	 * This function return all the (unique) names of the countries stored in
	 * the database. (Suggestion: Use this in a JComboBox)
	 * 
	 * @return a list containing all the names of the countries
	 */
	public List<Country> getCountryList();

	/**
	 * This function returns a country instance with the (unique) code equals to
	 * countryCode
	 * 
	 * @param countryCode
	 * @return the country with CODE equals to countryCode
	 */
	public Country findCountryByCode(String countryCode);

	/**
	 * This function returns a list of countries' names, that matches the given
	 * string countryName
	 * 
	 * @param countryName
	 *            the string to match
	 * @return a list of matching string, otherwise an empty list
	 */
	public List<String> findCountryByName(String countryName);

	/**
	 * 
	 * @param countryName
	 * @return CountryCode
	 */
	public String findCountryCodeByName(String countryName);
	
	
}
