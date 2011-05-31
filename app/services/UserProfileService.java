package services;

import models.User;
import models.UserProfile;

public class UserProfileService extends UserProfile {

	/**
	 * returns UserProfile object using user_id
	 * @param id
	 * @return
	 */
	public static UserProfile getUserProfileByUserId(int userId) {
		return UserProfile.find("byUserId", userId).first();
	}
	
	/**
     * returns user object find by slug
     * @param slug
     * @return
     */
    public static User getUserBySlug(String slug){
    	return User.find("bySlug", slug).first();
    }
    
    /**
     * this code backup, DON'T REMOVE THIS CODE
     */
    /**public static String getFullAddress(UserProfile userProfile){
    	String address = "";
    	
    	address += userProfile.address == null ? "" : userProfile.address;
    	address += userProfile.city == null ? "" : (address.length() < 1 ? "" : (", " + userProfile.city));
    	address += userProfile.state == null ? "" : (address.length() < 1 ? "" : (", " + userProfile.state));
    	address += userProfile.country == null ? "" : (address.length() < 1 ? "" : (", " + userProfile.country));
    	address += userProfile.zipcode == null ? "" : (address.length() < 1 ? "" : (", " + userProfile.zipcode));
    	
    	return address;
    }*/

	
}
