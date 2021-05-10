/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.EmotionalEntities.EmotionAxisConfig;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.EmotionalEntities.EventInfluence;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class EmotionAxisConfigJpaController implements Serializable {

    public EmotionAxisConfigJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmotionAxisConfig emotionAxisConfig) throws PreexistingEntityException, Exception {
        if (emotionAxisConfig.getEventInfluenceList() == null) {
            emotionAxisConfig.setEventInfluenceList(new ArrayList<EventInfluence>());
        }
        if (emotionAxisConfig.getEventInfluenceList() == null) {
            emotionAxisConfig.setEventInfluenceList(new ArrayList<EventInfluence>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EventInfluence> attachedEventInfluence = new ArrayList<EventInfluence>();
            for (EventInfluence eventInfluenceEventInfluenceToAttach : emotionAxisConfig.getEventInfluenceList()) {
                eventInfluenceEventInfluenceToAttach = em.getReference(eventInfluenceEventInfluenceToAttach.getClass(), eventInfluenceEventInfluenceToAttach.getId());
                attachedEventInfluence.add(eventInfluenceEventInfluenceToAttach);
            }
            emotionAxisConfig.setEventInfluenceList(attachedEventInfluence);
            List<EventInfluence> attachedEventInfluenceList = new ArrayList<EventInfluence>();
            for (EventInfluence eventInfluenceListEventInfluenceToAttach : emotionAxisConfig.getEventInfluenceList()) {
                eventInfluenceListEventInfluenceToAttach = em.getReference(eventInfluenceListEventInfluenceToAttach.getClass(), eventInfluenceListEventInfluenceToAttach.getId());
                attachedEventInfluenceList.add(eventInfluenceListEventInfluenceToAttach);
            }
            emotionAxisConfig.setEventInfluenceList(attachedEventInfluenceList);
            em.persist(emotionAxisConfig);
            for (EventInfluence eventInfluenceEventInfluence : emotionAxisConfig.getEventInfluenceList()) {
                EmotionAxisConfig oldEmotionaxisconfigIdOfEventInfluenceEventInfluence = eventInfluenceEventInfluence.getEmotionaxisconfigId();
                eventInfluenceEventInfluence.setEmotionaxisconfigId(emotionAxisConfig);
                eventInfluenceEventInfluence = em.merge(eventInfluenceEventInfluence);
                if (oldEmotionaxisconfigIdOfEventInfluenceEventInfluence != null) {
                    oldEmotionaxisconfigIdOfEventInfluenceEventInfluence.getEventInfluenceList().remove(eventInfluenceEventInfluence);
                    oldEmotionaxisconfigIdOfEventInfluenceEventInfluence = em.merge(oldEmotionaxisconfigIdOfEventInfluenceEventInfluence);
                }
            }
            for (EventInfluence eventInfluenceListEventInfluence : emotionAxisConfig.getEventInfluenceList()) {
                EmotionAxisConfig oldEmotionaxisconfigIdOfEventInfluenceListEventInfluence = eventInfluenceListEventInfluence.getEmotionaxisconfigId();
                eventInfluenceListEventInfluence.setEmotionaxisconfigId(emotionAxisConfig);
                eventInfluenceListEventInfluence = em.merge(eventInfluenceListEventInfluence);
                if (oldEmotionaxisconfigIdOfEventInfluenceListEventInfluence != null) {
                    oldEmotionaxisconfigIdOfEventInfluenceListEventInfluence.getEventInfluenceList().remove(eventInfluenceListEventInfluence);
                    oldEmotionaxisconfigIdOfEventInfluenceListEventInfluence = em.merge(oldEmotionaxisconfigIdOfEventInfluenceListEventInfluence);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmotionAxisConfig(emotionAxisConfig.getId()) != null) {
                throw new PreexistingEntityException("EmotionAxisConfig " + emotionAxisConfig + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmotionAxisConfig emotionAxisConfig) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmotionAxisConfig persistentEmotionAxisConfig = em.find(EmotionAxisConfig.class, emotionAxisConfig.getId());
            List<EventInfluence> eventInfluenceOld = persistentEmotionAxisConfig.getEventInfluenceList();
            List<EventInfluence> eventInfluenceNew = emotionAxisConfig.getEventInfluenceList();
            List<EventInfluence> eventInfluenceListOld = persistentEmotionAxisConfig.getEventInfluenceList();
            List<EventInfluence> eventInfluenceListNew = emotionAxisConfig.getEventInfluenceList();
            List<String> illegalOrphanMessages = null;
            for (EventInfluence eventInfluenceOldEventInfluence : eventInfluenceOld) {
                if (!eventInfluenceNew.contains(eventInfluenceOldEventInfluence)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EventInfluence " + eventInfluenceOldEventInfluence + " since its emotionaxisconfigId field is not nullable.");
                }
            }
            for (EventInfluence eventInfluenceListOldEventInfluence : eventInfluenceListOld) {
                if (!eventInfluenceListNew.contains(eventInfluenceListOldEventInfluence)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EventInfluence " + eventInfluenceListOldEventInfluence + " since its emotionaxisconfigId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EventInfluence> attachedEventInfluenceNew = new ArrayList<EventInfluence>();
            for (EventInfluence eventInfluenceNewEventInfluenceToAttach : eventInfluenceNew) {
                eventInfluenceNewEventInfluenceToAttach = em.getReference(eventInfluenceNewEventInfluenceToAttach.getClass(), eventInfluenceNewEventInfluenceToAttach.getId());
                attachedEventInfluenceNew.add(eventInfluenceNewEventInfluenceToAttach);
            }
            eventInfluenceNew = attachedEventInfluenceNew;
            emotionAxisConfig.setEventInfluenceList(eventInfluenceNew);
            List<EventInfluence> attachedEventInfluenceListNew = new ArrayList<EventInfluence>();
            for (EventInfluence eventInfluenceListNewEventInfluenceToAttach : eventInfluenceListNew) {
                eventInfluenceListNewEventInfluenceToAttach = em.getReference(eventInfluenceListNewEventInfluenceToAttach.getClass(), eventInfluenceListNewEventInfluenceToAttach.getId());
                attachedEventInfluenceListNew.add(eventInfluenceListNewEventInfluenceToAttach);
            }
            eventInfluenceListNew = attachedEventInfluenceListNew;
            emotionAxisConfig.setEventInfluenceList(eventInfluenceListNew);
            emotionAxisConfig = em.merge(emotionAxisConfig);
            for (EventInfluence eventInfluenceNewEventInfluence : eventInfluenceNew) {
                if (!eventInfluenceOld.contains(eventInfluenceNewEventInfluence)) {
                    EmotionAxisConfig oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence = eventInfluenceNewEventInfluence.getEmotionaxisconfigId();
                    eventInfluenceNewEventInfluence.setEmotionaxisconfigId(emotionAxisConfig);
                    eventInfluenceNewEventInfluence = em.merge(eventInfluenceNewEventInfluence);
                    if (oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence != null && !oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence.equals(emotionAxisConfig)) {
                        oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence.getEventInfluenceList().remove(eventInfluenceNewEventInfluence);
                        oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence = em.merge(oldEmotionaxisconfigIdOfEventInfluenceNewEventInfluence);
                    }
                }
            }
            for (EventInfluence eventInfluenceListNewEventInfluence : eventInfluenceListNew) {
                if (!eventInfluenceListOld.contains(eventInfluenceListNewEventInfluence)) {
                    EmotionAxisConfig oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence = eventInfluenceListNewEventInfluence.getEmotionaxisconfigId();
                    eventInfluenceListNewEventInfluence.setEmotionaxisconfigId(emotionAxisConfig);
                    eventInfluenceListNewEventInfluence = em.merge(eventInfluenceListNewEventInfluence);
                    if (oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence != null && !oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence.equals(emotionAxisConfig)) {
                        oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence.getEventInfluenceList().remove(eventInfluenceListNewEventInfluence);
                        oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence = em.merge(oldEmotionaxisconfigIdOfEventInfluenceListNewEventInfluence);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = emotionAxisConfig.getId();
                if (findEmotionAxisConfig(id) == null) {
                    throw new NonexistentEntityException("The emotionAxisConfig with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmotionAxisConfig emotionAxisConfig;
            try {
                emotionAxisConfig = em.getReference(EmotionAxisConfig.class, id);
                emotionAxisConfig.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emotionAxisConfig with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EventInfluence> eventInfluenceOrphanCheck = emotionAxisConfig.getEventInfluenceList();
            for (EventInfluence eventInfluenceOrphanCheckEventInfluence : eventInfluenceOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmotionAxisConfig (" + emotionAxisConfig + ") cannot be destroyed since the EventInfluence " + eventInfluenceOrphanCheckEventInfluence + " in its eventInfluence field has a non-nullable emotionaxisconfigId field.");
            }
            List<EventInfluence> eventInfluenceListOrphanCheck = emotionAxisConfig.getEventInfluenceList();
            for (EventInfluence eventInfluenceListOrphanCheckEventInfluence : eventInfluenceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmotionAxisConfig (" + emotionAxisConfig + ") cannot be destroyed since the EventInfluence " + eventInfluenceListOrphanCheckEventInfluence + " in its eventInfluenceList field has a non-nullable emotionaxisconfigId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(emotionAxisConfig);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmotionAxisConfig> findEmotionAxisConfigEntities() {
        return findEmotionAxisConfigEntities(true, -1, -1);
    }

    public List<EmotionAxisConfig> findEmotionAxisConfigEntities(int maxResults, int firstResult) {
        return findEmotionAxisConfigEntities(false, maxResults, firstResult);
    }

    private List<EmotionAxisConfig> findEmotionAxisConfigEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmotionAxisConfig.class));
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

    public EmotionAxisConfig findEmotionAxisConfig(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmotionAxisConfig.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmotionAxisConfigCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmotionAxisConfig> rt = cq.from(EmotionAxisConfig.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
