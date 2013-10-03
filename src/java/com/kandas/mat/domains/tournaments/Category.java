/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.domains.tournaments;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author fcambarieri
 */
@Entity
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String description;
    private Tournament tournament;
    private List<Subcategory> subcategorys;
    private Integer minAge;
    private Integer maxAge;
    private CategoryType categoryType;

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
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kandas.mat.domains.tournaments.Category[ id=" + id + " ]";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Subcategory> getSubcategorys() {
        return subcategorys;
    }

    public void setSubcategorys(List<Subcategory> subcategorys) {
        this.subcategorys = subcategorys;
    }

    /**
     * @return the minAge
     */
    public Integer getMinAge() {
        return minAge;
    }

    /**
     * @param minAge the minAge to set
     */
    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    /**
     * @return the maxAge
     */
    public Integer getMaxAge() {
        return maxAge;
    }

    /**
     * @param maxAge the maxAge to set
     */
    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * @return the categoryType
     */
    @Enumerated(EnumType.STRING)
    public CategoryType getCategoryType() {
        return categoryType;
    }

    /**
     * @param categoryType the categoryType to set
     */
    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
    
}
