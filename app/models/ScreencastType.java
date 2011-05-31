package models;
// Generated on the Mar 4, 2011 3:34:41 PM by Play!

import java.util.List;

import play.db.jpa.JPASupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * ScreencastType generated by Play!
 */
@Entity
@Table(name="screencast_type"
    , uniqueConstraints = { @UniqueConstraint( columnNames = { "type" } ) }
)
public class ScreencastType extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
    @Column(name="type", unique=true, nullable=false, insertable=true, updatable=true, length=50)
        public String type;
    
    @Column(name="description", unique=false, nullable=true, insertable=true, updatable=true, length=65535)
        public String description;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="type")
		public List<Screencast> screencasts;
     // Constructors

    /** default constructor */
    public ScreencastType() {
    }

	/** minimal constructor */
    public ScreencastType(int id, String type) {
        this.id = id;
        this.type = type;
    }
    /** full constructor */
    public ScreencastType(int id, String type, String description) {
       this.id = id;
       this.type = type;
       this.description = description;
    }
   


}


