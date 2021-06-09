/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Actividadrutinaria;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.PerfilMedico;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class ActividadrutinariaJpaController implements Serializable {

    public ActividadrutinariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividadrutinaria actividadrutinaria) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilMedico perfilMedicoPerfilpwaCedula = actividadrutinaria.getPerfilMedicoPerfilpwaCedula();
            if (perfilMedicoPerfilpwaCedula != null) {
                perfilMedicoPerfilpwaCedula = em.getReference(perfilMedicoPerfilpwaCedula.getClass(), perfilMedicoPerfilpwaCedula.getPerfilpwaCedula());
                actividadrutinaria.setPerfilMedicoPerfilpwaCedula(perfilMedicoPerfilpwaCedula);
            }
            em.persist(actividadrutinaria);
            if (perfilMedicoPerfilpwaCedula != null) {
                perfilMedicoPerfilpwaCedula.getActividadrutinariaList().add(actividadrutinaria);
                perfilMedicoPerfilpwaCedula = em.merge(perfilMedicoPerfilpwaCedula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActividadrutinaria(actividadrutinaria.getId()) != null) {
                throw new PreexistingEntityException("Actividadrutinaria " + actividadrutinaria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadrutinaria actividadrutinaria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadrutinaria persistentActividadrutinaria = em.find(Actividadrutinaria.class, actividadrutinaria.getId());
            PerfilMedico perfilMedicoPerfilpwaCedulaOld = persistentActividadrutinaria.getPerfilMedicoPerfilpwaCedula();
            PerfilMedico perfilMedicoPerfilpwaCedulaNew = actividadrutinaria.getPerfilMedicoPerfilpwaCedula();
            if (perfilMedicoPerfilpwaCedulaNew != null) {
                perfilMedicoPerfilpwaCedulaNew = em.getReference(perfilMedicoPerfilpwaCedulaNew.getClass(), perfilMedicoPerfilpwaCedulaNew.getPerfilpwaCedula());
                actividadrutinaria.setPerfilMedicoPerfilpwaCedula(perfilMedicoPerfilpwaCedulaNew);
            }
            actividadrutinaria = em.merge(actividadrutinaria);
            if (perfilMedicoPerfilpwaCedulaOld != null && !perfilMedicoPerfilpwaCedulaOld.equals(perfilMedicoPerfilpwaCedulaNew)) {
                perfilMedicoPerfilpwaCedulaOld.getActividadrutinariaList().remove(actividadrutinaria);
                perfilMedicoPerfilpwaCedulaOld = em.merge(perfilMedicoPerfilpwaCedulaOld);
            }
            if (perfilMedicoPerfilpwaCedulaNew != null && !perfilMedicoPerfilpwaCedulaNew.equals(perfilMedicoPerfilpwaCedulaOld)) {
                perfilMedicoPerfilpwaCedulaNew.getActividadrutinariaList().add(actividadrutinaria);
                perfilMedicoPerfilpwaCedulaNew = em.merge(perfilMedicoPerfilpwaCedulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = actividadrutinaria.getId();
                if (findActividadrutinaria(id) == null) {
                    throw new NonexistentEntityException("The actividadrutinaria with id " + id + " no longer exists.");
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
            Actividadrutinaria actividadrutinaria;
            try {
                actividadrutinaria = em.getReference(Actividadrutinaria.class, id);
                actividadrutinaria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadrutinaria with id " + id + " no longer exists.", enfe);
            }
            PerfilMedico perfilMedicoPerfilpwaCedula = actividadrutinaria.getPerfilMedicoPerfilpwaCedula();
            if (perfilMedicoPerfilpwaCedula != null) {
                perfilMedicoPerfilpwaCedula.getActividadrutinariaList().remove(actividadrutinaria);
                perfilMedicoPerfilpwaCedula = em.merge(perfilMedicoPerfilpwaCedula);
            }
            em.remove(actividadrutinaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadrutinaria> findActividadrutinariaEntities() {
        return findActividadrutinariaEntities(true, -1, -1);
    }

    public List<Actividadrutinaria> findActividadrutinariaEntities(int maxResults, int firstResult) {
        return findActividadrutinariaEntities(false, maxResults, firstResult);
    }

    private List<Actividadrutinaria> findActividadrutinariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadrutinaria.class));
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

    public Actividadrutinaria findActividadrutinaria(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadrutinaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadrutinariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadrutinaria> rt = cq.from(Actividadrutinaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
