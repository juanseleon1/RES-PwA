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
import ResPwAEntities.Antecedente;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.Regla;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class ReglaJpaController implements Serializable {

    public ReglaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Regla regla) throws PreexistingEntityException, Exception {
        if (regla.getAntecedenteList() == null) {
            regla.setAntecedenteList(new ArrayList<Antecedente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Antecedente> attachedAntecedenteList = new ArrayList<Antecedente>();
            for (Antecedente antecedenteListAntecedenteToAttach : regla.getAntecedenteList()) {
                antecedenteListAntecedenteToAttach = em.getReference(antecedenteListAntecedenteToAttach.getClass(), antecedenteListAntecedenteToAttach.getId());
                attachedAntecedenteList.add(antecedenteListAntecedenteToAttach);
            }
            regla.setAntecedenteList(attachedAntecedenteList);
            em.persist(regla);
            for (Antecedente antecedenteListAntecedente : regla.getAntecedenteList()) {
                antecedenteListAntecedente.getReglaList().add(regla);
                antecedenteListAntecedente = em.merge(antecedenteListAntecedente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegla(regla.getId()) != null) {
                throw new PreexistingEntityException("Regla " + regla + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Regla regla) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Regla persistentRegla = em.find(Regla.class, regla.getId());
            List<Antecedente> antecedenteListOld = persistentRegla.getAntecedenteList();
            List<Antecedente> antecedenteListNew = regla.getAntecedenteList();
            List<Antecedente> attachedAntecedenteListNew = new ArrayList<Antecedente>();
            for (Antecedente antecedenteListNewAntecedenteToAttach : antecedenteListNew) {
                antecedenteListNewAntecedenteToAttach = em.getReference(antecedenteListNewAntecedenteToAttach.getClass(), antecedenteListNewAntecedenteToAttach.getId());
                attachedAntecedenteListNew.add(antecedenteListNewAntecedenteToAttach);
            }
            antecedenteListNew = attachedAntecedenteListNew;
            regla.setAntecedenteList(antecedenteListNew);
            regla = em.merge(regla);
            for (Antecedente antecedenteListOldAntecedente : antecedenteListOld) {
                if (!antecedenteListNew.contains(antecedenteListOldAntecedente)) {
                    antecedenteListOldAntecedente.getReglaList().remove(regla);
                    antecedenteListOldAntecedente = em.merge(antecedenteListOldAntecedente);
                }
            }
            for (Antecedente antecedenteListNewAntecedente : antecedenteListNew) {
                if (!antecedenteListOld.contains(antecedenteListNewAntecedente)) {
                    antecedenteListNewAntecedente.getReglaList().add(regla);
                    antecedenteListNewAntecedente = em.merge(antecedenteListNewAntecedente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = regla.getId();
                if (findRegla(id) == null) {
                    throw new NonexistentEntityException("The regla with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Regla regla;
            try {
                regla = em.getReference(Regla.class, id);
                regla.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The regla with id " + id + " no longer exists.", enfe);
            }
            List<Antecedente> antecedenteList = regla.getAntecedenteList();
            for (Antecedente antecedenteListAntecedente : antecedenteList) {
                antecedenteListAntecedente.getReglaList().remove(regla);
                antecedenteListAntecedente = em.merge(antecedenteListAntecedente);
            }
            em.remove(regla);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Regla> findReglaEntities() {
        return findReglaEntities(true, -1, -1);
    }

    public List<Regla> findReglaEntities(int maxResults, int firstResult) {
        return findReglaEntities(false, maxResults, firstResult);
    }

    private List<Regla> findReglaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Regla.class));
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

    public Regla findRegla(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Regla.class, id);
        } finally {
            em.close();
        }
    }

    public int getReglaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Regla> rt = cq.from(Regla.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
