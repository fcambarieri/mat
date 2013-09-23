/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.emails;

import java.util.Collection;
import java.util.Iterator;
import org.openide.util.Lookup;

/**
 *
 * @author sabon
 */
public class EmailFactory {
    
    public static EmailBuilder createEmailBuilder(EmailBuilder.EmailKey key) {
        if (key == null) {
            throw new IllegalArgumentException("The parameter key can't be null");
        }
        
        Lookup.Template<EmailBuilder> template = new Lookup.Template(EmailBuilder.class);
        Lookup.Result<EmailBuilder> result= Lookup.getDefault().lookup(template);
        Collection c = result.allInstances();
        for (Iterator i = c.iterator(); i.hasNext(); ) {
            EmailBuilder eb = (EmailBuilder)i.next();
            if (key.equals(eb.getEmailKey())) {
                return eb;
            }
        }
        
        return null;
    } 
}
