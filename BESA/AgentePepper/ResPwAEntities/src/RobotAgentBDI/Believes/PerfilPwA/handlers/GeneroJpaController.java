/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA.handlers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RobotAgentBDI.Believes.PerfilPwA.Cancion;
import java.util.ArrayList;
import java.util.List;
import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import RobotAgentBDI.Believes.PerfilPwA.Genero;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.IllegalOrphanException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.NonexistentEntityException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
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
            List<Cuento> attachedCuentoList = new ArrayList<Cuento>();
            for (Cuento cuentoListCuentoToAttach : genero.getCuentoList()) {
                cuentoListCuentoToAttach = em.getReference(cuentoListCuentoToAttach.getClass(), cuentoListCuentoToAttach.getNombrecuento());
                attachedCuentoList.add(cuentoListCuentoToAttach);
            }
            genero.setCuentoList(attachedCuentoList);
            em.persist(genero);
            for (Cancion cancionListCancion : genero.getCancionList()) {
                Genero oldGeneroNombregeneroOfCancionListCancion = cancionListCancion.getGeneroNombregenero();
                cancionListCancion.setGeneroNombregenero(genero);
                cancionListCancion = em.merge(cancionListCancion);
                if (oldGeneroNombregeneroOfCancionListCancion != null) {
                    oldGeneroNombregeneroOfCancionListCancion.getCancionList().remove(cancionListCancion);
                    oldGeneroNombregeneroOfCancionListCancion = em.merge(oldGeneroNombregeneroOfCancionListCancion);
                }
            }
            for (Cuento cuentoListCuento : genero.getCuentoList()) {
                Genero oldGeneroNombregeneroOfCuentoListCuento = cuentoListCuento.getGeneroNombregenero();
                cuentoListCuento.setGeneroNombregenero(genero);
                cuentoListCuento = em.merge(cuentoListCuento);
                if (oldGeneroNombregeneroOfCuentoListCuento != null) {
                    oldGeneroNombregeneroOfCuentoListCuento.getCuentoList().remove(cuentoListCuento);
                    oldGeneroNombregeneroOfCuentoListCuento = em.merge(oldGeneroNombregeneroOfCuentoListCuento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenero(genero.getNombregenero()) != null) {
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
            Genero persistentGenero = em.find(Genero.class, genero.getNombregenero());
            List<Cancion> cancionListOld = persistentGenero.getCancionList();
            List<Cancion> cancionListNew = genero.getCancionList();
            List<Cuento> cuentoListOld = persistentGenero.getCuentoList();
            List<Cuento> cuentoListNew = genero.getCuentoList();
            List<String> illegalOrphanMessages = null;
            for (Cancion cancionListOldCancion : cancionListOld) {
                if (!cancionListNew.contains(cancionListOldCancion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cancion " + cancionListOldCancion + " since its generoNombregenero field is not nullable.");
                }
            }
            for (Cuento cuentoListOldCuento : cuentoListOld) {
                if (!cuentoListNew.contains(cuentoListOldCuento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cuento " + cuentoListOldCuento + " since its generoNombregenero field is not nullable.");
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
            List<Cuento> attachedCuentoListNew = new ArrayList<Cuento>();
            for (Cuento cuentoListNewCuentoToAttach : cuentoListNew) {
                cuentoListNewCuentoToAttach = em.getReference(cuentoListNewCuentoToAttach.getClass(), cuentoListNewCuentoToAttach.getNombrecuento());
                attachedCuentoListNew.add(cuentoListNewCuentoToAttach);
            }
            cuentoListNew = attachedCuentoListNew;
            genero.setCuentoList(cuentoListNew);
            genero = em.merge(genero);
            for (Cancion cancionListNewCancion : cancionListNew) {
                if (!cancionListOld.contains(cancionListNewCancion)) {
                    Genero oldGeneroNombregeneroOfCancionListNewCancion = cancionListNewCancion.getGeneroNombregenero();
                    cancionListNewCancion.setGeneroNombregenero(genero);
                    cancionListNewCancion = em.merge(cancionListNewCancion);
                    if (oldGeneroNombregeneroOfCancionListNewCancion != null && !oldGeneroNombregeneroOfCancionListNewCancion.equals(genero)) {
                        oldGeneroNombregeneroOfCancionListNewCancion.getCancionList().remove(cancionListNewCancion);
                        oldGeneroNombregeneroOfCancionListNewCancion = em.merge(oldGeneroNombregeneroOfCancionListNewCancion);
                    }
                }
            }
            for (Cuento cuentoListNewCuento : cuentoListNew) {
                if (!cuentoListOld.contains(cuentoListNewCuento)) {
                    Genero oldGeneroNombregeneroOfCuentoListNewCuento = cuentoListNewCuento.getGeneroNombregenero();
                    cuentoListNewCuento.setGeneroNombregenero(genero);
                    cuentoListNewCuento = em.merge(cuentoListNewCuento);
                    if (oldGeneroNombregeneroOfCuentoListNewCuento != null && !oldGeneroNombregeneroOfCuentoListNewCuento.equals(genero)) {
                        oldGeneroNombregeneroOfCuentoListNewCuento.getCuentoList().remove(cuentoListNewCuento);
                        oldGeneroNombregeneroOfCuentoListNewCuento = em.merge(oldGeneroNombregeneroOfCuentoListNewCuento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genero.getNombregenero();
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
                genero.getNombregenero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cancion> cancionListOrphanCheck = genero.getCancionList();
            for (Cancion cancionListOrphanCheckCancion : cancionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Cancion " + cancionListOrphanCheckCancion + " in its cancionList field has a non-nullable generoNombregenero field.");
            }
            List<Cuento> cuentoListOrphanCheck = genero.getCuentoList();
            for (Cuento cuentoListOrphanCheckCuento : cuentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Genero (" + genero + ") cannot be destroyed since the Cuento " + cuentoListOrphanCheckCuento + " in its cuentoList field has a non-nullable generoNombregenero field.");
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
