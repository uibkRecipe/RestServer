package persistent.help;

public class CityDistanceCalculator implements CityDistance {


	
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
	
	
	public static void main(String[] args) {
		//B = BOLZANO A = VERONA
		System.out.println(new CityDistanceCalculator().calcDistance(40.7, 17.3333 , 46.5167, 11.3667));
		
	}

}
