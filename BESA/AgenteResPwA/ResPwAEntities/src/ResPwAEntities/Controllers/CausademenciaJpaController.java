/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Causademencia;
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
public class CausademenciaJpaController implements Serializable {

    public CausademenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Causademencia causademencia) throws PreexistingEntityException, Exception {
        if (causademencia.getPerfilMedicoList() == null) {
            causademencia.setPerfilMedicoList(new ArrayList<PerfilMedico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PerfilMedico> attachedPerfilMedicoList = new ArrayList<PerfilMedico>();
            for (PerfilMedico perfilMedicoListPerfilMedicoToAttach : causademencia.getPerfilMedicoList()) {
                perfilMedicoListPerfilMedicoToAttach = em.getReference(perfilMedicoListPerfilMedicoToAttach.getClass(), perfilMedicoListPerfilMedicoToAttach.getPerfilpwaCedula());
                attachedPerfilMedicoList.add(perfilMedicoListPerfilMedicoToAttach);
            }
            causademencia.setPerfilMedicoList(attachedPerfilMedicoList);
            em.persist(causademencia);
            for (PerfilMedico perfilMedicoListPerfilMedico : causademencia.getPerfilMedicoList()) {
                Causademencia oldCausademenciaCondicionOfPerfilMedicoListPerfilMedico = perfilMedicoListPerfilMedico.getCausademenciaCondicion();
                perfilMedicoListPerfilMedico.setCausademenciaCondicion(causademencia);
                perfilMedicoListPerfilMedico = em.merge(perfilMedicoListPerfilMedico);
                if (oldCausademenciaCondicionOfPerfilMedicoListPerfilMedico != null) {
                    oldCausademenciaCondicionOfPerfilMedicoListPerfilMedico.getPerfilMedicoList().remove(perfilMedicoListPerfilMedico);
                    oldCausademenciaCondicionOfPerfilMedicoListPerfilMedico = em.merge(oldCausademenciaCondicionOfPerfilMedicoListPerfilMedico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCausademencia(causademencia.getCondicion()) != null) {
                throw new PreexistingEntityException("Causademencia " + causademencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Causademencia causademencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Causademencia persistentCausademencia = em.find(Causademencia.class, causademencia.getCondicion());
            List<PerfilMedico> perfilMedicoListOld = persistentCausademencia.getPerfilMedicoList();
            List<PerfilMedico> perfilMedicoListNew = causademencia.getPerfilMedicoList();
            List<PerfilMedico> attachedPerfilMedicoListNew = new ArrayList<PerfilMedico>();
            for (PerfilMedico perfilMedicoListNewPerfilMedicoToAttach : perfilMedicoListNew) {
                perfilMedicoListNewPerfilMedicoToAttach = em.getReference(perfilMedicoListNewPerfilMedicoToAttach.getClass(), perfilMedicoListNewPerfilMedicoToAttach.getPerfilpwaCedula());
                attachedPerfilMedicoListNew.add(perfilMedicoListNewPerfilMedicoToAttach);
            }
            perfilMedicoListNew = attachedPerfilMedicoListNew;
            causademencia.setPerfilMedicoList(perfilMedicoListNew);
            causademencia = em.merge(causademencia);
            for (PerfilMedico perfilMedicoListOldPerfilMedico : perfilMedicoListOld) {
                if (!perfilMedicoListNew.contains(perfilMedicoListOldPerfilMedico)) {
                    perfilMedicoListOldPerfilMedico.setCausademenciaCondicion(null);
                    perfilMedicoListOldPerfilMedico = em.merge(perfilMedicoListOldPerfilMedico);
                }
            }
            for (PerfilMedico perfilMedicoListNewPerfilMedico : perfilMedicoListNew) {
                if (!perfilMedicoListOld.contains(perfilMedicoListNewPerfilMedico)) {
                    Causademencia oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico = perfilMedicoListNewPerfilMedico.getCausademenciaCondicion();
                    perfilMedicoListNewPerfilMedico.setCausademenciaCondicion(causademencia);
                    perfilMedicoListNewPerfilMedico = em.merge(perfilMedicoListNewPerfilMedico);
                    if (oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico != null && !oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico.equals(causademencia)) {
                        oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico.getPerfilMedicoList().remove(perfilMedicoListNewPerfilMedico);
                        oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico = em.merge(oldCausademenciaCondicionOfPerfilMedicoListNewPerfilMedico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = causademencia.getCondicion();
                if (findCausademencia(id) == null) {
                    throw new NonexistentEntityException("The causademencia with id " + id + " no longer exists.");
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
            Causademencia causademencia;
            try {
                causademencia = em.getReference(Causademencia.class, id);
                causademencia.getCondicion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The causademencia with id " + id + " no longer exists.", enfe);
            }
            List<PerfilMedico> perfilMedicoList = causademencia.getPerfilMedicoList();
            for (PerfilMedico perfilMedicoListPerfilMedico : perfilMedicoList) {
                perfilMedicoListPerfilMedico.setCausademenciaCondicion(null);
                perfilMedicoListPerfilMedico = em.merge(perfilMedicoListPerfilMedico);
            }
            em.remove(causademencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Causademencia> findCausademenciaEntities() {
        return findCausademenciaEntities(true, -1, -1);
    }

    public List<Causademencia> findCausademenciaEntities(int maxResults, int firstResult) {
        return findCausademenciaEntities(false, maxResults, firstResult);
    }

    private List<Causademencia> findCausademenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Causademencia.class));
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

    public Causademencia findCausademencia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Causademencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCausademenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Causademencia> rt = cq.from(Causademencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
