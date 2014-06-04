package persistent.hibernateManager;

import java.io.File;
import java.util.ArrayList;
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

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import persistent.classes.User;

/**
 * Fassade for hibernate
 * 
 * @author mirko
 * 
 */
//Will map the resource to the URL todos
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

	private CityManager cityManager;
	private ComposedOfManager composedOfManager;
	private CountryManager countryManager;
	private FriendManager friendManager;
	private IngredientManager ingredientManager;
	private IngredientTypeManager ingredientTypeManager;
	private RatingManager ratingManager;
	private RecipeManager recipeManager;
	private RegionManager regionManager;
	private UserManager userManager;

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
	@Path("/login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User logIn(@PathParam("username") String userName,
			@PathParam("password") String suggestedPassword) {
		return userManager.logIn(userName, suggestedPassword);
	}

	 @POST
	 @Path("/register")
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addUser(User user){
	 if(userManager.addUser(user)){
	 return Response.status(200).entity(true).build();
	 }
	 return Response.status(200).entity(false).build();
	 }
	
	
	 public User findUserById(String userName) {
	 return userManager.findUserById(userName);
	 }
	
	
	 public boolean setUserFoto(User u, File f){
	 return userManager.setUserFoto(u, f);
	 }
	
	
	 public boolean setUserAsNotActive(User u){
	 return userManager.setUserAsNotActive(u);
	 }
	
	
	 public boolean setUserAsActive(String username){
	 return userManager.setUserAsActive(username);
	 }
	
	
	
	
	 /****************************************************************
	 *
	 * Country Functionalities
	 *
	 ****************************************************************/
	
	 @GET
	 @Path("/country")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<String> getCountryList(){
		 List<String> blub = new ArrayList();
		 blub.add("hallo");
		 blub.add("test");
		 return blub;
//	 return countryManager.getCountryList();
	 }
