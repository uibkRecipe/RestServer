package persistent.interfaces;

import java.io.File;

import persistent.classes.User;

/**
 * 
 * This interface describe the user functionalities
 * and define the type UserManager
 * 
 * 
 * @author mirko
 *
 */
public interface UserManagerInterface {


	/**
	 * Add a user to the table user
	 * 
	 * @param user
	 *            the user to add
	 * @return true on success, false on failure
	 */
	public boolean addUser(User user);

	/**
	 * Find a user by user_name
	 * 
	 * @param userName
	 * @return a user on success otherwise null
	 */
	public User findUserById(String userName);

	/**
	 * Return true if the given data matches the one in the database
	 * 
	 * @param userName
	 * @param suggestedPassword
	 * @return true if suggestedPassword matches the real password of the user
	 *         identified by userName
	 */
	public User logIn(String userName, String suggestedPassword);
	
	
	
	/**
	 * Set the File f as user foto of the user u
	 * 
	 * @param u
	 *            user, that want to set the foto
	 * @param f
	 *            file, that contains the foto
	 * @return true on success false otherwise
	 */
	public boolean setUserFoto(User u, File f);
	
	/**
	 * This function render a user active
	 * 
	 * @param u
	 *            the user to render active
	 * @return true on success false otherwise
	 */
	public boolean setUserAsNotActive(User u);

	/**
	 * This function render a user inactive
	 * 
	 * @param username
	 *            the primary key of the user to set as inactive
	 * @return true on success false otherwise
	 */
	public boolean setUserAsActive(String username);
}
