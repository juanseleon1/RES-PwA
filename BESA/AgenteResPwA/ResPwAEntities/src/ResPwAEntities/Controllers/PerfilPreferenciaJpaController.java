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
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.Preferenciaxbaile;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Preferenciaxcuento;
import ResPwAEntities.Preferenciaxcancion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class PerfilPreferenciaJpaController implements Serializable {

    public PerfilPreferenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PerfilPreferencia perfilPreferencia) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (perfilPreferencia.getPreferenciaxbaileList() == null) {
            perfilPreferencia.setPreferenciaxbaileList(new ArrayList<Preferenciaxbaile>());
        }
        if (perfilPreferencia.getActxpreferenciaList() == null) {
            perfilPreferencia.setActxpreferenciaList(new ArrayList<Actxpreferencia>());
        }
        if (perfilPreferencia.getPreferenciaxcuentoList() == null) {
            perfilPreferencia.setPreferenciaxcuentoList(new ArrayList<Preferenciaxcuento>());
        }
        if (perfilPreferencia.getPreferenciaxcancionList() == null) {
            perfilPreferencia.setPreferenciaxcancionList(new ArrayList<Preferenciaxcancion>());
        }
        List<String> illegalOrphanMessages = null;
        Perfilpwa perfilpwaOrphanCheck = perfilPreferencia.getPerfilpwa();
        if (perfilpwaOrphanCheck != null) {
            PerfilPreferencia oldPerfilPreferenciaOfPerfilpwa = perfilpwaOrphanCheck.getPerfilPreferencia();
            if (oldPerfilPreferenciaOfPerfilpwa != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Perfilpwa " + perfilpwaOrphanCheck + " already has an item of type PerfilPreferencia whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfilpwa perfilpwa = perfilPreferencia.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa = em.getReference(perfilpwa.getClass(), perfilpwa.getCedula());
                perfilPreferencia.setPerfilpwa(perfilpwa);
            }
            List<Preferenciaxbaile> attachedPreferenciaxbaileList = new ArrayList<Preferenciaxbaile>();
            for (Preferenciaxbaile preferenciaxbaileListPreferenciaxbaileToAttach : perfilPreferencia.getPreferenciaxbaileList()) {
                preferenciaxbaileListPreferenciaxbaileToAttach = em.getReference(preferenciaxbaileListPreferenciaxbaileToAttach.getClass(), preferenciaxbaileListPreferenciaxbaileToAttach.getPreferenciaxbailePK());
                attachedPreferenciaxbaileList.add(preferenciaxbaileListPreferenciaxbaileToAttach);
            }
            perfilPreferencia.setPreferenciaxbaileList(attachedPreferenciaxbaileList);
            List<Actxpreferencia> attachedActxpreferenciaList = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListActxpreferenciaToAttach : perfilPreferencia.getActxpreferenciaList()) {
                actxpreferenciaListActxpreferenciaToAttach = em.getReference(actxpreferenciaListActxpreferenciaToAttach.getClass(), actxpreferenciaListActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaList.add(actxpreferenciaListActxpreferenciaToAttach);
            }
            perfilPreferencia.setActxpreferenciaList(attachedActxpreferenciaList);
            List<Preferenciaxcuento> attachedPreferenciaxcuentoList = new ArrayList<Preferenciaxcuento>();
            for (Preferenciaxcuento preferenciaxcuentoListPreferenciaxcuentoToAttach : perfilPreferencia.getPreferenciaxcuentoList()) {
                preferenciaxcuentoListPreferenciaxcuentoToAttach = em.getReference(preferenciaxcuentoListPreferenciaxcuentoToAttach.getClass(), preferenciaxcuentoListPreferenciaxcuentoToAttach.getPreferenciaxcuentoPK());
                attachedPreferenciaxcuentoList.add(preferenciaxcuentoListPreferenciaxcuentoToAttach);
            }
            perfilPreferencia.setPreferenciaxcuentoList(attachedPreferenciaxcuentoList);
            List<Preferenciaxcancion> attachedPreferenciaxcancionList = new ArrayList<Preferenciaxcancion>();
            for (Preferenciaxcancion preferenciaxcancionListPreferenciaxcancionToAttach : perfilPreferencia.getPreferenciaxcancionList()) {
                preferenciaxcancionListPreferenciaxcancionToAttach = em.getReference(preferenciaxcancionListPreferenciaxcancionToAttach.getClass(), preferenciaxcancionListPreferenciaxcancionToAttach.getPreferenciaxcancionPK());
                attachedPreferenciaxcancionList.add(preferenciaxcancionListPreferenciaxcancionToAttach);
            }
            perfilPreferencia.setPreferenciaxcancionList(attachedPreferenciaxcancionList);
            em.persist(perfilPreferencia);
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(perfilPreferencia);
                perfilpwa = em.merge(perfilpwa);
            }
            for (Preferenciaxbaile preferenciaxbaileListPreferenciaxbaile : perfilPreferencia.getPreferenciaxbaileList()) {
                PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxbaileListPreferenciaxbaile = preferenciaxbaileListPreferenciaxbaile.getPerfilPreferencia();
                preferenciaxbaileListPreferenciaxbaile.setPerfilPreferencia(perfilPreferencia);
                preferenciaxbaileListPreferenciaxbaile = em.merge(preferenciaxbaileListPreferenciaxbaile);
                if (oldPerfilPreferenciaOfPreferenciaxbaileListPreferenciaxbaile != null) {
                    oldPerfilPreferenciaOfPreferenciaxbaileListPreferenciaxbaile.getPreferenciaxbaileList().remove(preferenciaxbaileListPreferenciaxbaile);
                    oldPerfilPreferenciaOfPreferenciaxbaileListPreferenciaxbaile = em.merge(oldPerfilPreferenciaOfPreferenciaxbaileListPreferenciaxbaile);
                }
            }
            for (Actxpreferencia actxpreferenciaListActxpreferencia : perfilPreferencia.getActxpreferenciaList()) {
                PerfilPreferencia oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia = actxpreferenciaListActxpreferencia.getPerfilPreferencia();
                actxpreferenciaListActxpreferencia.setPerfilPreferencia(perfilPreferencia);
                actxpreferenciaListActxpreferencia = em.merge(actxpreferenciaListActxpreferencia);
                if (oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia != null) {
                    oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListActxpreferencia);
                    oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia = em.merge(oldPerfilPreferenciaOfActxpreferenciaListActxpreferencia);
                }
            }
            for (Preferenciaxcuento preferenciaxcuentoListPreferenciaxcuento : perfilPreferencia.getPreferenciaxcuentoList()) {
                PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxcuentoListPreferenciaxcuento = preferenciaxcuentoListPreferenciaxcuento.getPerfilPreferencia();
                preferenciaxcuentoListPreferenciaxcuento.setPerfilPreferencia(perfilPreferencia);
                preferenciaxcuentoListPreferenciaxcuento = em.merge(preferenciaxcuentoListPreferenciaxcuento);
                if (oldPerfilPreferenciaOfPreferenciaxcuentoListPreferenciaxcuento != null) {
                    oldPerfilPreferenciaOfPreferenciaxcuentoListPreferenciaxcuento.getPreferenciaxcuentoList().remove(preferenciaxcuentoListPreferenciaxcuento);
                    oldPerfilPreferenciaOfPreferenciaxcuentoListPreferenciaxcuento = em.merge(oldPerfilPreferenciaOfPreferenciaxcuentoListPreferenciaxcuento);
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListPreferenciaxcancion : perfilPreferencia.getPreferenciaxcancionList()) {
                PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxcancionListPreferenciaxcancion = preferenciaxcancionListPreferenciaxcancion.getPerfilPreferencia();
                preferenciaxcancionListPreferenciaxcancion.setPerfilPreferencia(perfilPreferencia);
                preferenciaxcancionListPreferenciaxcancion = em.merge(preferenciaxcancionListPreferenciaxcancion);
                if (oldPerfilPreferenciaOfPreferenciaxcancionListPreferenciaxcancion != null) {
                    oldPerfilPreferenciaOfPreferenciaxcancionListPreferenciaxcancion.getPreferenciaxcancionList().remove(preferenciaxcancionListPreferenciaxcancion);
                    oldPerfilPreferenciaOfPreferenciaxcancionListPreferenciaxcancion = em.merge(oldPerfilPreferenciaOfPreferenciaxcancionListPreferenciaxcancion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilPreferencia(perfilPreferencia.getPerfilpwaCedula()) != null) {
                throw new PreexistingEntityException("PerfilPreferencia " + perfilPreferencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PerfilPreferencia perfilPreferencia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilPreferencia persistentPerfilPreferencia = em.find(PerfilPreferencia.class, perfilPreferencia.getPerfilpwaCedula());
            Perfilpwa perfilpwaOld = persistentPerfilPreferencia.getPerfilpwa();
            Perfilpwa perfilpwaNew = perfilPreferencia.getPerfilpwa();
            List<Preferenciaxbaile> preferenciaxbaileListOld = persistentPerfilPreferencia.getPreferenciaxbaileList();
            List<Preferenciaxbaile> preferenciaxbaileListNew = perfilPreferencia.getPreferenciaxbaileList();
            List<Actxpreferencia> actxpreferenciaListOld = persistentPerfilPreferencia.getActxpreferenciaList();
            List<Actxpreferencia> actxpreferenciaListNew = perfilPreferencia.getActxpreferenciaList();
            List<Preferenciaxcuento> preferenciaxcuentoListOld = persistentPerfilPreferencia.getPreferenciaxcuentoList();
            List<Preferenciaxcuento> preferenciaxcuentoListNew = perfilPreferencia.getPreferenciaxcuentoList();
            List<Preferenciaxcancion> preferenciaxcancionListOld = persistentPerfilPreferencia.getPreferenciaxcancionList();
            List<Preferenciaxcancion> preferenciaxcancionListNew = perfilPreferencia.getPreferenciaxcancionList();
            List<String> illegalOrphanMessages = null;
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                PerfilPreferencia oldPerfilPreferenciaOfPerfilpwa = perfilpwaNew.getPerfilPreferencia();
                if (oldPerfilPreferenciaOfPerfilpwa != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Perfilpwa " + perfilpwaNew + " already has an item of type PerfilPreferencia whose perfilpwa column cannot be null. Please make another selection for the perfilpwa field.");
                }
            }
            for (Preferenciaxbaile preferenciaxbaileListOldPreferenciaxbaile : preferenciaxbaileListOld) {
                if (!preferenciaxbaileListNew.contains(preferenciaxbaileListOldPreferenciaxbaile)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferenciaxbaile " + preferenciaxbaileListOldPreferenciaxbaile + " since its perfilPreferencia field is not nullable.");
                }
            }
            for (Actxpreferencia actxpreferenciaListOldActxpreferencia : actxpreferenciaListOld) {
                if (!actxpreferenciaListNew.contains(actxpreferenciaListOldActxpreferencia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actxpreferencia " + actxpreferenciaListOldActxpreferencia + " since its perfilPreferencia field is not nullable.");
                }
            }
            for (Preferenciaxcuento preferenciaxcuentoListOldPreferenciaxcuento : preferenciaxcuentoListOld) {
                if (!preferenciaxcuentoListNew.contains(preferenciaxcuentoListOldPreferenciaxcuento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferenciaxcuento " + preferenciaxcuentoListOldPreferenciaxcuento + " since its perfilPreferencia field is not nullable.");
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListOldPreferenciaxcancion : preferenciaxcancionListOld) {
                if (!preferenciaxcancionListNew.contains(preferenciaxcancionListOldPreferenciaxcancion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferenciaxcancion " + preferenciaxcancionListOldPreferenciaxcancion + " since its perfilPreferencia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perfilpwaNew != null) {
                perfilpwaNew = em.getReference(perfilpwaNew.getClass(), perfilpwaNew.getCedula());
                perfilPreferencia.setPerfilpwa(perfilpwaNew);
            }
            List<Preferenciaxbaile> attachedPreferenciaxbaileListNew = new ArrayList<Preferenciaxbaile>();
            for (Preferenciaxbaile preferenciaxbaileListNewPreferenciaxbaileToAttach : preferenciaxbaileListNew) {
                preferenciaxbaileListNewPreferenciaxbaileToAttach = em.getReference(preferenciaxbaileListNewPreferenciaxbaileToAttach.getClass(), preferenciaxbaileListNewPreferenciaxbaileToAttach.getPreferenciaxbailePK());
                attachedPreferenciaxbaileListNew.add(preferenciaxbaileListNewPreferenciaxbaileToAttach);
            }
            preferenciaxbaileListNew = attachedPreferenciaxbaileListNew;
            perfilPreferencia.setPreferenciaxbaileList(preferenciaxbaileListNew);
            List<Actxpreferencia> attachedActxpreferenciaListNew = new ArrayList<Actxpreferencia>();
            for (Actxpreferencia actxpreferenciaListNewActxpreferenciaToAttach : actxpreferenciaListNew) {
                actxpreferenciaListNewActxpreferenciaToAttach = em.getReference(actxpreferenciaListNewActxpreferenciaToAttach.getClass(), actxpreferenciaListNewActxpreferenciaToAttach.getActxpreferenciaPK());
                attachedActxpreferenciaListNew.add(actxpreferenciaListNewActxpreferenciaToAttach);
            }
            actxpreferenciaListNew = attachedActxpreferenciaListNew;
            perfilPreferencia.setActxpreferenciaList(actxpreferenciaListNew);
            List<Preferenciaxcuento> attachedPreferenciaxcuentoListNew = new ArrayList<Preferenciaxcuento>();
            for (Preferenciaxcuento preferenciaxcuentoListNewPreferenciaxcuentoToAttach : preferenciaxcuentoListNew) {
                preferenciaxcuentoListNewPreferenciaxcuentoToAttach = em.getReference(preferenciaxcuentoListNewPreferenciaxcuentoToAttach.getClass(), preferenciaxcuentoListNewPreferenciaxcuentoToAttach.getPreferenciaxcuentoPK());
                attachedPreferenciaxcuentoListNew.add(preferenciaxcuentoListNewPreferenciaxcuentoToAttach);
            }
            preferenciaxcuentoListNew = attachedPreferenciaxcuentoListNew;
            perfilPreferencia.setPreferenciaxcuentoList(preferenciaxcuentoListNew);
            List<Preferenciaxcancion> attachedPreferenciaxcancionListNew = new ArrayList<Preferenciaxcancion>();
            for (Preferenciaxcancion preferenciaxcancionListNewPreferenciaxcancionToAttach : preferenciaxcancionListNew) {
                preferenciaxcancionListNewPreferenciaxcancionToAttach = em.getReference(preferenciaxcancionListNewPreferenciaxcancionToAttach.getClass(), preferenciaxcancionListNewPreferenciaxcancionToAttach.getPreferenciaxcancionPK());
                attachedPreferenciaxcancionListNew.add(preferenciaxcancionListNewPreferenciaxcancionToAttach);
            }
            preferenciaxcancionListNew = attachedPreferenciaxcancionListNew;
            perfilPreferencia.setPreferenciaxcancionList(preferenciaxcancionListNew);
            perfilPreferencia = em.merge(perfilPreferencia);
            if (perfilpwaOld != null && !perfilpwaOld.equals(perfilpwaNew)) {
                perfilpwaOld.setPerfilPreferencia(null);
                perfilpwaOld = em.merge(perfilpwaOld);
            }
            if (perfilpwaNew != null && !perfilpwaNew.equals(perfilpwaOld)) {
                perfilpwaNew.setPerfilPreferencia(perfilPreferencia);
                perfilpwaNew = em.merge(perfilpwaNew);
            }
            for (Preferenciaxbaile preferenciaxbaileListNewPreferenciaxbaile : preferenciaxbaileListNew) {
                if (!preferenciaxbaileListOld.contains(preferenciaxbaileListNewPreferenciaxbaile)) {
                    PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile = preferenciaxbaileListNewPreferenciaxbaile.getPerfilPreferencia();
                    preferenciaxbaileListNewPreferenciaxbaile.setPerfilPreferencia(perfilPreferencia);
                    preferenciaxbaileListNewPreferenciaxbaile = em.merge(preferenciaxbaileListNewPreferenciaxbaile);
                    if (oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile != null && !oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile.equals(perfilPreferencia)) {
                        oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile.getPreferenciaxbaileList().remove(preferenciaxbaileListNewPreferenciaxbaile);
                        oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile = em.merge(oldPerfilPreferenciaOfPreferenciaxbaileListNewPreferenciaxbaile);
                    }
                }
            }
            for (Actxpreferencia actxpreferenciaListNewActxpreferencia : actxpreferenciaListNew) {
                if (!actxpreferenciaListOld.contains(actxpreferenciaListNewActxpreferencia)) {
                    PerfilPreferencia oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia = actxpreferenciaListNewActxpreferencia.getPerfilPreferencia();
                    actxpreferenciaListNewActxpreferencia.setPerfilPreferencia(perfilPreferencia);
                    actxpreferenciaListNewActxpreferencia = em.merge(actxpreferenciaListNewActxpreferencia);
                    if (oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia != null && !oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia.equals(perfilPreferencia)) {
                        oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia.getActxpreferenciaList().remove(actxpreferenciaListNewActxpreferencia);
                        oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia = em.merge(oldPerfilPreferenciaOfActxpreferenciaListNewActxpreferencia);
                    }
                }
            }
            for (Preferenciaxcuento preferenciaxcuentoListNewPreferenciaxcuento : preferenciaxcuentoListNew) {
                if (!preferenciaxcuentoListOld.contains(preferenciaxcuentoListNewPreferenciaxcuento)) {
                    PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento = preferenciaxcuentoListNewPreferenciaxcuento.getPerfilPreferencia();
                    preferenciaxcuentoListNewPreferenciaxcuento.setPerfilPreferencia(perfilPreferencia);
                    preferenciaxcuentoListNewPreferenciaxcuento = em.merge(preferenciaxcuentoListNewPreferenciaxcuento);
                    if (oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento != null && !oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento.equals(perfilPreferencia)) {
                        oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento.getPreferenciaxcuentoList().remove(preferenciaxcuentoListNewPreferenciaxcuento);
                        oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento = em.merge(oldPerfilPreferenciaOfPreferenciaxcuentoListNewPreferenciaxcuento);
                    }
                }
            }
            for (Preferenciaxcancion preferenciaxcancionListNewPreferenciaxcancion : preferenciaxcancionListNew) {
                if (!preferenciaxcancionListOld.contains(preferenciaxcancionListNewPreferenciaxcancion)) {
                    PerfilPreferencia oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion = preferenciaxcancionListNewPreferenciaxcancion.getPerfilPreferencia();
                    preferenciaxcancionListNewPreferenciaxcancion.setPerfilPreferencia(perfilPreferencia);
                    preferenciaxcancionListNewPreferenciaxcancion = em.merge(preferenciaxcancionListNewPreferenciaxcancion);
                    if (oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion != null && !oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion.equals(perfilPreferencia)) {
                        oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion.getPreferenciaxcancionList().remove(preferenciaxcancionListNewPreferenciaxcancion);
                        oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion = em.merge(oldPerfilPreferenciaOfPreferenciaxcancionListNewPreferenciaxcancion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = perfilPreferencia.getPerfilpwaCedula();
                if (findPerfilPreferencia(id) == null) {
                    throw new NonexistentEntityException("The perfilPreferencia with id " + id + " no longer exists.");
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
            PerfilPreferencia perfilPreferencia;
            try {
                perfilPreferencia = em.getReference(PerfilPreferencia.class, id);
                perfilPreferencia.getPerfilpwaCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilPreferencia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Preferenciaxbaile> preferenciaxbaileListOrphanCheck = perfilPreferencia.getPreferenciaxbaileList();
            for (Preferenciaxbaile preferenciaxbaileListOrphanCheckPreferenciaxbaile : preferenciaxbaileListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilPreferencia (" + perfilPreferencia + ") cannot be destroyed since the Preferenciaxbaile " + preferenciaxbaileListOrphanCheckPreferenciaxbaile + " in its preferenciaxbaileList field has a non-nullable perfilPreferencia field.");
            }
            List<Actxpreferencia> actxpreferenciaListOrphanCheck = perfilPreferencia.getActxpreferenciaList();
            for (Actxpreferencia actxpreferenciaListOrphanCheckActxpreferencia : actxpreferenciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilPreferencia (" + perfilPreferencia + ") cannot be destroyed since the Actxpreferencia " + actxpreferenciaListOrphanCheckActxpreferencia + " in its actxpreferenciaList field has a non-nullable perfilPreferencia field.");
            }
            List<Preferenciaxcuento> preferenciaxcuentoListOrphanCheck = perfilPreferencia.getPreferenciaxcuentoList();
            for (Preferenciaxcuento preferenciaxcuentoListOrphanCheckPreferenciaxcuento : preferenciaxcuentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilPreferencia (" + perfilPreferencia + ") cannot be destroyed since the Preferenciaxcuento " + preferenciaxcuentoListOrphanCheckPreferenciaxcuento + " in its preferenciaxcuentoList field has a non-nullable perfilPreferencia field.");
            }
            List<Preferenciaxcancion> preferenciaxcancionListOrphanCheck = perfilPreferencia.getPreferenciaxcancionList();
            for (Preferenciaxcancion preferenciaxcancionListOrphanCheckPreferenciaxcancion : preferenciaxcancionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PerfilPreferencia (" + perfilPreferencia + ") cannot be destroyed since the Preferenciaxcancion " + preferenciaxcancionListOrphanCheckPreferenciaxcancion + " in its preferenciaxcancionList field has a non-nullable perfilPreferencia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Perfilpwa perfilpwa = perfilPreferencia.getPerfilpwa();
            if (perfilpwa != null) {
                perfilpwa.setPerfilPreferencia(null);
                perfilpwa = em.merge(perfilpwa);
            }
            em.remove(perfilPreferencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PerfilPreferencia> findPerfilPreferenciaEntities() {
        return findPerfilPreferenciaEntities(true, -1, -1);
    }

    public List<PerfilPreferencia> findPerfilPreferenciaEntities(int maxResults, int firstResult) {
        return findPerfilPreferenciaEntities(false, maxResults, firstResult);
    }

    private List<PerfilPreferencia> findPerfilPreferenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerfilPreferencia.class));
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

    public PerfilPreferencia findPerfilPreferencia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerfilPreferencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilPreferenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerfilPreferencia> rt = cq.from(PerfilPreferencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
