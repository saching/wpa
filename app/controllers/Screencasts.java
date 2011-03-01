package controllers;

import java.util.List;

import models.Screencast;
import models.TblBusiness;
import play.mvc.Controller;

public class Screencasts extends Controller {

	/**
	 * controller action shows recent added screen casts
	 */
	public static void recents(){
		//get all the screencasts
    	List screencasts = Screencast.find("ORDER BY uploadedOn DESC LIMIT 10").fetch();
    	
    	//render template
        render(screencasts);
	}
}
