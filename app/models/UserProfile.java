package models;
// Generated on the Mar 4, 2011 3:02:13 PM by Play!

import play.db.jpa.JPASupport;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserProfile generated by Play!
 */
@Entity
@Table(name="user_profile"
    , uniqueConstraints = { @UniqueConstraint( columnNames = { "user_id" } ) }
)
public class UserProfile extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
    @Column(name="user_id", unique=true, nullable=false, insertable=true, updatable=true)
        public int userId;
    
    @Column(name="address", unique=false, nullable=true, insertable=true, updatable=true, length=1000)
        public String address;
    
    @Column(name="city", unique=false, nullable=true, insertable=true, updatable=true)
        public String city;
    
    @Column(name="state", unique=false, nullable=true, insertable=true, updatable=true)
        public String state;
    
    @Column(name="country", unique=false, nullable=true, insertable=true, updatable=true)
        public String country;
    
    @Column(name="zipcode", unique=false, nullable=true, insertable=true, updatable=true, length=10)
        public String zipcode;
    
    @Column(name="avatar", unique=false, nullable=true, insertable=true, updatable=true, length=1000)
        public String avatar;
    
    @Column(name="gender", unique=false, nullable=true, insertable=true, updatable=true)
        public Boolean gender;
    
    @Column(name="dob", unique=false, nullable=true, insertable=true, updatable=true, length=19)
        public Date dob;
    
    @Column(name="created_at", unique=false, nullable=false, insertable=true, updatable=true, length=19)
        public Date createdAt;
    
    @Column(name="screencast_interests", unique=false, nullable=true, insertable=true, updatable=true)
        public String screencastInterests;
    
    // Constructors

    /** default constructor */
    public UserProfile() {
    }

	/** minimal constructor */
    public UserProfile(int id, int userId, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
    }
    /** full constructor */
    public UserProfile(int id, int userId, String address, String city, String state, String country, String zipcode, String avatar, Boolean gender, Date dob, Date createdAt, String screencastInterests) {
       this.id = id;
       this.userId = userId;
       this.address = address;
       this.city = city;
       this.state = state;
       this.country = country;
       this.zipcode = zipcode;
       this.avatar = avatar;
       this.gender = gender;
       this.dob = dob;
       this.createdAt = createdAt;
       this.screencastInterests = screencastInterests;
    }
   
    public String getFullAddress(){
    	String address = "";
    	
    	address += this.address == null ? "" : this.address;
    	address += this.city == null ? "" : ((address.length() < 1 ? "" : ", ") + this.city);
    	address += this.state == null ? "" : ((address.length() < 1 ? "" : ", ") + this.state);
    	address += this.country == null ? "" : ((address.length() < 1 ? "" : ", ") + this.country);
    	address += this.zipcode == null ? "" : ((address.length() < 1 ? "" : ", ") + this.zipcode);
    	
    	return address;
    }

}


