package rest;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import persistent.classes.City;
import persistent.classes.Country;
import persistent.classes.Ingredient;
import persistent.classes.IngredientType;
import persistent.classes.Rating;
import persistent.classes.Recipe;
import persistent.classes.RecipeIngredients;
import persistent.classes.Region;
import persistent.classes.User;
import persistent.help.CO2Calculation;
import persistent.hibernateManager.CityManager;
import persistent.hibernateManager.ComposedOfManager;
import persistent.hibernateManager.CountryManager;
import persistent.hibernateManager.FavoriteRecipeManager;
import persistent.hibernateManager.FriendManager;
import persistent.hibernateManager.IngredientManager;
import persistent.hibernateManager.IngredientTypeManager;
import persistent.hibernateManager.RatingManager;
import persistent.hibernateManager.RecipeManager;
import persistent.hibernateManager.RegionManager;
import persistent.hibernateManager.UserManager;

/**
 * Fassade for hibernate
 * 
 * @author mirko
 * 
 */
// Will map the resource to the URL todos
@Path("/manager")
public class HibernateManager {
	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	private SessionFactory sessionFactory;
	StandardServiceRegistryBuilder ssrb;

	private CityManager			cityManager;
	private ComposedOfManager		composedOfManager;
	private CountryManager 		countryManager;
	private FavoriteRecipeManager	favoriteRecipeManager;
	private FriendManager			friendManager;
	private IngredientManager 		ingredientManager;
	private IngredientTypeManager	ingredientTypeManager;
	private RatingManager 			ratingManager;
	private RecipeManager 			recipeManager;
	private RegionManager			regionManager;
	private UserManager 			userManager;

	/**
	 * Constructor return a new HibernateManager
	 */
	public HibernateManager() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ssrb = new StandardServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		sessionFactory = configuration.buildSessionFactory(ssrb.build());

		cityManager = new CityManager(sessionFactory);
		composedOfManager = new ComposedOfManager(sessionFactory);
		countryManager = new CountryManager(sessionFactory);
		favoriteRecipeManager = new FavoriteRecipeManager(sessionFactory);
		friendManager = new FriendManager(sessionFactory);
		ingredientManager = new IngredientManager(sessionFactory);
		ingredientTypeManager = new IngredientTypeManager(sessionFactory);
		ratingManager = new RatingManager(sessionFactory);
		recipeManager = new RecipeManager(sessionFactory);
		regionManager = new RegionManager(sessionFactory);
		userManager = new UserManager(sessionFactory);
	}

	/**
	 * 
	 * @return actual session factory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Close the session
	 */
	public void closeSession() {
		sessionFactory.close();
	}

	/****************************************************************
	 * 
	 * User Functionalities
	 * 
	 ****************************************************************/

	@GET
	@Path("login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User logIn(@PathParam("username") String userName,
			@PathParam("password") String suggestedPassword) {
		return userManager.logIn(userName, suggestedPassword);
	}

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		if (userManager.addUser(user)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}

	@GET
	@Path("findUser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User findUserById(@PathParam("username") String userName) {
		return userManager.findUserById(userName);
	}

