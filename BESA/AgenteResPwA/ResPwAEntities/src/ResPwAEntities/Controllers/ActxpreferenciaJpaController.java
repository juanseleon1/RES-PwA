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
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.ActxpreferenciaPK;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilPreferencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class ActxpreferenciaJpaController implements Serializable {

    public ActxpreferenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actxpreferencia actxpreferencia) throws PreexistingEntityException, Exception {
        if (actxpreferencia.getActxpreferenciaPK() == null) {
            actxpreferencia.setActxpreferenciaPK(new ActxpreferenciaPK());
        }
        actxpreferencia.getActxpreferenciaPK().setActividadpwaId(actxpreferencia.getActividadpwa().getId());
        actxpreferencia.getActxpreferenciaPK().setPerfilPreferenciaCedula(actxpreferencia.getPerfilPreferencia().getPerfilpwaCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa actividadpwa = actxpreferencia.getActividadpwa();
            if (actividadpwa != null) {
                actividadpwa = em.getReference(actividadpwa.getClass(), actividadpwa.getId());
                actxpreferencia.setActividadpwa(actividadpwa);
            }
            PerfilPreferencia perfilPreferencia = actxpreferencia.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia = em.getReference(perfilPreferencia.getClass(), perfilPreferencia.getPerfilpwaCedula());
                actxpreferencia.setPerfilPreferencia(perfilPreferencia);
            }
            em.persist(actxpreferencia);
            if (actividadpwa != null) {
                actividadpwa.getActxpreferenciaList().add(actxpreferencia);
                actividadpwa = em.merge(actividadpwa);
            }
            if (perfilPreferencia != null) {
                perfilPreferencia.getActxpreferenciaList().add(actxpreferencia);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActxpreferencia(actxpreferencia.getActxpreferenciaPK()) != null) {
                throw new PreexistingEntityException("Actxpreferencia " + actxpreferencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actxpreferencia actxpreferencia) throws NonexistentEntityException, Exception {
        actxpreferencia.getActxpreferenciaPK().setActividadpwaId(actxpreferencia.getActividadpwa().getId());
        actxpreferencia.getActxpreferenciaPK().setPerfilPreferenciaCedula(actxpreferencia.getPerfilPreferencia().getPerfilpwaCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actxpreferencia persistentActxpreferencia = em.find(Actxpreferencia.class, actxpreferencia.getActxpreferenciaPK());
            Actividadpwa actividadpwaOld = persistentActxpreferencia.getActividadpwa();
            Actividadpwa actividadpwaNew = actxpreferencia.getActividadpwa();
            PerfilPreferencia perfilPreferenciaOld = persistentActxpreferencia.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = actxpreferencia.getPerfilPreferencia();
            if (actividadpwaNew != null) {
                actividadpwaNew = em.getReference(actividadpwaNew.getClass(), actividadpwaNew.getId());
                actxpreferencia.setActividadpwa(actividadpwaNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                actxpreferencia.setPerfilPreferencia(perfilPreferenciaNew);
            }
            actxpreferencia = em.merge(actxpreferencia);
            if (actividadpwaOld != null && !actividadpwaOld.equals(actividadpwaNew)) {
                actividadpwaOld.getActxpreferenciaList().remove(actxpreferencia);
                actividadpwaOld = em.merge(actividadpwaOld);
            }
            if (actividadpwaNew != null && !actividadpwaNew.equals(actividadpwaOld)) {
                actividadpwaNew.getActxpreferenciaList().add(actxpreferencia);
                actividadpwaNew = em.merge(actividadpwaNew);
            }
            if (perfilPreferenciaOld != null && !perfilPreferenciaOld.equals(perfilPreferenciaNew)) {
                perfilPreferenciaOld.getActxpreferenciaList().remove(actxpreferencia);
                perfilPreferenciaOld = em.merge(perfilPreferenciaOld);
            }
            if (perfilPreferenciaNew != null && !perfilPreferenciaNew.equals(perfilPreferenciaOld)) {
                perfilPreferenciaNew.getActxpreferenciaList().add(actxpreferencia);
                perfilPreferenciaNew = em.merge(perfilPreferenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ActxpreferenciaPK id = actxpreferencia.getActxpreferenciaPK();
                if (findActxpreferencia(id) == null) {
                    throw new NonexistentEntityException("The actxpreferencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ActxpreferenciaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actxpreferencia actxpreferencia;
            try {
                actxpreferencia = em.getReference(Actxpreferencia.class, id);
                actxpreferencia.getActxpreferenciaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actxpreferencia with id " + id + " no longer exists.", enfe);
            }
            Actividadpwa actividadpwa = actxpreferencia.getActividadpwa();
            if (actividadpwa != null) {
                actividadpwa.getActxpreferenciaList().remove(actxpreferencia);
                actividadpwa = em.merge(actividadpwa);
            }
            PerfilPreferencia perfilPreferencia = actxpreferencia.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia.getActxpreferenciaList().remove(actxpreferencia);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.remove(actxpreferencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actxpreferencia> findActxpreferenciaEntities() {
        return findActxpreferenciaEntities(true, -1, -1);
    }

    public List<Actxpreferencia> findActxpreferenciaEntities(int maxResults, int firstResult) {
        return findActxpreferenciaEntities(false, maxResults, firstResult);
    }

    private List<Actxpreferencia> findActxpreferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actxpreferencia.class));
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

    public Actxpreferencia findActxpreferencia(ActxpreferenciaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actxpreferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getActxpreferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actxpreferencia> rt = cq.from(Actxpreferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
