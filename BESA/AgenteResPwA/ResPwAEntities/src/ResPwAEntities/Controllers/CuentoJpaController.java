/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Cuento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Genero;
import ResPwAEntities.PerfilPreferencia;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Frases;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class CuentoJpaController implements Serializable {

    public CuentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cuento cuento) throws PreexistingEntityException, Exception {
        if (cuento.getPerfilPreferenciaList() == null) {
            cuento.setPerfilPreferenciaList(new ArrayList<PerfilPreferencia>());
        }
        if (cuento.getFrasesList() == null) {
            cuento.setFrasesList(new ArrayList<Frases>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero generoGenero = cuento.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero = em.getReference(generoGenero.getClass(), generoGenero.getGenero());
                cuento.setGeneroGenero(generoGenero);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaList = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferenciaToAttach : cuento.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaList.add(perfilPreferenciaListPerfilPreferenciaToAttach);
            }
            cuento.setPerfilPreferenciaList(attachedPerfilPreferenciaList);
            List<Frases> attachedFrasesList = new ArrayList<Frases>();
            for (Frases frasesListFrasesToAttach : cuento.getFrasesList()) {
                frasesListFrasesToAttach = em.getReference(frasesListFrasesToAttach.getClass(), frasesListFrasesToAttach.getFrasesPK());
                attachedFrasesList.add(frasesListFrasesToAttach);
            }
            cuento.setFrasesList(attachedFrasesList);
            em.persist(cuento);
            if (generoGenero != null) {
                generoGenero.getCuentoList().add(cuento);
                generoGenero = em.merge(generoGenero);
            }
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : cuento.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferencia.getCuentoList().add(cuento);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            for (Frases frasesListFrases : cuento.getFrasesList()) {
                Cuento oldCuentoOfFrasesListFrases = frasesListFrases.getCuento();
                frasesListFrases.setCuento(cuento);
                frasesListFrases = em.merge(frasesListFrases);
                if (oldCuentoOfFrasesListFrases != null) {
                    oldCuentoOfFrasesListFrases.getFrasesList().remove(frasesListFrases);
                    oldCuentoOfFrasesListFrases = em.merge(oldCuentoOfFrasesListFrases);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCuento(cuento.getNombre()) != null) {
                throw new PreexistingEntityException("Cuento " + cuento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuento cuento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuento persistentCuento = em.find(Cuento.class, cuento.getNombre());
            Genero generoGeneroOld = persistentCuento.getGeneroGenero();
            Genero generoGeneroNew = cuento.getGeneroGenero();
            List<PerfilPreferencia> perfilPreferenciaListOld = persistentCuento.getPerfilPreferenciaList();
            List<PerfilPreferencia> perfilPreferenciaListNew = cuento.getPerfilPreferenciaList();
            List<Frases> frasesListOld = persistentCuento.getFrasesList();
            List<Frases> frasesListNew = cuento.getFrasesList();
            List<String> illegalOrphanMessages = null;
            for (Frases frasesListOldFrases : frasesListOld) {
                if (!frasesListNew.contains(frasesListOldFrases)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Frases " + frasesListOldFrases + " since its cuento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (generoGeneroNew != null) {
                generoGeneroNew = em.getReference(generoGeneroNew.getClass(), generoGeneroNew.getGenero());
                cuento.setGeneroGenero(generoGeneroNew);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaListNew = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferenciaToAttach : perfilPreferenciaListNew) {
                perfilPreferenciaListNewPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListNewPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListNewPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaListNew.add(perfilPreferenciaListNewPerfilPreferenciaToAttach);
            }
            perfilPreferenciaListNew = attachedPerfilPreferenciaListNew;
            cuento.setPerfilPreferenciaList(perfilPreferenciaListNew);
            List<Frases> attachedFrasesListNew = new ArrayList<Frases>();
            for (Frases frasesListNewFrasesToAttach : frasesListNew) {
                frasesListNewFrasesToAttach = em.getReference(frasesListNewFrasesToAttach.getClass(), frasesListNewFrasesToAttach.getFrasesPK());
                attachedFrasesListNew.add(frasesListNewFrasesToAttach);
            }
            frasesListNew = attachedFrasesListNew;
            cuento.setFrasesList(frasesListNew);
            cuento = em.merge(cuento);
            if (generoGeneroOld != null && !generoGeneroOld.equals(generoGeneroNew)) {
                generoGeneroOld.getCuentoList().remove(cuento);
                generoGeneroOld = em.merge(generoGeneroOld);
            }
            if (generoGeneroNew != null && !generoGeneroNew.equals(generoGeneroOld)) {
                generoGeneroNew.getCuentoList().add(cuento);
                generoGeneroNew = em.merge(generoGeneroNew);
            }
            for (PerfilPreferencia perfilPreferenciaListOldPerfilPreferencia : perfilPreferenciaListOld) {
                if (!perfilPreferenciaListNew.contains(perfilPreferenciaListOldPerfilPreferencia)) {
                    perfilPreferenciaListOldPerfilPreferencia.getCuentoList().remove(cuento);
                    perfilPreferenciaListOldPerfilPreferencia = em.merge(perfilPreferenciaListOldPerfilPreferencia);
                }
            }
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferencia : perfilPreferenciaListNew) {
                if (!perfilPreferenciaListOld.contains(perfilPreferenciaListNewPerfilPreferencia)) {
                    perfilPreferenciaListNewPerfilPreferencia.getCuentoList().add(cuento);
                    perfilPreferenciaListNewPerfilPreferencia = em.merge(perfilPreferenciaListNewPerfilPreferencia);
                }
            }
            for (Frases frasesListNewFrases : frasesListNew) {
                if (!frasesListOld.contains(frasesListNewFrases)) {
                    Cuento oldCuentoOfFrasesListNewFrases = frasesListNewFrases.getCuento();
                    frasesListNewFrases.setCuento(cuento);
                    frasesListNewFrases = em.merge(frasesListNewFrases);
                    if (oldCuentoOfFrasesListNewFrases != null && !oldCuentoOfFrasesListNewFrases.equals(cuento)) {
                        oldCuentoOfFrasesListNewFrases.getFrasesList().remove(frasesListNewFrases);
                        oldCuentoOfFrasesListNewFrases = em.merge(oldCuentoOfFrasesListNewFrases);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cuento.getNombre();
                if (findCuento(id) == null) {
                    throw new NonexistentEntityException("The cuento with id " + id + " no longer exists.");
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
            Cuento cuento;
            try {
                cuento = em.getReference(Cuento.class, id);
                cuento.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Frases> frasesListOrphanCheck = cuento.getFrasesList();
            for (Frases frasesListOrphanCheckFrases : frasesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cuento (" + cuento + ") cannot be destroyed since the Frases " + frasesListOrphanCheckFrases + " in its frasesList field has a non-nullable cuento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Genero generoGenero = cuento.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero.getCuentoList().remove(cuento);
                generoGenero = em.merge(generoGenero);
            }
            List<PerfilPreferencia> perfilPreferenciaList = cuento.getPerfilPreferenciaList();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : perfilPreferenciaList) {
                perfilPreferenciaListPerfilPreferencia.getCuentoList().remove(cuento);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.remove(cuento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuento> findCuentoEntities() {
        return findCuentoEntities(true, -1, -1);
    }

    public List<Cuento> findCuentoEntities(int maxResults, int firstResult) {
        return findCuentoEntities(false, maxResults, firstResult);
    }

    private List<Cuento> findCuentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuento.class));
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

    public Cuento findCuento(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuento.class, id);
        } finally {
            em.close();
        }
    }

    public int getCuentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuento> rt = cq.from(Cuento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
