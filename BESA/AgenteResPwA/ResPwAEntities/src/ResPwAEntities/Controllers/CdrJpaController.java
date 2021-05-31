/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Cdr;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.PerfilMedico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class CdrJpaController implements Serializable {

    public CdrJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cdr cdr) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        PerfilMedico perfilMedicoOrphanCheck = cdr.getPerfilMedico();
        if (perfilMedicoOrphanCheck != null) {
            Cdr oldCdrOfPerfilMedico = perfilMedicoOrphanCheck.getCdr();
            if (oldCdrOfPerfilMedico != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The PerfilMedico " + perfilMedicoOrphanCheck + " already has an item of type Cdr whose perfilMedico column cannot be null. Please make another selection for the perfilMedico field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilMedico perfilMedico = cdr.getPerfilMedico();
            if (perfilMedico != null) {
                perfilMedico = em.getReference(perfilMedico.getClass(), perfilMedico.getPerfilpwaCedula());
                cdr.setPerfilMedico(perfilMedico);
            }
            em.persist(cdr);
            if (perfilMedico != null) {
                perfilMedico.setCdr(cdr);
                perfilMedico = em.merge(perfilMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCdr(cdr.getPerfilMedicoCedula()) != null) {
                throw new PreexistingEntityException("Cdr " + cdr + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cdr cdr) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cdr persistentCdr = em.find(Cdr.class, cdr.getPerfilMedicoCedula());
            PerfilMedico perfilMedicoOld = persistentCdr.getPerfilMedico();
            PerfilMedico perfilMedicoNew = cdr.getPerfilMedico();
            List<String> illegalOrphanMessages = null;
            if (perfilMedicoNew != null && !perfilMedicoNew.equals(perfilMedicoOld)) {
                Cdr oldCdrOfPerfilMedico = perfilMedicoNew.getCdr();
                if (oldCdrOfPerfilMedico != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The PerfilMedico " + perfilMedicoNew + " already has an item of type Cdr whose perfilMedico column cannot be null. Please make another selection for the perfilMedico field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perfilMedicoNew != null) {
                perfilMedicoNew = em.getReference(perfilMedicoNew.getClass(), perfilMedicoNew.getPerfilpwaCedula());
                cdr.setPerfilMedico(perfilMedicoNew);
            }
            cdr = em.merge(cdr);
            if (perfilMedicoOld != null && !perfilMedicoOld.equals(perfilMedicoNew)) {
                perfilMedicoOld.setCdr(null);
                perfilMedicoOld = em.merge(perfilMedicoOld);
            }
            if (perfilMedicoNew != null && !perfilMedicoNew.equals(perfilMedicoOld)) {
                perfilMedicoNew.setCdr(cdr);
                perfilMedicoNew = em.merge(perfilMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cdr.getPerfilMedicoCedula();
                if (findCdr(id) == null) {
                    throw new NonexistentEntityException("The cdr with id " + id + " no longer exists.");
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
            Cdr cdr;
            try {
                cdr = em.getReference(Cdr.class, id);
                cdr.getPerfilMedicoCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cdr with id " + id + " no longer exists.", enfe);
            }
            PerfilMedico perfilMedico = cdr.getPerfilMedico();
            if (perfilMedico != null) {
                perfilMedico.setCdr(null);
                perfilMedico = em.merge(perfilMedico);
            }
            em.remove(cdr);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cdr> findCdrEntities() {
        return findCdrEntities(true, -1, -1);
    }

    public List<Cdr> findCdrEntities(int maxResults, int firstResult) {
        return findCdrEntities(false, maxResults, firstResult);
    }

    private List<Cdr> findCdrEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cdr.class));
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

    public Cdr findCdr(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cdr.class, id);
        } finally {
            em.close();
        }
    }

    public int getCdrCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cdr> rt = cq.from(Cdr.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
