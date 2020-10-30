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
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.Actividadpwa;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Cuento;
import ResPwAEntities.Cancion;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilPreferencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class PerfilPreferenciaJpaController implements Serializable {

    public PerfilPreferenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerfilPreferencia perfilPreferencia) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (perfilPreferencia.getActividadpwaList() == null) {
            perfilPreferencia.setActividadpwaList(new ArrayList<Actividadpwa>());
        }
        if (perfilPreferencia.getCuentoList() == null) {
            perfilPreferencia.setCuentoList(new ArrayList<Cuento>());
        }
        if (perfilPreferencia.getCancionList() == null) {
            perfilPreferencia.setCancionList(new ArrayList<Cancion>());
        }
        List<String> illegalOrphanMessages = null;
        Perfilpwa perfilpwaOrphanCheck = perfilPreferencia.getPerfilpwa();
        if (perfilpwaOrphanCheck != null) {
            PerfilPreferencia oldPerfilPreferenciaOfPerfilpwa = perfilpwaOrphanCheck.getPerfilPreferencia();
            if (oldPerfilPreferenciaOfPerfilpwa != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Perfilpwa " + perfilpwaOrphanCheck + " already has an item of type PerfilPreferencia whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfilpwa perfilpwa = perfilPreferencia.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa = em.getReference(perfilpwa.getClass(), perfilpwa.getCedula());
                perfilPreferencia.setPerfilpwa(perfilpwa);
            }
            List<Actividadpwa> attachedActividadpwaList = new ArrayList<Actividadpwa>();
            for (Actividadpwa actividadpwaListActividadpwaToAttach : perfilPreferencia.getActividadpwaList()) {
                actividadpwaListActividadpwaToAttach = em.getReference(actividadpwaListActividadpwaToAttach.getClass(), actividadpwaListActividadpwaToAttach.getId());
                attachedActividadpwaList.add(actividadpwaListActividadpwaToAttach);
            }
            perfilPreferencia.setActividadpwaList(attachedActividadpwaList);
            List<Cuento> attachedCuentoList = new ArrayList<Cuento>();
            for (Cuento cuentoListCuentoToAttach : perfilPreferencia.getCuentoList()) {
                cuentoListCuentoToAttach = em.getReference(cuentoListCuentoToAttach.getClass(), cuentoListCuentoToAttach.getNombrecuento());
                attachedCuentoList.add(cuentoListCuentoToAttach);
            }
            perfilPreferencia.setCuentoList(attachedCuentoList);
            List<Cancion> attachedCancionList = new ArrayList<Cancion>();
            for (Cancion cancionListCancionToAttach : perfilPreferencia.getCancionList()) {
                cancionListCancionToAttach = em.getReference(cancionListCancionToAttach.getClass(), cancionListCancionToAttach.getNombre());
                attachedCancionList.add(cancionListCancionToAttach);
            }
            perfilPreferencia.setCancionList(attachedCancionList);
            em.persist(perfilPreferencia);
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(perfilPreferencia);
                perfilpwa = em.merge(perfilpwa);
            }
            for (Actividadpwa actividadpwaListActividadpwa : perfilPreferencia.getActividadpwaList()) {
                actividadpwaListActividadpwa.getPerfilPreferenciaList().add(perfilPreferencia);
                actividadpwaListActividadpwa = em.merge(actividadpwaListActividadpwa);
            }
            for (Cuento cuentoListCuento : perfilPreferencia.getCuentoList()) {
                cuentoListCuento.getPerfilPreferenciaList().add(perfilPreferencia);
                cuentoListCuento = em.merge(cuentoListCuento);
            }
            for (Cancion cancionListCancion : perfilPreferencia.getCancionList()) {
                cancionListCancion.getPerfilPreferenciaList().add(perfilPreferencia);
                cancionListCancion = em.merge(cancionListCancion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilPreferencia(perfilPreferencia.getPerfilpwaCedula()) != null) {
                throw new PreexistingEntityException("PerfilPreferencia " + perfilPreferencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerfilPreferencia perfilPreferencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilPreferencia persistentPerfilPreferencia = em.find(PerfilPreferencia.class, perfilPreferencia.getPerfilpwaCedula());
            Perfilpwa perfilpwaOld = persistentPerfilPreferencia.getPerfilpwa();
            Perfilpwa perfilpwaNew = perfilPreferencia.getPerfilpwa();
            List<Actividadpwa> actividadpwaListOld = persistentPerfilPreferencia.getActividadpwaList();
            List<Actividadpwa> actividadpwaListNew = perfilPreferencia.getActividadpwaList();
            List<Cuento> cuentoListOld = persistentPerfilPreferencia.getCuentoList();
            List<Cuento> cuentoListNew = perfilPreferencia.getCuentoList();
            List<Cancion> cancionListOld = persistentPerfilPreferencia.getCancionList();
            List<Cancion> cancionListNew = perfilPreferencia.getCancionList();
            List<String> illegalOrphanMessages = null;
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                PerfilPreferencia oldPerfilPreferenciaOfPerfilpwa = perfilpwaNew.getPerfilPreferencia();
                if (oldPerfilPreferenciaOfPerfilpwa != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Perfilpwa " + perfilpwaNew + " already has an item of type PerfilPreferencia whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perfilpwaNew != null) {
                perfilpwaNew = em.getReference(perfilpwaNew.getClass(), perfilpwaNew.getCedula());
                perfilPreferencia.setPerfilpwa(perfilpwaNew);
            }
            List<Actividadpwa> attachedActividadpwaListNew = new ArrayList<Actividadpwa>();
            for (Actividadpwa actividadpwaListNewActividadpwaToAttach : actividadpwaListNew) {
                actividadpwaListNewActividadpwaToAttach = em.getReference(actividadpwaListNewActividadpwaToAttach.getClass(), actividadpwaListNewActividadpwaToAttach.getId());
                attachedActividadpwaListNew.add(actividadpwaListNewActividadpwaToAttach);
            }
            actividadpwaListNew = attachedActividadpwaListNew;
            perfilPreferencia.setActividadpwaList(actividadpwaListNew);
            List<Cuento> attachedCuentoListNew = new ArrayList<Cuento>();
            for (Cuento cuentoListNewCuentoToAttach : cuentoListNew) {
                cuentoListNewCuentoToAttach = em.getReference(cuentoListNewCuentoToAttach.getClass(), cuentoListNewCuentoToAttach.getNombrecuento());
                attachedCuentoListNew.add(cuentoListNewCuentoToAttach);
            }
            cuentoListNew = attachedCuentoListNew;
            perfilPreferencia.setCuentoList(cuentoListNew);
            List<Cancion> attachedCancionListNew = new ArrayList<Cancion>();
            for (Cancion cancionListNewCancionToAttach : cancionListNew) {
                cancionListNewCancionToAttach = em.getReference(cancionListNewCancionToAttach.getClass(), cancionListNewCancionToAttach.getNombre());
                attachedCancionListNew.add(cancionListNewCancionToAttach);
            }
            cancionListNew = attachedCancionListNew;
            perfilPreferencia.setCancionList(cancionListNew);
            perfilPreferencia = em.merge(perfilPreferencia);
            if (perfilpwaOld != null && !perfilpwaOld.equals(perfilpwaNew)) {
                perfilpwaOld.setPerfilPreferencia(null);
                perfilpwaOld = em.merge(perfilpwaOld);
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                perfilpwaNew.setPerfilPreferencia(perfilPreferencia);
                perfilpwaNew = em.merge(perfilpwaNew);
            }
            for (Actividadpwa actividadpwaListOldActividadpwa : actividadpwaListOld) {
                if (!actividadpwaListNew.contains(actividadpwaListOldActividadpwa)) {
                    actividadpwaListOldActividadpwa.getPerfilPreferenciaList().remove(perfilPreferencia);
                    actividadpwaListOldActividadpwa = em.merge(actividadpwaListOldActividadpwa);
                }
            }
            for (Actividadpwa actividadpwaListNewActividadpwa : actividadpwaListNew) {
                if (!actividadpwaListOld.contains(actividadpwaListNewActividadpwa)) {
                    actividadpwaListNewActividadpwa.getPerfilPreferenciaList().add(perfilPreferencia);
                    actividadpwaListNewActividadpwa = em.merge(actividadpwaListNewActividadpwa);
                }
            }
            for (Cuento cuentoListOldCuento : cuentoListOld) {
                if (!cuentoListNew.contains(cuentoListOldCuento)) {
                    cuentoListOldCuento.getPerfilPreferenciaList().remove(perfilPreferencia);
                    cuentoListOldCuento = em.merge(cuentoListOldCuento);
                }
            }
            for (Cuento cuentoListNewCuento : cuentoListNew) {
                if (!cuentoListOld.contains(cuentoListNewCuento)) {
                    cuentoListNewCuento.getPerfilPreferenciaList().add(perfilPreferencia);
                    cuentoListNewCuento = em.merge(cuentoListNewCuento);
                }
            }
            for (Cancion cancionListOldCancion : cancionListOld) {
                if (!cancionListNew.contains(cancionListOldCancion)) {
                    cancionListOldCancion.getPerfilPreferenciaList().remove(perfilPreferencia);
                    cancionListOldCancion = em.merge(cancionListOldCancion);
                }
            }
            for (Cancion cancionListNewCancion : cancionListNew) {
                if (!cancionListOld.contains(cancionListNewCancion)) {
                    cancionListNewCancion.getPerfilPreferenciaList().add(perfilPreferencia);
                    cancionListNewCancion = em.merge(cancionListNewCancion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = perfilPreferencia.getPerfilpwaCedula();
                if (findPerfilPreferencia(id) == null) {
                    throw new NonexistentEntityException("The perfilPreferencia with id " + id + " no longer exists.");
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
            PerfilPreferencia perfilPreferencia;
            try {
                perfilPreferencia = em.getReference(PerfilPreferencia.class, id);
                perfilPreferencia.getPerfilpwaCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilPreferencia with id " + id + " no longer exists.", enfe);
            }
            Perfilpwa perfilpwa = perfilPreferencia.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(null);
                perfilpwa = em.merge(perfilpwa);
            }
            List<Actividadpwa> actividadpwaList = perfilPreferencia.getActividadpwaList();
            for (Actividadpwa actividadpwaListActividadpwa : actividadpwaList) {
                actividadpwaListActividadpwa.getPerfilPreferenciaList().remove(perfilPreferencia);
                actividadpwaListActividadpwa = em.merge(actividadpwaListActividadpwa);
            }
            List<Cuento> cuentoList = perfilPreferencia.getCuentoList();
            for (Cuento cuentoListCuento : cuentoList) {
                cuentoListCuento.getPerfilPreferenciaList().remove(perfilPreferencia);
                cuentoListCuento = em.merge(cuentoListCuento);
            }
            List<Cancion> cancionList = perfilPreferencia.getCancionList();
            for (Cancion cancionListCancion : cancionList) {
                cancionListCancion.getPerfilPreferenciaList().remove(perfilPreferencia);
                cancionListCancion = em.merge(cancionListCancion);
            }
            em.remove(perfilPreferencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerfilPreferencia> findPerfilPreferenciaEntities() {
        return findPerfilPreferenciaEntities(true, -1, -1);
    }

    public List<PerfilPreferencia> findPerfilPreferenciaEntities(int maxResults, int firstResult) {
        return findPerfilPreferenciaEntities(false, maxResults, firstResult);
    }

    private List<PerfilPreferencia> findPerfilPreferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerfilPreferencia.class));
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

    public PerfilPreferencia findPerfilPreferencia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerfilPreferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilPreferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerfilPreferencia> rt = cq.from(PerfilPreferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
