/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.bean;

/**
 *
 * @author sabon
 */
import com.kandas.mat.bean.util.JsfUtil;
import com.kandas.mat.domains.User;
import com.kandas.mat.domains.UserStatus;
import com.kandas.mat.services.UsersService;
import com.kandas.mat.services.exceptions.UniqueEmailConstraintException;
import com.kandas.mat.services.exceptions.ValidationException;
import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
 
/**
 *
 * @author sabon
 */
@ManagedBean(name = "registrationBean")
public class RegistrationBean {
 
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String reemail;
    
    @Size(min=8,max=16)  
    private String password;
    @Size(min=8,max=16)
    private String repassword;
    
    @Size(min=2,max=30)
    private String firstName;
    
    @Size(min=2,max=30)
    private String lastName;
    
    //@AssertTrue
    private boolean termsAndCondition;
    private UsersService usersService;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReemail() {
        return reemail;
    }

    public void setReemail(String reemail) {
        this.reemail = reemail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String create() {
        try {
            
            validateRegistration();
            
            User current = new User();
            current.setEmail(email);
            current.setFirstName(firstName);
            current.setLastName(lastName);
            current.setPassword(password);
            current.setCreationDate(new Date());
            current.setLastModifiedDate(current.getCreationDate());
            current.setStatus(UserStatus.PENDING_VALIDATION);
            getUsersService().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistrationOk"));
            return "/registration/registrationOk.xhtml";
        } catch (ValidationException e) {
             JsfUtil.addErrorMessage(e, e.getMessage());
             return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    private void validateRegistration() {
        User user = getUsersService().findByEmail(email);
        if (user != null) {
            throw new UniqueEmailConstraintException(ResourceBundle.getBundle("/Bundle").getString("EmailAlreadyRegister"));
        }
        
        if (!password.equals(repassword)) {
            throw new ValidationException(ResourceBundle.getBundle("/Bundle").getString("PasswordAndRepassworAreNotTheSame"));
        }
    }
    
  

    private UsersService getUsersService() {
        if (usersService == null) {
            usersService = new UsersService();
        }
        return usersService;
    }

    public boolean isTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(boolean termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }
}