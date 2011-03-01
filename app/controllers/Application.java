package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	//get all the business
    	List business = null;//TblBusiness.find(" createdBy > 1 ORDER BY createdAt DESC").fetch();
    	
    	//render template
        render(business);
    }

}