package rest;

import persistent.classes.City;
import persistent.hibernateManager.HibernateUtil;

public class CityDistanceCalculator implements CityDistance {
	
	private static CityDistanceCalculator instance = null;
	
	private CityDistanceCalculator(){
	
	}
	
	

	public static CityDistanceCalculator getInstance() {
		if (instance == null) {
			instance = new CityDistanceCalculator();
		}

		return instance;
	}
	
	public  double calcDistance(double latitudeA, double longitudeA,
			double latitudeB, double longitudeB) {
			double R = 6372.795477598;
			double radLatA = Math.PI * latitudeA / 180;
			double radLonA = Math.PI * longitudeA / 180;
			double radLatB = Math.PI * latitudeB / 180;
			double radLonB = Math.PI * longitudeB / 180;
				 
			double phi = Math.abs(radLonA - radLonB);
				 
			double P = Math.acos((Math.sin(radLatA) * Math.sin(radLatB)) + (Math.cos(radLatA) * Math.cos(radLatB) * Math.cos(phi)));
				 
			return P * R;
			
	}
	//Für rest server
	public  double calcDistance(int cityID1, int cityID2) {
			City c1 = HibernateUtil.getInstance().findCityByID(cityID1);
			City c2 = HibernateUtil.getInstance().findCityByID(cityID2);
			return calcDistance(c1.getLatitude(), c1.getLongitude(), c2.getLatitude(), c2.getLongitude());
	}
	
	
	public static void main(String[] args) {
		//B = BOLZANO A = VERONA
		System.out.println(HibernateUtil.getInstance().findCityByID(2000) + " " + HibernateUtil.getInstance().findCityByID(22059));;
		System.out.println(new CityDistanceCalculator().calcDistance(2000, 22059));
		
	}

}
