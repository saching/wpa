package controller.functional;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

public class ScreencastsTest extends FunctionalTest {

    @Test
    public void testThatRecentsPageWorks() {
        Response response = GET("/Screencasts/recents");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
}