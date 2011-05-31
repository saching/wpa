package models;
// Generated on the Mar 4, 2011 3:02:13 PM by Play!

import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;

/**
 * User generated by Play!
 */
@Entity
@Table(name="user"
    , uniqueConstraints = { @UniqueConstraint( columnNames = { "slug" } ), @UniqueConstraint( columnNames = { "username", "email" } ) }
)
public class User extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
     @Required
    @Column(name="username", unique=false, nullable=false, insertable=true, updatable=true)
        public String username;
    
     @Email
    @Column(name="email", unique=false, nullable=false, insertable=true, updatable=true)
        public String email;
    
     @Required
    @Column(name="firstname", unique=false, nullable=true, insertable=true, updatable=true)
        public String firstname;
    
     @Required
    @Column(name="lastname", unique=false, nullable=true, insertable=true, updatable=true)
        public String lastname;
    
     @Password
     @Equals("repeatpassword")
    @Column(name="password", unique=false, nullable=false, insertable=true, updatable=true)
        public String password;
     
     public String repeatpassword;
     
    @Column(name="is_active", unique=false, nullable=false, insertable=true, updatable=true)
        public boolean isActive;
    
    @Column(name="created_at", unique=false, nullable=false, insertable=true, updatable=true, length=19)
        public Date createdAt;
    
    @Column(name="slug", unique=true, nullable=false, insertable=true, updatable=true)
        public String slug;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
    	public List<FollowerLog> followers; 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
		public List<Screencast> uploadedScreencasts; 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
		public List<ScreencastComment> screencastComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
		public List<ScreencastLikes> screencastLikes;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="user")
		public List<ScreencastViewLog> screencastViewLogs;
    
     // Constructors
    
    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(int id, String username, String email, String password, boolean isActive, Date createdAt, String slug) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.slug = slug;
    }
    /** full constructor */
    public User(int id, String username, String email, String firstname, String lastname, String password, boolean isActive, Date createdAt, String slug) {
       this.id = id;
       this.username = username;
       this.email = email;
       this.firstname = firstname;
       this.lastname = lastname;
       this.password = password;
       this.isActive = isActive;
       this.createdAt = createdAt;
       this.slug = slug;
    }
    
    /** full constructor */
    public User(String username, String email, String password, boolean isActive, Date createdAt, String firstname, String lastname, String slug) {
       this.id = id;
       this.username = username;
       this.email = email;
       this.firstname = firstname;
       this.lastname = lastname;
       this.password = password;
       this.isActive = isActive;
       this.createdAt = createdAt;
       this.slug = slug;
    }
    
    

}


