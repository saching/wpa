import java.util.Date;

import models.Screencast;

import org.junit.Test;

import play.test.UnitTest;


public class ScreencastTest extends UnitTest {

	@Test
	public void createAndRetrieveScreencast() {
	    // Create a new Screencast and save it
	//	new Screencast("videot.png", "My test video1", "My test video1 desc", 1, 1, new Date(), false, true).save(); 
		
		Screencast scast = Screencast.find("byUrl", "videot.png").first();
		
		// Test 
	    assertEquals(1, Screencast.count());
	    assertEquals("videot.png", scast.url);
	    assertEquals("My test video1", scast.title);
	}	
	
}
