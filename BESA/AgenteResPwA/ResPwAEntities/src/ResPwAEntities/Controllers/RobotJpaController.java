/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Emocion;
import ResPwAEntities.Robot;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class RobotJpaController implements Serializable {

    public RobotJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Robot robot) throws PreexistingEntityException, Exception {
        if (robot.getEmocionList() == null) {
            robot.setEmocionList(new ArrayList<Emocion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Emocion> attachedEmocionList = new ArrayList<Emocion>();
            for (Emocion emocionListEmocionToAttach : robot.getEmocionList()) {
                emocionListEmocionToAttach = em.getReference(emocionListEmocionToAttach.getClass(), emocionListEmocionToAttach.getId());
                attachedEmocionList.add(emocionListEmocionToAttach);
            }
            robot.setEmocionList(attachedEmocionList);
            em.persist(robot);
            for (Emocion emocionListEmocion : robot.getEmocionList()) {
                Robot oldRobotIdOfEmocionListEmocion = emocionListEmocion.getRobotId();
                emocionListEmocion.setRobotId(robot);
                emocionListEmocion = em.merge(emocionListEmocion);
                if (oldRobotIdOfEmocionListEmocion != null) {
                    oldRobotIdOfEmocionListEmocion.getEmocionList().remove(emocionListEmocion);
                    oldRobotIdOfEmocionListEmocion = em.merge(oldRobotIdOfEmocionListEmocion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRobot(robot.getId()) != null) {
                throw new PreexistingEntityException("Robot " + robot + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Robot robot) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Robot persistentRobot = em.find(Robot.class, robot.getId());
            List<Emocion> emocionListOld = persistentRobot.getEmocionList();
            List<Emocion> emocionListNew = robot.getEmocionList();
            List<String> illegalOrphanMessages = null;
            for (Emocion emocionListOldEmocion : emocionListOld) {
                if (!emocionListNew.contains(emocionListOldEmocion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emocion " + emocionListOldEmocion + " since its robotId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Emocion> attachedEmocionListNew = new ArrayList<Emocion>();
            for (Emocion emocionListNewEmocionToAttach : emocionListNew) {
                emocionListNewEmocionToAttach = em.getReference(emocionListNewEmocionToAttach.getClass(), emocionListNewEmocionToAttach.getId());
                attachedEmocionListNew.add(emocionListNewEmocionToAttach);
            }
            emocionListNew = attachedEmocionListNew;
            robot.setEmocionList(emocionListNew);
            robot = em.merge(robot);
            for (Emocion emocionListNewEmocion : emocionListNew) {
                if (!emocionListOld.contains(emocionListNewEmocion)) {
                    Robot oldRobotIdOfEmocionListNewEmocion = emocionListNewEmocion.getRobotId();
                    emocionListNewEmocion.setRobotId(robot);
                    emocionListNewEmocion = em.merge(emocionListNewEmocion);
                    if (oldRobotIdOfEmocionListNewEmocion != null && !oldRobotIdOfEmocionListNewEmocion.equals(robot)) {
                        oldRobotIdOfEmocionListNewEmocion.getEmocionList().remove(emocionListNewEmocion);
                        oldRobotIdOfEmocionListNewEmocion = em.merge(oldRobotIdOfEmocionListNewEmocion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = robot.getId();
                if (findRobot(id) == null) {
                    throw new NonexistentEntityException("The robot with id " + id + " no longer exists.");
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
            Robot robot;
            try {
                robot = em.getReference(Robot.class, id);
                robot.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The robot with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Emocion> emocionListOrphanCheck = robot.getEmocionList();
            for (Emocion emocionListOrphanCheckEmocion : emocionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Robot (" + robot + ") cannot be destroyed since the Emocion " + emocionListOrphanCheckEmocion + " in its emocionList field has a non-nullable robotId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(robot);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Robot> findRobotEntities() {
        return findRobotEntities(true, -1, -1);
    }

    public List<Robot> findRobotEntities(int maxResults, int firstResult) {
        return findRobotEntities(false, maxResults, firstResult);
    }

    private List<Robot> findRobotEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Robot.class));
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

    public Robot findRobot(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Robot.class, id);
        } finally {
            em.close();
        }
    }

    public int getRobotCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Robot> rt = cq.from(Robot.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
