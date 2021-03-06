package models;
// Generated on the Mar 4, 2011 3:02:13 PM by Play!

import play.db.jpa.JPASupport;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ScreencastRelated generated by Play!
 */
@Entity
@Table(name="screencast_related"
    , uniqueConstraints = {  }
)
public class ScreencastRelated extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
    @ManyToOne(cascade={}, fetch=FetchType.LAZY)
    @JoinColumn(name="screencast_id", unique=false, nullable=false, insertable=true, updatable=true)
        public Screencast screencast;
    
    @ManyToOne(cascade={}, fetch=FetchType.LAZY)
    @JoinColumn(name="related_screencast_id", unique=false, nullable=false, insertable=true, updatable=true)
        public Screencast relatedScreencast;
    
    @Column(name="created_at", unique=false, nullable=false, insertable=true, updatable=true, length=19)
        public Date createdAt;
     // Constructors

    /** default constructor */
    public ScreencastRelated() {
    }

    /** full constructor */
    public ScreencastRelated(int id, Screencast screencast, Screencast relatedScreencast, Date createdAt) {
       this.id = id;
       this.screencast = screencast;
       this.relatedScreencast = relatedScreencast;
       this.createdAt = createdAt;
    }
   


}


