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
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
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
        if (dificultad.getActxpreferenciaList() == null) {
            dificultad.setActxpreferenciaList(new ArrayList<Actxpreferencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Actxpreferencia> attachedActxpreferenciaList = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListActxpreferenciaToAttach : dificultad.getActxpreferenciaList()) {
                actxpreferenciaListActxpreferenciaToAttach = em.getReference(actxpreferenciaListActxpreferenciaToAttach.getClass(), actxpreferenciaListActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaList.add(actxpreferenciaListActxpreferenciaToAttach);
            }
            dificultad.setActxpreferenciaList(attachedActxpreferenciaList);
            em.persist(dificultad);
            for (Actxpreferencia actxpreferenciaListActxpreferencia : dificultad.getActxpreferenciaList()) {
                Dificultad oldDificultadDificultadOfActxpreferenciaListActxpreferencia = actxpreferenciaListActxpreferencia.getDificultadDificultad();
                actxpreferenciaListActxpreferencia.setDificultadDificultad(dificultad);
                actxpreferenciaListActxpreferencia = em.merge(actxpreferenciaListActxpreferencia);
                if (oldDificultadDificultadOfActxpreferenciaListActxpreferencia != null) {
                    oldDificultadDificultadOfActxpreferenciaListActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListActxpreferencia);
                    oldDificultadDificultadOfActxpreferenciaListActxpreferencia = em.merge(oldDificultadDificultadOfActxpreferenciaListActxpreferencia);
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

    public void edit(Dificultad dificultad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dificultad persistentDificultad = em.find(Dificultad.class, dificultad.getDificultad());
            List<Actxpreferencia> actxpreferenciaListOld = persistentDificultad.getActxpreferenciaList();
            List<Actxpreferencia> actxpreferenciaListNew = dificultad.getActxpreferenciaList();
            List<String> illegalOrphanMessages = null;
            for (Actxpreferencia actxpreferenciaListOldActxpreferencia : actxpreferenciaListOld) {
                if (!actxpreferenciaListNew.contains(actxpreferenciaListOldActxpreferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actxpreferencia " + actxpreferenciaListOldActxpreferencia + " since its dificultadDificultad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Actxpreferencia> attachedActxpreferenciaListNew = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListNewActxpreferenciaToAttach : actxpreferenciaListNew) {
                actxpreferenciaListNewActxpreferenciaToAttach = em.getReference(actxpreferenciaListNewActxpreferenciaToAttach.getClass(), actxpreferenciaListNewActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaListNew.add(actxpreferenciaListNewActxpreferenciaToAttach);
            }
            actxpreferenciaListNew = attachedActxpreferenciaListNew;
            dificultad.setActxpreferenciaList(actxpreferenciaListNew);
            dificultad = em.merge(dificultad);
            for (Actxpreferencia actxpreferenciaListNewActxpreferencia : actxpreferenciaListNew) {
                if (!actxpreferenciaListOld.contains(actxpreferenciaListNewActxpreferencia)) {
                    Dificultad oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia = actxpreferenciaListNewActxpreferencia.getDificultadDificultad();
                    actxpreferenciaListNewActxpreferencia.setDificultadDificultad(dificultad);
                    actxpreferenciaListNewActxpreferencia = em.merge(actxpreferenciaListNewActxpreferencia);
                    if (oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia != null && !oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia.equals(dificultad)) {
                        oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListNewActxpreferencia);
                        oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia = em.merge(oldDificultadDificultadOfActxpreferenciaListNewActxpreferencia);
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Actxpreferencia> actxpreferenciaListOrphanCheck = dificultad.getActxpreferenciaList();
            for (Actxpreferencia actxpreferenciaListOrphanCheckActxpreferencia : actxpreferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dificultad (" + dificultad + ") cannot be destroyed since the Actxpreferencia " + actxpreferenciaListOrphanCheckActxpreferencia + " in its actxpreferenciaList field has a non-nullable dificultadDificultad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
