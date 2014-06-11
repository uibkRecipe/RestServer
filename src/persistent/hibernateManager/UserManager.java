package persistent.hibernateManager;

import java.io.File;
import java.io.FileInputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.User;
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

	

	/**
	 * Constructor given a session it return an UserManager
	 * 
	 * @param session
	 */
	public UserManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	
	public boolean addUser(User user) {
		boolean success = true;
		Session session = null;
		Transaction t = null; 
		/** If the object is not already contained **/
		try {
			session = sessionFactory.openSession();
			t = session.beginTransaction();
			session.save(user);
			t.commit();
		} catch (Exception e) {
			if(t != null)
				t.rollback();
			System.out.println("We are sorry but your username already exists");
			success = false;
		} finally {
			
			session.close();
		}
		return success;
	}

	
	public User findUserById(String userName) {
		Session session = sessionFactory.openSession();
		User u = null;
		
			
		Transaction t = null; 
		try {
			t = session.beginTransaction();
			u = (User) session.get(User.class, userName);
		} catch (Exception e) {
			if(t != null)
				t.rollback();
			System.out.println("User " + userName + "could not be found");
		} finally {
			t.commit();
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
		session.close();
		return success;
	}
	

	
	
	public boolean setUserAsNotActive(String username){
		User u = this.findUserById(username);
		boolean success = true;
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			u.setIsActive(0);
			session.saveOrUpdate(u);
			
		} catch(Exception e){
			e.printStackTrace();
			if(t != null)
				t.rollback();
			success = false;
		} finally {
			t.commit();
		}
			return success;
	}
	
	
	public boolean setUserAsActive(String username){
		boolean success = true;
		Session session = sessionFactory.openSession();
		Transaction t = null;
		
	
		try {
			User u  = this.findUserById(username);
			if(u == null)
				throw new Exception();
			u.setIsActive(1);
			t = session.beginTransaction();
			session.saveOrUpdate(u);
		} catch(Exception e){
			System.out.println("Hello World");
			e.printStackTrace();
			if(t != null)
				t.rollback();
			success = false;
		}
		t.commit();
		return success;
	}


	@Override
	public boolean changePassword(String username, String oldPassword,
			String newPassword, String newPasswordConfirm) {
		User u = this.logIn(username, oldPassword);
		boolean success = true;
		if(u == null){
			System.out.println("The given old password is not correct");
			return false;
		}
		if(!newPassword.equals(newPasswordConfirm)){
			System.out.println("The given new passwords are different");
			return false;
		}
		Session s = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = s.beginTransaction();
		
			u.setPassword(newPassword);
			s.update(u);
		} catch(Exception e) {
			if(t != null)
				t.rollback();
			success = false;
		} finally {
			t.commit();
			s.close();
		}
		
		return success;
	}
	

}