//	
//	 @GET
//	 @Path("/countrybycode/{countryCode}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public Country getCountryByCode(@PathParam("countryCode") String
//	 countryCode) {
//	 return countryManager.getCountryByCode(countryCode);
//	 }
//	
//	 @GET
//	 @Path("/country/{countryName}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<String> findCountryByName(
//	 @PathParam("countryName") String countryName) {
//	 return countryManager.findCountryByName(countryName);
//	 }
//	
//	 @GET
//	 @Path("/countrycode/{countryName}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public String findCountryCodeByName(
//	 @PathParam("countryName") String countryName) {
//	 return countryManager.findCountryCodeByName(countryName);
//	 }
//	
//	
//	
//	
//	
//	 /*****************************************************************
//	 *
//	 * City Functionalities
//	 *
//	 *****************************************************************/
//	
//	 @GET
//	 @Path("/city/{cityId}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public City findCityByID(@PathParam("cityId") int cityID){
//	 return cityManager.findCityByID(cityID);
//	 }
//	
//	 @GET
//	 @Path("/city/{cityName}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<City> findCityByName(@PathParam("cityName") String cityName)
//	 {
//	 return cityManager.findCityByName(cityName);
//	 }
//	
//	 @GET
//	 @Path("/city/{country}/{region}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<City> findCityNameByCountryAndRegion(
//	 @PathParam("country") String country,
//	 @PathParam("region") String region) {
//	 return cityManager.findCityNameByCountryAndRegion(country, region);
//	 }
//	
//	
//	 /******************************************************************
//	 *
//	 * Friends functionalities
//	 *
//	 ******************************************************************/
//	 @GET
//	 @Path("/friend/{username}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<String> getFriend(@PathParam("username") String username){
//	 return friendManager.getFriend(username);
//	 }
//	
//	 /**
//	 * return value was boolean, I changed it to Response!
//	 * (I am not sure about that!)
//	 *
//	 * Is it better that we return in the FriendManager class at function
//	 * addFriend a type Friend instead of the type boolean. So we can
//	 * return here the Response.
//	 */
//	 @POST
//	 @Path("/friend/{username1}/{username2}")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addFriend(@PathParam("username1") String username1,
//	 @PathParam("username2") String username2) {
//	 if (friendManager.addFriend(username1, username2)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	
//	 @DELETE
//	 @Path("/friend")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public boolean deleteFriend(String username1, String username2){
//	 return friendManager.deleteFriend(username1, username2);
//	 }
//	
//	 @GET
//	 @Path("/friend/{username1}/{username2}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public boolean existFriend(@PathParam("username1") String username1,
//	 @PathParam("username2") String username2) {
//	 return friendManager.existFriend(username1, username2);
//	 }
//	
//	 /****************************************************************
//	 *
//	 * Recipe functionalities
//	 *
//	 ****************************************************************/
//	 @POST
//	 @Path("/recipe")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addRecipe(Recipe r){
//	 if (recipeManager.addRecipe(r)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	
//	 @GET
//	 @Path("/recipe/{author}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<Recipe> findRecipeByAutor(@PathParam("author") String
//	 username) {
//	 return recipeManager.findRecipeByAutor(username);
//	 }
//	
//	
//	 @DELETE
//	 @Path("/recipe")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public boolean removeRecipe(String username, int recipeID) {
//	 return recipeManager.removeRecipe(username, recipeID);
//	 }
//	
//	
//	
//	 public List<Recipe> getRezeptByCategory(Category C) {
//	 return recipeManager.getRezeptByCategory(C);
//	 }
//	
//	
//	
//	 public List<Recipe> getRezeptByCategory() {
//	 return recipeManager.getRezeptByCategory();
//	 }
//	
//	
//	
//	
//	 /***************************************************************
//	 *
//	 * IngredientType manager
//	 *
//	 ***************************************************************/
//	 @GET
//	 @Path("/ingredientType")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<String> getAllIngredientType(){
//	 return ingredientTypeManager.getAllIngredientType();
//	 }
//	
//	 @GET
//	 @Path("/ingredientType/{ingredientName}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<IngredientType>
//	 findIngredientByName(@PathParam("ingredientName") String name){
//	 return ingredientTypeManager.findIngredientByName(name);
//	 }
//	
//	 @POST
//	 @Path("/ingredientType")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addIngredientType(IngredientType ingredientType){
//	 if (ingredientTypeManager.addIngredientType(ingredientType)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	
//	 /***********************************************************
//	 *
//	 * Composed Of
//	 *
//	 ***********************************************************/
//	 @GET
//	 @Path("/ingredient/{ingredientId}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<IngredientType> getIngredients(
//	 @PathParam("ingredientId") int recipeID) {
//	 return composedOfManager.getIngredients(recipeID);
//	 }
//	
//	 @GET
//	 @Path("/recipe/ingredients")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<Recipe> findRezeptByIngredient(List<IngredientType> lz) {
//	 return composedOfManager.findRezeptByIngredient(lz);
//	 }
//	
//	 @POST
//	 @Path("/ingredient")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addIngredientToRecipe(int recipeID, List<IngredientType>
//	 ingredients,
//	 List<String> quantity){
//	 if (composedOfManager.addIngredientToRecipe(recipeID, ingredients,
//	 quantity)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	
//	 /************************************************************************
//	 *
//	 * Ingredient
//	 *
//	 ************************************************************************/
//	 @POST
//	 @Path("/ingredient")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addIngredient(Ingredient ingredient) {
//	 if (ingredientManager.addIngredient(ingredient)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	 /***************************************************************************
//	 *
//	 * Rating
//	 *
//	 ****************************************************************************/
//	 @POST
//	 @Path("/rating")
//	 @Consumes(MediaType.APPLICATION_JSON)
//	 public Response addRating(Rating rating){
//	 if (ratingManager.addRating(rating)) {
//	 return Response.status(200).entity(true).build();
//	 }
//	 return Response.status(200).entity(false).build();
//	 }
//	
//	 /***************************************************************************
//	 *
//	 * Region
//	 *
//	 ****************************************************************************/
//	 @GET
//	 @Path("/region/{countryCode}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public List<Region> getRegionByCountryCode(
//	 @PathParam("countryCode") String Code) {
//	 return regionManager.getRegionByCountryCode(Code);
//	 }

}