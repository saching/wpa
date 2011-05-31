package controller.functional;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class UsersTest extends FunctionalTest {

	@Test
    public void testThatRecentsPageWorks() {
        Response response = GET("/Screencasts/recents");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}
