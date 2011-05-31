package controller.unit;

import java.util.Date;

import models.User;

import org.junit.Test;

import play.test.UnitTest;
import services.UserService;

public class UserTest extends UnitTest {

	//method to check new slug method (method should not return similar slugs)
	@Test
	public void testGetNewSlug(){
		String slugA = UserService.getNewSlug();
		String slugB = UserService.getNewSlug();
		assertNotSame("Slug are not same.", slugA, slugB);
	}
	
	//get user by username/email
	@Test
	public void testGetUserByUsername(){
		String email = "aarifm@siddhatech.com";
		String username = "sachinG";
		User temp = new User("sachinG", "aarifm@siddhatech.com", "sachin", true, new Date(), "sachin", "gosarade", UserService.getNewSlug()).save();
		
		//get user by email
		User user = UserService.getUserByUsername(email);		
		assertEquals("sachinG", user.username);
		assertEquals("sachingosarade", (user.firstname+user.lastname));
		assertEquals("sachin", user.password);
		
		//get user by username
		user = UserService.getUserByUsername(username);		
		assertEquals("aarifm@siddhatech.com", user.email);
		assertEquals("sachingosarade", (user.firstname+user.lastname));
		assertEquals("sachin", user.password);
	}
	
	//to check getUserBySug method
	@Test
	public void testGetUserbySlug(){
		String slug = UserService.getNewSlug();
		new User("sachinGs", "saching@siddhatech.com", "sachins", false, new Date(), "AC", "Jadhav", slug).merge();
		User user = UserService.getUserBySlug(slug);
		
		assertEquals("SachinGs", user.username);
		assertEquals("saching@siddhatech.com", user.email);
	}
}
