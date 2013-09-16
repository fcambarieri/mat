/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.bean.validations.annotations;

/**
 *
 * @author sabon
 */
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
//import org.primefaces.validate.bean.ClientConstraint;

@Target({METHOD,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
//@Constraint(validatedBy=EmailConstraintValidator.class)
//@ClientConstraint(resolvedBy=EmailClientValidationConstraint.class)
@Documented
public @interface UniqueEmail {
    
    String message() default "{org.primefaces.examples.primefaces}";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
