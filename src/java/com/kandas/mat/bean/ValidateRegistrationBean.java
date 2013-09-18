/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.bean;

import com.kandas.mat.domains.User;
import com.kandas.mat.domains.UserStatus;
import com.kandas.mat.services.UsersService;
import com.kandas.mat.services.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author sabon
 */
@ManagedBean(name = "validateRegistrationBean")
@RequestScoped
public class ValidateRegistrationBean implements Serializable{
    
    private String code ;
    private UsersService userService;
    
    public ValidateRegistrationBean() {
    }

    public void setCode(String code) {
        this.code = code;
        if (this.code != null) {
            User user = getUserService().findByVerificationCode(code);
            if (user != null) {
                if (UserStatus.PENDING_VALIDATION.equals(user.getStatus())) {
                    try {
                        user.setStatus(UserStatus.ACTIVE);
                        user.setLastModifiedDate(new Date());
                        getUserService().edit(user);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(ValidateRegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(ValidateRegistrationBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
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
