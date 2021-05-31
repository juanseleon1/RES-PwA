/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Baile;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Genero;
import ResPwAEntities.Preferenciaxbaile;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
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
        if (baile.getPreferenciaxbaileList() == null) {
            baile.setPreferenciaxbaileList(new ArrayList<Preferenciaxbaile>());
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
            List<Preferenciaxbaile> attachedPreferenciaxbaileList = new ArrayList<Preferenciaxbaile>();
            for (Preferenciaxbaile preferenciaxbaileListPreferenciaxbaileToAttach : baile.getPreferenciaxbaileList()) {
                preferenciaxbaileListPreferenciaxbaileToAttach = em.getReference(preferenciaxbaileListPreferenciaxbaileToAttach.getClass(), preferenciaxbaileListPreferenciaxbaileToAttach.getPreferenciaxbailePK());
                attachedPreferenciaxbaileList.add(preferenciaxbaileListPreferenciaxbaileToAttach);
            }
            baile.setPreferenciaxbaileList(attachedPreferenciaxbaileList);
            em.persist(baile);
            if (generoGenero != null) {
                generoGenero.getBaileList().add(baile);
                generoGenero = em.merge(generoGenero);
            }
            for (Preferenciaxbaile preferenciaxbaileListPreferenciaxbaile : baile.getPreferenciaxbaileList()) {
                Baile oldBaileOfPreferenciaxbaileListPreferenciaxbaile = preferenciaxbaileListPreferenciaxbaile.getBaile();
                preferenciaxbaileListPreferenciaxbaile.setBaile(baile);
                preferenciaxbaileListPreferenciaxbaile = em.merge(preferenciaxbaileListPreferenciaxbaile);
                if (oldBaileOfPreferenciaxbaileListPreferenciaxbaile != null) {
                    oldBaileOfPreferenciaxbaileListPreferenciaxbaile.getPreferenciaxbaileList().remove(preferenciaxbaileListPreferenciaxbaile);
                    oldBaileOfPreferenciaxbaileListPreferenciaxbaile = em.merge(oldBaileOfPreferenciaxbaileListPreferenciaxbaile);
                }
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

    public void edit(Baile baile) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baile persistentBaile = em.find(Baile.class, baile.getId());
            Genero generoGeneroOld = persistentBaile.getGeneroGenero();
            Genero generoGeneroNew = baile.getGeneroGenero();
            List<Preferenciaxbaile> preferenciaxbaileListOld = persistentBaile.getPreferenciaxbaileList();
            List<Preferenciaxbaile> preferenciaxbaileListNew = baile.getPreferenciaxbaileList();
            List<String> illegalOrphanMessages = null;
            for (Preferenciaxbaile preferenciaxbaileListOldPreferenciaxbaile : preferenciaxbaileListOld) {
                if (!preferenciaxbaileListNew.contains(preferenciaxbaileListOldPreferenciaxbaile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferenciaxbaile " + preferenciaxbaileListOldPreferenciaxbaile + " since its baile field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (generoGeneroNew != null) {
                generoGeneroNew = em.getReference(generoGeneroNew.getClass(), generoGeneroNew.getGenero());
                baile.setGeneroGenero(generoGeneroNew);
            }
            List<Preferenciaxbaile> attachedPreferenciaxbaileListNew = new ArrayList<Preferenciaxbaile>();
            for (Preferenciaxbaile preferenciaxbaileListNewPreferenciaxbaileToAttach : preferenciaxbaileListNew) {
                preferenciaxbaileListNewPreferenciaxbaileToAttach = em.getReference(preferenciaxbaileListNewPreferenciaxbaileToAttach.getClass(), preferenciaxbaileListNewPreferenciaxbaileToAttach.getPreferenciaxbailePK());
                attachedPreferenciaxbaileListNew.add(preferenciaxbaileListNewPreferenciaxbaileToAttach);
            }
            preferenciaxbaileListNew = attachedPreferenciaxbaileListNew;
            baile.setPreferenciaxbaileList(preferenciaxbaileListNew);
            baile = em.merge(baile);
            if (generoGeneroOld != null && !generoGeneroOld.equals(generoGeneroNew)) {
                generoGeneroOld.getBaileList().remove(baile);
                generoGeneroOld = em.merge(generoGeneroOld);
            }
            if (generoGeneroNew != null && !generoGeneroNew.equals(generoGeneroOld)) {
                generoGeneroNew.getBaileList().add(baile);
                generoGeneroNew = em.merge(generoGeneroNew);
            }
            for (Preferenciaxbaile preferenciaxbaileListNewPreferenciaxbaile : preferenciaxbaileListNew) {
                if (!preferenciaxbaileListOld.contains(preferenciaxbaileListNewPreferenciaxbaile)) {
                    Baile oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile = preferenciaxbaileListNewPreferenciaxbaile.getBaile();
                    preferenciaxbaileListNewPreferenciaxbaile.setBaile(baile);
                    preferenciaxbaileListNewPreferenciaxbaile = em.merge(preferenciaxbaileListNewPreferenciaxbaile);
                    if (oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile != null && !oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile.equals(baile)) {
                        oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile.getPreferenciaxbaileList().remove(preferenciaxbaileListNewPreferenciaxbaile);
                        oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile = em.merge(oldBaileOfPreferenciaxbaileListNewPreferenciaxbaile);
                    }
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

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Preferenciaxbaile> preferenciaxbaileListOrphanCheck = baile.getPreferenciaxbaileList();
            for (Preferenciaxbaile preferenciaxbaileListOrphanCheckPreferenciaxbaile : preferenciaxbaileListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Baile (" + baile + ") cannot be destroyed since the Preferenciaxbaile " + preferenciaxbaileListOrphanCheckPreferenciaxbaile + " in its preferenciaxbaileList field has a non-nullable baile field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Genero generoGenero = baile.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero.getBaileList().remove(baile);
                generoGenero = em.merge(generoGenero);
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
