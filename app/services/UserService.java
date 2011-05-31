package services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import play.mvc.Scope.Session;

import utils.CommonUtils;

import com.google.gson.JsonObject;

import models.User;
import models.UserProfile;

public class UserService extends User {

	/**
	 * returns next slug
	 * 
	 * @return
	 */
	public static String getNewSlug() {
		Random random = new Random();
		return Long.toString(new Date().getTime()) + random.nextInt();
	}

	/**
	 * returns user object using username
	 * 
	 * @param username
	 * @return
	 */
	public static User getUserByUsername(String username) {
		User user = User.find("byUsername", username).first();

		// if user not found matching with username
		if (user == null)
			user = User.find("byEmail", username).first();

		return user;
	}

	/**
	 * returns user object find by slug
	 * 
	 * @param slug
	 * @return
	 */
	public static User getUserBySlug(String slug) {
		return User.find("bySlug", slug).first();
	}

	/**
	 * method used at the time of login
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static User connect(String username, String password) {
		password = CommonUtils.encryptPassword(password);

		User user = getUserByUsername(username);
		if (user != null && user.password.equalsIgnoreCase(password))
			return user;

		return null;
	}

	/**
	 * facebook connect user save code
	 */
	public static void facebookOAuthCallback(JsonObject data) {
		try {
			String email = data.get("email").getAsString();
			
			//get user matching with the email
			User user = getUserByUsername(email);
			
			//if user not found then create new user
			if (user == null) {
				
				//set the default values for User object
				user = new User(email, email, "temp", true, new Date(), data.get(
						"first_name").getAsString(), data.get("last_name")
						.getAsString(), getNewSlug());
				
				//save the user object
				user.save();
				
				UserProfile userProfile = new UserProfile();

				// get city, state from fb
					String[] location = data.get("location").getAsJsonObject().get(
						"name").getAsString().split(",");
					
				// if state not found
				if (location == null || location.length < 2)
					userProfile.city = data.get("location").getAsJsonObject()
							.get("name").getAsString();
				else {
					userProfile.city = location[0];
					userProfile.state = location[1];
				}
				
				//get the date of birth
				DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
				userProfile.dob = dateFormat.parse(data.get("birthday").getAsString());
				
				//get the gender
				userProfile.gender = "male".equalsIgnoreCase(data
						.get("gender").getAsString());
				
				//set mandetory fields
				user = getUserByUsername(email);
				userProfile.createdAt = new Date();
				userProfile.userId = user.id;
				
				//save the user profile object
				userProfile.save();
			}
			
			//set the logged in session
			Session.current().put("username", email);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
