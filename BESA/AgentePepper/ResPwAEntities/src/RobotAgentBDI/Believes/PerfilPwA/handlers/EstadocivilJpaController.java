/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.PerfilPwA.handlers;

import RobotAgentBDI.Believes.PerfilPwA.Estadocivil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import RobotAgentBDI.Believes.PerfilPwA.Perfilpwa;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.NonexistentEntityException;
import RobotAgentBDI.Believes.PerfilPwA.handlers.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class EstadocivilJpaController implements Serializable {

    public EstadocivilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocivil estadocivil) throws PreexistingEntityException, Exception {
        if (estadocivil.getPerfilpwaList() == null) {
            estadocivil.setPerfilpwaList(new ArrayList<Perfilpwa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Perfilpwa> attachedPerfilpwaList = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListPerfilpwaToAttach : estadocivil.getPerfilpwaList()) {
                perfilpwaListPerfilpwaToAttach = em.getReference(perfilpwaListPerfilpwaToAttach.getClass(), perfilpwaListPerfilpwaToAttach.getCedula());
                attachedPerfilpwaList.add(perfilpwaListPerfilpwaToAttach);
            }
            estadocivil.setPerfilpwaList(attachedPerfilpwaList);
            em.persist(estadocivil);
            for (Perfilpwa perfilpwaListPerfilpwa : estadocivil.getPerfilpwaList()) {
                Estadocivil oldEstadocivilTipoestadoOfPerfilpwaListPerfilpwa = perfilpwaListPerfilpwa.getEstadocivilTipoestado();
                perfilpwaListPerfilpwa.setEstadocivilTipoestado(estadocivil);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
                if (oldEstadocivilTipoestadoOfPerfilpwaListPerfilpwa != null) {
                    oldEstadocivilTipoestadoOfPerfilpwaListPerfilpwa.getPerfilpwaList().remove(perfilpwaListPerfilpwa);
                    oldEstadocivilTipoestadoOfPerfilpwaListPerfilpwa = em.merge(oldEstadocivilTipoestadoOfPerfilpwaListPerfilpwa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadocivil(estadocivil.getTipoestado()) != null) {
                throw new PreexistingEntityException("Estadocivil " + estadocivil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadocivil estadocivil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocivil persistentEstadocivil = em.find(Estadocivil.class, estadocivil.getTipoestado());
            List<Perfilpwa> perfilpwaListOld = persistentEstadocivil.getPerfilpwaList();
            List<Perfilpwa> perfilpwaListNew = estadocivil.getPerfilpwaList();
            List<Perfilpwa> attachedPerfilpwaListNew = new ArrayList<Perfilpwa>();
            for (Perfilpwa perfilpwaListNewPerfilpwaToAttach : perfilpwaListNew) {
                perfilpwaListNewPerfilpwaToAttach = em.getReference(perfilpwaListNewPerfilpwaToAttach.getClass(), perfilpwaListNewPerfilpwaToAttach.getCedula());
                attachedPerfilpwaListNew.add(perfilpwaListNewPerfilpwaToAttach);
            }
            perfilpwaListNew = attachedPerfilpwaListNew;
            estadocivil.setPerfilpwaList(perfilpwaListNew);
            estadocivil = em.merge(estadocivil);
            for (Perfilpwa perfilpwaListOldPerfilpwa : perfilpwaListOld) {
                if (!perfilpwaListNew.contains(perfilpwaListOldPerfilpwa)) {
                    perfilpwaListOldPerfilpwa.setEstadocivilTipoestado(null);
                    perfilpwaListOldPerfilpwa = em.merge(perfilpwaListOldPerfilpwa);
                }
            }
            for (Perfilpwa perfilpwaListNewPerfilpwa : perfilpwaListNew) {
                if (!perfilpwaListOld.contains(perfilpwaListNewPerfilpwa)) {
                    Estadocivil oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa = perfilpwaListNewPerfilpwa.getEstadocivilTipoestado();
                    perfilpwaListNewPerfilpwa.setEstadocivilTipoestado(estadocivil);
                    perfilpwaListNewPerfilpwa = em.merge(perfilpwaListNewPerfilpwa);
                    if (oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa != null && !oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa.equals(estadocivil)) {
                        oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa.getPerfilpwaList().remove(perfilpwaListNewPerfilpwa);
                        oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa = em.merge(oldEstadocivilTipoestadoOfPerfilpwaListNewPerfilpwa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estadocivil.getTipoestado();
                if (findEstadocivil(id) == null) {
                    throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.");
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
            Estadocivil estadocivil;
            try {
                estadocivil = em.getReference(Estadocivil.class, id);
                estadocivil.getTipoestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.", enfe);
            }
            List<Perfilpwa> perfilpwaList = estadocivil.getPerfilpwaList();
            for (Perfilpwa perfilpwaListPerfilpwa : perfilpwaList) {
                perfilpwaListPerfilpwa.setEstadocivilTipoestado(null);
                perfilpwaListPerfilpwa = em.merge(perfilpwaListPerfilpwa);
            }
            em.remove(estadocivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadocivil> findEstadocivilEntities() {
        return findEstadocivilEntities(true, -1, -1);
    }

    public List<Estadocivil> findEstadocivilEntities(int maxResults, int firstResult) {
        return findEstadocivilEntities(false, maxResults, firstResult);
    }

    private List<Estadocivil> findEstadocivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadocivil.class));
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

    public Estadocivil findEstadocivil(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadocivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadocivilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadocivil> rt = cq.from(Estadocivil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
