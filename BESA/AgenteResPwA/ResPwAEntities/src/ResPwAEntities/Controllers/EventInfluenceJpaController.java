/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.EmotionalEntities.EventInfluence;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author maria.f.garces.cala
 */
public class EventInfluenceJpaController implements Serializable {

    public EventInfluenceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EventInfluence eventInfluence) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(eventInfluence);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEventInfluence(eventInfluence.getId()) != null) {
                throw new PreexistingEntityException("EventInfluence " + eventInfluence + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EventInfluence eventInfluence) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            eventInfluence = em.merge(eventInfluence);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eventInfluence.getId();
                if (findEventInfluence(id) == null) {
                    throw new NonexistentEntityException("The eventInfluence with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventInfluence eventInfluence;
            try {
                eventInfluence = em.getReference(EventInfluence.class, id);
                eventInfluence.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventInfluence with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventInfluence);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EventInfluence> findEventInfluenceEntities() {
        return findEventInfluenceEntities(true, -1, -1);
    }

    public List<EventInfluence> findEventInfluenceEntities(int maxResults, int firstResult) {
        return findEventInfluenceEntities(false, maxResults, firstResult);
    }

    private List<EventInfluence> findEventInfluenceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventInfluence.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EventInfluence findEventInfluence(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventInfluence.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventInfluenceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventInfluence> rt = cq.from(EventInfluence.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
