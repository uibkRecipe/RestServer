package persistent.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
	public List<String> getCountryList();

	/**
	 * This function returns a country instance with the (unique) code equals to
	 * countryCode
	 * 
	 * @param countryCode
	 * @return the country with CODE equals to countryCode
	 */
	public Country getCountryByCode(String countryCode);

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
	 * @return
	 */
	public String findCountryCodeByName(String countryName);
	
	
}
