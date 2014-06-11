package persistent.interfaces;

import java.util.List;

/**
 * 
 * @author mirko
 *
 */
public interface FriendManagerInterface {

	/**
	 * This function returns a list containing all the friends
	 * of the user univocally identified by the string username 
	 * @param username
	 * @return a list containing all the friends' usernames.
	 */
	public List<String> getFriend(String username);
	/**
	 * This function try to add a friendship
	 * @param username1
	 * @param username2
	 * @return true on success, false otherwise
	 */
	public boolean addFriend(String username1, String username2);
	/**
	 * This function try to delete a friendship between two users
	 * identified by their usernames
	 * @param username1
	 * @param username2
	 * @return true on success, false otherwise
	 */
	public boolean deleteFriend(String username1, String username2);
	/**
	 * This function says if there is a friendship between two users
	 * identified by their usernames.
	 * @param username1
	 * @param username2
	 * @return true on success, false otherwise
	 */
	public boolean existFriend(String username1, String username2);
}
