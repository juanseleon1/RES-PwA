/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ResPwAEntities.Controllers;

import ResPwAEntities.Accion;
import java.math.BigDecimal;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Joint;
import ResPwAEntities.Robot;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Emocion;
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
            Emocion emocion = accion.getEmocion();
            if (emocion != null) {
                emocion = em.getReference(emocion.getClass(), emocion.getId());
                accion.setEmocion(emocion);
            }
            Robot robot = accion.getRobot();
            if (robot != null) {
                robot = em.getReference(robot.getClass(), robot.getId());
                accion.setRobot(robot);
            }
            List<Joint> attachedJointList = new ArrayList<Joint>();
            for (Joint jointListJointToAttach : accion.getJointList()) {
                jointListJointToAttach = em.getReference(jointListJointToAttach.getClass(), jointListJointToAttach.getId());
                attachedJointList.add(jointListJointToAttach);
            }
            accion.setJointList(attachedJointList);
            em.persist(accion);
            if (emocion != null) {
                emocion.getAccionList().add(accion);
                emocion = em.merge(emocion);
            }
            if (robot != null) {
                robot.getAccionList().add(accion);
                robot = em.merge(robot);
            }
            for (Joint jointListJoints : accion.getJointList()) {
                jointListJoints.getAccionList().add(accion);
                jointListJoints = em.merge(jointListJoints);
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

    public void edit(Accion accion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Accion persistentAccion = em.find(Accion.class, accion.getId());
            Emocion emocionOld = persistentAccion.getEmocion();
            Emocion emocionNew = accion.getEmocion();
            Robot robotOld = persistentAccion.getRobot();
            Robot robotNew = accion.getRobot();
            List<Joint> jointListOld = persistentAccion.getJointList();
            List<Joint> jointListNew = accion.getJointList();
            List<String> illegalOrphanMessages = null;
            for (Joint jointListOldJoint : jointListOld) {
                if (!jointListNew.contains(jointListOldJoint)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Joint " + jointListOldJoint + " since its accionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (emocionNew != null) {
                emocionNew = em.getReference(emocionNew.getClass(), emocionNew.getId());
                accion.setEmocion(emocionNew);
            }
            if (robotNew != null) {
                robotNew = em.getReference(robotNew.getClass(), robotNew.getId());
                accion.setRobot(robotNew);
            }
            List<Joint> attachedJointListNew = new ArrayList<Joint>();
            for (Joint jointListNewJointToAttach : jointListNew) {
                jointListNewJointToAttach = em.getReference(jointListNewJointToAttach.getClass(), jointListNewJointToAttach.getId());
                attachedJointListNew.add(jointListNewJointToAttach);
            }
            jointListNew = attachedJointListNew;
            accion.setJointList(jointListNew);
            
            accion = em.merge(accion);
            if (emocionOld != null && !emocionOld.equals(emocionNew)) {
                emocionOld.getAccionList().remove(accion);
                emocionOld = em.merge(emocionOld);
            }
            if (emocionNew != null && !emocionNew.equals(emocionOld)) {
                emocionNew.getAccionList().add(accion);
                emocionNew = em.merge(emocionNew);
            }
            if (robotOld != null && !robotOld.equals(robotNew)) {
                robotOld.getAccionList().remove(accion);
                robotOld = em.merge(robotOld);
            }
            if (robotNew != null && !robotNew.equals(robotOld)) {
                robotNew.getAccionList().add(accion);
                robotNew = em.merge(robotNew);
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Joint> jointListOrphanCheck = accion.getJointList();
            for (Joint jointListOrphanCheckEnriq : jointListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Accion (" + accion + ") cannot be destroyed since the Joint " + jointListOrphanCheckEnriq + " in its jointList field has a non-nullable accionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Robot robot = accion.getRobot();
            if (robot != null) {
                robot.getAccionList().remove(accion);
                robot = em.merge(robot);
            }
            Emocion emocion = accion.getEmocion();
            if (emocion != null) {
                emocion.getAccionList().remove(accion);
                emocion = em.merge(emocion);
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