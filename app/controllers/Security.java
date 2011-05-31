package controllers;

import java.util.Date;

import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Codec;
import play.mvc.Scope.Session;
import services.UserService;
import models.User;

public class Security extends Secure.Security {
	
    static boolean authenticate(String username, String password) {
    	return UserService.connect(username, password) != null;
    }
    
    static boolean check(String profile) {
	    
    	//check for the guest user
    	if("user".equalsIgnoreCase(profile)){
    		return isConnected();
    	}
    	
    	//checks for the logged user
    	if("guest".equals(profile)) {
    		return !isConnected();
	    }
    	
    	//checks for the admin user
    	if("admin".equals(profile)) {
	    	return false;
	    }
	    
	    return false;
	}
    
    //sets user object in session
    static void onAuthenticated(){
    	User user = UserService.getUserByUsername(connected());
    	
    	Session.current().put("user", user);
    }
    
    //remove the custom session variables
    static void onDisconnected(){
    	Session.current().remove("user");
    }
    
 // user registration action
	public static void register() {
		String randomID = Codec.UUID();
		render(randomID);
	}

	// user create action
	public static void postRegister(
			@Required(message = "Username is required") String username,
			@Required(message = "Email is required") String email,
			@Required(message = "Password is required") String password,
			@Required(message = "Repeat-password type the code") String repeatpassword,
			String firstname, String lastname, String randomID, String code) {

		validation.equals(code, Cache.get(randomID)).message(
				"Invalid code. Please type it again");
		if (validation.hasErrors()) {
			render("Users/register.html", randomID);
		}

		new User(username, email, password, false, new Date(), firstname,
				lastname, UserService.getNewSlug()).save();

		flash.success("Thank you, for registering %s", username);
		Cache.delete(randomID);
		render();
	}
	
	
    
}