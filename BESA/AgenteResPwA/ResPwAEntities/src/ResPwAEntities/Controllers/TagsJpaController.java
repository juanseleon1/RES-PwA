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
import ResPwAEntities.Cancion;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Tags;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class TagsJpaController implements Serializable {

    public TagsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tags tags) throws PreexistingEntityException, Exception {
        if (tags.getCancionList() == null) {
            tags.setCancionList(new ArrayList<Cancion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cancion> attachedCancionList = new ArrayList<Cancion>();
            for (Cancion cancionListCancionToAttach : tags.getCancionList()) {
                cancionListCancionToAttach = em.getReference(cancionListCancionToAttach.getClass(), cancionListCancionToAttach.getNombre());
                attachedCancionList.add(cancionListCancionToAttach);
            }
            tags.setCancionList(attachedCancionList);
            em.persist(tags);
            for (Cancion cancionListCancion : tags.getCancionList()) {
                cancionListCancion.getTagsList().add(tags);
                cancionListCancion = em.merge(cancionListCancion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTags(tags.getId()) != null) {
                throw new PreexistingEntityException("Tags " + tags + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tags tags) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tags persistentTags = em.find(Tags.class, tags.getId());
            List<Cancion> cancionListOld = persistentTags.getCancionList();
            List<Cancion> cancionListNew = tags.getCancionList();
            List<Cancion> attachedCancionListNew = new ArrayList<Cancion>();
            for (Cancion cancionListNewCancionToAttach : cancionListNew) {
                cancionListNewCancionToAttach = em.getReference(cancionListNewCancionToAttach.getClass(), cancionListNewCancionToAttach.getNombre());
                attachedCancionListNew.add(cancionListNewCancionToAttach);
            }
            cancionListNew = attachedCancionListNew;
            tags.setCancionList(cancionListNew);
            tags = em.merge(tags);
            for (Cancion cancionListOldCancion : cancionListOld) {
                if (!cancionListNew.contains(cancionListOldCancion)) {
                    cancionListOldCancion.getTagsList().remove(tags);
                    cancionListOldCancion = em.merge(cancionListOldCancion);
                }
            }
            for (Cancion cancionListNewCancion : cancionListNew) {
                if (!cancionListOld.contains(cancionListNewCancion)) {
                    cancionListNewCancion.getTagsList().add(tags);
                    cancionListNewCancion = em.merge(cancionListNewCancion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tags.getId();
                if (findTags(id) == null) {
                    throw new NonexistentEntityException("The tags with id " + id + " no longer exists.");
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
            Tags tags;
            try {
                tags = em.getReference(Tags.class, id);
                tags.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tags with id " + id + " no longer exists.", enfe);
            }
            List<Cancion> cancionList = tags.getCancionList();
            for (Cancion cancionListCancion : cancionList) {
                cancionListCancion.getTagsList().remove(tags);
                cancionListCancion = em.merge(cancionListCancion);
            }
            em.remove(tags);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tags> findTagsEntities() {
        return findTagsEntities(true, -1, -1);
    }

    public List<Tags> findTagsEntities(int maxResults, int firstResult) {
        return findTagsEntities(false, maxResults, firstResult);
    }

    private List<Tags> findTagsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tags.class));
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

    public Tags findTags(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tags.class, id);
        } finally {
            em.close();
        }
    }

    public int getTagsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tags> rt = cq.from(Tags.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
