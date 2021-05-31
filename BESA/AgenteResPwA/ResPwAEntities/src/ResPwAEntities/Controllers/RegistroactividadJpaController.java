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
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.Registroactividad;
import ResPwAEntities.RegistroactividadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class RegistroactividadJpaController implements Serializable {

    public RegistroactividadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Registroactividad registroactividad) throws PreexistingEntityException, Exception {
        if (registroactividad.getRegistroactividadPK() == null) {
            registroactividad.setRegistroactividadPK(new RegistroactividadPK());
        }
        registroactividad.getRegistroactividadPK().setActividadpwaId(registroactividad.getActividadpwa().getId());
        registroactividad.getRegistroactividadPK().setPerfilpwaCedula(registroactividad.getPerfilpwa().getCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa actividadpwa = registroactividad.getActividadpwa();
            if (actividadpwa != null) {
                actividadpwa = em.getReference(actividadpwa.getClass(), actividadpwa.getId());
                registroactividad.setActividadpwa(actividadpwa);
            }
            Perfilpwa perfilpwa = registroactividad.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa = em.getReference(perfilpwa.getClass(), perfilpwa.getCedula());
                registroactividad.setPerfilpwa(perfilpwa);
            }
            em.persist(registroactividad);
            if (actividadpwa != null) {
                actividadpwa.getRegistroactividadList().add(registroactividad);
                actividadpwa = em.merge(actividadpwa);
            }
            if (perfilpwa != null) {
                perfilpwa.getRegistroactividadList().add(registroactividad);
                perfilpwa = em.merge(perfilpwa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroactividad(registroactividad.getRegistroactividadPK()) != null) {
                throw new PreexistingEntityException("Registroactividad " + registroactividad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Registroactividad registroactividad) throws NonexistentEntityException, Exception {
        registroactividad.getRegistroactividadPK().setActividadpwaId(registroactividad.getActividadpwa().getId());
        registroactividad.getRegistroactividadPK().setPerfilpwaCedula(registroactividad.getPerfilpwa().getCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroactividad persistentRegistroactividad = em.find(Registroactividad.class, registroactividad.getRegistroactividadPK());
            Actividadpwa actividadpwaOld = persistentRegistroactividad.getActividadpwa();
            Actividadpwa actividadpwaNew = registroactividad.getActividadpwa();
            Perfilpwa perfilpwaOld = persistentRegistroactividad.getPerfilpwa();
            Perfilpwa perfilpwaNew = registroactividad.getPerfilpwa();
            if (actividadpwaNew != null) {
                actividadpwaNew = em.getReference(actividadpwaNew.getClass(), actividadpwaNew.getId());
                registroactividad.setActividadpwa(actividadpwaNew);
            }
            if (perfilpwaNew != null) {
                perfilpwaNew = em.getReference(perfilpwaNew.getClass(), perfilpwaNew.getCedula());
                registroactividad.setPerfilpwa(perfilpwaNew);
            }
            registroactividad = em.merge(registroactividad);
            if (actividadpwaOld != null && !actividadpwaOld.equals(actividadpwaNew)) {
                actividadpwaOld.getRegistroactividadList().remove(registroactividad);
                actividadpwaOld = em.merge(actividadpwaOld);
            }
            if (actividadpwaNew != null && !actividadpwaNew.equals(actividadpwaOld)) {
                actividadpwaNew.getRegistroactividadList().add(registroactividad);
                actividadpwaNew = em.merge(actividadpwaNew);
            }
            if (perfilpwaOld != null && !perfilpwaOld.equals(perfilpwaNew)) {
                perfilpwaOld.getRegistroactividadList().remove(registroactividad);
                perfilpwaOld = em.merge(perfilpwaOld);
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                perfilpwaNew.getRegistroactividadList().add(registroactividad);
                perfilpwaNew = em.merge(perfilpwaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroactividadPK id = registroactividad.getRegistroactividadPK();
                if (findRegistroactividad(id) == null) {
                    throw new NonexistentEntityException("The registroactividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroactividadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroactividad registroactividad;
            try {
                registroactividad = em.getReference(Registroactividad.class, id);
                registroactividad.getRegistroactividadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroactividad with id " + id + " no longer exists.", enfe);
            }
            Actividadpwa actividadpwa = registroactividad.getActividadpwa();
            if (actividadpwa != null) {
                actividadpwa.getRegistroactividadList().remove(registroactividad);
                actividadpwa = em.merge(actividadpwa);
            }
            Perfilpwa perfilpwa = registroactividad.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa.getRegistroactividadList().remove(registroactividad);
                perfilpwa = em.merge(perfilpwa);
            }
            em.remove(registroactividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Registroactividad> findRegistroactividadEntities() {
        return findRegistroactividadEntities(true, -1, -1);
    }

    public List<Registroactividad> findRegistroactividadEntities(int maxResults, int firstResult) {
        return findRegistroactividadEntities(false, maxResults, firstResult);
    }

    private List<Registroactividad> findRegistroactividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Registroactividad.class));
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

    public Registroactividad findRegistroactividad(RegistroactividadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Registroactividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroactividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Registroactividad> rt = cq.from(Registroactividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
