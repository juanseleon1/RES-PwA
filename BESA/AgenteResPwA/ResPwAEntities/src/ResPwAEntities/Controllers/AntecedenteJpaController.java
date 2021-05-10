/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Antecedente;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class AntecedenteJpaController implements Serializable {

    public AntecedenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Antecedente antecedente) throws PreexistingEntityException, Exception {
        if (antecedente.getReglaList() == null) {
            antecedente.setReglaList(new ArrayList<Regla>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Regla> attachedReglaList = new ArrayList<Regla>();
            for (Regla reglaListReglaToAttach : antecedente.getReglaList()) {
                reglaListReglaToAttach = em.getReference(reglaListReglaToAttach.getClass(), reglaListReglaToAttach.getId());
                attachedReglaList.add(reglaListReglaToAttach);
            }
            antecedente.setReglaList(attachedReglaList);
            em.persist(antecedente);
            for (Regla reglaListRegla : antecedente.getReglaList()) {
                reglaListRegla.getAntecedenteList().add(antecedente);
                reglaListRegla = em.merge(reglaListRegla);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAntecedente(antecedente.getId()) != null) {
                throw new PreexistingEntityException("Antecedente " + antecedente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Antecedente antecedente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Antecedente persistentAntecedente = em.find(Antecedente.class, antecedente.getId());
            List<Regla> reglaListOld = persistentAntecedente.getReglaList();
            List<Regla> reglaListNew = antecedente.getReglaList();
            List<Regla> attachedReglaListNew = new ArrayList<Regla>();
            for (Regla reglaListNewReglaToAttach : reglaListNew) {
                reglaListNewReglaToAttach = em.getReference(reglaListNewReglaToAttach.getClass(), reglaListNewReglaToAttach.getId());
                attachedReglaListNew.add(reglaListNewReglaToAttach);
            }
            reglaListNew = attachedReglaListNew;
            antecedente.setReglaList(reglaListNew);
            antecedente = em.merge(antecedente);
            for (Regla reglaListOldRegla : reglaListOld) {
                if (!reglaListNew.contains(reglaListOldRegla)) {
                    reglaListOldRegla.getAntecedenteList().remove(antecedente);
                    reglaListOldRegla = em.merge(reglaListOldRegla);
                }
            }
            for (Regla reglaListNewRegla : reglaListNew) {
                if (!reglaListOld.contains(reglaListNewRegla)) {
                    reglaListNewRegla.getAntecedenteList().add(antecedente);
                    reglaListNewRegla = em.merge(reglaListNewRegla);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = antecedente.getId();
                if (findAntecedente(id) == null) {
                    throw new NonexistentEntityException("The antecedente with id " + id + " no longer exists.");
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
            Antecedente antecedente;
            try {
                antecedente = em.getReference(Antecedente.class, id);
                antecedente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antecedente with id " + id + " no longer exists.", enfe);
            }
            List<Regla> reglaList = antecedente.getReglaList();
            for (Regla reglaListRegla : reglaList) {
                reglaListRegla.getAntecedenteList().remove(antecedente);
                reglaListRegla = em.merge(reglaListRegla);
            }
            em.remove(antecedente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Antecedente> findAntecedenteEntities() {
        return findAntecedenteEntities(true, -1, -1);
    }

    public List<Antecedente> findAntecedenteEntities(int maxResults, int firstResult) {
        return findAntecedenteEntities(false, maxResults, firstResult);
    }

    private List<Antecedente> findAntecedenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Antecedente.class));
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

    public Antecedente findAntecedente(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Antecedente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntecedenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Antecedente> rt = cq.from(Antecedente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
