package models;
// Generated on the Feb 28, 2011 7:24:07 PM by Play!

import play.db.jpa.JPASupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User generated by Play!
 */
@Entity
@Table(name="user"
    ,catalog="testdrive"
    , uniqueConstraints = {  }
)
public class User extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public long id;
    
    @Column(name="email", unique=false, nullable=true, insertable=true, updatable=true)
        public String email;
    
    @Column(name="fullname", unique=false, nullable=true, insertable=true, updatable=true)
        public String fullname;
    
    @Column(name="isAdmin", unique=false, nullable=false, insertable=true, updatable=true)
        public boolean isAdmin;
    
    @Column(name="password", unique=false, nullable=true, insertable=true, updatable=true)
        public String password;
     // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(long id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }
    /** full constructor */
    public User(long id, String email, String fullname, boolean isAdmin, String password) {
       this.id = id;
       this.email = email;
       this.fullname = fullname;
       this.isAdmin = isAdmin;
       this.password = password;
    }
   


}


