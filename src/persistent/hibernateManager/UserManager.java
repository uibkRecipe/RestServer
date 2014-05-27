package persistent.hibernateManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import persistent.classes.User;

public class UserManager {
	Session session;

	

	/**
	 * Constructor given a session it return an HibernateManager
	 * 
	 * @param session
	 */
	public UserManager(Session session) {
		this.session = session;
	}

	/**
	 * Add a user to the table user
	 * 
	 * @param user
	 *            the user to add
	 * @return true on success, false on failure
	 */
	public boolean addUser(User user) {
		boolean success = true;
		session.beginTransaction();
		/** If the object is not already contained **/
		try {
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("We are sorry but your username already exists");
			success = false;
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Find a user by user_name
	 * 
	 * @param userName
	 * @return a user on success otherwise null
	 */
	public User findUserById(String userName) {
		User u = null;
		session.beginTransaction();
		try {
			u = (User) session.get(User.class, userName);
		} catch (Exception e) {
			System.out.println("User " + userName + "could not be found");
		} finally {
			session.close();
		}
		return u;
	}

	/**
	 * Return true if the given data matches the one in the database
	 * 
	 * @param userName
	 * @param suggestedPassword
	 * @return true if suggestedPassword matches the real password of the user
	 *         identified by userName
	 */
	public boolean logIn(String userName, String suggestedPassword) {
		User u = findUserById(userName);
		if (u == null) {
			System.out.println("The user " + userName + " does not exist");
			return false;
		}
		if (suggestedPassword.equals(u.getPassword())) {
			System.out.println("Your Data are correct");
		} else {
			System.out.println("You entered the false password");
		}
		return true;
	}

	
}
