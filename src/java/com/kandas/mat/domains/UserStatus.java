/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.domains;

/**
 *
 * @author sabon
 */
public enum UserStatus {
    
    ACTIVE("active"),
    DEACTIVE("deactive"),
    PENDING_VALIDATION("pending_validation");
    
    private String name;

    private UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
