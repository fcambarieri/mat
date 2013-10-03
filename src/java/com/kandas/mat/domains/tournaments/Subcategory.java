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

/**
 *
 * @author fcambarieri
 */
@Entity
public class Subcategory implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String description;
    private Category category;
    private Integer minValue;
    private Integer maxValue;

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
        if (!(object instanceof Subcategory)) {
            return false;
        }
        Subcategory other = (Subcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kandas.mat.domains.tournaments.Subcategory[ id=" + id + " ]";
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
     * @return the minValue
     */
    public Integer getMinValue() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the maxValue
     */
    public Integer getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    
}
