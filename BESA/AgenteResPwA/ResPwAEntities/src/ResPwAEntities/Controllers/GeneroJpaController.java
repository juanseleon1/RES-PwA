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
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Baile;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Cuento;
import ResPwAEntities.Genero;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) throws PreexistingEntityException, Exception {
        if (genero.getCancionList() == null) {
            genero.setCancionList(new ArrayList<Cancion>());
        }
        if (genero.getBaileList() == null) {
            genero.setBaileList(new ArrayList<Baile>());
        }
        if (genero.getCuentoList() == null) {
            genero.setCuentoList(new ArrayList<Cuento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cancion> attachedCancionList = new ArrayList<Cancion>();
            for (Cancion cancionListCancionToAttach : genero.getCancionList()) {
                cancionListCancionToAttach = em.getReference(cancionListCancionToAttach.getClass(), cancionListCancionToAttach.getNombre());
                attachedCancionList.add(cancionListCancionToAttach);
            }
            genero.setCancionList(attachedCancionList);
            List<Baile> attachedBaileList = new ArrayList<Baile>();
            for (Baile baileListBaileToAttach : genero.getBaileList()) {
                baileListBaileToAttach = em.getReference(baileListBaileToAttach.getClass(), baileListBaileToAttach.getId());
                attachedBaileList.add(baileListBaileToAttach);
            }
            genero.setBaileList(attachedBaileList);
            List<Cuento> attachedCuentoList = new ArrayList<Cuento>();
            for (Cuento cuentoListCuentoToAttach : genero.getCuentoList()) {
                cuentoListCuentoToAttach = em.getReference(cuentoListCuentoToAttach.getClass(), cuentoListCuentoToAttach.getNombre());
                attachedCuentoList.add(cuentoListCuentoToAttach);
            }
            genero.setCuentoList(attachedCuentoList);
            em.persist(genero);
            for (Cancion cancionListCancion : genero.getCancionList()) {
                Genero oldGeneroGeneroOfCancionListCancion = cancionListCancion.getGeneroGenero();
                cancionListCancion.setGeneroGenero(genero);
                cancionListCancion = em.merge(cancionListCancion);
                if (oldGeneroGeneroOfCancionListCancion != null) {
                    oldGeneroGeneroOfCancionListCancion.getCancionList().remove(cancionListCancion);
                    oldGeneroGeneroOfCancionListCancion = em.merge(oldGeneroGeneroOfCancionListCancion);
                }
            }
            for (Baile baileListBaile : genero.getBaileList()) {
                Genero oldGeneroGeneroOfBaileListBaile = baileListBaile.getGeneroGenero();
                baileListBaile.setGeneroGenero(genero);
                baileListBaile = em.merge(baileListBaile);
                if (oldGeneroGeneroOfBaileListBaile != null) {
                    oldGeneroGeneroOfBaileListBaile.getBaileList().remove(baileListBaile);
                    oldGeneroGeneroOfBaileListBaile = em.merge(oldGeneroGeneroOfBaileListBaile);
                }
            }
            for (Cuento cuentoListCuento : genero.getCuentoList()) {
                Genero oldGeneroGeneroOfCuentoListCuento = cuentoListCuento.getGeneroGenero();
                cuentoListCuento.setGeneroGenero(genero);
                cuentoListCuento = em.merge(cuentoListCuento);
                if (oldGeneroGeneroOfCuentoListCuento != null) {
                    oldGeneroGeneroOfCuentoListCuento.getCuentoList().remove(cuentoListCuento);
                    oldGeneroGeneroOfCuentoListCuento = em.merge(oldGeneroGeneroOfCuentoListCuento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenero(genero.getGenero()) != null) {
                throw new PreexistingEntityException("Genero " + genero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getGenero());
            List<Cancion> cancionListOld = persistentGenero.getCancionList();
            List<Cancion> cancionListNew = genero.getCancionList();
            List<Baile> baileListOld = persistentGenero.getBaileList();
            List<Baile> baileListNew = genero.getBaileList();
            List<Cuento> cuentoListOld = persistentGenero.getCuentoList();
            List<Cuento> cuentoListNew = genero.getCuentoList();
            List<String> illegalOrphanMessages = null;
            for (Cancion cancionListOldCancion : cancionListOld) {
                if (!cancionListNew.contains(cancionListOldCancion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cancion " + cancionListOldCancion + " since its generoGenero field is not nullable.");
                }
            }
            for (Baile baileListOldBaile : baileListOld) {
                if (!baileListNew.contains(baileListOldBaile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Baile " + baileListOldBaile + " since its generoGenero field is not nullable.");
                }
            }
            for (Cuento cuentoListOldCuento : cuentoListOld) {
                if (!cuentoListNew.contains(cuentoListOldCuento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuento " + cuentoListOldCuento + " since its generoGenero field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cancion> attachedCancionListNew = new ArrayList<Cancion>();
            for (Cancion cancionListNewCancionToAttach : cancionListNew) {
                cancionListNewCancionToAttach = em.getReference(cancionListNewCancionToAttach.getClass(), cancionListNewCancionToAttach.getNombre());
                attachedCancionListNew.add(cancionListNewCancionToAttach);
            }
            cancionListNew = attachedCancionListNew;
            genero.setCancionList(cancionListNew);
            List<Baile> attachedBaileListNew = new ArrayList<Baile>();
            for (Baile baileListNewBaileToAttach : baileListNew) {
                baileListNewBaileToAttach = em.getReference(baileListNewBaileToAttach.getClass(), baileListNewBaileToAttach.getId());
                attachedBaileListNew.add(baileListNewBaileToAttach);
            }
            baileListNew = attachedBaileListNew;
            genero.setBaileList(baileListNew);
            List<Cuento> attachedCuentoListNew = new ArrayList<Cuento>();
            for (Cuento cuentoListNewCuentoToAttach : cuentoListNew) {
                cuentoListNewCuentoToAttach = em.getReference(cuentoListNewCuentoToAttach.getClass(), cuentoListNewCuentoToAttach.getNombre());
                attachedCuentoListNew.add(cuentoListNewCuentoToAttach);
            }
            cuentoListNew = attachedCuentoListNew;
            genero.setCuentoList(cuentoListNew);
            genero = em.merge(genero);
            for (Cancion cancionListNewCancion : cancionListNew) {
                if (!cancionListOld.contains(cancionListNewCancion)) {
                    Genero oldGeneroGeneroOfCancionListNewCancion = cancionListNewCancion.getGeneroGenero();
                    cancionListNewCancion.setGeneroGenero(genero);
                    cancionListNewCancion = em.merge(cancionListNewCancion);
                    if (oldGeneroGeneroOfCancionListNewCancion != null && !oldGeneroGeneroOfCancionListNewCancion.equals(genero)) {
                        oldGeneroGeneroOfCancionListNewCancion.getCancionList().remove(cancionListNewCancion);
                        oldGeneroGeneroOfCancionListNewCancion = em.merge(oldGeneroGeneroOfCancionListNewCancion);
                    }
                }
            }
            for (Baile baileListNewBaile : baileListNew) {
                if (!baileListOld.contains(baileListNewBaile)) {
                    Genero oldGeneroGeneroOfBaileListNewBaile = baileListNewBaile.getGeneroGenero();
                    baileListNewBaile.setGeneroGenero(genero);
                    baileListNewBaile = em.merge(baileListNewBaile);
                    if (oldGeneroGeneroOfBaileListNewBaile != null && !oldGeneroGeneroOfBaileListNewBaile.equals(genero)) {
                        oldGeneroGeneroOfBaileListNewBaile.getBaileList().remove(baileListNewBaile);
                        oldGeneroGeneroOfBaileListNewBaile = em.merge(oldGeneroGeneroOfBaileListNewBaile);
                    }
                }
            }
            for (Cuento cuentoListNewCuento : cuentoListNew) {
                if (!cuentoListOld.contains(cuentoListNewCuento)) {
                    Genero oldGeneroGeneroOfCuentoListNewCuento = cuentoListNewCuento.getGeneroGenero();
                    cuentoListNewCuento.setGeneroGenero(genero);
                    cuentoListNewCuento = em.merge(cuentoListNewCuento);
                    if (oldGeneroGeneroOfCuentoListNewCuento != null && !oldGeneroGeneroOfCuentoListNewCuento.equals(genero)) {
                        oldGeneroGeneroOfCuentoListNewCuento.getCuentoList().remove(cuentoListNewCuento);
                        oldGeneroGeneroOfCuentoListNewCuento = em.merge(oldGeneroGeneroOfCuentoListNewCuento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genero.getGenero();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getGenero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cancion> cancionListOrphanCheck = genero.getCancionList();
            for (Cancion cancionListOrphanCheckCancion : cancionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Cancion " + cancionListOrphanCheckCancion + " in its cancionList field has a non-nullable generoGenero field.");
            }
            List<Baile> baileListOrphanCheck = genero.getBaileList();
            for (Baile baileListOrphanCheckBaile : baileListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Baile " + baileListOrphanCheckBaile + " in its baileList field has a non-nullable generoGenero field.");
            }
            List<Cuento> cuentoListOrphanCheck = genero.getCuentoList();
            for (Cuento cuentoListOrphanCheckCuento : cuentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Cuento " + cuentoListOrphanCheckCuento + " in its cuentoList field has a non-nullable generoGenero field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
