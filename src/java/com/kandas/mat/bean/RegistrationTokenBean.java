/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.bean;

import com.kandas.mat.bean.util.JsfUtil;
import com.kandas.mat.domains.User;
import com.kandas.mat.services.UsersService;
import com.kandas.mat.services.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Pattern;

/**
 *
 * @author sabon
 */
@ManagedBean(name = "registrationTokenBean")
public class RegistrationTokenBean implements Serializable{
    
    private String code ;
    
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Incorrect email")
    private String email;
    private UsersService userService;
    
    public RegistrationTokenBean() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String sendRegistrationCodeAgain() {
        try {
            User user  = getUserService().findByEmail(email);
            if (user == null){
                String message = String.format(ResourceBundle.getBundle("/Bundle").getString("EmailNotExists"),email);
                JsfUtil.addErrorMessage(message);
                return null;
            }

            user.setVerificationCode(RegistrationBean.generateCodeRegistration());
            
            getUserService().edit(user);
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistrationOk"));
            
            return "";
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RegistrationTokenBean.class.getName()).log(Level.SEVERE, null, ex);
            String message = String.format(ResourceBundle.getBundle("/Bundle").getString("EmailNotExists"),email);
            JsfUtil.addErrorMessage(message);
        } catch (Exception ex) {
            Logger.getLogger(RegistrationTokenBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public String getCode() {
        return code;
    }

    public UsersService getUserService() {
        if (this.userService == null) {
            this.userService = new UsersService();
        }
        return userService;
    }
    
    
    
}
