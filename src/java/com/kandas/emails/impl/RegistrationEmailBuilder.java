/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.emails.impl;

import com.kandas.emails.AbstractEmailBuilder;
import java.util.ResourceBundle;

/**
 *
 * @author sabon
 */

public class RegistrationEmailBuilder extends AbstractEmailBuilder{

    
    @Override
    public String getTemplate() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
            sb.append("<head>");
                sb.append("<title>");
                    sb.append("##TITLE##");
                sb.append("</title>");
            sb.append("</head>");
            sb.append("<body>");
                sb.append("<label>");
                    sb.append(ResourceBundle.getBundle("/Bundle").getString("ConfirmationEmailLabel"));
                sb.append("</label>");
                sb.append("<label>");
                    sb.append(ResourceBundle.getBundle("/Bundle").getString("ConfirmationEmailCodeLabel"));
                sb.append("</label>");
            sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    @Override
    public EmailKey getEmailKey() {
        return EmailKey.REGISTRATION;
    }
    
}
