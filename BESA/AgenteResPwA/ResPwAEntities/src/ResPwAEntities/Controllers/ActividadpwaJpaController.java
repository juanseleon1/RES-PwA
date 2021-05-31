/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Actividadpwa;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Registroactividad;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
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
        if (actividadpwa.getRegistroactividadList() == null) {
            actividadpwa.setRegistroactividadList(new ArrayList<Registroactividad>());
        }
        if (actividadpwa.getActxpreferenciaList() == null) {
            actividadpwa.setActxpreferenciaList(new ArrayList<Actxpreferencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Registroactividad> attachedRegistroactividadList = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListRegistroactividadToAttach : actividadpwa.getRegistroactividadList()) {
                registroactividadListRegistroactividadToAttach = em.getReference(registroactividadListRegistroactividadToAttach.getClass(), registroactividadListRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadList.add(registroactividadListRegistroactividadToAttach);
            }
            actividadpwa.setRegistroactividadList(attachedRegistroactividadList);
            List<Actxpreferencia> attachedActxpreferenciaList = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListActxpreferenciaToAttach : actividadpwa.getActxpreferenciaList()) {
                actxpreferenciaListActxpreferenciaToAttach = em.getReference(actxpreferenciaListActxpreferenciaToAttach.getClass(), actxpreferenciaListActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaList.add(actxpreferenciaListActxpreferenciaToAttach);
            }
            actividadpwa.setActxpreferenciaList(attachedActxpreferenciaList);
            em.persist(actividadpwa);
            for (Registroactividad registroactividadListRegistroactividad : actividadpwa.getRegistroactividadList()) {
                Actividadpwa oldActividadpwaOfRegistroactividadListRegistroactividad = registroactividadListRegistroactividad.getActividadpwa();
                registroactividadListRegistroactividad.setActividadpwa(actividadpwa);
                registroactividadListRegistroactividad = em.merge(registroactividadListRegistroactividad);
                if (oldActividadpwaOfRegistroactividadListRegistroactividad != null) {
                    oldActividadpwaOfRegistroactividadListRegistroactividad.getRegistroactividadList().remove(registroactividadListRegistroactividad);
                    oldActividadpwaOfRegistroactividadListRegistroactividad = em.merge(oldActividadpwaOfRegistroactividadListRegistroactividad);
                }
            }
            for (Actxpreferencia actxpreferenciaListActxpreferencia : actividadpwa.getActxpreferenciaList()) {
                Actividadpwa oldActividadpwaOfActxpreferenciaListActxpreferencia = actxpreferenciaListActxpreferencia.getActividadpwa();
                actxpreferenciaListActxpreferencia.setActividadpwa(actividadpwa);
                actxpreferenciaListActxpreferencia = em.merge(actxpreferenciaListActxpreferencia);
                if (oldActividadpwaOfActxpreferenciaListActxpreferencia != null) {
                    oldActividadpwaOfActxpreferenciaListActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListActxpreferencia);
                    oldActividadpwaOfActxpreferenciaListActxpreferencia = em.merge(oldActividadpwaOfActxpreferenciaListActxpreferencia);
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
            List<Registroactividad> registroactividadListOld = persistentActividadpwa.getRegistroactividadList();
            List<Registroactividad> registroactividadListNew = actividadpwa.getRegistroactividadList();
            List<Actxpreferencia> actxpreferenciaListOld = persistentActividadpwa.getActxpreferenciaList();
            List<Actxpreferencia> actxpreferenciaListNew = actividadpwa.getActxpreferenciaList();
            List<String> illegalOrphanMessages = null;
            for (Registroactividad registroactividadListOldRegistroactividad : registroactividadListOld) {
                if (!registroactividadListNew.contains(registroactividadListOldRegistroactividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroactividad " + registroactividadListOldRegistroactividad + " since its actividadpwa field is not nullable.");
                }
            }
            for (Actxpreferencia actxpreferenciaListOldActxpreferencia : actxpreferenciaListOld) {
                if (!actxpreferenciaListNew.contains(actxpreferenciaListOldActxpreferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actxpreferencia " + actxpreferenciaListOldActxpreferencia + " since its actividadpwa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Registroactividad> attachedRegistroactividadListNew = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListNewRegistroactividadToAttach : registroactividadListNew) {
                registroactividadListNewRegistroactividadToAttach = em.getReference(registroactividadListNewRegistroactividadToAttach.getClass(), registroactividadListNewRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadListNew.add(registroactividadListNewRegistroactividadToAttach);
            }
            registroactividadListNew = attachedRegistroactividadListNew;
            actividadpwa.setRegistroactividadList(registroactividadListNew);
            List<Actxpreferencia> attachedActxpreferenciaListNew = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListNewActxpreferenciaToAttach : actxpreferenciaListNew) {
                actxpreferenciaListNewActxpreferenciaToAttach = em.getReference(actxpreferenciaListNewActxpreferenciaToAttach.getClass(), actxpreferenciaListNewActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaListNew.add(actxpreferenciaListNewActxpreferenciaToAttach);
            }
            actxpreferenciaListNew = attachedActxpreferenciaListNew;
            actividadpwa.setActxpreferenciaList(actxpreferenciaListNew);
            actividadpwa = em.merge(actividadpwa);
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
            for (Actxpreferencia actxpreferenciaListNewActxpreferencia : actxpreferenciaListNew) {
                if (!actxpreferenciaListOld.contains(actxpreferenciaListNewActxpreferencia)) {
                    Actividadpwa oldActividadpwaOfActxpreferenciaListNewActxpreferencia = actxpreferenciaListNewActxpreferencia.getActividadpwa();
                    actxpreferenciaListNewActxpreferencia.setActividadpwa(actividadpwa);
                    actxpreferenciaListNewActxpreferencia = em.merge(actxpreferenciaListNewActxpreferencia);
                    if (oldActividadpwaOfActxpreferenciaListNewActxpreferencia != null && !oldActividadpwaOfActxpreferenciaListNewActxpreferencia.equals(actividadpwa)) {
                        oldActividadpwaOfActxpreferenciaListNewActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListNewActxpreferencia);
                        oldActividadpwaOfActxpreferenciaListNewActxpreferencia = em.merge(oldActividadpwaOfActxpreferenciaListNewActxpreferencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = actividadpwa.getId();
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
            List<Actxpreferencia> actxpreferenciaListOrphanCheck = actividadpwa.getActxpreferenciaList();
            for (Actxpreferencia actxpreferenciaListOrphanCheckActxpreferencia : actxpreferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividadpwa (" + actividadpwa + ") cannot be destroyed since the Actxpreferencia " + actxpreferenciaListOrphanCheckActxpreferencia + " in its actxpreferenciaList field has a non-nullable actividadpwa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public Actividadpwa findActividadpwa(BigDecimal id) {
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
