/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juans
 */
@Entity
@Table(name = "BOOKS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b")
    , @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId")
    , @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title")
    , @NamedQuery(name = "Books.findByAuthorLastName", query = "SELECT b FROM Books b WHERE b.authorLastName = :authorLastName")
    , @NamedQuery(name = "Books.findByAuthorFirstName", query = "SELECT b FROM Books b WHERE b.authorFirstName = :authorFirstName")
    , @NamedQuery(name = "Books.findByRating", query = "SELECT b FROM Books b WHERE b.rating = :rating")})
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BOOK_ID")
    private String bookId;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "AUTHOR_LAST_NAME")
    private String authorLastName;
    @Column(name = "AUTHOR_FIRST_NAME")
    private String authorFirstName;
    @Column(name = "RATING")
    private BigInteger rating;

    public Books() {
    }

    public Books(String bookId) {
        this.bookId = bookId;
    }

    public Books(String bookId, String title, String authorLastName) {
        this.bookId = bookId;
        this.title = title;
        this.authorLastName = authorLastName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public BigInteger getRating() {
        return rating;
    }

    public void setRating(BigInteger rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResPwAEntities.Books[ bookId=" + bookId + " ]";
    }
    
}
