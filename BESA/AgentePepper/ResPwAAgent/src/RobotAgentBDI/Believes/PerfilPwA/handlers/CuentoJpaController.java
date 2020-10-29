/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA.handlers;

import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RobotAgentBDI.Believes.PerfilPwA.Genero;
import RobotAgentBDI.Believes.PerfilPwA.PerfilPreferencia;
import java.util.ArrayList;
import java.util.List;
import RobotAgentBDI.Believes.PerfilPwA.Frases;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.IllegalOrphanException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.NonexistentEntityException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.PreexistingEntityException;
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
            Genero generoNombregenero = cuento.getGeneroNombregenero();
            if (generoNombregenero != null) {
                generoNombregenero = em.getReference(generoNombregenero.getClass(), generoNombregenero.getNombregenero());
                cuento.setGeneroNombregenero(generoNombregenero);
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
            if (generoNombregenero != null) {
                generoNombregenero.getCuentoList().add(cuento);
                generoNombregenero = em.merge(generoNombregenero);
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
            if (findCuento(cuento.getNombrecuento()) != null) {
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
            Cuento persistentCuento = em.find(Cuento.class, cuento.getNombrecuento());
            Genero generoNombregeneroOld = persistentCuento.getGeneroNombregenero();
            Genero generoNombregeneroNew = cuento.getGeneroNombregenero();
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
            if (generoNombregeneroNew != null) {
                generoNombregeneroNew = em.getReference(generoNombregeneroNew.getClass(), generoNombregeneroNew.getNombregenero());
                cuento.setGeneroNombregenero(generoNombregeneroNew);
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
            if (generoNombregeneroOld != null && !generoNombregeneroOld.equals(generoNombregeneroNew)) {
                generoNombregeneroOld.getCuentoList().remove(cuento);
                generoNombregeneroOld = em.merge(generoNombregeneroOld);
            }
            if (generoNombregeneroNew != null && !generoNombregeneroNew.equals(generoNombregeneroOld)) {
                generoNombregeneroNew.getCuentoList().add(cuento);
                generoNombregeneroNew = em.merge(generoNombregeneroNew);
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
                String id = cuento.getNombrecuento();
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
                cuento.getNombrecuento();
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
            Genero generoNombregenero = cuento.getGeneroNombregenero();
            if (generoNombregenero != null) {
                generoNombregenero.getCuentoList().remove(cuento);
                generoNombregenero = em.merge(generoNombregenero);
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
