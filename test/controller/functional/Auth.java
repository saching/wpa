package controller.functional;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class Auth extends FunctionalTest {

	//functional test for login page
	@Test
    public void testThatLoginPageWorks() {
        Response response = GET("/login");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
	
	//functional for logout page
	@Test
    public void testThatLogoutPageWorks() {
        Response response = GET("/logout");
        assertStatus(302, response);
	    assertHeaderEquals("Location", "http://localhost/login", response);
    }
	
	
	//functional test for auth check
	@Test
	public void testAuthenticationSecurity() {
	    Response response = GET("/Screencasts/recents");
	    assertStatus(302, response);
	    assertHeaderEquals("Location", "http://localhost/login", response);
	}
}
