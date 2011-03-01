package models;
// Generated on the Feb 28, 2011 7:25:06 PM by Play!

import play.db.jpa.JPASupport;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Screencast generated by Play!
 */
@Entity
@Table(name="screencast"
    ,catalog="wpa"
    , uniqueConstraints = { @UniqueConstraint( columnNames = { "url" } ) }
)
public class Screencast extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
    @Column(name="url", unique=true, nullable=false, insertable=true, updatable=true)
        public String url;
    
    @Column(name="title", unique=false, nullable=false, insertable=true, updatable=true)
        public String title;
    
    @Column(name="description", unique=false, nullable=false, insertable=true, updatable=true, length=65535)
        public String description;
    
    @Column(name="type", unique=false, nullable=false, insertable=true, updatable=true)
        public int type;
    
    @Column(name="uploaded_by", unique=false, nullable=false, insertable=true, updatable=true)
        public int uploadedBy;
    
    @Column(name="uploaded_on", unique=false, nullable=false, insertable=true, updatable=true, length=19)
        public Date uploadedOn;
    
    @Column(name="is_deleted", unique=false, nullable=false, insertable=true, updatable=true)
        public boolean isDeleted;
    
    @Column(name="is_reviewed", unique=false, nullable=false, insertable=true, updatable=true)
        public boolean isReviewed;
     // Constructors

    /** default constructor */
    public Screencast() {
    }

    /** full constructor */
    public Screencast(int id, String url, String title, String description, int type, int uploadedBy, Date uploadedOn, boolean isDeleted, boolean isReviewed) {
       this.id = id;
       this.url = url;
       this.title = title;
       this.description = description;
       this.type = type;
       this.uploadedBy = uploadedBy;
       this.uploadedOn = uploadedOn;
       this.isDeleted = isDeleted;
       this.isReviewed = isReviewed;
    }
   


}

