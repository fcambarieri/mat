/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.services.exceptions;

/**
 *
 * @author sabon
 */
public class UniqueEmailConstraintException extends ValidationException {

    public UniqueEmailConstraintException(String message) {
        super(message);
    }

    public UniqueEmailConstraintException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
