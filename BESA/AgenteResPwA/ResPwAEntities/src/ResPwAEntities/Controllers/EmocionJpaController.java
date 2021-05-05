/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Emocion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author juans
 */
public class EmocionJpaController implements Serializable {

    public EmocionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emocion emocion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(emocion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmocion(emocion.getId()) != null) {
                throw new PreexistingEntityException("Emocion " + emocion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emocion emocion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            emocion = em.merge(emocion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = emocion.getId();
                if (findEmocion(id) == null) {
                    throw new NonexistentEntityException("The emocion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emocion emocion;
            try {
                emocion = em.getReference(Emocion.class, id);
                emocion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emocion with id " + id + " no longer exists.", enfe);
            }
            em.remove(emocion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emocion> findEmocionEntities() {
        return findEmocionEntities(true, -1, -1);
    }

    public List<Emocion> findEmocionEntities(int maxResults, int firstResult) {
        return findEmocionEntities(false, maxResults, firstResult);
    }

    private List<Emocion> findEmocionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emocion.class));
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

    public Emocion findEmocion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emocion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmocionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emocion> rt = cq.from(Emocion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
