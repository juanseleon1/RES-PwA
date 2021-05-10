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
import ResPwAEntities.Baile;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Cuento;
import ResPwAEntities.Cancion;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilPreferencia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
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
        if (perfilPreferencia.getBaileList() == null) {
            perfilPreferencia.setBaileList(new ArrayList<Baile>());
        }
        if (perfilPreferencia.getCuentoList() == null) {
            perfilPreferencia.setCuentoList(new ArrayList<Cuento>());
        }
        if (perfilPreferencia.getCancionList() == null) {
            perfilPreferencia.setCancionList(new ArrayList<Cancion>());
        }
        if (perfilPreferencia.getActxpreferenciaList() == null) {
            perfilPreferencia.setActxpreferenciaList(new ArrayList<Actxpreferencia>());
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
            List<Baile> attachedBaileList = new ArrayList<Baile>();
            for (Baile baileListBaileToAttach : perfilPreferencia.getBaileList()) {
                baileListBaileToAttach = em.getReference(baileListBaileToAttach.getClass(), baileListBaileToAttach.getId());
                attachedBaileList.add(baileListBaileToAttach);
            }
            perfilPreferencia.setBaileList(attachedBaileList);
            List<Cuento> attachedCuentoList = new ArrayList<Cuento>();
            for (Cuento cuentoListCuentoToAttach : perfilPreferencia.getCuentoList()) {
                cuentoListCuentoToAttach = em.getReference(cuentoListCuentoToAttach.getClass(), cuentoListCuentoToAttach.getNombre());
                attachedCuentoList.add(cuentoListCuentoToAttach);
            }
            perfilPreferencia.setCuentoList(attachedCuentoList);
            List<Cancion> attachedCancionList = new ArrayList<Cancion>();
            for (Cancion cancionListCancionToAttach : perfilPreferencia.getCancionList()) {
                cancionListCancionToAttach = em.getReference(cancionListCancionToAttach.getClass(), cancionListCancionToAttach.getNombre());
                attachedCancionList.add(cancionListCancionToAttach);
            }
            perfilPreferencia.setCancionList(attachedCancionList);
            List<Actxpreferencia> attachedActxpreferenciaList = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListActxpreferenciaToAttach : perfilPreferencia.getActxpreferenciaList()) {
                actxpreferenciaListActxpreferenciaToAttach = em.getReference(actxpreferenciaListActxpreferenciaToAttach.getClass(), actxpreferenciaListActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaList.add(actxpreferenciaListActxpreferenciaToAttach);
            }
            perfilPreferencia.setActxpreferenciaList(attachedActxpreferenciaList);
            em.persist(perfilPreferencia);
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(perfilPreferencia);
                perfilpwa = em.merge(perfilpwa);
            }
            for (Baile baileListBaile : perfilPreferencia.getBaileList()) {
                baileListBaile.getPerfilPreferenciaList().add(perfilPreferencia);
                baileListBaile = em.merge(baileListBaile);
            }
            for (Cuento cuentoListCuento : perfilPreferencia.getCuentoList()) {
                cuentoListCuento.getPerfilPreferenciaList().add(perfilPreferencia);
                cuentoListCuento = em.merge(cuentoListCuento);
            }
            for (Cancion cancionListCancion : perfilPreferencia.getCancionList()) {
                cancionListCancion.getPerfilPreferenciaList().add(perfilPreferencia);
                cancionListCancion = em.merge(cancionListCancion);
            }
            for (Actxpreferencia actxpreferenciaListActxpreferencia : perfilPreferencia.getActxpreferenciaList()) {
                PerfilPreferencia oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia = actxpreferenciaListActxpreferencia.getPerfilPreferencia();
                actxpreferenciaListActxpreferencia.setPerfilPreferencia(perfilPreferencia);
                actxpreferenciaListActxpreferencia = em.merge(actxpreferenciaListActxpreferencia);
                if (oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia != null) {
                    oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListActxpreferencia);
                    oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia = em.merge(oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia);
                }
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
            List<Baile> baileListOld = persistentPerfilPreferencia.getBaileList();
            List<Baile> baileListNew = perfilPreferencia.getBaileList();
            List<Cuento> cuentoListOld = persistentPerfilPreferencia.getCuentoList();
            List<Cuento> cuentoListNew = perfilPreferencia.getCuentoList();
            List<Cancion> cancionListOld = persistentPerfilPreferencia.getCancionList();
            List<Cancion> cancionListNew = perfilPreferencia.getCancionList();
            List<Actxpreferencia> actxpreferenciaListOld = persistentPerfilPreferencia.getActxpreferenciaList();
            List<Actxpreferencia> actxpreferenciaListNew = perfilPreferencia.getActxpreferenciaList();
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
            for (Actxpreferencia actxpreferenciaListOldActxpreferencia : actxpreferenciaListOld) {
                if (!actxpreferenciaListNew.contains(actxpreferenciaListOldActxpreferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actxpreferencia " + actxpreferenciaListOldActxpreferencia + " since its perfilPreferencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perfilpwaNew != null) {
                perfilpwaNew = em.getReference(perfilpwaNew.getClass(), perfilpwaNew.getCedula());
                perfilPreferencia.setPerfilpwa(perfilpwaNew);
            }
            List<Baile> attachedBaileListNew = new ArrayList<Baile>();
            for (Baile baileListNewBaileToAttach : baileListNew) {
                baileListNewBaileToAttach = em.getReference(baileListNewBaileToAttach.getClass(), baileListNewBaileToAttach.getId());
                attachedBaileListNew.add(baileListNewBaileToAttach);
            }
            baileListNew = attachedBaileListNew;
            perfilPreferencia.setBaileList(baileListNew);
            List<Cuento> attachedCuentoListNew = new ArrayList<Cuento>();
            for (Cuento cuentoListNewCuentoToAttach : cuentoListNew) {
                cuentoListNewCuentoToAttach = em.getReference(cuentoListNewCuentoToAttach.getClass(), cuentoListNewCuentoToAttach.getNombre());
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
            List<Actxpreferencia> attachedActxpreferenciaListNew = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListNewActxpreferenciaToAttach : actxpreferenciaListNew) {
                actxpreferenciaListNewActxpreferenciaToAttach = em.getReference(actxpreferenciaListNewActxpreferenciaToAttach.getClass(), actxpreferenciaListNewActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaListNew.add(actxpreferenciaListNewActxpreferenciaToAttach);
            }
            actxpreferenciaListNew = attachedActxpreferenciaListNew;
            perfilPreferencia.setActxpreferenciaList(actxpreferenciaListNew);
            perfilPreferencia = em.merge(perfilPreferencia);
            if (perfilpwaOld != null && !perfilpwaOld.equals(perfilpwaNew)) {
                perfilpwaOld.setPerfilPreferencia(null);
                perfilpwaOld = em.merge(perfilpwaOld);
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                perfilpwaNew.setPerfilPreferencia(perfilPreferencia);
                perfilpwaNew = em.merge(perfilpwaNew);
            }
            for (Baile baileListOldBaile : baileListOld) {
                if (!baileListNew.contains(baileListOldBaile)) {
                    baileListOldBaile.getPerfilPreferenciaList().remove(perfilPreferencia);
                    baileListOldBaile = em.merge(baileListOldBaile);
                }
            }
            for (Baile baileListNewBaile : baileListNew) {
                if (!baileListOld.contains(baileListNewBaile)) {
                    baileListNewBaile.getPerfilPreferenciaList().add(perfilPreferencia);
                    baileListNewBaile = em.merge(baileListNewBaile);
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
            for (Actxpreferencia actxpreferenciaListNewActxpreferencia : actxpreferenciaListNew) {
                if (!actxpreferenciaListOld.contains(actxpreferenciaListNewActxpreferencia)) {
                    PerfilPreferencia oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia = actxpreferenciaListNewActxpreferencia.getPerfilPreferencia();
                    actxpreferenciaListNewActxpreferencia.setPerfilPreferencia(perfilPreferencia);
                    actxpreferenciaListNewActxpreferencia = em.merge(actxpreferenciaListNewActxpreferencia);
                    if (oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia != null && !oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia.equals(perfilPreferencia)) {
                        oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListNewActxpreferencia);
                        oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia = em.merge(oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia);
                    }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Actxpreferencia> actxpreferenciaListOrphanCheck = perfilPreferencia.getActxpreferenciaList();
            for (Actxpreferencia actxpreferenciaListOrphanCheckActxpreferencia : actxpreferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilPreferencia (" + perfilPreferencia + ") cannot be destroyed since the Actxpreferencia " + actxpreferenciaListOrphanCheckActxpreferencia + " in its actxpreferenciaList field has a non-nullable perfilPreferencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Perfilpwa perfilpwa = perfilPreferencia.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(null);
                perfilpwa = em.merge(perfilpwa);
            }
            List<Baile> baileList = perfilPreferencia.getBaileList();
            for (Baile baileListBaile : baileList) {
                baileListBaile.getPerfilPreferenciaList().remove(perfilPreferencia);
                baileListBaile = em.merge(baileListBaile);
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
