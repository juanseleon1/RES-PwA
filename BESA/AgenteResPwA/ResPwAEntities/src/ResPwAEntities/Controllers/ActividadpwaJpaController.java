/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Dificultad;
import ResPwAEntities.PerfilPreferencia;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Registroactividad;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
 */
public class ActividadpwaJpaController implements Serializable {

    public ActividadpwaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividadpwa actividadpwa) throws PreexistingEntityException, Exception {
        if (actividadpwa.getPerfilPreferenciaList() == null) {
            actividadpwa.setPerfilPreferenciaList(new ArrayList<PerfilPreferencia>());
        }
        if (actividadpwa.getRegistroactividadList() == null) {
            actividadpwa.setRegistroactividadList(new ArrayList<Registroactividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dificultad dificultadDificultad = actividadpwa.getDificultadDificultad();
            if (dificultadDificultad != null) {
                dificultadDificultad = em.getReference(dificultadDificultad.getClass(), dificultadDificultad.getDificultad());
                actividadpwa.setDificultadDificultad(dificultadDificultad);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaList = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferenciaToAttach : actividadpwa.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaList.add(perfilPreferenciaListPerfilPreferenciaToAttach);
            }
            actividadpwa.setPerfilPreferenciaList(attachedPerfilPreferenciaList);
            List<Registroactividad> attachedRegistroactividadList = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListRegistroactividadToAttach : actividadpwa.getRegistroactividadList()) {
                registroactividadListRegistroactividadToAttach = em.getReference(registroactividadListRegistroactividadToAttach.getClass(), registroactividadListRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadList.add(registroactividadListRegistroactividadToAttach);
            }
            actividadpwa.setRegistroactividadList(attachedRegistroactividadList);
            em.persist(actividadpwa);
            if (dificultadDificultad != null) {
                dificultadDificultad.getActividadpwaList().add(actividadpwa);
                dificultadDificultad = em.merge(dificultadDificultad);
            }
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : actividadpwa.getPerfilPreferenciaList()) {
                perfilPreferenciaListPerfilPreferencia.getActividadpwaList().add(actividadpwa);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            for (Registroactividad registroactividadListRegistroactividad : actividadpwa.getRegistroactividadList()) {
                Actividadpwa oldActividadpwaOfRegistroactividadListRegistroactividad = registroactividadListRegistroactividad.getActividadpwa();
                registroactividadListRegistroactividad.setActividadpwa(actividadpwa);
                registroactividadListRegistroactividad = em.merge(registroactividadListRegistroactividad);
                if (oldActividadpwaOfRegistroactividadListRegistroactividad != null) {
                    oldActividadpwaOfRegistroactividadListRegistroactividad.getRegistroactividadList().remove(registroactividadListRegistroactividad);
                    oldActividadpwaOfRegistroactividadListRegistroactividad = em.merge(oldActividadpwaOfRegistroactividadListRegistroactividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findActividadpwa(actividadpwa.getId()) != null) {
                throw new PreexistingEntityException("Actividadpwa " + actividadpwa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividadpwa actividadpwa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa persistentActividadpwa = em.find(Actividadpwa.class, actividadpwa.getId());
            Dificultad dificultadDificultadOld = persistentActividadpwa.getDificultadDificultad();
            Dificultad dificultadDificultadNew = actividadpwa.getDificultadDificultad();
            List<PerfilPreferencia> perfilPreferenciaListOld = persistentActividadpwa.getPerfilPreferenciaList();
            List<PerfilPreferencia> perfilPreferenciaListNew = actividadpwa.getPerfilPreferenciaList();
            List<Registroactividad> registroactividadListOld = persistentActividadpwa.getRegistroactividadList();
            List<Registroactividad> registroactividadListNew = actividadpwa.getRegistroactividadList();
            List<String> illegalOrphanMessages = null;
            for (Registroactividad registroactividadListOldRegistroactividad : registroactividadListOld) {
                if (!registroactividadListNew.contains(registroactividadListOldRegistroactividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroactividad " + registroactividadListOldRegistroactividad + " since its actividadpwa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (dificultadDificultadNew != null) {
                dificultadDificultadNew = em.getReference(dificultadDificultadNew.getClass(), dificultadDificultadNew.getDificultad());
                actividadpwa.setDificultadDificultad(dificultadDificultadNew);
            }
            List<PerfilPreferencia> attachedPerfilPreferenciaListNew = new ArrayList<PerfilPreferencia>();
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferenciaToAttach : perfilPreferenciaListNew) {
                perfilPreferenciaListNewPerfilPreferenciaToAttach = em.getReference(perfilPreferenciaListNewPerfilPreferenciaToAttach.getClass(), perfilPreferenciaListNewPerfilPreferenciaToAttach.getPerfilpwaCedula());
                attachedPerfilPreferenciaListNew.add(perfilPreferenciaListNewPerfilPreferenciaToAttach);
            }
            perfilPreferenciaListNew = attachedPerfilPreferenciaListNew;
            actividadpwa.setPerfilPreferenciaList(perfilPreferenciaListNew);
            List<Registroactividad> attachedRegistroactividadListNew = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListNewRegistroactividadToAttach : registroactividadListNew) {
                registroactividadListNewRegistroactividadToAttach = em.getReference(registroactividadListNewRegistroactividadToAttach.getClass(), registroactividadListNewRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadListNew.add(registroactividadListNewRegistroactividadToAttach);
            }
            registroactividadListNew = attachedRegistroactividadListNew;
            actividadpwa.setRegistroactividadList(registroactividadListNew);
            actividadpwa = em.merge(actividadpwa);
            if (dificultadDificultadOld != null && !dificultadDificultadOld.equals(dificultadDificultadNew)) {
                dificultadDificultadOld.getActividadpwaList().remove(actividadpwa);
                dificultadDificultadOld = em.merge(dificultadDificultadOld);
            }
            if (dificultadDificultadNew != null && !dificultadDificultadNew.equals(dificultadDificultadOld)) {
                dificultadDificultadNew.getActividadpwaList().add(actividadpwa);
                dificultadDificultadNew = em.merge(dificultadDificultadNew);
            }
            for (PerfilPreferencia perfilPreferenciaListOldPerfilPreferencia : perfilPreferenciaListOld) {
                if (!perfilPreferenciaListNew.contains(perfilPreferenciaListOldPerfilPreferencia)) {
                    perfilPreferenciaListOldPerfilPreferencia.getActividadpwaList().remove(actividadpwa);
                    perfilPreferenciaListOldPerfilPreferencia = em.merge(perfilPreferenciaListOldPerfilPreferencia);
                }
            }
            for (PerfilPreferencia perfilPreferenciaListNewPerfilPreferencia : perfilPreferenciaListNew) {
                if (!perfilPreferenciaListOld.contains(perfilPreferenciaListNewPerfilPreferencia)) {
                    perfilPreferenciaListNewPerfilPreferencia.getActividadpwaList().add(actividadpwa);
                    perfilPreferenciaListNewPerfilPreferencia = em.merge(perfilPreferenciaListNewPerfilPreferencia);
                }
            }
            for (Registroactividad registroactividadListNewRegistroactividad : registroactividadListNew) {
                if (!registroactividadListOld.contains(registroactividadListNewRegistroactividad)) {
                    Actividadpwa oldActividadpwaOfRegistroactividadListNewRegistroactividad = registroactividadListNewRegistroactividad.getActividadpwa();
                    registroactividadListNewRegistroactividad.setActividadpwa(actividadpwa);
                    registroactividadListNewRegistroactividad = em.merge(registroactividadListNewRegistroactividad);
                    if (oldActividadpwaOfRegistroactividadListNewRegistroactividad != null && !oldActividadpwaOfRegistroactividadListNewRegistroactividad.equals(actividadpwa)) {
                        oldActividadpwaOfRegistroactividadListNewRegistroactividad.getRegistroactividadList().remove(registroactividadListNewRegistroactividad);
                        oldActividadpwaOfRegistroactividadListNewRegistroactividad = em.merge(oldActividadpwaOfRegistroactividadListNewRegistroactividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigInteger id = actividadpwa.getId();
                if (findActividadpwa(id) == null) {
                    throw new NonexistentEntityException("The actividadpwa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividadpwa actividadpwa;
            try {
                actividadpwa = em.getReference(Actividadpwa.class, id);
                actividadpwa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividadpwa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Registroactividad> registroactividadListOrphanCheck = actividadpwa.getRegistroactividadList();
            for (Registroactividad registroactividadListOrphanCheckRegistroactividad : registroactividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividadpwa (" + actividadpwa + ") cannot be destroyed since the Registroactividad " + registroactividadListOrphanCheckRegistroactividad + " in its registroactividadList field has a non-nullable actividadpwa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Dificultad dificultadDificultad = actividadpwa.getDificultadDificultad();
            if (dificultadDificultad != null) {
                dificultadDificultad.getActividadpwaList().remove(actividadpwa);
                dificultadDificultad = em.merge(dificultadDificultad);
            }
            List<PerfilPreferencia> perfilPreferenciaList = actividadpwa.getPerfilPreferenciaList();
            for (PerfilPreferencia perfilPreferenciaListPerfilPreferencia : perfilPreferenciaList) {
                perfilPreferenciaListPerfilPreferencia.getActividadpwaList().remove(actividadpwa);
                perfilPreferenciaListPerfilPreferencia = em.merge(perfilPreferenciaListPerfilPreferencia);
            }
            em.remove(actividadpwa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividadpwa> findActividadpwaEntities() {
        return findActividadpwaEntities(true, -1, -1);
    }

    public List<Actividadpwa> findActividadpwaEntities(int maxResults, int firstResult) {
        return findActividadpwaEntities(false, maxResults, firstResult);
    }

    private List<Actividadpwa> findActividadpwaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividadpwa.class));
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

    public Actividadpwa findActividadpwa(BigInteger id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividadpwa.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadpwaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividadpwa> rt = cq.from(Actividadpwa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
