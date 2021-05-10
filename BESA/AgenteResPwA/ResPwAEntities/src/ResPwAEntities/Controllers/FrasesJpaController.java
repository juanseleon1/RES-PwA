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
import ResPwAEntities.Enriq;
import ResPwAEntities.Frases;
import ResPwAEntities.FrasesPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class FrasesJpaController implements Serializable {

    public FrasesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Frases frases) throws PreexistingEntityException, Exception {
        if (frases.getFrasesPK() == null) {
            frases.setFrasesPK(new FrasesPK());
        }
        if (frases.getEnriqList() == null) {
            frases.setEnriqList(new ArrayList<Enriq>());
        }
        frases.getFrasesPK().setCuentoNombre(frases.getCuento().getNombre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuento cuento = frases.getCuento();
            if (cuento != null) {
                cuento = em.getReference(cuento.getClass(), cuento.getNombre());
                frases.setCuento(cuento);
            }
            List<Enriq> attachedEnriqList = new ArrayList<Enriq>();
            for (Enriq enriqListEnriqToAttach : frases.getEnriqList()) {
                enriqListEnriqToAttach = em.getReference(enriqListEnriqToAttach.getClass(), enriqListEnriqToAttach.getParams());
                attachedEnriqList.add(enriqListEnriqToAttach);
            }
            frases.setEnriqList(attachedEnriqList);
            em.persist(frases);
            if (cuento != null) {
                cuento.getFrasesList().add(frases);
                cuento = em.merge(cuento);
            }
            for (Enriq enriqListEnriq : frases.getEnriqList()) {
                Frases oldFrasesOfEnriqListEnriq = enriqListEnriq.getFrases();
                enriqListEnriq.setFrases(frases);
                enriqListEnriq = em.merge(enriqListEnriq);
                if (oldFrasesOfEnriqListEnriq != null) {
                    oldFrasesOfEnriqListEnriq.getEnriqList().remove(enriqListEnriq);
                    oldFrasesOfEnriqListEnriq = em.merge(oldFrasesOfEnriqListEnriq);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFrases(frases.getFrasesPK()) != null) {
                throw new PreexistingEntityException("Frases " + frases + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Frases frases) throws NonexistentEntityException, Exception {
        frases.getFrasesPK().setCuentoNombre(frases.getCuento().getNombre());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Frases persistentFrases = em.find(Frases.class, frases.getFrasesPK());
            Cuento cuentoOld = persistentFrases.getCuento();
            Cuento cuentoNew = frases.getCuento();
            List<Enriq> enriqListOld = persistentFrases.getEnriqList();
            List<Enriq> enriqListNew = frases.getEnriqList();
            if (cuentoNew != null) {
                cuentoNew = em.getReference(cuentoNew.getClass(), cuentoNew.getNombre());
                frases.setCuento(cuentoNew);
            }
            List<Enriq> attachedEnriqListNew = new ArrayList<Enriq>();
            for (Enriq enriqListNewEnriqToAttach : enriqListNew) {
                enriqListNewEnriqToAttach = em.getReference(enriqListNewEnriqToAttach.getClass(), enriqListNewEnriqToAttach.getParams());
                attachedEnriqListNew.add(enriqListNewEnriqToAttach);
            }
            enriqListNew = attachedEnriqListNew;
            frases.setEnriqList(enriqListNew);
            frases = em.merge(frases);
            if (cuentoOld != null && !cuentoOld.equals(cuentoNew)) {
                cuentoOld.getFrasesList().remove(frases);
                cuentoOld = em.merge(cuentoOld);
            }
            if (cuentoNew != null && !cuentoNew.equals(cuentoOld)) {
                cuentoNew.getFrasesList().add(frases);
                cuentoNew = em.merge(cuentoNew);
            }
            for (Enriq enriqListOldEnriq : enriqListOld) {
                if (!enriqListNew.contains(enriqListOldEnriq)) {
                    enriqListOldEnriq.setFrases(null);
                    enriqListOldEnriq = em.merge(enriqListOldEnriq);
                }
            }
            for (Enriq enriqListNewEnriq : enriqListNew) {
                if (!enriqListOld.contains(enriqListNewEnriq)) {
                    Frases oldFrasesOfEnriqListNewEnriq = enriqListNewEnriq.getFrases();
                    enriqListNewEnriq.setFrases(frases);
                    enriqListNewEnriq = em.merge(enriqListNewEnriq);
                    if (oldFrasesOfEnriqListNewEnriq != null && !oldFrasesOfEnriqListNewEnriq.equals(frases)) {
                        oldFrasesOfEnriqListNewEnriq.getEnriqList().remove(enriqListNewEnriq);
                        oldFrasesOfEnriqListNewEnriq = em.merge(oldFrasesOfEnriqListNewEnriq);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FrasesPK id = frases.getFrasesPK();
                if (findFrases(id) == null) {
                    throw new NonexistentEntityException("The frases with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FrasesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Frases frases;
            try {
                frases = em.getReference(Frases.class, id);
                frases.getFrasesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The frases with id " + id + " no longer exists.", enfe);
            }
            Cuento cuento = frases.getCuento();
            if (cuento != null) {
                cuento.getFrasesList().remove(frases);
                cuento = em.merge(cuento);
            }
            List<Enriq> enriqList = frases.getEnriqList();
            for (Enriq enriqListEnriq : enriqList) {
                enriqListEnriq.setFrases(null);
                enriqListEnriq = em.merge(enriqListEnriq);
            }
            em.remove(frases);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Frases> findFrasesEntities() {
        return findFrasesEntities(true, -1, -1);
    }

    public List<Frases> findFrasesEntities(int maxResults, int firstResult) {
        return findFrasesEntities(false, maxResults, firstResult);
    }

    private List<Frases> findFrasesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Frases.class));
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

    public Frases findFrases(FrasesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Frases.class, id);
        } finally {
            em.close();
        }
    }

    public int getFrasesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Frases> rt = cq.from(Frases.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
