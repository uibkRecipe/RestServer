package persistent.hibernateManager;

import java.io.File;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import persistent.classes.City;
import persistent.classes.Country;
import persistent.classes.FavoriteRecipe;
import persistent.classes.Ingredient;
import persistent.classes.IngredientType;
import persistent.classes.Rating;
import persistent.classes.Recipe;
import persistent.classes.RecipeIngredients;
import persistent.classes.Region;
import persistent.classes.User;
import persistent.interfaces.HibernateUtilInterface;
/**
 * Fassade for hibernate
 * @author mirko
 *
 */
public class HibernateUtil implements HibernateUtilInterface{
	
	private SessionFactory 					sessionFactory; 
	StandardServiceRegistryBuilder 			ssrb;
	
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
	
	
	
	private static HibernateUtil instance = null;

	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}

		return instance;
	}
	
	
	/**
	 * Constructor return a new HibernateManager
	 */
	private HibernateUtil() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		ssrb = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(ssrb
				.build());
		
		
		
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
	public SessionFactory getSessionFactory(){
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
	 *  User Functionalities
	 *  
	 ****************************************************************/
	

	public User logIn(String userName, String suggestedPassword){
		return userManager.logIn(userName, suggestedPassword);
	}
		
	public boolean addUser(User user){
		return userManager.addUser(user);
	}
	
	
	public User findUserById(String userName) {
		return userManager.findUserById(userName);
	}
	
	
	
	
	public boolean setUserFoto(User u, File f){
		return userManager.setUserFoto(u, f);
	}
	

	public boolean setUserAsNotActive(String username){
		return userManager.setUserAsNotActive(username);
	}
	public boolean setUserAsActive(String username){
		return userManager.setUserAsActive(username);
	}
	
	@Override
	public boolean changePassword(String username, String oldPassword,
			String newPassword, String newPasswordConfirm) {
		return userManager.changePassword(username, oldPassword, newPassword, newPasswordConfirm);
	}
	
	
	
	
	/****************************************************************
	 * 
	 * Country Functionalities
	 * 
	 ****************************************************************/
	
	public List<Country> getCountryList(){
		return countryManager.getCountryList();
	}

	public Country findCountryByCode(String countryCode){
		return countryManager.findCountryByCode(countryCode);
	}
	
	public List<String> findCountryByName(String countryName){
		return countryManager.findCountryByName(countryName);
	}
	
	@Override
	public String findCountryCodeByName(String countryName) {
		return countryManager.findCountryCodeByName(countryName);
	}
	
	
	
	
	
	/*****************************************************************
	 * 
	 * City Functionalities
	 * 
	 *****************************************************************/
	
	
	public City findCityByID(int cityID){
		return cityManager.findCityByID(cityID);
	}
	public List<City> findCityByName(String cityName) {
		return cityManager.findCityByName(cityName);
	}
	
	@Override
	public List<City> findCityNameByCountryAndRegion(String country,
			String region) {
		
		return cityManager.findCityNameByCountryAndRegion(country, region);
	}
	

	@Override
	public List<City> findCityByCountry(String country) {
		return cityManager.findCityByCountry(country);	
	}
	
	
	/******************************************************************
	 * 
	 * 
	 * Friends functionalities
	 * 
	 * 
	 ******************************************************************/ 
	public List<String> getFriend(String username){
		return friendManager.getFriend(username);
	}
	public boolean addFriend(String username1, String username2){
		return friendManager.addFriend(username1, username2);
	}
	public boolean deleteFriend(String username1, String username2){
		return friendManager.deleteFriend(username1, username2);
	}
	@Override
	public boolean existFriend(String username1, String username2) {
		return friendManager.existFriend(username1, username2);
	}
	
	/****************************************************************
	 *  
	 * Recipe functionalities
	 *
	 ****************************************************************/
	public boolean addRecipe(Recipe r){
		return recipeManager.addRecipe(r);
	}
	
	public List<Recipe> findRecipeByAutor(String username){
		return recipeManager.findRecipeByAutor(username);
	}
	

	@Override
	public boolean removeRecipe(String username, int recipeID) {
		
		return recipeManager.removeRecipe(username, recipeID);
	}


	@Override
	public List<Recipe> findRecipeByCategory(String category) {
		return recipeManager.findRecipeByCategory(category);
	}


	@Override
	public List<Recipe> findRecipeByCategory() {
		return recipeManager.findRecipeByCategory();
	}
	

	@Override
	public boolean setRecipeFoto(String username, int recipeID, File f) {
		return recipeManager.setRecipeFoto(username, recipeID, f);
	}


	@Override
	public Recipe findRecipeById(int recipeID) {
		return recipeManager.findRecipeById(recipeID);
	}


	@Override
	public List<Recipe> getAllRecipes() {
		return recipeManager.getAllRecipes();
	}
	
	@Override
	public List<Recipe> findRecipeByName(String name) {
		return recipeManager.findRecipeByName(name);
	}


	@Override
	public List<Recipe> findRecipeByTime(int mintime, int maxtime) {
		return recipeManager.findRecipeByTime(mintime, maxtime);
	}
	
	
	/***************************************************************
	 * 
	 * IngredientType manager
	 * 
	 ***************************************************************/
	public List<IngredientType> findAllIngredientType(){
		return ingredientTypeManager.findAllIngredientType();
	}
	public List<IngredientType> findIngredientByName(String name){
		return ingredientTypeManager.findIngredientByName(name);
	}
	public boolean addIngredientType(IngredientType ingredientType){
		return ingredientTypeManager.addIngredientType(ingredientType);
	}
	

	


	


	




	/***********************************************************
	 * 
	 * Composed Of
	 * 
	 ***********************************************************/
	@Override
	public RecipeIngredients getIngredients(int recipeID) {
		return composedOfManager.getIngredients(recipeID);	
	}
	@Override
	public List<Recipe> findRecipeByIngredient(List<IngredientType> lz) {
		return composedOfManager.findRecipeByIngredient(lz);
	}
	
	public boolean addIngredientToRecipe(int recipeID, List<IngredientType> ingredients,
			List<String> quantity){
		return composedOfManager.addIngredientToRecipe(recipeID, ingredients, quantity);
	}
	
	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1) {
		return composedOfManager.findRecipeByIngredient(ingredient1);
	}


	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1, int ingredient2) {
		return composedOfManager.findRecipeByIngredient(ingredient1, ingredient2);
	}


	@Override
	public List<Recipe> findRecipeByIngredient(int ingredient1,
			int ingredient2, int ingredient3) {
		return composedOfManager.findRecipeByIngredient(ingredient1, ingredient2, ingredient3);
	}

	/************************************************************************
	 * 
	 * Ingredient
	 * 
	 ************************************************************************/
	@Override
	public boolean addIngredient(Ingredient ingredient) {
		return ingredientManager.addIngredient(ingredient);
		
	}
	
	@Override
	public List<Ingredient> findIngredientsByIngredientType(int ingredientTypeID) {
		return ingredientManager.findIngredientsByIngredientType(ingredientTypeID);
	}
	
	/***************************************************************************
	 * 
	 * Rating 
	 * 
	 ****************************************************************************/

	
	public boolean addRating(Rating rating){
		return ratingManager.addRating(rating);
	}
	
	/***************************************************************************
	 * 
	 * Region
	 * 
	 ****************************************************************************/
	
	public List<Region> findRegionByCountryCode(String Code){
		return regionManager.findRegionByCountryCode(Code);
	}


	
	/******************************************************************
	 * 
	 * Miscellaneous 
	 * 
	 *
	 * 
	 *******************************************************************/
	@Override
	public City getUserPosition(String username) {
		User u = findUserById(username);
		return cityManager.findCityByID(u.getCity());
		
	}


	@Override
	public List<Recipe> findRecipeByTime(int time) {
		return recipeManager.findRecipeByTime(time);
	}


	@Override
	public List<Recipe> findFavoriteRecipe(String username) {
		return favoriteRecipeManager.findFavoriteRecipe(username);
	}


	@Override
	public boolean addFavoriteRecipe(int recipeID, String username) {
		return favoriteRecipeManager.addFavoriteRecipe(recipeID, username);
	}


	@Override
	public boolean addFavoriteRecipe(FavoriteRecipe fr) {
		return  favoriteRecipeManager.addFavoriteRecipe(fr);
	}


	@Override
	public Region findRegionByCountryAndRegionName(String Code, String Name) {
		return regionManager.findRegionByCountryAndRegionName(Code, Name);
	}


	@Override
	public boolean addCooked(int recipeID) {
		return recipeManager.addCooked(recipeID);
	}










	





	




	

	
}
