package persistent.ressource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import persistent.classes.Ort;
import persistent.classes.User;
import persistent.hibernateManager.OrtManager;
import persistent.hibernateManager.UserManager;
/**
 * Fassade for hibernate
 * @author mirko
 *
 */

//Will map the resource to the URL todos
@Path("/user")
public class HibernateManager {
	
	
	 // Allows to insert contextual objects into the class, 
	  // e.g. ServletContext, Request, Response, UriInfo
	  @Context
	  UriInfo uriInfo;
	  @Context
	  Request request;

	Session session;
	UserManager userManager;
	OrtManager ortManager;
	
	/**
	 * Constructor return a new HibernateManager
	 */
	public HibernateManager() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb
				.build());
		session = sessionFactory.openSession();
		userManager = new UserManager(session);
		ortManager = new OrtManager(session);
	}

	/**
	 * Close the session
	 */
	public void closeSession() {
		session.close();
	}
	
	/****************************************************************
	 * 
	 *  User Functionalities
	 *  
	 ****************************************************************/
	
	@GET
	@Path("login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean logIn(@PathParam("username") String username, @PathParam("password") String password){
		return userManager.logIn(username, password);
	}
	
	
//	 @GET
//	 @Produces(MediaType.TEXT_XML)
//	 public User getTodosBrowser() {
//		 User simon = new User();
//		  simon.setUser_name("simon123");
//		  simon.setPassword("blub");
//		  simon.setEmail("simon.targa@blub.com");
//		  return simon;
//	  }
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		if(userManager.addUser(user)){
		User blub = new User("simon1234", "123213123", "adkfljads@");
		  return Response.status(200).entity(blub).build();
		}
		return Response.status(200).entity(user).build();
//		else
//			return Response.status(200).entity(null).build();
	}
	
	/*****************************************************************
	 * 
	 * Ort Functionalities
	 * 
	 *****************************************************************/
	
	public boolean addOrt(Ort ort){
		return ortManager.addOrt(ort);
	}

	public Ort findOrtById(int ortId){
		return ortManager.findOrtById(ortId);
	}
	
	@GET
	@Path("{ort}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ort> findOrtByName(@PathParam("ort") String ortName){
		return ortManager.findOrtByName(ortName);
	}
}
