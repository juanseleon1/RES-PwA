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
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
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
public class JointJpaController implements Serializable {

    public JointJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Joint joint) throws PreexistingEntityException, Exception {
        if (joint.getAccionList() == null) {
            joint.setAccionList(new ArrayList<Accion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Accion> attachedAccionList = new ArrayList<Accion>();
            for (Accion accionListAccionToAttach : joint.getAccionList()) {
                accionListAccionToAttach = em.getReference(accionListAccionToAttach.getClass(), accionListAccionToAttach.getId());
                attachedAccionList.add(accionListAccionToAttach);
            }
            joint.setAccionList(attachedAccionList);
            em.persist(joint);
            for (Accion accionListAccion : joint.getAccionList()) {
                accionListAccion.getJointList().add(joint);
                accionListAccion = em.merge(accionListAccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJoint(joint.getId()) != null) {
                throw new PreexistingEntityException("Joint " + joint + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Joint joint) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Joint persistentJoint = em.find(Joint.class, joint.getId());
            List<Accion> accionListOld = persistentJoint.getAccionList();
            List<Accion> accionListNew = joint.getAccionList();
            List<Accion> attachedAccionListNew = new ArrayList<Accion>();
            for (Accion accionListNewAccionToAttach : accionListNew) {
                accionListNewAccionToAttach = em.getReference(accionListNewAccionToAttach.getClass(), accionListNewAccionToAttach.getId());
                attachedAccionListNew.add(accionListNewAccionToAttach);
            }
            accionListNew = attachedAccionListNew;
            joint.setAccionList(accionListNew);
            joint = em.merge(joint);
            for (Accion accionListOldAccion : accionListOld) {
                if (!accionListNew.contains(accionListOldAccion)) {
                    accionListOldAccion.getJointList().remove(joint);
                    accionListOldAccion = em.merge(accionListOldAccion);
                }
            }
            for (Accion accionListNewAccion : accionListNew) {
                if (!accionListOld.contains(accionListNewAccion)) {
                    accionListNewAccion.getJointList().add(joint);
                    accionListNewAccion = em.merge(accionListNewAccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = joint.getId();
                if (findJoint(id) == null) {
                    throw new NonexistentEntityException("The joint with id " + id + " no longer exists.");
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
            Joint joint;
            try {
                joint = em.getReference(Joint.class, id);
                joint.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The joint with id " + id + " no longer exists.", enfe);
            }
            List<Accion> accionList = joint.getAccionList();
            for (Accion accionListAccion : accionList) {
                accionListAccion.getJointList().remove(joint);
                accionListAccion = em.merge(accionListAccion);
            }
            em.remove(joint);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Joint> findJointEntities() {
        return findJointEntities(true, -1, -1);
    }

    public List<Joint> findJointEntities(int maxResults, int firstResult) {
        return findJointEntities(false, maxResults, firstResult);
    }

    private List<Joint> findJointEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Joint.class));
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

    public Joint findJoint(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Joint.class, id);
        } finally {
            em.close();
        }
    }

    public int getJointCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Joint> rt = cq.from(Joint.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
