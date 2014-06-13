package rest;

import java.util.Comparator;

import persistent.classes.Recipe;

public class CO2FriendlyComporator implements Comparator<Recipe> {

	@Override
	public int compare(Recipe arg0, Recipe arg1) {
		double difference = arg0.getDistance() - arg1.getDistance();
		if(difference < 0){
			return -1;
		}
		else if(difference >0){
			return 1;
		}
		return 0;
	}

}
