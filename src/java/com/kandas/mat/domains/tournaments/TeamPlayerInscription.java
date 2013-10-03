/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.domains.tournaments;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author fcambarieri
 */
@Entity
public class TeamPlayerInscription implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private TeamPlayer teamPlayer;
    private Tournament tournament;
    private Belt belt;
    private Category category;
    private Subcategory subcategory; 

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
        if (!(object instanceof TeamPlayerInscription)) {
            return false;
        }
        TeamPlayerInscription other = (TeamPlayerInscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kandas.mat.domains.tournaments.TeamPlayerInscription[ id=" + id + " ]";
    }

    /**
     * @return the teamPlayer
     */
    @OneToOne
    public TeamPlayer getTeamPlayer() {
        return teamPlayer;
    }

    /**
     * @param teamPlayer the teamPlayer to set
     */
    public void setTeamPlayer(TeamPlayer teamPlayer) {
        this.teamPlayer = teamPlayer;
    }

    /**
     * @return the tournament
     */
    @ManyToOne
    public Tournament getTournament() {
        return tournament;
    }

    /**
     * @param tournament the tournament to set
     */
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    /**
     * @return the belt
     */
    @ManyToOne
    public Belt getBelt() {
        return belt;
    }

    /**
     * @param belt the belt to set
     */
    public void setBelt(Belt belt) {
        this.belt = belt;
    }

    /**
     * @return the category
     */
    @ManyToOne
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the subcategory
     */
    @ManyToOne
    public Subcategory getSubcategory() {
        return subcategory;
    }

    /**
     * @param subcategory the subcategory to set
     */
    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
    
}
