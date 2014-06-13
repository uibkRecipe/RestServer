package rest;

public interface  CityDistance {
	
	double R = 6372.795477598;
	/**
	 * This fucntion calculate the distance between two cities
	 * A and B
	 * http://blog.casertano.name/calcolo-della-distanza-tra-due-punti-geografici/
	 * Assumption: Earth is a perfect sphere with radius 
	 * @param latitudeA latitude of the city A
	 * @param longitudeA longitude of the city A
	 * @param latitudeB latitude of the city A
	 * @param longitude longitude of the city B
	 * @return the distance between the two cites.
	 */
	public double calcDistance(double latitudeA, double longitudeA, 
			double latitudeB, double longitudeB);
	
	
	public  double calcDistance(int cityID1, int cityID2);
	
}
