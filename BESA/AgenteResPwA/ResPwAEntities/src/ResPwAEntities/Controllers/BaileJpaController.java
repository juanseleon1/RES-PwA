/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Baile;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Genero;
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
public class BaileJpaController implements Serializable {

    public BaileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Baile baile) throws PreexistingEntityException, Exception {
        if (baile.getPerfilPreferenciaList() == null) {
            baile.setPerfilPreferenciaList(new ArrayList<PerfilPreferencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero generoGenero = baile.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero = em.getReference(generoGenero.getClass(), generoGenero.getGenero());
                baile.setGeneroGenero(generoGenero);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaList = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferenciaToAttach : baile.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaList.add(perfilPreferenciaListPerfilPreferenciaToAttach);
            }
            baile.setPerfilPreferenciaList(attachedPerfilPreferenciaList);
            em.persist(baile);
            if (generoGenero != null) {
                generoGenero.getBaileList().add(baile);
                generoGenero = em.merge(generoGenero);
            }
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : baile.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferencia.getBaileList().add(baile);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBaile(baile.getId()) != null) {
                throw new PreexistingEntityException("Baile " + baile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Baile baile) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baile persistentBaile = em.find(Baile.class, baile.getId());
            Genero generoGeneroOld = persistentBaile.getGeneroGenero();
            Genero generoGeneroNew = baile.getGeneroGenero();
            List<PerfilPreferencia> perfilPreferenciaListOld = persistentBaile.getPerfilPreferenciaList();
            List<PerfilPreferencia> perfilPreferenciaListNew = baile.getPerfilPreferenciaList();
            if (generoGeneroNew != null) {
                generoGeneroNew = em.getReference(generoGeneroNew.getClass(), generoGeneroNew.getGenero());
                baile.setGeneroGenero(generoGeneroNew);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaListNew = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferenciaToAttach : perfilPreferenciaListNew) {
                perfilPreferenciaListNewPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListNewPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListNewPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaListNew.add(perfilPreferenciaListNewPerfilPreferenciaToAttach);
            }
            perfilPreferenciaListNew = attachedPerfilPreferenciaListNew;
            baile.setPerfilPreferenciaList(perfilPreferenciaListNew);
            baile = em.merge(baile);
            if (generoGeneroOld != null && !generoGeneroOld.equals(generoGeneroNew)) {
                generoGeneroOld.getBaileList().remove(baile);
                generoGeneroOld = em.merge(generoGeneroOld);
            }
            if (generoGeneroNew != null && !generoGeneroNew.equals(generoGeneroOld)) {
                generoGeneroNew.getBaileList().add(baile);
                generoGeneroNew = em.merge(generoGeneroNew);
            }
            for (PerfilPreferencia perfilPreferenciaListOldPerfilPreferencia : perfilPreferenciaListOld) {
                if (!perfilPreferenciaListNew.contains(perfilPreferenciaListOldPerfilPreferencia)) {
                    perfilPreferenciaListOldPerfilPreferencia.getBaileList().remove(baile);
                    perfilPreferenciaListOldPerfilPreferencia = em.merge(perfilPreferenciaListOldPerfilPreferencia);
                }
            }
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferencia : perfilPreferenciaListNew) {
                if (!perfilPreferenciaListOld.contains(perfilPreferenciaListNewPerfilPreferencia)) {
                    perfilPreferenciaListNewPerfilPreferencia.getBaileList().add(baile);
                    perfilPreferenciaListNewPerfilPreferencia = em.merge(perfilPreferenciaListNewPerfilPreferencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = baile.getId();
                if (findBaile(id) == null) {
                    throw new NonexistentEntityException("The baile with id " + id + " no longer exists.");
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
            Baile baile;
            try {
                baile = em.getReference(Baile.class, id);
                baile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baile with id " + id + " no longer exists.", enfe);
            }
            Genero generoGenero = baile.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero.getBaileList().remove(baile);
                generoGenero = em.merge(generoGenero);
            }
            List<PerfilPreferencia> perfilPreferenciaList = baile.getPerfilPreferenciaList();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : perfilPreferenciaList) {
                perfilPreferenciaListPerfilPreferencia.getBaileList().remove(baile);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.remove(baile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Baile> findBaileEntities() {
        return findBaileEntities(true, -1, -1);
    }

    public List<Baile> findBaileEntities(int maxResults, int firstResult) {
        return findBaileEntities(false, maxResults, firstResult);
    }

    private List<Baile> findBaileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Baile.class));
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

    public Baile findBaile(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Baile.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Baile> rt = cq.from(Baile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
