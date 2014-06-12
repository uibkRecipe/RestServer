package persistent.interfaces;

import java.util.List;

import persistent.classes.Region;


public interface RegionManagerInterface {
	/**
	 * Return a list containing all the regions of a country identified by its code
	 * For instance: AT returns the regions of Austria and so on.
	 * @param Code 
	 * @return a List of region if the code is found, otherwise null
	 */
	public List<Region>  findRegionByCountryCode(String Code);
	
	
	public Region findRegionByCountryAndRegionName(String Code, String Name);
}
