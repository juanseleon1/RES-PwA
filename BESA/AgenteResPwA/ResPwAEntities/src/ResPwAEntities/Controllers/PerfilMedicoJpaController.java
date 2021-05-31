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
import ResPwAEntities.Cdr;
import ResPwAEntities.Causademencia;
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.Actividadrutinaria;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilMedico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class PerfilMedicoJpaController implements Serializable {

    public PerfilMedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerfilMedico perfilMedico) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (perfilMedico.getActividadrutinariaList() == null) {
            perfilMedico.setActividadrutinariaList(new ArrayList<Actividadrutinaria>());
        }
        List<String> illegalOrphanMessages = null;
        Perfilpwa perfilpwaOrphanCheck = perfilMedico.getPerfilpwa();
        if (perfilpwaOrphanCheck != null) {
            PerfilMedico oldPerfilMedicoOfPerfilpwa = perfilpwaOrphanCheck.getPerfilMedico();
            if (oldPerfilMedicoOfPerfilpwa != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Perfilpwa " + perfilpwaOrphanCheck + " already has an item of type PerfilMedico whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cdr cdr = perfilMedico.getCdr();
            if (cdr != null) {
                cdr = em.getReference(cdr.getClass(), cdr.getPerfilMedicoCedula());
                perfilMedico.setCdr(cdr);
            }
            Causademencia causademenciaCondicion = perfilMedico.getCausademenciaCondicion();
            if (causademenciaCondicion != null) {
                causademenciaCondicion = em.getReference(causademenciaCondicion.getClass(), causademenciaCondicion.getCondicion());
                perfilMedico.setCausademenciaCondicion(causademenciaCondicion);
            }
            Perfilpwa perfilpwa = perfilMedico.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa = em.getReference(perfilpwa.getClass(), perfilpwa.getCedula());
                perfilMedico.setPerfilpwa(perfilpwa);
            }
            List<Actividadrutinaria> attachedActividadrutinariaList = new ArrayList<Actividadrutinaria>();
            for (Actividadrutinaria actividadrutinariaListActividadrutinariaToAttach : perfilMedico.getActividadrutinariaList()) {
                actividadrutinariaListActividadrutinariaToAttach = em.getReference(actividadrutinariaListActividadrutinariaToAttach.getClass(), actividadrutinariaListActividadrutinariaToAttach.getId());
                attachedActividadrutinariaList.add(actividadrutinariaListActividadrutinariaToAttach);
            }
            perfilMedico.setActividadrutinariaList(attachedActividadrutinariaList);
            em.persist(perfilMedico);
            if (cdr != null) {
                PerfilMedico oldPerfilMedicoOfCdr = cdr.getPerfilMedico();
                if (oldPerfilMedicoOfCdr != null) {
                    oldPerfilMedicoOfCdr.setCdr(null);
                    oldPerfilMedicoOfCdr = em.merge(oldPerfilMedicoOfCdr);
                }
                cdr.setPerfilMedico(perfilMedico);
                cdr = em.merge(cdr);
            }
            if (causademenciaCondicion != null) {
                causademenciaCondicion.getPerfilMedicoList().add(perfilMedico);
                causademenciaCondicion = em.merge(causademenciaCondicion);
            }
            if (perfilpwa != null) {
                perfilpwa.setPerfilMedico(perfilMedico);
                perfilpwa = em.merge(perfilpwa);
            }
            for (Actividadrutinaria actividadrutinariaListActividadrutinaria : perfilMedico.getActividadrutinariaList()) {
                PerfilMedico oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListActividadrutinaria = actividadrutinariaListActividadrutinaria.getPerfilMedicoPerfilpwaCedula();
                actividadrutinariaListActividadrutinaria.setPerfilMedicoPerfilpwaCedula(perfilMedico);
                actividadrutinariaListActividadrutinaria = em.merge(actividadrutinariaListActividadrutinaria);
                if (oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListActividadrutinaria != null) {
                    oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListActividadrutinaria.getActividadrutinariaList().remove(actividadrutinariaListActividadrutinaria);
                    oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListActividadrutinaria = em.merge(oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListActividadrutinaria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilMedico(perfilMedico.getPerfilpwaCedula()) != null) {
                throw new PreexistingEntityException("PerfilMedico " + perfilMedico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerfilMedico perfilMedico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilMedico persistentPerfilMedico = em.find(PerfilMedico.class, perfilMedico.getPerfilpwaCedula());
            Cdr cdrOld = persistentPerfilMedico.getCdr();
            Cdr cdrNew = perfilMedico.getCdr();
            Causademencia causademenciaCondicionOld = persistentPerfilMedico.getCausademenciaCondicion();
            Causademencia causademenciaCondicionNew = perfilMedico.getCausademenciaCondicion();
            Perfilpwa perfilpwaOld = persistentPerfilMedico.getPerfilpwa();
            Perfilpwa perfilpwaNew = perfilMedico.getPerfilpwa();
            List<Actividadrutinaria> actividadrutinariaListOld = persistentPerfilMedico.getActividadrutinariaList();
            List<Actividadrutinaria> actividadrutinariaListNew = perfilMedico.getActividadrutinariaList();
            List<String> illegalOrphanMessages = null;
            if (cdrOld != null && !cdrOld.equals(cdrNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Cdr " + cdrOld + " since its perfilMedico field is not nullable.");
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                PerfilMedico oldPerfilMedicoOfPerfilpwa = perfilpwaNew.getPerfilMedico();
                if (oldPerfilMedicoOfPerfilpwa != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Perfilpwa " + perfilpwaNew + " already has an item of type PerfilMedico whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cdrNew != null) {
                cdrNew = em.getReference(cdrNew.getClass(), cdrNew.getPerfilMedicoCedula());
                perfilMedico.setCdr(cdrNew);
            }
            if (causademenciaCondicionNew != null) {
                causademenciaCondicionNew = em.getReference(causademenciaCondicionNew.getClass(), causademenciaCondicionNew.getCondicion());
                perfilMedico.setCausademenciaCondicion(causademenciaCondicionNew);
            }
            if (perfilpwaNew != null) {
                perfilpwaNew = em.getReference(perfilpwaNew.getClass(), perfilpwaNew.getCedula());
                perfilMedico.setPerfilpwa(perfilpwaNew);
            }
            List<Actividadrutinaria> attachedActividadrutinariaListNew = new ArrayList<Actividadrutinaria>();
            for (Actividadrutinaria actividadrutinariaListNewActividadrutinariaToAttach : actividadrutinariaListNew) {
                actividadrutinariaListNewActividadrutinariaToAttach = em.getReference(actividadrutinariaListNewActividadrutinariaToAttach.getClass(), actividadrutinariaListNewActividadrutinariaToAttach.getId());
                attachedActividadrutinariaListNew.add(actividadrutinariaListNewActividadrutinariaToAttach);
            }
            actividadrutinariaListNew = attachedActividadrutinariaListNew;
            perfilMedico.setActividadrutinariaList(actividadrutinariaListNew);
            perfilMedico = em.merge(perfilMedico);
            if (cdrNew != null && !cdrNew.equals(cdrOld)) {
                PerfilMedico oldPerfilMedicoOfCdr = cdrNew.getPerfilMedico();
                if (oldPerfilMedicoOfCdr != null) {
                    oldPerfilMedicoOfCdr.setCdr(null);
                    oldPerfilMedicoOfCdr = em.merge(oldPerfilMedicoOfCdr);
                }
                cdrNew.setPerfilMedico(perfilMedico);
                cdrNew = em.merge(cdrNew);
            }
            if (causademenciaCondicionOld != null && !causademenciaCondicionOld.equals(causademenciaCondicionNew)) {
                causademenciaCondicionOld.getPerfilMedicoList().remove(perfilMedico);
                causademenciaCondicionOld = em.merge(causademenciaCondicionOld);
            }
            if (causademenciaCondicionNew != null && !causademenciaCondicionNew.equals(causademenciaCondicionOld)) {
                causademenciaCondicionNew.getPerfilMedicoList().add(perfilMedico);
                causademenciaCondicionNew = em.merge(causademenciaCondicionNew);
            }
            if (perfilpwaOld != null && !perfilpwaOld.equals(perfilpwaNew)) {
                perfilpwaOld.setPerfilMedico(null);
                perfilpwaOld = em.merge(perfilpwaOld);
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                perfilpwaNew.setPerfilMedico(perfilMedico);
                perfilpwaNew = em.merge(perfilpwaNew);
            }
            for (Actividadrutinaria actividadrutinariaListOldActividadrutinaria : actividadrutinariaListOld) {
                if (!actividadrutinariaListNew.contains(actividadrutinariaListOldActividadrutinaria)) {
                    actividadrutinariaListOldActividadrutinaria.setPerfilMedicoPerfilpwaCedula(null);
                    actividadrutinariaListOldActividadrutinaria = em.merge(actividadrutinariaListOldActividadrutinaria);
                }
            }
            for (Actividadrutinaria actividadrutinariaListNewActividadrutinaria : actividadrutinariaListNew) {
                if (!actividadrutinariaListOld.contains(actividadrutinariaListNewActividadrutinaria)) {
                    PerfilMedico oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria = actividadrutinariaListNewActividadrutinaria.getPerfilMedicoPerfilpwaCedula();
                    actividadrutinariaListNewActividadrutinaria.setPerfilMedicoPerfilpwaCedula(perfilMedico);
                    actividadrutinariaListNewActividadrutinaria = em.merge(actividadrutinariaListNewActividadrutinaria);
                    if (oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria != null && !oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria.equals(perfilMedico)) {
                        oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria.getActividadrutinariaList().remove(actividadrutinariaListNewActividadrutinaria);
                        oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria = em.merge(oldPerfilMedicoPerfilpwaCedulaOfActividadrutinariaListNewActividadrutinaria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = perfilMedico.getPerfilpwaCedula();
                if (findPerfilMedico(id) == null) {
                    throw new NonexistentEntityException("The perfilMedico with id " + id + " no longer exists.");
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
            PerfilMedico perfilMedico;
            try {
                perfilMedico = em.getReference(PerfilMedico.class, id);
                perfilMedico.getPerfilpwaCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilMedico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Cdr cdrOrphanCheck = perfilMedico.getCdr();
            if (cdrOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilMedico (" + perfilMedico + ") cannot be destroyed since the Cdr " + cdrOrphanCheck + " in its cdr field has a non-nullable perfilMedico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Causademencia causademenciaCondicion = perfilMedico.getCausademenciaCondicion();
            if (causademenciaCondicion != null) {
                causademenciaCondicion.getPerfilMedicoList().remove(perfilMedico);
                causademenciaCondicion = em.merge(causademenciaCondicion);
            }
            Perfilpwa perfilpwa = perfilMedico.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa.setPerfilMedico(null);
                perfilpwa = em.merge(perfilpwa);
            }
            List<Actividadrutinaria> actividadrutinariaList = perfilMedico.getActividadrutinariaList();
            for (Actividadrutinaria actividadrutinariaListActividadrutinaria : actividadrutinariaList) {
                actividadrutinariaListActividadrutinaria.setPerfilMedicoPerfilpwaCedula(null);
                actividadrutinariaListActividadrutinaria = em.merge(actividadrutinariaListActividadrutinaria);
            }
            em.remove(perfilMedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerfilMedico> findPerfilMedicoEntities() {
        return findPerfilMedicoEntities(true, -1, -1);
    }

    public List<PerfilMedico> findPerfilMedicoEntities(int maxResults, int firstResult) {
        return findPerfilMedicoEntities(false, maxResults, firstResult);
    }

    private List<PerfilMedico> findPerfilMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerfilMedico.class));
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

    public PerfilMedico findPerfilMedico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerfilMedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerfilMedico> rt = cq.from(PerfilMedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
