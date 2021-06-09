/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Cuento;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Preferenciaxcuento;
import ResPwAEntities.PreferenciaxcuentoPK;
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
public class PreferenciaxcuentoJpaController implements Serializable {

    public PreferenciaxcuentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preferenciaxcuento preferenciaxcuento) throws PreexistingEntityException, Exception {
        if (preferenciaxcuento.getPreferenciaxcuentoPK() == null) {
            preferenciaxcuento.setPreferenciaxcuentoPK(new PreferenciaxcuentoPK());
        }
        preferenciaxcuento.getPreferenciaxcuentoPK().setCuentoNombre(preferenciaxcuento.getCuento().getNombre());
        preferenciaxcuento.getPreferenciaxcuentoPK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxcuento.getPerfilPreferencia().getPerfilpwaCedula());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuento cuento = preferenciaxcuento.getCuento();
            if (cuento != null) {
                cuento = em.getReference(cuento.getClass(), cuento.getNombre());
                preferenciaxcuento.setCuento(cuento);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxcuento.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia = em.getReference(perfilPreferencia.getClass(), perfilPreferencia.getPerfilpwaCedula());
                preferenciaxcuento.setPerfilPreferencia(perfilPreferencia);
            }
            em.persist(preferenciaxcuento);
            if (cuento != null) {
                cuento.getPreferenciaxcuentoList().add(preferenciaxcuento);
                cuento = em.merge(cuento);
            }
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxcuentoList().add(preferenciaxcuento);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreferenciaxcuento(preferenciaxcuento.getPreferenciaxcuentoPK()) != null) {
                throw new PreexistingEntityException("Preferenciaxcuento " + preferenciaxcuento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preferenciaxcuento preferenciaxcuento) throws NonexistentEntityException, Exception {
        preferenciaxcuento.getPreferenciaxcuentoPK().setCuentoNombre(preferenciaxcuento.getCuento().getNombre());
        preferenciaxcuento.getPreferenciaxcuentoPK().setPerfilPreferenciaPerfilpwaCedula(preferenciaxcuento.getPerfilPreferencia().getPerfilpwaCedula());

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxcuento persistentPreferenciaxcuento = em.find(Preferenciaxcuento.class, preferenciaxcuento.getPreferenciaxcuentoPK());
            Cuento cuentoOld = persistentPreferenciaxcuento.getCuento();
            Cuento cuentoNew = preferenciaxcuento.getCuento();
            PerfilPreferencia perfilPreferenciaOld = persistentPreferenciaxcuento.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = preferenciaxcuento.getPerfilPreferencia();
            if (cuentoNew != null) {
                cuentoNew = em.getReference(cuentoNew.getClass(), cuentoNew.getNombre());
                preferenciaxcuento.setCuento(cuentoNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                preferenciaxcuento.setPerfilPreferencia(perfilPreferenciaNew);
            }
            preferenciaxcuento = em.merge(preferenciaxcuento);
            if (cuentoOld != null && !cuentoOld.equals(cuentoNew)) {
                cuentoOld.getPreferenciaxcuentoList().remove(preferenciaxcuento);
                cuentoOld = em.merge(cuentoOld);
            }
            if (cuentoNew != null && !cuentoNew.equals(cuentoOld)) {
                cuentoNew.getPreferenciaxcuentoList().add(preferenciaxcuento);
                cuentoNew = em.merge(cuentoNew);
            }
            if (perfilPreferenciaOld != null && !perfilPreferenciaOld.equals(perfilPreferenciaNew)) {
                perfilPreferenciaOld.getPreferenciaxcuentoList().remove(preferenciaxcuento);
                perfilPreferenciaOld = em.merge(perfilPreferenciaOld);
            }
            if (perfilPreferenciaNew != null && !perfilPreferenciaNew.equals(perfilPreferenciaOld)) {
                perfilPreferenciaNew.getPreferenciaxcuentoList().add(preferenciaxcuento);
                perfilPreferenciaNew = em.merge(perfilPreferenciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PreferenciaxcuentoPK id = preferenciaxcuento.getPreferenciaxcuentoPK();
                if (findPreferenciaxcuento(id) == null) {
                    throw new NonexistentEntityException("The preferenciaxcuento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PreferenciaxcuentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferenciaxcuento preferenciaxcuento;
            try {
                preferenciaxcuento = em.getReference(Preferenciaxcuento.class, id);
                preferenciaxcuento.getPreferenciaxcuentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preferenciaxcuento with id " + id + " no longer exists.", enfe);
            }
            Cuento cuento = preferenciaxcuento.getCuento();
            if (cuento != null) {
                cuento.getPreferenciaxcuentoList().remove(preferenciaxcuento);
                cuento = em.merge(cuento);
            }
            PerfilPreferencia perfilPreferencia = preferenciaxcuento.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia.getPreferenciaxcuentoList().remove(preferenciaxcuento);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            em.remove(preferenciaxcuento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preferenciaxcuento> findPreferenciaxcuentoEntities() {
        return findPreferenciaxcuentoEntities(true, -1, -1);
    }

    public List<Preferenciaxcuento> findPreferenciaxcuentoEntities(int maxResults, int firstResult) {
        return findPreferenciaxcuentoEntities(false, maxResults, firstResult);
    }

    private List<Preferenciaxcuento> findPreferenciaxcuentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preferenciaxcuento.class));
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

    public Preferenciaxcuento findPreferenciaxcuento(PreferenciaxcuentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preferenciaxcuento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreferenciaxcuentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preferenciaxcuento> rt = cq.from(Preferenciaxcuento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
