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
 * @author juans
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa actividadpwaId = registroactividad.getActividadpwaId();
            if (actividadpwaId != null) {
                actividadpwaId = em.getReference(actividadpwaId.getClass(), actividadpwaId.getId());
                registroactividad.setActividadpwaId(actividadpwaId);
            }
            Perfilpwa perfilpwaCedula = registroactividad.getPerfilpwaCedula();
            if (perfilpwaCedula != null) {
                perfilpwaCedula = em.getReference(perfilpwaCedula.getClass(), perfilpwaCedula.getCedula());
                registroactividad.setPerfilpwaCedula(perfilpwaCedula);
            }
            em.persist(registroactividad);
            if (actividadpwaId != null) {
                actividadpwaId.getRegistroactividadList().add(registroactividad);
                actividadpwaId = em.merge(actividadpwaId);
            }
            if (perfilpwaCedula != null) {
                perfilpwaCedula.getRegistroactividadList().add(registroactividad);
                perfilpwaCedula = em.merge(perfilpwaCedula);
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Registroactividad persistentRegistroactividad = em.find(Registroactividad.class, registroactividad.getRegistroactividadPK());
            Actividadpwa actividadpwaIdOld = persistentRegistroactividad.getActividadpwaId();
            Actividadpwa actividadpwaIdNew = registroactividad.getActividadpwaId();
            Perfilpwa perfilpwaCedulaOld = persistentRegistroactividad.getPerfilpwaCedula();
            Perfilpwa perfilpwaCedulaNew = registroactividad.getPerfilpwaCedula();
            if (actividadpwaIdNew != null) {
                actividadpwaIdNew = em.getReference(actividadpwaIdNew.getClass(), actividadpwaIdNew.getId());
                registroactividad.setActividadpwaId(actividadpwaIdNew);
            }
            if (perfilpwaCedulaNew != null) {
                perfilpwaCedulaNew = em.getReference(perfilpwaCedulaNew.getClass(), perfilpwaCedulaNew.getCedula());
                registroactividad.setPerfilpwaCedula(perfilpwaCedulaNew);
            }
            registroactividad = em.merge(registroactividad);
            if (actividadpwaIdOld != null && !actividadpwaIdOld.equals(actividadpwaIdNew)) {
                actividadpwaIdOld.getRegistroactividadList().remove(registroactividad);
                actividadpwaIdOld = em.merge(actividadpwaIdOld);
            }
            if (actividadpwaIdNew != null && !actividadpwaIdNew.equals(actividadpwaIdOld)) {
                actividadpwaIdNew.getRegistroactividadList().add(registroactividad);
                actividadpwaIdNew = em.merge(actividadpwaIdNew);
            }
            if (perfilpwaCedulaOld != null && !perfilpwaCedulaOld.equals(perfilpwaCedulaNew)) {
                perfilpwaCedulaOld.getRegistroactividadList().remove(registroactividad);
                perfilpwaCedulaOld = em.merge(perfilpwaCedulaOld);
            }
            if (perfilpwaCedulaNew != null && !perfilpwaCedulaNew.equals(perfilpwaCedulaOld)) {
                perfilpwaCedulaNew.getRegistroactividadList().add(registroactividad);
                perfilpwaCedulaNew = em.merge(perfilpwaCedulaNew);
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
            Actividadpwa actividadpwaId = registroactividad.getActividadpwaId();
            if (actividadpwaId != null) {
                actividadpwaId.getRegistroactividadList().remove(registroactividad);
                actividadpwaId = em.merge(actividadpwaId);
            }
            Perfilpwa perfilpwaCedula = registroactividad.getPerfilpwaCedula();
            if (perfilpwaCedula != null) {
                perfilpwaCedula.getRegistroactividadList().remove(registroactividad);
                perfilpwaCedula = em.merge(perfilpwaCedula);
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
