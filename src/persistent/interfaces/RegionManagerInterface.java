package persistent.interfaces;

import java.util.List;

import persistent.classes.Region;


public interface RegionManagerInterface {
	
	public List<Region>  getRegionByCountryCode(String Code);
}
