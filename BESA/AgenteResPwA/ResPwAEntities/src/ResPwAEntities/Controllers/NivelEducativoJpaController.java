/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.NivelEducativo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Perfilpwa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class NivelEducativoJpaController implements Serializable {

    public NivelEducativoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NivelEducativo nivelEducativo) throws PreexistingEntityException, Exception {
        if (nivelEducativo.getPerfilpwaList() == null) {
            nivelEducativo.setPerfilpwaList(new ArrayList<Perfilpwa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfilpwa> attachedPerfilpwaList = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListPerfilpwaToAttach : nivelEducativo.getPerfilpwaList()) {
                perfilpwaListPerfilpwaToAttach = em.getReference(perfilpwaListPerfilpwaToAttach.getClass(), perfilpwaListPerfilpwaToAttach.getCedula());
                attachedPerfilpwaList.add(perfilpwaListPerfilpwaToAttach);
            }
            nivelEducativo.setPerfilpwaList(attachedPerfilpwaList);
            em.persist(nivelEducativo);
            for (Perfilpwa perfilpwaListPerfilpwa : nivelEducativo.getPerfilpwaList()) {
                NivelEducativo oldNivelEducativoTiponeOfPerfilpwaListPerfilpwa = perfilpwaListPerfilpwa.getNivelEducativoTipone();
                perfilpwaListPerfilpwa.setNivelEducativoTipone(nivelEducativo);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
                if (oldNivelEducativoTiponeOfPerfilpwaListPerfilpwa != null) {
                    oldNivelEducativoTiponeOfPerfilpwaListPerfilpwa.getPerfilpwaList().remove(perfilpwaListPerfilpwa);
                    oldNivelEducativoTiponeOfPerfilpwaListPerfilpwa = em.merge(oldNivelEducativoTiponeOfPerfilpwaListPerfilpwa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNivelEducativo(nivelEducativo.getTipone()) != null) {
                throw new PreexistingEntityException("NivelEducativo " + nivelEducativo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NivelEducativo nivelEducativo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NivelEducativo persistentNivelEducativo = em.find(NivelEducativo.class, nivelEducativo.getTipone());
            List<Perfilpwa> perfilpwaListOld = persistentNivelEducativo.getPerfilpwaList();
            List<Perfilpwa> perfilpwaListNew = nivelEducativo.getPerfilpwaList();
            List<Perfilpwa> attachedPerfilpwaListNew = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListNewPerfilpwaToAttach : perfilpwaListNew) {
                perfilpwaListNewPerfilpwaToAttach = em.getReference(perfilpwaListNewPerfilpwaToAttach.getClass(), perfilpwaListNewPerfilpwaToAttach.getCedula());
                attachedPerfilpwaListNew.add(perfilpwaListNewPerfilpwaToAttach);
            }
            perfilpwaListNew = attachedPerfilpwaListNew;
            nivelEducativo.setPerfilpwaList(perfilpwaListNew);
            nivelEducativo = em.merge(nivelEducativo);
            for (Perfilpwa perfilpwaListOldPerfilpwa : perfilpwaListOld) {
                if (!perfilpwaListNew.contains(perfilpwaListOldPerfilpwa)) {
                    perfilpwaListOldPerfilpwa.setNivelEducativoTipone(null);
                    perfilpwaListOldPerfilpwa = em.merge(perfilpwaListOldPerfilpwa);
                }
            }
            for (Perfilpwa perfilpwaListNewPerfilpwa : perfilpwaListNew) {
                if (!perfilpwaListOld.contains(perfilpwaListNewPerfilpwa)) {
                    NivelEducativo oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa = perfilpwaListNewPerfilpwa.getNivelEducativoTipone();
                    perfilpwaListNewPerfilpwa.setNivelEducativoTipone(nivelEducativo);
                    perfilpwaListNewPerfilpwa = em.merge(perfilpwaListNewPerfilpwa);
                    if (oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa != null && !oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa.equals(nivelEducativo)) {
                        oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa.getPerfilpwaList().remove(perfilpwaListNewPerfilpwa);
                        oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa = em.merge(oldNivelEducativoTiponeOfPerfilpwaListNewPerfilpwa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = nivelEducativo.getTipone();
                if (findNivelEducativo(id) == null) {
                    throw new NonexistentEntityException("The nivelEducativo with id " + id + " no longer exists.");
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
            NivelEducativo nivelEducativo;
            try {
                nivelEducativo = em.getReference(NivelEducativo.class, id);
                nivelEducativo.getTipone();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nivelEducativo with id " + id + " no longer exists.", enfe);
            }
            List<Perfilpwa> perfilpwaList = nivelEducativo.getPerfilpwaList();
            for (Perfilpwa perfilpwaListPerfilpwa : perfilpwaList) {
                perfilpwaListPerfilpwa.setNivelEducativoTipone(null);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
            }
            em.remove(nivelEducativo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NivelEducativo> findNivelEducativoEntities() {
        return findNivelEducativoEntities(true, -1, -1);
    }

    public List<NivelEducativo> findNivelEducativoEntities(int maxResults, int firstResult) {
        return findNivelEducativoEntities(false, maxResults, firstResult);
    }

    private List<NivelEducativo> findNivelEducativoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NivelEducativo.class));
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

    public NivelEducativo findNivelEducativo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NivelEducativo.class, id);
        } finally {
            em.close();
        }
    }

    public int getNivelEducativoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NivelEducativo> rt = cq.from(NivelEducativo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
