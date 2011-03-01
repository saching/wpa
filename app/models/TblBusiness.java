package models;
// Generated on the Feb 28, 2011 7:24:07 PM by Play!

import play.db.jpa.JPASupport;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblBusiness generated by Play!
 */
@Entity
@Table(name="tbl_business"
    ,catalog="testdrive"
    , uniqueConstraints = {  }
)
public class TblBusiness extends JPASupport implements java.io.Serializable {

    // Fields

     @Id
    
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
        public int id;
    
    @Column(name="name", unique=false, nullable=false, insertable=true, updatable=true, length=500)
        public String name;
    
    @Column(name="summary", unique=false, nullable=false, insertable=true, updatable=true, length=65535)
        public String summary;
    
    @Column(name="created_by", unique=false, nullable=false, insertable=true, updatable=true)
        public int createdBy;
    
    @Column(name="created_at", unique=false, nullable=false, insertable=true, updatable=true, length=19)
        public Date createdAt;
     // Constructors

    /** default constructor */
    public TblBusiness() {
    }

    /** full constructor */
    public TblBusiness(int id, String name, String summary, int createdBy, Date createdAt) {
       this.id = id;
       this.name = name;
       this.summary = summary;
       this.createdBy = createdBy;
       this.createdAt = createdAt;
    }
   


}

