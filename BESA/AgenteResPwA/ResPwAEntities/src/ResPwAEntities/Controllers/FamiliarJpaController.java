/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Familiar;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Perfilpwa;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class FamiliarJpaController implements Serializable {

    public FamiliarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Familiar familiar) throws PreexistingEntityException, Exception {
        if (familiar.getPerfilpwaList() == null) {
            familiar.setPerfilpwaList(new ArrayList<Perfilpwa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfilpwa> attachedPerfilpwaList = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListPerfilpwaToAttach : familiar.getPerfilpwaList()) {
                perfilpwaListPerfilpwaToAttach = em.getReference(perfilpwaListPerfilpwaToAttach.getClass(), perfilpwaListPerfilpwaToAttach.getCedula());
                attachedPerfilpwaList.add(perfilpwaListPerfilpwaToAttach);
            }
            familiar.setPerfilpwaList(attachedPerfilpwaList);
            em.persist(familiar);
            for (Perfilpwa perfilpwaListPerfilpwa : familiar.getPerfilpwaList()) {
                perfilpwaListPerfilpwa.getFamiliarList().add(familiar);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFamiliar(familiar.getId()) != null) {
                throw new PreexistingEntityException("Familiar " + familiar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Familiar familiar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Familiar persistentFamiliar = em.find(Familiar.class, familiar.getId());
            List<Perfilpwa> perfilpwaListOld = persistentFamiliar.getPerfilpwaList();
            List<Perfilpwa> perfilpwaListNew = familiar.getPerfilpwaList();
            List<Perfilpwa> attachedPerfilpwaListNew = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListNewPerfilpwaToAttach : perfilpwaListNew) {
                perfilpwaListNewPerfilpwaToAttach = em.getReference(perfilpwaListNewPerfilpwaToAttach.getClass(), perfilpwaListNewPerfilpwaToAttach.getCedula());
                attachedPerfilpwaListNew.add(perfilpwaListNewPerfilpwaToAttach);
            }
            perfilpwaListNew = attachedPerfilpwaListNew;
            familiar.setPerfilpwaList(perfilpwaListNew);
            familiar = em.merge(familiar);
            for (Perfilpwa perfilpwaListOldPerfilpwa : perfilpwaListOld) {
                if (!perfilpwaListNew.contains(perfilpwaListOldPerfilpwa)) {
                    perfilpwaListOldPerfilpwa.getFamiliarList().remove(familiar);
                    perfilpwaListOldPerfilpwa = em.merge(perfilpwaListOldPerfilpwa);
                }
            }
            for (Perfilpwa perfilpwaListNewPerfilpwa : perfilpwaListNew) {
                if (!perfilpwaListOld.contains(perfilpwaListNewPerfilpwa)) {
                    perfilpwaListNewPerfilpwa.getFamiliarList().add(familiar);
                    perfilpwaListNewPerfilpwa = em.merge(perfilpwaListNewPerfilpwa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = familiar.getId();
                if (findFamiliar(id) == null) {
                    throw new NonexistentEntityException("The familiar with id " + id + " no longer exists.");
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
            Familiar familiar;
            try {
                familiar = em.getReference(Familiar.class, id);
                familiar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The familiar with id " + id + " no longer exists.", enfe);
            }
            List<Perfilpwa> perfilpwaList = familiar.getPerfilpwaList();
            for (Perfilpwa perfilpwaListPerfilpwa : perfilpwaList) {
                perfilpwaListPerfilpwa.getFamiliarList().remove(familiar);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
            }
            em.remove(familiar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Familiar> findFamiliarEntities() {
        return findFamiliarEntities(true, -1, -1);
    }

    public List<Familiar> findFamiliarEntities(int maxResults, int firstResult) {
        return findFamiliarEntities(false, maxResults, firstResult);
    }

    private List<Familiar> findFamiliarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Familiar.class));
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

    public Familiar findFamiliar(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Familiar.class, id);
        } finally {
            em.close();
        }
    }

    public int getFamiliarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Familiar> rt = cq.from(Familiar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
