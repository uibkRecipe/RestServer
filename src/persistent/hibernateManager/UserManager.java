package persistent.hibernateManager;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.User;
import persistent.help.ByteConverter;
import persistent.interfaces.UserManagerInterface;


/**
 * This class implement the user functionalities.
 * 
 * 
 * 
 * @author Mirko Bez
 *
 */
public class UserManager extends PersistentManager implements UserManagerInterface{

	private ByteConverter byteConverter;

	/**
	 * Constructor given a session it return an UserManager
	 * 
	 * @param session
	 */
	public UserManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	
		ByteConverter byteConverter = new ByteConverter();
	}

	
	public boolean addUser(User user) {
		boolean success = true;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/** If the object is not already contained **/
		try {
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			try{
				session.getTransaction().rollback();
			}catch(Exception e1){}
			success = false;
		} finally {
			session.close();
		}
		return success;
	}

	
	public User findUserById(String userName) {
		Session session = sessionFactory.openSession();
		User u = null;
		
			
		session.beginTransaction();
		try {
			u = (User) session.get(User.class, userName);
		} catch (Exception e) {
			System.out.println("User " + userName + "could not be found");
		} 
		session.close();
		return u;
	}

	
	public User logIn(String userName, String suggestedPassword) {
		boolean  correct = true;
		User u = findUserById(userName);
		if (u == null) {
			System.out.println("The user " + userName + " does not exist");
			correct = false;
		}
		else if(u.getIsActive() == 0){
			System.out.println("The user is no more active");
			correct = false;
		}
		else if (suggestedPassword.equals(u.getPassword())) {
			System.out.println("Your Data are correct");
		} else {
			System.out.println("You entered the false password");
			correct = false;
		}
		
		if(correct)
			return u;
		else
			return null;
	}
	
	
	
	
	public boolean setUserFoto(User u, File f){
		if(f == null){
			return false;
		}
		Session session = sessionFactory.openSession();
		boolean success = true;
		byte [] binaryFile = new byte [(int)f.length()];
		session.beginTransaction();
		try {
			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(binaryFile);
			fileInputStream.close();
		} catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		
		u.setFoto(binaryFile);
		session.save(u);
		session.getTransaction().commit();
		
		return success;
	}
	

	
	
	public boolean setUserAsNotActive(User u){
		boolean success = true;
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		u.setIsActive(0);
		try {
			session.update(u);
		} catch(Exception e){
			e.printStackTrace();
			t.rollback();
			success = false;
		}
		t.commit();
		return success;
	}
	
	
	public boolean setUserAsActive(String username){
		boolean success = true;
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		
		User u  = this.findUserById(username);
		u.setIsActive(1);
		try {
			session.update(u);
		} catch(Exception e){
			e.printStackTrace();
			t.rollback();
			success = false;
		}
		t.commit();
		return success;
	}
	

}
