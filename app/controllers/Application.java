package controllers;

import play.*;
import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.*;
import services.UserService;

import java.net.URL;
import java.util.*;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import models.*;

public class Application extends Controller {

	@Before
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

	public static void index() {
		// get all the business
		List business = null;// TblBusiness.find(" createdBy > 1 ORDER BY createdAt DESC").fetch();

		// render template
		render(business);
	}

	// method returns captcha image
	public static void captcha(String id) {
		Images.Captcha captcha = Images.captcha();
		String code = captcha.getText("#E4EAFD");
		Cache.set(id, code, "10mn");

		renderBinary(captcha);
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
			render("Application/register.html", randomID);
		}

		new User(username, email, password, false, new Date(), firstname,
				lastname, UserService.getNewSlug()).save();
		
		User user = UserService.getUserByUsername(username);
		sendRegistrationMail(email, user.id);

		flash.success("Thank you, for registering %s", username);
		Cache.delete(randomID);
		render();
	}

	private static void sendRegistrationMail(String to, int activationCode) {

		try {
			Random random = new Random();
			String site = "http://localhost:9001";
			HtmlEmail email = new HtmlEmail();

			email.addTo(to);
			email.setFrom("saching@siddhatech.com", "Sachin Gosarade");
			email.setSubject("Registration : Test email");

			// embed the image and get the content id
			String url = site + "/activate?user=" + random.nextInt() + "&aff=" + activationCode;
			
			// set the html message
			email.setHtmlMsg("<html><body style='font-family:arial;font-size:11px;'><h1>Registration Successful</h1><br /><br /><p>Thank you, for registering on wpa.com. Please activate you account, <a target='_blank' href='"+ url +"'>click here</a></p><br/><br/>wpa.com</body></html>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages, too bad :(");
			
			email.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// feedback method for facebook connect
	public static void fbconnect() {
		flash.success("You facebook account has been confirmed.");
		session.put("forceUrl", "Application/fbconnect.html");
		render();
	}

}