//	 public boolean setUserFoto(User u, File f){
//		 return userManager.setUserFoto(u, f);
//	 }
//
//	//
//	// public boolean setUserAsNotActive(User u){
//	// return userManager.setUserAsNotActive(u);
//	// }
//	//
//	//
//	// public boolean setUserAsActive(String username){
//	// return userManager.setUserAsActive(username);
//	// }
	
	@PUT
	@Path("changePassword/{username}/{oldPassword}/{newPassword}/{newPasswordConfirm}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePassword(@PathParam("username") String username,
			@PathParam("oldPassword") String oldPassword,
			@PathParam("newPassword") String newPassword,
			@PathParam("newPasswordConfirm") String newPasswordConfirm) {
		if (userManager.changePassword(username, oldPassword, newPassword,
				newPasswordConfirm)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}
			
//	/****************************************************************
//	 * 
//	 * Country Functionalities
//	 * 
//	 ****************************************************************/
//
	@GET
	@Path("/country")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountryList() {
		return countryManager.getCountryList();
	}

	@GET
	@Path("/countryByCode/{countryCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country getCountryByCode(@PathParam("countryCode") String countryCode) {
		return countryManager.findCountryByCode(countryCode);
	}

	@GET
	@Path("/country/{countryName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> findCountryByName(
			@PathParam("countryName") String countryName) {
		return countryManager.findCountryByName(countryName);
	}

	@GET
	@Path("/countrycode/{countryName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findCountryCodeByName(
			@PathParam("countryName") String countryName) {
		return countryManager.findCountryCodeByName(countryName);
	}

	/*****************************************************************
	 * 
	 * City Functionalities
	 * 
	 *****************************************************************/

	@GET
	@Path("/citybyID/{cityId}")
	@Produces(MediaType.APPLICATION_JSON)
	public City findCityByID(@PathParam("cityId") int cityID) {
		return cityManager.findCityByID(cityID);
	}

	@GET
	@Path("/citybyName/{cityName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> findCityByName(@PathParam("cityName") String cityName) {
		return cityManager.findCityByName(cityName);
	}

	@GET
	@Path("/city/{country}/{region}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> findCityNameByCountryAndRegion(
			@PathParam("country") String country,
			@PathParam("region") String region) {
		return cityManager.findCityNameByCountryAndRegion(country, region);
	}
	
	
	@GET
	@Path("/cityByCountry/{country}")
	public List<City> findCityByCountry(@PathParam("country") String country) {
		return cityManager.findCityByCountry(country);	
	}

	/******************************************************************
	 * 
	 * Friends functionalities
	 * 
	 ******************************************************************/
	@GET
	@Path("/getFriends/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getFriend(@PathParam("username") String username) {
		return friendManager.getFriend(username);
	}

	@POST
	@Path("/addFriend/{username2}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFriend(String username1,
			@PathParam("username2") String username2) {
		if (friendManager.addFriend(username1, username2)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}

	@DELETE
	@Path("/deleteFriend/{username1}/{username2}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFriend(String username1, String username2) {
		if (friendManager.deleteFriend(username1, username2))
			return Response.status(200).entity(true).build();
		return Response.status(200).entity(false).build();
	}

	@GET
	@Path("/existFriend/{username1}/{username2}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean existFriend(@PathParam("username1") String username1,
			@PathParam("username2") String username2) {
		return friendManager.existFriend(username1, username2);
	}

	/****************************************************************
	 * 
	 * Recipe functionalities
	 * 
	 ****************************************************************/
	@POST
	@Path("/addRecipe")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRecipe(Recipe r) {
		if (recipeManager.addRecipe(r)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}

	@GET
	@Path("/finRecipeByAuthor/{author}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByAutor(@PathParam("author") String username) {
		return recipeManager.findRecipeByAutor(username);
	}

	@DELETE
	@Path("/recipe/{username}/{recipeID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeRecipe(@PathParam("username") String username,
			@PathParam("recipeID") int recipeID) {
		if (recipeManager.removeRecipe(username, recipeID))
			return Response.status(200).entity(true).build();
		return Response.status(200).entity(false).build();
	}
	
	
	@GET
	@Path("/findRecipe/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByCategory(@PathParam("category") String category) {
		return recipeManager.findRecipeByCategory(category);
	}	

//	public boolean setRecipeFoto(String username, int recipeID, File f) {
//		return recipeManager.setRecipeFoto(username, recipeID, f);
//	}

	@GET
	@Path("/findRecipeID/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe findRecipeById(@PathParam("id") int recipeID) {
		return recipeManager.findRecipeById(recipeID);
	}

	@GET
	@Path("/getAllRecipes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getAllRecipes() {
		return recipeManager.getAllRecipes();
	}
	
	@GET
	@Path("/findRecipeName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByName(@PathParam("name") String name) {
		return recipeManager.findRecipeByName(name);
	}


	@GET
	@Path("/findRecipeTime/{minTime}/{maxTime}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByTime(@PathParam("minTime") int mintime, @PathParam("maxTime") int maxtime) {
		return recipeManager.findRecipeByTime(mintime, maxtime);
	}
	
	
	@GET
	@Path("/Co2Value/{recipeID}/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe calculateCO2(@PathParam("recipeID") int recipeID, @PathParam("username") String username){
		return CO2Calculation.getInstance().calculateCO2(recipeID, username);
	}

//	public List<Recipe> getRezeptByCategory(Category C) {
//		return recipeManager.getRezeptByCategory(C);
//	}
//
//	public List<Recipe> getRezeptByCategory() {
//		return recipeManager.getRezeptByCategory();
//	}
//
	
	/***************************************************************
	 * 
	 * IngredientType manager
	 * 
	 ***************************************************************/
	@GET
	@Path("/ingredientType")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IngredientType> findAlLIngredientType() {
		return ingredientTypeManager.findAllIngredientType();
	}

	@GET
	@Path("/ingredientType/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IngredientType> findIngredientByName(
			@PathParam("name") String name) {
		return ingredientTypeManager.findIngredientByName(name);
	}

	@POST
	@Path("/addIngredientType")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addIngredientType(IngredientType ingredientType) {
		if (ingredientTypeManager.addIngredientType(ingredientType)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}

	/***********************************************************
	 * 
	 * Composed Of
	 * 
	 ***********************************************************/
	@GET
	@Path("/ingredient/{ingredientId}")
	@Produces(MediaType.APPLICATION_JSON)
	public RecipeIngredients getIngredients(
			@PathParam("ingredientId") int recipeID) {
		return composedOfManager.getIngredients(recipeID);
	}

	// @GET
	// @Path("/recipe/ingredients/{lz}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public List<Recipe> findRezeptByIngredient(@PathParam("lz")
	// List<IngredientType> lz) {
	// return composedOfManager.findRezeptByIngredient(lz);
	// }

	@POST
	@Path("/ingredient/{recipeID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addIngredientToRecipe(@PathParam("recipeID") int recipeID, RecipeIngredients recIngredients) {
		if (composedOfManager
				.addIngredientToRecipe(recipeID,
						recIngredients.getIngredients(),
						recIngredients.getQuantities())) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}
	
	@GET
	@Path("/findRecipe1/{i1}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByIngredient(@PathParam("i1") int ingredient1) {
		return composedOfManager.findRecipeByIngredient(ingredient1);
	}


	@GET
	@Path("/findRecipe2/{i1}/{i2}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByIngredient(@PathParam("i1") int ingredient1, @PathParam("i2") int ingredient2) {
		return composedOfManager.findRecipeByIngredient(ingredient1, ingredient2);
	}

	@GET
	@Path("/findRecipe3/{i1}/{i2}/{i3}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findRecipeByIngredient(@PathParam("i1") int ingredient1, @PathParam("i2") int ingredient2, @PathParam("i3") int ingredient3) {
		return composedOfManager.findRecipeByIngredient(ingredient1, ingredient2, ingredient3);
	}

	
	 /************************************************************************
	 *
	 * Ingredient
	 *
	 ************************************************************************/
	@POST
	@Path("/addIngredient")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addIngredient(Ingredient ingredient) {
		if (ingredientManager.addIngredient(ingredient)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}
	
	@GET
	@Path("/findIngredientByType/{type}")
	public List<Ingredient> findIngredientsByIngredientType(@PathParam("type") int ingredientTypeID) {
		return ingredientManager.findIngredientsByIngredientType(ingredientTypeID);
	}

	 /***************************************************************************
	 *
	 * Rating
	 *
	 ****************************************************************************/
	@POST
	@Path("/addRating")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRating(Rating rating) {
		if (ratingManager.addRating(rating)) {
			return Response.status(200).entity(true).build();
		}
		return Response.status(200).entity(false).build();
	}

	/***************************************************************************
	 * 
	 * Region
	 * 
	 ****************************************************************************/
	@GET
	@Path("/region/{countryCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Region> findRegionByCountryCode(
			@PathParam("countryCode") String Code) {
		return regionManager.findRegionByCountryCode(Code);
	}
	
	/******************************************************************
	 * 
	 * Miscellaneous 
	 * 
	 *
	 * 
	 *******************************************************************/
	

	@GET
	@Path("/findFavRecipe/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> findFavoriteRecipe(@PathParam("username") String username) {
		return favoriteRecipeManager.findFavoriteRecipe(username);
	}

	@POST
	@Path("/addFavRecipe/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addFavoriteRecipe(int recipeID, @PathParam("username") String username) {
		return favoriteRecipeManager.addFavoriteRecipe(recipeID, username);
	}


	@POST
	@Path("/addCooked/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean addCooked(@PathParam("id") int recipeID) {
		return recipeManager.addCooked(recipeID);
	}


}
