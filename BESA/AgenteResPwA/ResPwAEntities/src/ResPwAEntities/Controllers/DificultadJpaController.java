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
import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Dificultad;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class DificultadJpaController implements Serializable {

    public DificultadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dificultad dificultad) throws PreexistingEntityException, Exception {
        if (dificultad.getActividadpwaList() == null) {
            dificultad.setActividadpwaList(new ArrayList<Actividadpwa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Actividadpwa> attachedActividadpwaList = new ArrayList<Actividadpwa>();
            for (Actividadpwa actividadpwaListActividadpwaToAttach : dificultad.getActividadpwaList()) {
                actividadpwaListActividadpwaToAttach = em.getReference(actividadpwaListActividadpwaToAttach.getClass(), actividadpwaListActividadpwaToAttach.getId());
                attachedActividadpwaList.add(actividadpwaListActividadpwaToAttach);
            }
            dificultad.setActividadpwaList(attachedActividadpwaList);
            em.persist(dificultad);
            for (Actividadpwa actividadpwaListActividadpwa : dificultad.getActividadpwaList()) {
                Dificultad oldDificultadDificultadOfActividadpwaListActividadpwa = actividadpwaListActividadpwa.getDificultadDificultad();
                actividadpwaListActividadpwa.setDificultadDificultad(dificultad);
                actividadpwaListActividadpwa = em.merge(actividadpwaListActividadpwa);
                if (oldDificultadDificultadOfActividadpwaListActividadpwa != null) {
                    oldDificultadDificultadOfActividadpwaListActividadpwa.getActividadpwaList().remove(actividadpwaListActividadpwa);
                    oldDificultadDificultadOfActividadpwaListActividadpwa = em.merge(oldDificultadDificultadOfActividadpwaListActividadpwa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDificultad(dificultad.getDificultad()) != null) {
                throw new PreexistingEntityException("Dificultad " + dificultad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dificultad dificultad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dificultad persistentDificultad = em.find(Dificultad.class, dificultad.getDificultad());
            List<Actividadpwa> actividadpwaListOld = persistentDificultad.getActividadpwaList();
            List<Actividadpwa> actividadpwaListNew = dificultad.getActividadpwaList();
            List<Actividadpwa> attachedActividadpwaListNew = new ArrayList<Actividadpwa>();
            for (Actividadpwa actividadpwaListNewActividadpwaToAttach : actividadpwaListNew) {
                actividadpwaListNewActividadpwaToAttach = em.getReference(actividadpwaListNewActividadpwaToAttach.getClass(), actividadpwaListNewActividadpwaToAttach.getId());
                attachedActividadpwaListNew.add(actividadpwaListNewActividadpwaToAttach);
            }
            actividadpwaListNew = attachedActividadpwaListNew;
            dificultad.setActividadpwaList(actividadpwaListNew);
            dificultad = em.merge(dificultad);
            for (Actividadpwa actividadpwaListOldActividadpwa : actividadpwaListOld) {
                if (!actividadpwaListNew.contains(actividadpwaListOldActividadpwa)) {
                    actividadpwaListOldActividadpwa.setDificultadDificultad(null);
                    actividadpwaListOldActividadpwa = em.merge(actividadpwaListOldActividadpwa);
                }
            }
            for (Actividadpwa actividadpwaListNewActividadpwa : actividadpwaListNew) {
                if (!actividadpwaListOld.contains(actividadpwaListNewActividadpwa)) {
                    Dificultad oldDificultadDificultadOfActividadpwaListNewActividadpwa = actividadpwaListNewActividadpwa.getDificultadDificultad();
                    actividadpwaListNewActividadpwa.setDificultadDificultad(dificultad);
                    actividadpwaListNewActividadpwa = em.merge(actividadpwaListNewActividadpwa);
                    if (oldDificultadDificultadOfActividadpwaListNewActividadpwa != null && !oldDificultadDificultadOfActividadpwaListNewActividadpwa.equals(dificultad)) {
                        oldDificultadDificultadOfActividadpwaListNewActividadpwa.getActividadpwaList().remove(actividadpwaListNewActividadpwa);
                        oldDificultadDificultadOfActividadpwaListNewActividadpwa = em.merge(oldDificultadDificultadOfActividadpwaListNewActividadpwa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = dificultad.getDificultad();
                if (findDificultad(id) == null) {
                    throw new NonexistentEntityException("The dificultad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dificultad dificultad;
            try {
                dificultad = em.getReference(Dificultad.class, id);
                dificultad.getDificultad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dificultad with id " + id + " no longer exists.", enfe);
            }
            List<Actividadpwa> actividadpwaList = dificultad.getActividadpwaList();
            for (Actividadpwa actividadpwaListActividadpwa : actividadpwaList) {
                actividadpwaListActividadpwa.setDificultadDificultad(null);
                actividadpwaListActividadpwa = em.merge(actividadpwaListActividadpwa);
            }
            em.remove(dificultad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dificultad> findDificultadEntities() {
        return findDificultadEntities(true, -1, -1);
    }

    public List<Dificultad> findDificultadEntities(int maxResults, int firstResult) {
        return findDificultadEntities(false, maxResults, firstResult);
    }

    private List<Dificultad> findDificultadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dificultad.class));
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

    public Dificultad findDificultad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dificultad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDificultadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dificultad> rt = cq.from(Dificultad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
