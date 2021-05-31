/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Accion;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Emocion;
import ResPwAEntities.Joint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class AccionJpaController implements Serializable {

    public AccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Accion accion) throws PreexistingEntityException, Exception {
        if (accion.getJointList() == null) {
            accion.setJointList(new ArrayList<Joint>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emocion emocionId = accion.getEmocionId();
            if (emocionId != null) {
                emocionId = em.getReference(emocionId.getClass(), emocionId.getId());
                accion.setEmocionId(emocionId);
            }
            List<Joint> attachedJointList = new ArrayList<Joint>();
            for (Joint jointListJointToAttach : accion.getJointList()) {
                jointListJointToAttach = em.getReference(jointListJointToAttach.getClass(), jointListJointToAttach.getId());
                attachedJointList.add(jointListJointToAttach);
            }
            accion.setJointList(attachedJointList);
            em.persist(accion);
            if (emocionId != null) {
                emocionId.getAccionList().add(accion);
                emocionId = em.merge(emocionId);
            }
            for (Joint jointListJoint : accion.getJointList()) {
                jointListJoint.getAccionList().add(accion);
                jointListJoint = em.merge(jointListJoint);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAccion(accion.getId()) != null) {
                throw new PreexistingEntityException("Accion " + accion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Accion accion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accion persistentAccion = em.find(Accion.class, accion.getId());
            Emocion emocionIdOld = persistentAccion.getEmocionId();
            Emocion emocionIdNew = accion.getEmocionId();
            List<Joint> jointListOld = persistentAccion.getJointList();
            List<Joint> jointListNew = accion.getJointList();
            if (emocionIdNew != null) {
                emocionIdNew = em.getReference(emocionIdNew.getClass(), emocionIdNew.getId());
                accion.setEmocionId(emocionIdNew);
            }
            List<Joint> attachedJointListNew = new ArrayList<Joint>();
            for (Joint jointListNewJointToAttach : jointListNew) {
                jointListNewJointToAttach = em.getReference(jointListNewJointToAttach.getClass(), jointListNewJointToAttach.getId());
                attachedJointListNew.add(jointListNewJointToAttach);
            }
            jointListNew = attachedJointListNew;
            accion.setJointList(jointListNew);
            accion = em.merge(accion);
            if (emocionIdOld != null && !emocionIdOld.equals(emocionIdNew)) {
                emocionIdOld.getAccionList().remove(accion);
                emocionIdOld = em.merge(emocionIdOld);
            }
            if (emocionIdNew != null && !emocionIdNew.equals(emocionIdOld)) {
                emocionIdNew.getAccionList().add(accion);
                emocionIdNew = em.merge(emocionIdNew);
            }
            for (Joint jointListOldJoint : jointListOld) {
                if (!jointListNew.contains(jointListOldJoint)) {
                    jointListOldJoint.getAccionList().remove(accion);
                    jointListOldJoint = em.merge(jointListOldJoint);
                }
            }
            for (Joint jointListNewJoint : jointListNew) {
                if (!jointListOld.contains(jointListNewJoint)) {
                    jointListNewJoint.getAccionList().add(accion);
                    jointListNewJoint = em.merge(jointListNewJoint);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = accion.getId();
                if (findAccion(id) == null) {
                    throw new NonexistentEntityException("The accion with id " + id + " no longer exists.");
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
            Accion accion;
            try {
                accion = em.getReference(Accion.class, id);
                accion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accion with id " + id + " no longer exists.", enfe);
            }
            Emocion emocionId = accion.getEmocionId();
            if (emocionId != null) {
                emocionId.getAccionList().remove(accion);
                emocionId = em.merge(emocionId);
            }
            List<Joint> jointList = accion.getJointList();
            for (Joint jointListJoint : jointList) {
                jointListJoint.getAccionList().remove(accion);
                jointListJoint = em.merge(jointListJoint);
            }
            em.remove(accion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Accion> findAccionEntities() {
        return findAccionEntities(true, -1, -1);
    }

    public List<Accion> findAccionEntities(int maxResults, int firstResult) {
        return findAccionEntities(false, maxResults, firstResult);
    }

    private List<Accion> findAccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Accion.class));
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

    public Accion findAccion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Accion.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Accion> rt = cq.from(Accion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
