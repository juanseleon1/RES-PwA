/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Dificultad;
import ResPwAEntities.PerfilPreferencia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class ActividadpwaJpaController implements Serializable {

    public ActividadpwaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividadpwa actividadpwa) throws PreexistingEntityException, Exception {
        if (actividadpwa.getPerfilPreferenciaList() == null) {
            actividadpwa.setPerfilPreferenciaList(new ArrayList<PerfilPreferencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dificultad dificultadDificultad = actividadpwa.getDificultadDificultad();
            if (dificultadDificultad != null) {
                dificultadDificultad = em.getReference(dificultadDificultad.getClass(), dificultadDificultad.getDificultad());
                actividadpwa.setDificultadDificultad(dificultadDificultad);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaList = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferenciaToAttach : actividadpwa.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaList.add(perfilPreferenciaListPerfilPreferenciaToAttach);
            }
            actividadpwa.setPerfilPreferenciaList(attachedPerfilPreferenciaList);
            em.persist(actividadpwa);
            if (dificultadDificultad != null) {
                dificultadDificultad.getActividadpwaList().add(actividadpwa);
                dificultadDificultad = em.merge(dificultadDificultad);
            }
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : actividadpwa.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferencia.getActividadpwaList().add(actividadpwa);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActividadpwa(actividadpwa.getId()) != null) {
                throw new PreexistingEntityException("Actividadpwa " + actividadpwa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadpwa actividadpwa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa persistentActividadpwa = em.find(Actividadpwa.class, actividadpwa.getId());
            Dificultad dificultadDificultadOld = persistentActividadpwa.getDificultadDificultad();
            Dificultad dificultadDificultadNew = actividadpwa.getDificultadDificultad();
            List<PerfilPreferencia> perfilPreferenciaListOld = persistentActividadpwa.getPerfilPreferenciaList();
            List<PerfilPreferencia> perfilPreferenciaListNew = actividadpwa.getPerfilPreferenciaList();
            if (dificultadDificultadNew != null) {
                dificultadDificultadNew = em.getReference(dificultadDificultadNew.getClass(), dificultadDificultadNew.getDificultad());
                actividadpwa.setDificultadDificultad(dificultadDificultadNew);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaListNew = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferenciaToAttach : perfilPreferenciaListNew) {
                perfilPreferenciaListNewPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListNewPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListNewPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaListNew.add(perfilPreferenciaListNewPerfilPreferenciaToAttach);
            }
            perfilPreferenciaListNew = attachedPerfilPreferenciaListNew;
            actividadpwa.setPerfilPreferenciaList(perfilPreferenciaListNew);
            actividadpwa = em.merge(actividadpwa);
            if (dificultadDificultadOld != null && !dificultadDificultadOld.equals(dificultadDificultadNew)) {
                dificultadDificultadOld.getActividadpwaList().remove(actividadpwa);
                dificultadDificultadOld = em.merge(dificultadDificultadOld);
            }
            if (dificultadDificultadNew != null && !dificultadDificultadNew.equals(dificultadDificultadOld)) {
                dificultadDificultadNew.getActividadpwaList().add(actividadpwa);
                dificultadDificultadNew = em.merge(dificultadDificultadNew);
            }
            for (PerfilPreferencia perfilPreferenciaListOldPerfilPreferencia : perfilPreferenciaListOld) {
                if (!perfilPreferenciaListNew.contains(perfilPreferenciaListOldPerfilPreferencia)) {
                    perfilPreferenciaListOldPerfilPreferencia.getActividadpwaList().remove(actividadpwa);
                    perfilPreferenciaListOldPerfilPreferencia = em.merge(perfilPreferenciaListOldPerfilPreferencia);
                }
            }
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferencia : perfilPreferenciaListNew) {
                if (!perfilPreferenciaListOld.contains(perfilPreferenciaListNewPerfilPreferencia)) {
                    perfilPreferenciaListNewPerfilPreferencia.getActividadpwaList().add(actividadpwa);
                    perfilPreferenciaListNewPerfilPreferencia = em.merge(perfilPreferenciaListNewPerfilPreferencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = actividadpwa.getId();
                if (findActividadpwa(id) == null) {
                    throw new NonexistentEntityException("The actividadpwa with id " + id + " no longer exists.");
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
            Actividadpwa actividadpwa;
            try {
                actividadpwa = em.getReference(Actividadpwa.class, id);
                actividadpwa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadpwa with id " + id + " no longer exists.", enfe);
            }
            Dificultad dificultadDificultad = actividadpwa.getDificultadDificultad();
            if (dificultadDificultad != null) {
                dificultadDificultad.getActividadpwaList().remove(actividadpwa);
                dificultadDificultad = em.merge(dificultadDificultad);
            }
            List<PerfilPreferencia> perfilPreferenciaList = actividadpwa.getPerfilPreferenciaList();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : perfilPreferenciaList) {
                perfilPreferenciaListPerfilPreferencia.getActividadpwaList().remove(actividadpwa);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.remove(actividadpwa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadpwa> findActividadpwaEntities() {
        return findActividadpwaEntities(true, -1, -1);
    }

    public List<Actividadpwa> findActividadpwaEntities(int maxResults, int firstResult) {
        return findActividadpwaEntities(false, maxResults, firstResult);
    }

    private List<Actividadpwa> findActividadpwaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadpwa.class));
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

    public Actividadpwa findActividadpwa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadpwa.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadpwaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadpwa> rt = cq.from(Actividadpwa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
