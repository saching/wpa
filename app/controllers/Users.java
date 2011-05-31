package controllers;

import java.util.Date;

import models.User;
import models.UserProfile;
import play.cache.Cache;
import play.data.validation.Equals;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import services.UserProfileService;
import services.UserService;
import utils.CommonUtils;

@With(Secure.class)
public class Users extends Controller {

	@Before(unless = { "fbconnect", "postPassword", "register", "postRegister" })
	static void checkConnected() {
		if (session.contains("username")) {
			renderArgs.put("username", session.get("username"));
		}

		// to check the password is saved or not
		if (session.contains("forceUrl")) {
			flash.error("Please provide password.");
			render(session.get("forceUrl"));
		}
	}

	/**
	 * action to save post password after facebook connect
	 * 
	 * @param password
	 * @param repeatpassword
	 */
	public static void postPassword(
			@Required(message = "Password is required") String password,
			@Required(message = "Repeat-password type the code") String repeatpassword) {
		if (request.method.equalsIgnoreCase("POST")) {
			User user = UserService.getUserByUsername(session.get("username"));
			// update the password
			user.password = CommonUtils.encryptPassword(password);
			user.save();

			session.remove("forceUrl");
			flash.success("Profile created successfully.");

			// forward to application index
			Application.index();
		}
	}

	// action to view user profile
	public static void view() {
		User user = UserService.getUserByUsername(session.get("username"));
		UserProfile userprofile = null;

		// check if user object is null
		if (user != null)
			userprofile = UserProfileService.getUserProfileByUserId(user.id);

		render(user, userprofile);
	}

	/**
	 * action to edit profile details
	 */
	public static void editProfile(String form, String ly) {
		boolean layout;
		if(ly == null || ly.equalsIgnoreCase("yes"))
			layout = true;
		else
			layout = false;
		
		User user = UserService.getUserByUsername(session.get("username"));
		UserProfile userprofile = null;

		// check if user object is null
		if (user != null)
			userprofile = UserProfileService.getUserProfileByUserId(user.id);

		//set password into session
		session.put("edit_password", user.password);
		
		if(form == null)
			form = "account";
		
		render(user, userprofile, layout, form);
	}

	/**
	 * action to update the user account
	 * 
	 * @param user
	 */
	public static void updateAccount(@Valid User user) {
		boolean layout = false;
		String form = "account";
		if (request.method.equalsIgnoreCase("POST")) {
			if (validation.hasErrors()) {
				render("Users/editProfile.html",
						user,
						UserProfileService.getUserProfileByUserId(user.id),
						layout, form);
			} else {

				if (user.password == null) {
					// set the old password if user do not want to change the
					// password
					user.password = session.get("edit_password");
				} else {
					// encrypt the password if user added new password
					user.password = CommonUtils.encryptPassword(user.password);
				}

				// update user account
				user.save();

				flash.success("Account details updated successfully.");
				render("Users/editProfile.html",
						user,
						UserProfileService.getUserProfileByUserId(user.id),
						layout, form);
			}
		} else {
			flash.error("Invalid access to page.");
			render("Application/index.html");
		}
	}

	public static void updateDetails(@Valid UserProfile userprofile) {
		boolean layout = false;
		
		String form = "profile";
		if (request.method.equalsIgnoreCase("POST")) {
			User user = User.find("byId", userprofile.userId).first();
			if (validation.hasErrors()) {
				render("Users/editProfile.html", user, userprofile, layout, form);
			} else {
				// save the user profile changes
				userprofile.save();
				flash.success("Profile details updated successfully.");
				render("Users/editProfile.html", user, userprofile,	layout, form);
			}
		} else {
			flash.error("Invalid access to page.");
			render("Application/index.html");
		}
	}
}
