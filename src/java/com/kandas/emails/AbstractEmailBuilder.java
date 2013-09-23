/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.emails;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author sabon
 */
public abstract class AbstractEmailBuilder implements EmailBuilder{

    private Map<String, String> params;

    public AbstractEmailBuilder() {
        this.params = new HashMap<String, String>();
    }

    @Override
    public void addParams(String key, String value) {
        this.params.put(key, value);
    }
    
    @Override
    public String createEmail(Map<String, String> rewriteParams) {
    
        if (rewriteParams != null) {
            this.params.putAll(rewriteParams);
        }
        
        String template = getTemplate();
        
        Iterator<Entry<String, String>> it = this.params.entrySet().iterator();
        while(it.hasNext()) {
            Entry<String, String> entry = it.next();
            template = template.replaceAll(entry.getKey(), entry.getValue());
        }
        
        return template;
        

    }

    
}
