/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.domains.tournaments;

import com.kandas.mat.domains.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author fcambarieri
 */
@Entity
public class Tournament implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date inscriptionEnd;
    private User user;
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tournament)) {
            return false;
        }
        Tournament other = (Tournament) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kandas.mat.domains.tournaments.Tournament[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the startDate
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the inscriptionEnd
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getInscriptionEnd() {
        return inscriptionEnd;
    }

    /**
     * @param inscriptionEnd the inscriptionEnd to set
     */
    public void setInscriptionEnd(Date inscriptionEnd) {
        this.inscriptionEnd = inscriptionEnd;
    }

    /**
     * @return the user
     */
    @ManyToOne
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    
}
