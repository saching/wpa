package controllers;

import java.util.List;

import models.Screencast;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import play.modules.paginate.ValuePaginator;

@With(Secure.class)
public class Screencasts extends Controller {

	@Before
    static void checkConnected() {
        if(session.contains("username")){
            renderArgs.put("username", session.get("username"));
        }
        
      //to check the password is saved or not
        if(session.contains("forceUrl")){
        	flash.error("Please provide password.");
        	render(session.get("forceUrl"));
        }
    }
	
	/**
	 * controller action shows recent added screen casts
	 */
	public static void list(String type, String page){
		String sql = "ORDER BY uploadedOn DESC LIMIT 10";
		if(type == null)
			sql = "ORDER BY uploadedOn DESC";
		
		//get all the screen casts
    	List screencasts = Screencast.find(sql).fetch();
    	ValuePaginator paginator = new ValuePaginator(screencasts);
        render(paginator);
    	
	}
	
	
	
}
