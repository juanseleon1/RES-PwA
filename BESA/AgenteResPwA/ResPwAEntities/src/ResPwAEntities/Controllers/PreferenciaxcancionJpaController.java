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
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Preferenciaxcancion;
import ResPwAEntities.PreferenciaxcancionPK;
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
public class PreferenciaxcancionJpaController implements Serializable {

    public PreferenciaxcancionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preferenciaxcancion preferenciaxcancion) throws PreexistingEntityException, Exception {
        if (preferenciaxcancion.getPreferenciaxcancionPK() == null) {
            preferenciaxcancion.setPreferenciaxcancionPK(new PreferenciaxcancionPK());
        }
        preferenciaxcancion.getPreferenciaxcancionPK().setCancionNombre(preferenciaxcancion.getCancion().getNombre());
        preferenciaxcancion.getPreferenciaxcancionPK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxcancion.getPerfilPreferencia().getPerfilpwaCedula());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cancion cancion = preferenciaxcancion.getCancion();
            if (cancion != null) {
                cancion = em.getReference(cancion.getClass(), cancion.getNombre());
                preferenciaxcancion.setCancion(cancion);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxcancion.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia = em.getReference(perfilPreferencia.getClass(), perfilPreferencia.getPerfilpwaCedula());
                preferenciaxcancion.setPerfilPreferencia(perfilPreferencia);
            }
            em.persist(preferenciaxcancion);
            if (cancion != null) {
                cancion.getPreferenciaxcancionList().add(preferenciaxcancion);
                cancion = em.merge(cancion);
            }
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxcancionList().add(preferenciaxcancion);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreferenciaxcancion(preferenciaxcancion.getPreferenciaxcancionPK()) != null) {
                throw new PreexistingEntityException("Preferenciaxcancion " + preferenciaxcancion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preferenciaxcancion preferenciaxcancion) throws NonexistentEntityException, Exception {
        preferenciaxcancion.getPreferenciaxcancionPK().setCancionNombre(preferenciaxcancion.getCancion().getNombre());
        preferenciaxcancion.getPreferenciaxcancionPK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxcancion.getPerfilPreferencia().getPerfilpwaCedula());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxcancion persistentPreferenciaxcancion = em.find(Preferenciaxcancion.class, preferenciaxcancion.getPreferenciaxcancionPK());
            Cancion cancionOld = persistentPreferenciaxcancion.getCancion();
            Cancion cancionNew = preferenciaxcancion.getCancion();
            PerfilPreferencia perfilPreferenciaOld = persistentPreferenciaxcancion.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = preferenciaxcancion.getPerfilPreferencia();
            if (cancionNew != null) {
                cancionNew = em.getReference(cancionNew.getClass(), cancionNew.getNombre());
                preferenciaxcancion.setCancion(cancionNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                preferenciaxcancion.setPerfilPreferencia(perfilPreferenciaNew);
            }
            preferenciaxcancion = em.merge(preferenciaxcancion);
            if (cancionOld != null && !cancionOld.equals(cancionNew)) {
                cancionOld.getPreferenciaxcancionList().remove(preferenciaxcancion);
                cancionOld = em.merge(cancionOld);
            }
            if (cancionNew != null && !cancionNew.equals(cancionOld)) {
                cancionNew.getPreferenciaxcancionList().add(preferenciaxcancion);
                cancionNew = em.merge(cancionNew);
            }
            if (perfilPreferenciaOld != null && !perfilPreferenciaOld.equals(perfilPreferenciaNew)) {
                perfilPreferenciaOld.getPreferenciaxcancionList().remove(preferenciaxcancion);
                perfilPreferenciaOld = em.merge(perfilPreferenciaOld);
            }
            if (perfilPreferenciaNew != null && !perfilPreferenciaNew.equals(perfilPreferenciaOld)) {
                perfilPreferenciaNew.getPreferenciaxcancionList().add(preferenciaxcancion);
                perfilPreferenciaNew = em.merge(perfilPreferenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PreferenciaxcancionPK id = preferenciaxcancion.getPreferenciaxcancionPK();
                if (findPreferenciaxcancion(id) == null) {
                    throw new NonexistentEntityException("The preferenciaxcancion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PreferenciaxcancionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxcancion preferenciaxcancion;
            try {
                preferenciaxcancion = em.getReference(Preferenciaxcancion.class, id);
                preferenciaxcancion.getPreferenciaxcancionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preferenciaxcancion with id " + id + " no longer exists.", enfe);
            }
            Cancion cancion = preferenciaxcancion.getCancion();
            if (cancion != null) {
                cancion.getPreferenciaxcancionList().remove(preferenciaxcancion);
                cancion = em.merge(cancion);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxcancion.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxcancionList().remove(preferenciaxcancion);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.remove(preferenciaxcancion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preferenciaxcancion> findPreferenciaxcancionEntities() {
        return findPreferenciaxcancionEntities(true, -1, -1);
    }

    public List<Preferenciaxcancion> findPreferenciaxcancionEntities(int maxResults, int firstResult) {
        return findPreferenciaxcancionEntities(false, maxResults, firstResult);
    }

    private List<Preferenciaxcancion> findPreferenciaxcancionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preferenciaxcancion.class));
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

    public Preferenciaxcancion findPreferenciaxcancion(PreferenciaxcancionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preferenciaxcancion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreferenciaxcancionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preferenciaxcancion> rt = cq.from(Preferenciaxcancion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
