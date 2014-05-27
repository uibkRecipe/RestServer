package persistent.classes;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User{
	private String user_name;
	private String password;
	private String email;
	private String vname;
	private String nname;
	
	/* Should be filled only by request with a query*/
	//private List<String> friends = new ArrayList<String>();
	/** Hibernate default constructor */
	public User(){
		
	}
	/**
	 * Constructor to create a new user, without asking for surname and name
	 * @param uname	Username of the new user
	 * @param password Password of the new user
	 * @param email
	 */
	public User(String uname, String password, String email){
		this.user_name = uname;
		this.password = password;
		this.email = email;
		this.vname = null;
		this.nname = null;
	}
	
	/**
	 * Constructor to create a new user, with asking for surname and name
	 * @param uname	Username of the new user
	 * @param password Password of the new user
	 * @param email Email of the new user
	 * @param vname surname of the new user
	 * @param nname name of the new user
	 */
	public User(String uname, String password, String email, String vname, String nname){
		this.user_name = uname;
		this.password = password;
		this.email = email;
		this.vname = vname;
		this.nname = nname;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}
	/*
	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	*/
	public String toString(){
		return "ID = " + user_name + "email = " + email;
	}

}
