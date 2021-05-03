/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Accion;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Emocion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (emocion.getAccionList() == null) {
            emocion.setAccionList(new ArrayList<Accion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Accion> attachedAccionList = new ArrayList<Accion>();
            for (Accion accionListAccionToAttach : emocion.getAccionList()) {
                accionListAccionToAttach = em.getReference(accionListAccionToAttach.getClass(), accionListAccionToAttach.getId());
                attachedAccionList.add(accionListAccionToAttach);
            }
            emocion.setAccionList(attachedAccionList);
            em.persist(emocion);
            for (Accion accionListAccion : emocion.getAccionList()) {
                Emocion oldEmocionOfAccionListAccion = accionListAccion.getEmocion();
                accionListAccion.setEmocion(emocion);
                accionListAccion = em.merge(accionListAccion);
                if (oldEmocionOfAccionListAccion != null) {
                    oldEmocionOfAccionListAccion.getAccionList().remove(accionListAccion);
                    oldEmocionOfAccionListAccion = em.merge(oldEmocionOfAccionListAccion);
                }
            }
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

    public void edit(Emocion emocion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emocion persistentEmocion = em.find(Emocion.class, emocion.getId());
            List<Accion> accionListOld = persistentEmocion.getAccionList();
            List<Accion> accionListNew = emocion.getAccionList();
            List<String> illegalOrphanMessages = null;
            for (Accion accionListOldAccion : accionListOld) {
                if (!accionListNew.contains(accionListOldAccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Accion " + accionListOldAccion + " since its emocion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Accion> attachedAccionListNew = new ArrayList<Accion>();
            for (Accion accionListNewAccionToAttach : accionListNew) {
                accionListNewAccionToAttach = em.getReference(accionListNewAccionToAttach.getClass(), accionListNewAccionToAttach.getId());
                attachedAccionListNew.add(accionListNewAccionToAttach);
            }
            accionListNew = attachedAccionListNew;
            emocion.setAccionList(accionListNew);
            emocion = em.merge(emocion);
            for (Accion accionListNewAccion : accionListNew) {
                if (!accionListOld.contains(accionListNewAccion)) {
                    Emocion oldEmocionOfAccionListNewAccion = accionListNewAccion.getEmocion();
                    accionListNewAccion.setEmocion(emocion);
                    accionListNewAccion = em.merge(accionListNewAccion);
                    if (oldEmocionOfAccionListNewAccion != null && !oldEmocionOfAccionListNewAccion.equals(emocion)) {
                        oldEmocionOfAccionListNewAccion.getAccionList().remove(accionListNewAccion);
                        oldEmocionOfAccionListNewAccion = em.merge(oldEmocionOfAccionListNewAccion);
                    }
                }
            }
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

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Accion> accionListOrphanCheck = emocion.getAccionList();
            for (Accion accionListOrphanCheckAccion : accionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Emocion (" + emocion + ") cannot be destroyed since the Accion " + accionListOrphanCheckAccion + " in its accionList field has a non-nullable emocion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
