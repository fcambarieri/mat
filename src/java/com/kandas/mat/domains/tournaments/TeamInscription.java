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

/**
 *
 * @author fcambarieri
 */
@Entity
public class TeamInscription implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Tournament tournament;
    private Team team;
    private InscriptionStatus status;
    
    

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
        if (!(object instanceof TeamInscription)) {
            return false;
        }
        TeamInscription other = (TeamInscription) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kandas.mat.domains.tournaments.TournamentInscription[ id=" + id + " ]";
    }

    /**
     * @return the tournament
     */
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
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * @return the status
     */
    public InscriptionStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(InscriptionStatus status) {
        this.status = status;
    }
}
