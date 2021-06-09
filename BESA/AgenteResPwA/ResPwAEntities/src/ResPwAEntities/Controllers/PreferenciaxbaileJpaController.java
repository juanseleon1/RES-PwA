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
import ResPwAEntities.Baile;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Preferenciaxbaile;
import ResPwAEntities.PreferenciaxbailePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
<<<<<<< HEAD
 * @author maria.f.garces.cala
=======
 * @author juans
>>>>>>> master
 */
public class PreferenciaxbaileJpaController implements Serializable {

    public PreferenciaxbaileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preferenciaxbaile preferenciaxbaile) throws PreexistingEntityException, Exception {
        if (preferenciaxbaile.getPreferenciaxbailePK() == null) {
            preferenciaxbaile.setPreferenciaxbailePK(new PreferenciaxbailePK());
        }
        preferenciaxbaile.getPreferenciaxbailePK().setBaileId(preferenciaxbaile.getBaile().getId());
        preferenciaxbaile.getPreferenciaxbailePK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxbaile.getPerfilPreferencia().getPerfilpwaCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Baile baile = preferenciaxbaile.getBaile();
            if (baile != null) {
                baile = em.getReference(baile.getClass(), baile.getId());
                preferenciaxbaile.setBaile(baile);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxbaile.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia = em.getReference(perfilPreferencia.getClass(), perfilPreferencia.getPerfilpwaCedula());
                preferenciaxbaile.setPerfilPreferencia(perfilPreferencia);
            }
            em.persist(preferenciaxbaile);
            if (baile != null) {
                baile.getPreferenciaxbaileList().add(preferenciaxbaile);
                baile = em.merge(baile);
            }
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxbaileList().add(preferenciaxbaile);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreferenciaxbaile(preferenciaxbaile.getPreferenciaxbailePK()) != null) {
                throw new PreexistingEntityException("Preferenciaxbaile " + preferenciaxbaile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preferenciaxbaile preferenciaxbaile) throws NonexistentEntityException, Exception {
        preferenciaxbaile.getPreferenciaxbailePK().setBaileId(preferenciaxbaile.getBaile().getId());
        preferenciaxbaile.getPreferenciaxbailePK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxbaile.getPerfilPreferencia().getPerfilpwaCedula());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxbaile persistentPreferenciaxbaile = em.find(Preferenciaxbaile.class, preferenciaxbaile.getPreferenciaxbailePK());
            Baile baileOld = persistentPreferenciaxbaile.getBaile();
            Baile baileNew = preferenciaxbaile.getBaile();
            PerfilPreferencia perfilPreferenciaOld = persistentPreferenciaxbaile.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = preferenciaxbaile.getPerfilPreferencia();
            if (baileNew != null) {
                baileNew = em.getReference(baileNew.getClass(), baileNew.getId());
                preferenciaxbaile.setBaile(baileNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                preferenciaxbaile.setPerfilPreferencia(perfilPreferenciaNew);
            }
            preferenciaxbaile = em.merge(preferenciaxbaile);
            if (baileOld != null && !baileOld.equals(baileNew)) {
                baileOld.getPreferenciaxbaileList().remove(preferenciaxbaile);
                baileOld = em.merge(baileOld);
            }
            if (baileNew != null && !baileNew.equals(baileOld)) {
                baileNew.getPreferenciaxbaileList().add(preferenciaxbaile);
                baileNew = em.merge(baileNew);
            }
            if (perfilPreferenciaOld != null && !perfilPreferenciaOld.equals(perfilPreferenciaNew)) {
                perfilPreferenciaOld.getPreferenciaxbaileList().remove(preferenciaxbaile);
                perfilPreferenciaOld = em.merge(perfilPreferenciaOld);
            }
            if (perfilPreferenciaNew != null && !perfilPreferenciaNew.equals(perfilPreferenciaOld)) {
                perfilPreferenciaNew.getPreferenciaxbaileList().add(preferenciaxbaile);
                perfilPreferenciaNew = em.merge(perfilPreferenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PreferenciaxbailePK id = preferenciaxbaile.getPreferenciaxbailePK();
                if (findPreferenciaxbaile(id) == null) {
                    throw new NonexistentEntityException("The preferenciaxbaile with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PreferenciaxbailePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxbaile preferenciaxbaile;
            try {
                preferenciaxbaile = em.getReference(Preferenciaxbaile.class, id);
                preferenciaxbaile.getPreferenciaxbailePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preferenciaxbaile with id " + id + " no longer exists.", enfe);
            }
            Baile baile = preferenciaxbaile.getBaile();
            if (baile != null) {
                baile.getPreferenciaxbaileList().remove(preferenciaxbaile);
                baile = em.merge(baile);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxbaile.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxbaileList().remove(preferenciaxbaile);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.remove(preferenciaxbaile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preferenciaxbaile> findPreferenciaxbaileEntities() {
        return findPreferenciaxbaileEntities(true, -1, -1);
    }

    public List<Preferenciaxbaile> findPreferenciaxbaileEntities(int maxResults, int firstResult) {
        return findPreferenciaxbaileEntities(false, maxResults, firstResult);
    }

    private List<Preferenciaxbaile> findPreferenciaxbaileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preferenciaxbaile.class));
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

    public Preferenciaxbaile findPreferenciaxbaile(PreferenciaxbailePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preferenciaxbaile.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreferenciaxbaileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preferenciaxbaile> rt = cq.from(Preferenciaxbaile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
