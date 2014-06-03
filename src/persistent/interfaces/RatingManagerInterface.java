package persistent.interfaces;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistent.classes.Rating;

public interface RatingManagerInterface {
	/**
	 * This function add a rating to the DB
	 * @param rating the rating to add
	 * @return true on success, false otherwise
	 */
	public boolean addRating(Rating rating);
}
