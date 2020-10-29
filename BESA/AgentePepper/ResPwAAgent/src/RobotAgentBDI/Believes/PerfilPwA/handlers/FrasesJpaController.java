/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA.handlers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import RobotAgentBDI.Believes.PerfilPwA.Frases;
import RobotAgentBDI.Believes.PerfilPwA.FrasesPK;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.NonexistentEntityException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class FrasesJpaController implements Serializable {

    public FrasesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Frases frases) throws PreexistingEntityException, Exception {
        if (frases.getFrasesPK() == null) {
            frases.setFrasesPK(new FrasesPK());
        }
        frases.getFrasesPK().setCuentoNombrecuento(frases.getCuento().getNombrecuento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuento cuento = frases.getCuento();
            if (cuento != null) {
                cuento = em.getReference(cuento.getClass(), cuento.getNombrecuento());
                frases.setCuento(cuento);
            }
            em.persist(frases);
            if (cuento != null) {
                cuento.getFrasesList().add(frases);
                cuento = em.merge(cuento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFrases(frases.getFrasesPK()) != null) {
                throw new PreexistingEntityException("Frases " + frases + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Frases frases) throws NonexistentEntityException, Exception {
        frases.getFrasesPK().setCuentoNombrecuento(frases.getCuento().getNombrecuento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Frases persistentFrases = em.find(Frases.class, frases.getFrasesPK());
            Cuento cuentoOld = persistentFrases.getCuento();
            Cuento cuentoNew = frases.getCuento();
            if (cuentoNew != null) {
                cuentoNew = em.getReference(cuentoNew.getClass(), cuentoNew.getNombrecuento());
                frases.setCuento(cuentoNew);
            }
            frases = em.merge(frases);
            if (cuentoOld != null && !cuentoOld.equals(cuentoNew)) {
                cuentoOld.getFrasesList().remove(frases);
                cuentoOld = em.merge(cuentoOld);
            }
            if (cuentoNew != null && !cuentoNew.equals(cuentoOld)) {
                cuentoNew.getFrasesList().add(frases);
                cuentoNew = em.merge(cuentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FrasesPK id = frases.getFrasesPK();
                if (findFrases(id) == null) {
                    throw new NonexistentEntityException("The frases with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FrasesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Frases frases;
            try {
                frases = em.getReference(Frases.class, id);
                frases.getFrasesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The frases with id " + id + " no longer exists.", enfe);
            }
            Cuento cuento = frases.getCuento();
            if (cuento != null) {
                cuento.getFrasesList().remove(frases);
                cuento = em.merge(cuento);
            }
            em.remove(frases);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Frases> findFrasesEntities() {
        return findFrasesEntities(true, -1, -1);
    }

    public List<Frases> findFrasesEntities(int maxResults, int firstResult) {
        return findFrasesEntities(false, maxResults, firstResult);
    }

    private List<Frases> findFrasesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Frases.class));
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

    public Frases findFrases(FrasesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Frases.class, id);
        } finally {
            em.close();
        }
    }

    public int getFrasesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Frases> rt = cq.from(Frases.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
