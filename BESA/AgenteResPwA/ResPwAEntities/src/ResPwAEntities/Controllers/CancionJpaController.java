/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Cancion;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.Genero;
import ResPwAEntities.Tags;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Enriq;
import ResPwAEntities.Preferenciaxcancion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class CancionJpaController implements Serializable {

    public CancionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cancion cancion) throws PreexistingEntityException, Exception {
        if (cancion.getTagsList() == null) {
            cancion.setTagsList(new ArrayList<Tags>());
        }
        if (cancion.getEnriqList() == null) {
            cancion.setEnriqList(new ArrayList<Enriq>());
        }
        if (cancion.getPreferenciaxcancionList() == null) {
            cancion.setPreferenciaxcancionList(new ArrayList<Preferenciaxcancion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero generoGenero = cancion.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero = em.getReference(generoGenero.getClass(), generoGenero.getGenero());
                cancion.setGeneroGenero(generoGenero);
            }
            List<Tags> attachedTagsList = new ArrayList<Tags>();
            for (Tags tagsListTagsToAttach : cancion.getTagsList()) {
                tagsListTagsToAttach = em.getReference(tagsListTagsToAttach.getClass(), tagsListTagsToAttach.getId());
                attachedTagsList.add(tagsListTagsToAttach);
            }
            cancion.setTagsList(attachedTagsList);
            List<Enriq> attachedEnriqList = new ArrayList<Enriq>();
            for (Enriq enriqListEnriqToAttach : cancion.getEnriqList()) {
                enriqListEnriqToAttach = em.getReference(enriqListEnriqToAttach.getClass(), enriqListEnriqToAttach.getParams());
                attachedEnriqList.add(enriqListEnriqToAttach);
            }
            cancion.setEnriqList(attachedEnriqList);
            List<Preferenciaxcancion> attachedPreferenciaxcancionList = new ArrayList<Preferenciaxcancion>();
            for (Preferenciaxcancion preferenciaxcancionListPreferenciaxcancionToAttach : cancion.getPreferenciaxcancionList()) {
                preferenciaxcancionListPreferenciaxcancionToAttach = em.getReference(preferenciaxcancionListPreferenciaxcancionToAttach.getClass(), preferenciaxcancionListPreferenciaxcancionToAttach.getPreferenciaxcancionPK());
                attachedPreferenciaxcancionList.add(preferenciaxcancionListPreferenciaxcancionToAttach);
            }
            cancion.setPreferenciaxcancionList(attachedPreferenciaxcancionList);
            em.persist(cancion);
            if (generoGenero != null) {
                generoGenero.getCancionList().add(cancion);
                generoGenero = em.merge(generoGenero);
            }
            for (Tags tagsListTags : cancion.getTagsList()) {
                tagsListTags.getCancionList().add(cancion);
                tagsListTags = em.merge(tagsListTags);
            }
            for (Enriq enriqListEnriq : cancion.getEnriqList()) {
                Cancion oldCancionNombreOfEnriqListEnriq = enriqListEnriq.getCancionNombre();
                enriqListEnriq.setCancionNombre(cancion);
                enriqListEnriq = em.merge(enriqListEnriq);
                if (oldCancionNombreOfEnriqListEnriq != null) {
                    oldCancionNombreOfEnriqListEnriq.getEnriqList().remove(enriqListEnriq);
                    oldCancionNombreOfEnriqListEnriq = em.merge(oldCancionNombreOfEnriqListEnriq);
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListPreferenciaxcancion : cancion.getPreferenciaxcancionList()) {
                Cancion oldCancionOfPreferenciaxcancionListPreferenciaxcancion = preferenciaxcancionListPreferenciaxcancion.getCancion();
                preferenciaxcancionListPreferenciaxcancion.setCancion(cancion);
                preferenciaxcancionListPreferenciaxcancion = em.merge(preferenciaxcancionListPreferenciaxcancion);
                if (oldCancionOfPreferenciaxcancionListPreferenciaxcancion != null) {
                    oldCancionOfPreferenciaxcancionListPreferenciaxcancion.getPreferenciaxcancionList().remove(preferenciaxcancionListPreferenciaxcancion);
                    oldCancionOfPreferenciaxcancionListPreferenciaxcancion = em.merge(oldCancionOfPreferenciaxcancionListPreferenciaxcancion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCancion(cancion.getNombre()) != null) {
                throw new PreexistingEntityException("Cancion " + cancion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cancion cancion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cancion persistentCancion = em.find(Cancion.class, cancion.getNombre());
            Genero generoGeneroOld = persistentCancion.getGeneroGenero();
            Genero generoGeneroNew = cancion.getGeneroGenero();
            List<Tags> tagsListOld = persistentCancion.getTagsList();
            List<Tags> tagsListNew = cancion.getTagsList();
            List<Enriq> enriqListOld = persistentCancion.getEnriqList();
            List<Enriq> enriqListNew = cancion.getEnriqList();
            List<Preferenciaxcancion> preferenciaxcancionListOld = persistentCancion.getPreferenciaxcancionList();
            List<Preferenciaxcancion> preferenciaxcancionListNew = cancion.getPreferenciaxcancionList();
            List<String> illegalOrphanMessages = null;
            for (Enriq enriqListOldEnriq : enriqListOld) {
                if (!enriqListNew.contains(enriqListOldEnriq)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Enriq " + enriqListOldEnriq + " since its cancionNombre field is not nullable.");
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListOldPreferenciaxcancion : preferenciaxcancionListOld) {
                if (!preferenciaxcancionListNew.contains(preferenciaxcancionListOldPreferenciaxcancion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferenciaxcancion " + preferenciaxcancionListOldPreferenciaxcancion + " since its cancion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (generoGeneroNew != null) {
                generoGeneroNew = em.getReference(generoGeneroNew.getClass(), generoGeneroNew.getGenero());
                cancion.setGeneroGenero(generoGeneroNew);
            }
            List<Tags> attachedTagsListNew = new ArrayList<Tags>();
            for (Tags tagsListNewTagsToAttach : tagsListNew) {
                tagsListNewTagsToAttach = em.getReference(tagsListNewTagsToAttach.getClass(), tagsListNewTagsToAttach.getId());
                attachedTagsListNew.add(tagsListNewTagsToAttach);
            }
            tagsListNew = attachedTagsListNew;
            cancion.setTagsList(tagsListNew);
            List<Enriq> attachedEnriqListNew = new ArrayList<Enriq>();
            for (Enriq enriqListNewEnriqToAttach : enriqListNew) {
                enriqListNewEnriqToAttach = em.getReference(enriqListNewEnriqToAttach.getClass(), enriqListNewEnriqToAttach.getParams());
                attachedEnriqListNew.add(enriqListNewEnriqToAttach);
            }
            enriqListNew = attachedEnriqListNew;
            cancion.setEnriqList(enriqListNew);
            List<Preferenciaxcancion> attachedPreferenciaxcancionListNew = new ArrayList<Preferenciaxcancion>();
            for (Preferenciaxcancion preferenciaxcancionListNewPreferenciaxcancionToAttach : preferenciaxcancionListNew) {
                preferenciaxcancionListNewPreferenciaxcancionToAttach = em.getReference(preferenciaxcancionListNewPreferenciaxcancionToAttach.getClass(), preferenciaxcancionListNewPreferenciaxcancionToAttach.getPreferenciaxcancionPK());
                attachedPreferenciaxcancionListNew.add(preferenciaxcancionListNewPreferenciaxcancionToAttach);
            }
            preferenciaxcancionListNew = attachedPreferenciaxcancionListNew;
            cancion.setPreferenciaxcancionList(preferenciaxcancionListNew);
            cancion = em.merge(cancion);
            if (generoGeneroOld != null && !generoGeneroOld.equals(generoGeneroNew)) {
                generoGeneroOld.getCancionList().remove(cancion);
                generoGeneroOld = em.merge(generoGeneroOld);
            }
            if (generoGeneroNew != null && !generoGeneroNew.equals(generoGeneroOld)) {
                generoGeneroNew.getCancionList().add(cancion);
                generoGeneroNew = em.merge(generoGeneroNew);
            }
            for (Tags tagsListOldTags : tagsListOld) {
                if (!tagsListNew.contains(tagsListOldTags)) {
                    tagsListOldTags.getCancionList().remove(cancion);
                    tagsListOldTags = em.merge(tagsListOldTags);
                }
            }
            for (Tags tagsListNewTags : tagsListNew) {
                if (!tagsListOld.contains(tagsListNewTags)) {
                    tagsListNewTags.getCancionList().add(cancion);
                    tagsListNewTags = em.merge(tagsListNewTags);
                }
            }
            for (Enriq enriqListNewEnriq : enriqListNew) {
                if (!enriqListOld.contains(enriqListNewEnriq)) {
                    Cancion oldCancionNombreOfEnriqListNewEnriq = enriqListNewEnriq.getCancionNombre();
                    enriqListNewEnriq.setCancionNombre(cancion);
                    enriqListNewEnriq = em.merge(enriqListNewEnriq);
                    if (oldCancionNombreOfEnriqListNewEnriq != null && !oldCancionNombreOfEnriqListNewEnriq.equals(cancion)) {
                        oldCancionNombreOfEnriqListNewEnriq.getEnriqList().remove(enriqListNewEnriq);
                        oldCancionNombreOfEnriqListNewEnriq = em.merge(oldCancionNombreOfEnriqListNewEnriq);
                    }
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListNewPreferenciaxcancion : preferenciaxcancionListNew) {
                if (!preferenciaxcancionListOld.contains(preferenciaxcancionListNewPreferenciaxcancion)) {
                    Cancion oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion = preferenciaxcancionListNewPreferenciaxcancion.getCancion();
                    preferenciaxcancionListNewPreferenciaxcancion.setCancion(cancion);
                    preferenciaxcancionListNewPreferenciaxcancion = em.merge(preferenciaxcancionListNewPreferenciaxcancion);
                    if (oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion != null && !oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion.equals(cancion)) {
                        oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion.getPreferenciaxcancionList().remove(preferenciaxcancionListNewPreferenciaxcancion);
                        oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion = em.merge(oldCancionOfPreferenciaxcancionListNewPreferenciaxcancion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cancion.getNombre();
                if (findCancion(id) == null) {
                    throw new NonexistentEntityException("The cancion with id " + id + " no longer exists.");
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
            Cancion cancion;
            try {
                cancion = em.getReference(Cancion.class, id);
                cancion.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cancion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Enriq> enriqListOrphanCheck = cancion.getEnriqList();
            for (Enriq enriqListOrphanCheckEnriq : enriqListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cancion (" + cancion + ") cannot be destroyed since the Enriq " + enriqListOrphanCheckEnriq + " in its enriqList field has a non-nullable cancionNombre field.");
            }
            List<Preferenciaxcancion> preferenciaxcancionListOrphanCheck = cancion.getPreferenciaxcancionList();
            for (Preferenciaxcancion preferenciaxcancionListOrphanCheckPreferenciaxcancion : preferenciaxcancionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cancion (" + cancion + ") cannot be destroyed since the Preferenciaxcancion " + preferenciaxcancionListOrphanCheckPreferenciaxcancion + " in its preferenciaxcancionList field has a non-nullable cancion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Genero generoGenero = cancion.getGeneroGenero();
            if (generoGenero != null) {
                generoGenero.getCancionList().remove(cancion);
                generoGenero = em.merge(generoGenero);
            }
            List<Tags> tagsList = cancion.getTagsList();
            for (Tags tagsListTags : tagsList) {
                tagsListTags.getCancionList().remove(cancion);
                tagsListTags = em.merge(tagsListTags);
            }
            em.remove(cancion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cancion> findCancionEntities() {
        return findCancionEntities(true, -1, -1);
    }

    public List<Cancion> findCancionEntities(int maxResults, int firstResult) {
        return findCancionEntities(false, maxResults, firstResult);
    }

    private List<Cancion> findCancionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cancion.class));
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

    public Cancion findCancion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cancion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCancionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cancion> rt = cq.from(Cancion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
