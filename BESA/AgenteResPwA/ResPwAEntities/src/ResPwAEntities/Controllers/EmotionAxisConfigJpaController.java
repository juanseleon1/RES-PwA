/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.EmotionalEntities.EmotionAxisConfig;
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
public class EmotionAxisConfigJpaController implements Serializable {

    public EmotionAxisConfigJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmotionAxisConfig emotionAxisConfig) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(emotionAxisConfig);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmotionAxisConfig emotionAxisConfig) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            emotionAxisConfig = em.merge(emotionAxisConfig);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = emotionAxisConfig.getId();
                if (findEmotionAxisConfig(id) == null) {
                    throw new NonexistentEntityException("The emotionAxisConfig with id " + id + " no longer exists.");
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
            EmotionAxisConfig emotionAxisConfig;
            try {
                emotionAxisConfig = em.getReference(EmotionAxisConfig.class, id);
                emotionAxisConfig.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emotionAxisConfig with id " + id + " no longer exists.", enfe);
            }
            em.remove(emotionAxisConfig);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmotionAxisConfig> findEmotionAxisConfigEntities() {
        return findEmotionAxisConfigEntities(true, -1, -1);
    }

    public List<EmotionAxisConfig> findEmotionAxisConfigEntities(int maxResults, int firstResult) {
        return findEmotionAxisConfigEntities(false, maxResults, firstResult);
    }

    private List<EmotionAxisConfig> findEmotionAxisConfigEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmotionAxisConfig.class));
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

    public EmotionAxisConfig findEmotionAxisConfig(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmotionAxisConfig.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmotionAxisConfigCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmotionAxisConfig> rt = cq.from(EmotionAxisConfig.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
