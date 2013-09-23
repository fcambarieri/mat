/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.emails;

import java.util.Map;

/**
 *
 * @author sabon
 */
public interface EmailBuilder {

    public enum EmailKey {

        REGISTRATION,
        REGISTRATION_RETRY_CODE,
        FORGOT_PASSWORD;
    }

    String getTemplate();
    
    /**
     * @param subject
     * @param to
     * @param value for replacement
     */
    String createEmail(Map<String, String> params);

    /**
     * @return Email key
     * 
     */
    EmailKey getEmailKey();
    
    void addParams(String key, String value);
}
