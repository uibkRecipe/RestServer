package persistent.hibernateManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import persistent.classes.Friend;
import persistent.interfaces.FriendManagerInterface;

public class FriendManager extends PersistentManager implements FriendManagerInterface {
	
	
	public FriendManager(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getFriend(String username){
		List<String> lf = new ArrayList<String>();
		List<String> lf1 = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/** If the object is not already contained **/
		try {
			SQLQuery query = session.createSQLQuery("SELECT username2 FROM FRIEND WHERE username1='" + username + "'");
			SQLQuery query1 = session.createSQLQuery("SELECT username1 FROM FRIEND WHERE username2='" + username + "'");
			lf = (List<String>)query.list();
			lf1 = (List<String>)query1.list();
			lf.addAll(lf1);
		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
			
		} finally {
			
		}
		return lf;
		
	}
	public boolean addFriend(String username1, String username2){
		boolean ret = true;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Friend f = new Friend(username1, username2);
			session.save(f);
			session.getTransaction().commit();
		}catch (Exception e){
			System.out.println("ERROR : " + e.getMessage());
			session.getTransaction().rollback();
			ret = false;
		}
		finally{
			
			
		}
		return ret;
	}
	public boolean deleteFriend(String username1, String username2){
		boolean ret = true;
		if(!existFriend(username1, username2)){
			System.err.println("You cannot delete a not existing friend");
			return false;
		}
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			session.delete(new Friend(username1, username2));
			t.commit();

			
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
		} 
		return ret;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public boolean existFriend(String username1, String username2){
		boolean exist = true;
		int count = 0;
		Session session = sessionFactory.openSession();
		Transaction t = null;
		try {
			t = session.beginTransaction();
			SQLQuery query = session.createSQLQuery
					("SELECT count(*) FROM FRIEND WHERE username1='" + username1 + "' AND username2='" + username2 + "'");
			List<BigInteger> l = (List<BigInteger>)query.list();
			count = l.get(0).intValue();
			query = session.createSQLQuery
					("SELECT count(*) FROM FRIEND WHERE username2='" + username1 + "' AND username1='" + username2 + "'");
			
			l = (List<BigInteger>)query.list();
			count += l.get(0).intValue();
			System.out.println(count);
			if(count == 0)
				exist = false;
			session.getTransaction().commit();
		} catch(Exception e){
			if(t != null)
				t.rollback();
			e.printStackTrace();
			
		} finally {

		}
		
		return exist;
	}
}
