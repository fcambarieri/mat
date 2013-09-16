/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.services;

import com.kandas.mat.domains.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author sabon
 */
public class UsersService extends AbstractService<User>{

    @Override
    public Class getEntityClass() {
       return User.class;
    }
    
    public User findByEmail(String email) {
        User user = null;
        EntityManager em = getEntityManager();
        try {
            Query query = getEntityManager().createQuery("select u from User u where u.email = :email").setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            
        } finally {
            em.close();
        }
        
        return user;
    }
}
