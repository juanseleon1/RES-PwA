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
import ResPwAEntities.Robot;
import ResPwAEntities.Accion;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Emocion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
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
            Robot robotId = emocion.getRobotId();
            if (robotId != null) {
                robotId = em.getReference(robotId.getClass(), robotId.getId());
                emocion.setRobotId(robotId);
            }
            List<Accion> attachedAccionList = new ArrayList<Accion>();
            for (Accion accionListAccionToAttach : emocion.getAccionList()) {
                accionListAccionToAttach = em.getReference(accionListAccionToAttach.getClass(), accionListAccionToAttach.getId());
                attachedAccionList.add(accionListAccionToAttach);
            }
            emocion.setAccionList(attachedAccionList);
            em.persist(emocion);
            if (robotId != null) {
                robotId.getEmocionList().add(emocion);
                robotId = em.merge(robotId);
            }
            for (Accion accionListAccion : emocion.getAccionList()) {
                Emocion oldEmocionIdOfAccionListAccion = accionListAccion.getEmocionId();
                accionListAccion.setEmocionId(emocion);
                accionListAccion = em.merge(accionListAccion);
                if (oldEmocionIdOfAccionListAccion != null) {
                    oldEmocionIdOfAccionListAccion.getAccionList().remove(accionListAccion);
                    oldEmocionIdOfAccionListAccion = em.merge(oldEmocionIdOfAccionListAccion);
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
            Robot robotIdOld = persistentEmocion.getRobotId();
            Robot robotIdNew = emocion.getRobotId();
            List<Accion> accionListOld = persistentEmocion.getAccionList();
            List<Accion> accionListNew = emocion.getAccionList();
            List<String> illegalOrphanMessages = null;
            for (Accion accionListOldAccion : accionListOld) {
                if (!accionListNew.contains(accionListOldAccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Accion " + accionListOldAccion + " since its emocionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (robotIdNew != null) {
                robotIdNew = em.getReference(robotIdNew.getClass(), robotIdNew.getId());
                emocion.setRobotId(robotIdNew);
            }
            List<Accion> attachedAccionListNew = new ArrayList<Accion>();
            for (Accion accionListNewAccionToAttach : accionListNew) {
                accionListNewAccionToAttach = em.getReference(accionListNewAccionToAttach.getClass(), accionListNewAccionToAttach.getId());
                attachedAccionListNew.add(accionListNewAccionToAttach);
            }
            accionListNew = attachedAccionListNew;
            emocion.setAccionList(accionListNew);
            emocion = em.merge(emocion);
            if (robotIdOld != null && !robotIdOld.equals(robotIdNew)) {
                robotIdOld.getEmocionList().remove(emocion);
                robotIdOld = em.merge(robotIdOld);
            }
            if (robotIdNew != null && !robotIdNew.equals(robotIdOld)) {
                robotIdNew.getEmocionList().add(emocion);
                robotIdNew = em.merge(robotIdNew);
            }
            for (Accion accionListNewAccion : accionListNew) {
                if (!accionListOld.contains(accionListNewAccion)) {
                    Emocion oldEmocionIdOfAccionListNewAccion = accionListNewAccion.getEmocionId();
                    accionListNewAccion.setEmocionId(emocion);
                    accionListNewAccion = em.merge(accionListNewAccion);
                    if (oldEmocionIdOfAccionListNewAccion != null && !oldEmocionIdOfAccionListNewAccion.equals(emocion)) {
                        oldEmocionIdOfAccionListNewAccion.getAccionList().remove(accionListNewAccion);
                        oldEmocionIdOfAccionListNewAccion = em.merge(oldEmocionIdOfAccionListNewAccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = emocion.getId();
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
                illegalOrphanMessages.add("This Emocion (" + emocion + ") cannot be destroyed since the Accion " + accionListOrphanCheckAccion + " in its accionList field has a non-nullable emocionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Robot robotId = emocion.getRobotId();
            if (robotId != null) {
                robotId.getEmocionList().remove(emocion);
                robotId = em.merge(robotId);
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

    public Emocion findEmocion(String id) {
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
