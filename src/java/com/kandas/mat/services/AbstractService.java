/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.mat.services;

import com.kandas.mat.services.exceptions.NonexistentEntityException;
import com.kandas.mat.domains.User;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

/**
 *
 * @author sabon
 */
public abstract class AbstractService<T> implements Serializable {

    private EntityManagerFactory emf = null;

    public AbstractService() {
        this(Persistence.createEntityManagerFactory("matPU"));
    }
    
    public AbstractService(EntityManagerFactory emf) {
        this.emf = emf;
    }
   

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public interface OnTransaction<T> {
        void on(T value);
    } 
            
    public void create(T entity, OnTransaction onTransaction) {
        EntityManager em = null;
        EntityTransaction tranx = null;
        try {
            em = getEntityManager();
            tranx = em.getTransaction();
            tranx.begin();
            em.persist(entity);
            if (onTransaction != null) {
                onTransaction.on(entity);
            }
            tranx.commit();
        } catch (Exception e) {
            if (tranx != null) {
                tranx.setRollbackOnly();
            }
            throw new RuntimeException("Cound not create entity",e);
        } finally {
            
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = getEntityId(entity);//entity.getId();
                if (findOne(id) == null) {
                    throw new NonexistentEntityException(String.format("The %s with id %d no longer exists.", getEntityClass().getSimpleName(), id));
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    private Long getEntityId(T value)  {
         try {
             Class c = value.getClass();
             Method method = c.getMethod("getId");
             Object result = method.invoke(value);
             return (Long) result;
         } catch (NoSuchMethodException ex) {
             Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SecurityException ex) {
             Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
             Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalArgumentException ex) {
             Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InvocationTargetException ex) {
             Logger.getLogger(AbstractService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T values;
            try {
                values = (T) em.getReference(getEntityClass(), id);
                getEntityId(values);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            em.remove(values);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<T> findAll() {
        return find(true, -1, -1);
    }

    public List<T> find(int maxResults, int firstResult) {
        return find(false, maxResults, firstResult);
    }

    public abstract Class getEntityClass();
    
    private List<T> find(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery(String.format("select object(o) from %s as o", getEntityClass().getSimpleName()));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public T findOne(Long id) {
        EntityManager em = getEntityManager();
        try {
            return (T) em.find(getEntityClass(), id);
        } finally {
            em.close();
        }
    }

    public int getCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery(String.format("select count(o) from %s as o", getEntityClass().getSimpleName()));
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
