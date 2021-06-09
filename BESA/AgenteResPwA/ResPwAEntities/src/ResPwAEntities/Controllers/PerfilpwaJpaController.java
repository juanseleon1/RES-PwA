/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResPwAEntities.Controllers;

import ResPwAEntities.Controllers.exceptions.IllegalOrphanException;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ResPwAEntities.PerfilMedico;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Cuidador;
import ResPwAEntities.Estadocivil;
import ResPwAEntities.NivelEducativo;
import ResPwAEntities.Familiar;
import ResPwAEntities.Perfilpwa;
import java.util.ArrayList;
import java.util.List;
import ResPwAEntities.Registroactividad;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author maria.f.garces.cala
 */
public class PerfilpwaJpaController implements Serializable {

    public PerfilpwaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfilpwa perfilpwa) throws PreexistingEntityException, Exception {
        if (perfilpwa.getFamiliarList() == null) {
            perfilpwa.setFamiliarList(new ArrayList<Familiar>());
        }
        if (perfilpwa.getRegistroactividadList() == null) {
            perfilpwa.setRegistroactividadList(new ArrayList<Registroactividad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilMedico perfilMedico = perfilpwa.getPerfilMedico();
            if (perfilMedico != null) {
                perfilMedico = em.getReference(perfilMedico.getClass(), perfilMedico.getPerfilpwaCedula());
                perfilpwa.setPerfilMedico(perfilMedico);
            }
            PerfilPreferencia perfilPreferencia = perfilpwa.getPerfilPreferencia();
            if (perfilPreferencia != null) {
                perfilPreferencia = em.getReference(perfilPreferencia.getClass(), perfilPreferencia.getPerfilpwaCedula());
                perfilpwa.setPerfilPreferencia(perfilPreferencia);
            }
            Cuidador cuidadorNombreusuario = perfilpwa.getCuidadorNombreusuario();
            if (cuidadorNombreusuario != null) {
                cuidadorNombreusuario = em.getReference(cuidadorNombreusuario.getClass(), cuidadorNombreusuario.getNombreusuario());
                perfilpwa.setCuidadorNombreusuario(cuidadorNombreusuario);
            }
            Estadocivil estadocivilTipoec = perfilpwa.getEstadocivilTipoec();
            if (estadocivilTipoec != null) {
                estadocivilTipoec = em.getReference(estadocivilTipoec.getClass(), estadocivilTipoec.getTipoec());
                perfilpwa.setEstadocivilTipoec(estadocivilTipoec);
            }
            NivelEducativo nivelEducativoTipone = perfilpwa.getNivelEducativoTipone();
            if (nivelEducativoTipone != null) {
                nivelEducativoTipone = em.getReference(nivelEducativoTipone.getClass(), nivelEducativoTipone.getTipone());
                perfilpwa.setNivelEducativoTipone(nivelEducativoTipone);
            }
            List<Familiar> attachedFamiliarList = new ArrayList<Familiar>();
            for (Familiar familiarListFamiliarToAttach : perfilpwa.getFamiliarList()) {
                familiarListFamiliarToAttach = em.getReference(familiarListFamiliarToAttach.getClass(), familiarListFamiliarToAttach.getId());
                attachedFamiliarList.add(familiarListFamiliarToAttach);
            }
            perfilpwa.setFamiliarList(attachedFamiliarList);
            List<Registroactividad> attachedRegistroactividadList = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListRegistroactividadToAttach : perfilpwa.getRegistroactividadList()) {
                registroactividadListRegistroactividadToAttach = em.getReference(registroactividadListRegistroactividadToAttach.getClass(), registroactividadListRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadList.add(registroactividadListRegistroactividadToAttach);
            }
            perfilpwa.setRegistroactividadList(attachedRegistroactividadList);
            em.persist(perfilpwa);
            if (perfilMedico != null) {
                Perfilpwa oldPerfilpwaOfPerfilMedico = perfilMedico.getPerfilpwa();
                if (oldPerfilpwaOfPerfilMedico != null) {
                    oldPerfilpwaOfPerfilMedico.setPerfilMedico(null);
                    oldPerfilpwaOfPerfilMedico = em.merge(oldPerfilpwaOfPerfilMedico);
                }
                perfilMedico.setPerfilpwa(perfilpwa);
                perfilMedico = em.merge(perfilMedico);
            }
            if (perfilPreferencia != null) {
                Perfilpwa oldPerfilpwaOfPerfilPreferencia = perfilPreferencia.getPerfilpwa();
                if (oldPerfilpwaOfPerfilPreferencia != null) {
                    oldPerfilpwaOfPerfilPreferencia.setPerfilPreferencia(null);
                    oldPerfilpwaOfPerfilPreferencia = em.merge(oldPerfilpwaOfPerfilPreferencia);
                }
                perfilPreferencia.setPerfilpwa(perfilpwa);
                perfilPreferencia = em.merge(perfilPreferencia);
            }
            if (cuidadorNombreusuario != null) {
                cuidadorNombreusuario.getPerfilpwaList().add(perfilpwa);
                cuidadorNombreusuario = em.merge(cuidadorNombreusuario);
            }
            if (estadocivilTipoec != null) {
                estadocivilTipoec.getPerfilpwaList().add(perfilpwa);
                estadocivilTipoec = em.merge(estadocivilTipoec);
            }
            if (nivelEducativoTipone != null) {
                nivelEducativoTipone.getPerfilpwaList().add(perfilpwa);
                nivelEducativoTipone = em.merge(nivelEducativoTipone);
            }
            for (Familiar familiarListFamiliar : perfilpwa.getFamiliarList()) {
                familiarListFamiliar.getPerfilpwaList().add(perfilpwa);
                familiarListFamiliar = em.merge(familiarListFamiliar);
            }
            for (Registroactividad registroactividadListRegistroactividad : perfilpwa.getRegistroactividadList()) {
                Perfilpwa oldPerfilpwaOfRegistroactividadListRegistroactividad = registroactividadListRegistroactividad.getPerfilpwa();
                registroactividadListRegistroactividad.setPerfilpwa(perfilpwa);
                registroactividadListRegistroactividad = em.merge(registroactividadListRegistroactividad);
                if (oldPerfilpwaOfRegistroactividadListRegistroactividad != null) {
                    oldPerfilpwaOfRegistroactividadListRegistroactividad.getRegistroactividadList().remove(registroactividadListRegistroactividad);
                    oldPerfilpwaOfRegistroactividadListRegistroactividad = em.merge(oldPerfilpwaOfRegistroactividadListRegistroactividad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilpwa(perfilpwa.getCedula()) != null) {
                throw new PreexistingEntityException("Perfilpwa " + perfilpwa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfilpwa perfilpwa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfilpwa persistentPerfilpwa = em.find(Perfilpwa.class, perfilpwa.getCedula());
            PerfilMedico perfilMedicoOld = persistentPerfilpwa.getPerfilMedico();
            PerfilMedico perfilMedicoNew = perfilpwa.getPerfilMedico();
            PerfilPreferencia perfilPreferenciaOld = persistentPerfilpwa.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = perfilpwa.getPerfilPreferencia();
            Cuidador cuidadorNombreusuarioOld = persistentPerfilpwa.getCuidadorNombreusuario();
            Cuidador cuidadorNombreusuarioNew = perfilpwa.getCuidadorNombreusuario();
            Estadocivil estadocivilTipoecOld = persistentPerfilpwa.getEstadocivilTipoec();
            Estadocivil estadocivilTipoecNew = perfilpwa.getEstadocivilTipoec();
            NivelEducativo nivelEducativoTiponeOld = persistentPerfilpwa.getNivelEducativoTipone();
            NivelEducativo nivelEducativoTiponeNew = perfilpwa.getNivelEducativoTipone();
            List<Familiar> familiarListOld = persistentPerfilpwa.getFamiliarList();
            List<Familiar> familiarListNew = perfilpwa.getFamiliarList();
            List<Registroactividad> registroactividadListOld = persistentPerfilpwa.getRegistroactividadList();
            List<Registroactividad> registroactividadListNew = perfilpwa.getRegistroactividadList();
            List<String> illegalOrphanMessages = null;
            if (perfilMedicoOld != null && !perfilMedicoOld.equals(perfilMedicoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PerfilMedico " + perfilMedicoOld + " since its perfilpwa field is not nullable.");
            }
            if (perfilPreferenciaOld != null && !perfilPreferenciaOld.equals(perfilPreferenciaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PerfilPreferencia " + perfilPreferenciaOld + " since its perfilpwa field is not nullable.");
            }
            for (Registroactividad registroactividadListOldRegistroactividad : registroactividadListOld) {
                if (!registroactividadListNew.contains(registroactividadListOldRegistroactividad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Registroactividad " + registroactividadListOldRegistroactividad + " since its perfilpwa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perfilMedicoNew != null) {
                perfilMedicoNew = em.getReference(perfilMedicoNew.getClass(), perfilMedicoNew.getPerfilpwaCedula());
                perfilpwa.setPerfilMedico(perfilMedicoNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                perfilpwa.setPerfilPreferencia(perfilPreferenciaNew);
            }
            if (cuidadorNombreusuarioNew != null) {
                cuidadorNombreusuarioNew = em.getReference(cuidadorNombreusuarioNew.getClass(), cuidadorNombreusuarioNew.getNombreusuario());
                perfilpwa.setCuidadorNombreusuario(cuidadorNombreusuarioNew);
            }
            if (estadocivilTipoecNew != null) {
                estadocivilTipoecNew = em.getReference(estadocivilTipoecNew.getClass(), estadocivilTipoecNew.getTipoec());
                perfilpwa.setEstadocivilTipoec(estadocivilTipoecNew);
            }
            if (nivelEducativoTiponeNew != null) {
                nivelEducativoTiponeNew = em.getReference(nivelEducativoTiponeNew.getClass(), nivelEducativoTiponeNew.getTipone());
                perfilpwa.setNivelEducativoTipone(nivelEducativoTiponeNew);
            }
            List<Familiar> attachedFamiliarListNew = new ArrayList<Familiar>();
            for (Familiar familiarListNewFamiliarToAttach : familiarListNew) {
                familiarListNewFamiliarToAttach = em.getReference(familiarListNewFamiliarToAttach.getClass(), familiarListNewFamiliarToAttach.getId());
                attachedFamiliarListNew.add(familiarListNewFamiliarToAttach);
            }
            familiarListNew = attachedFamiliarListNew;
            perfilpwa.setFamiliarList(familiarListNew);
            List<Registroactividad> attachedRegistroactividadListNew = new ArrayList<Registroactividad>();
            for (Registroactividad registroactividadListNewRegistroactividadToAttach : registroactividadListNew) {
                registroactividadListNewRegistroactividadToAttach = em.getReference(registroactividadListNewRegistroactividadToAttach.getClass(), registroactividadListNewRegistroactividadToAttach.getRegistroactividadPK());
                attachedRegistroactividadListNew.add(registroactividadListNewRegistroactividadToAttach);
            }
            registroactividadListNew = attachedRegistroactividadListNew;
            perfilpwa.setRegistroactividadList(registroactividadListNew);
            perfilpwa = em.merge(perfilpwa);
            if (perfilMedicoNew != null && !perfilMedicoNew.equals(perfilMedicoOld)) {
                Perfilpwa oldPerfilpwaOfPerfilMedico = perfilMedicoNew.getPerfilpwa();
                if (oldPerfilpwaOfPerfilMedico != null) {
                    oldPerfilpwaOfPerfilMedico.setPerfilMedico(null);
                    oldPerfilpwaOfPerfilMedico = em.merge(oldPerfilpwaOfPerfilMedico);
                }
                perfilMedicoNew.setPerfilpwa(perfilpwa);
                perfilMedicoNew = em.merge(perfilMedicoNew);
            }
            if (perfilPreferenciaNew != null && !perfilPreferenciaNew.equals(perfilPreferenciaOld)) {
                Perfilpwa oldPerfilpwaOfPerfilPreferencia = perfilPreferenciaNew.getPerfilpwa();
                if (oldPerfilpwaOfPerfilPreferencia != null) {
                    oldPerfilpwaOfPerfilPreferencia.setPerfilPreferencia(null);
                    oldPerfilpwaOfPerfilPreferencia = em.merge(oldPerfilpwaOfPerfilPreferencia);
                }
                perfilPreferenciaNew.setPerfilpwa(perfilpwa);
                perfilPreferenciaNew = em.merge(perfilPreferenciaNew);
            }
            if (cuidadorNombreusuarioOld != null && !cuidadorNombreusuarioOld.equals(cuidadorNombreusuarioNew)) {
                cuidadorNombreusuarioOld.getPerfilpwaList().remove(perfilpwa);
                cuidadorNombreusuarioOld = em.merge(cuidadorNombreusuarioOld);
            }
            if (cuidadorNombreusuarioNew != null && !cuidadorNombreusuarioNew.equals(cuidadorNombreusuarioOld)) {
                cuidadorNombreusuarioNew.getPerfilpwaList().add(perfilpwa);
                cuidadorNombreusuarioNew = em.merge(cuidadorNombreusuarioNew);
            }
            if (estadocivilTipoecOld != null && !estadocivilTipoecOld.equals(estadocivilTipoecNew)) {
                estadocivilTipoecOld.getPerfilpwaList().remove(perfilpwa);
                estadocivilTipoecOld = em.merge(estadocivilTipoecOld);
            }
            if (estadocivilTipoecNew != null && !estadocivilTipoecNew.equals(estadocivilTipoecOld)) {
                estadocivilTipoecNew.getPerfilpwaList().add(perfilpwa);
                estadocivilTipoecNew = em.merge(estadocivilTipoecNew);
            }
            if (nivelEducativoTiponeOld != null && !nivelEducativoTiponeOld.equals(nivelEducativoTiponeNew)) {
                nivelEducativoTiponeOld.getPerfilpwaList().remove(perfilpwa);
                nivelEducativoTiponeOld = em.merge(nivelEducativoTiponeOld);
            }
            if (nivelEducativoTiponeNew != null && !nivelEducativoTiponeNew.equals(nivelEducativoTiponeOld)) {
                nivelEducativoTiponeNew.getPerfilpwaList().add(perfilpwa);
                nivelEducativoTiponeNew = em.merge(nivelEducativoTiponeNew);
            }
            for (Familiar familiarListOldFamiliar : familiarListOld) {
                if (!familiarListNew.contains(familiarListOldFamiliar)) {
                    familiarListOldFamiliar.getPerfilpwaList().remove(perfilpwa);
                    familiarListOldFamiliar = em.merge(familiarListOldFamiliar);
                }
            }
            for (Familiar familiarListNewFamiliar : familiarListNew) {
                if (!familiarListOld.contains(familiarListNewFamiliar)) {
                    familiarListNewFamiliar.getPerfilpwaList().add(perfilpwa);
                    familiarListNewFamiliar = em.merge(familiarListNewFamiliar);
                }
            }
            for (Registroactividad registroactividadListNewRegistroactividad : registroactividadListNew) {
                if (!registroactividadListOld.contains(registroactividadListNewRegistroactividad)) {
                    Perfilpwa oldPerfilpwaOfRegistroactividadListNewRegistroactividad = registroactividadListNewRegistroactividad.getPerfilpwa();
                    registroactividadListNewRegistroactividad.setPerfilpwa(perfilpwa);
                    registroactividadListNewRegistroactividad = em.merge(registroactividadListNewRegistroactividad);
                    if (oldPerfilpwaOfRegistroactividadListNewRegistroactividad != null && !oldPerfilpwaOfRegistroactividadListNewRegistroactividad.equals(perfilpwa)) {
                        oldPerfilpwaOfRegistroactividadListNewRegistroactividad.getRegistroactividadList().remove(registroactividadListNewRegistroactividad);
                        oldPerfilpwaOfRegistroactividadListNewRegistroactividad = em.merge(oldPerfilpwaOfRegistroactividadListNewRegistroactividad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = perfilpwa.getCedula();
                if (findPerfilpwa(id) == null) {
                    throw new NonexistentEntityException("The perfilpwa with id " + id + " no longer exists.");
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
            Perfilpwa perfilpwa;
            try {
                perfilpwa = em.getReference(Perfilpwa.class, id);
                perfilpwa.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilpwa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            PerfilMedico perfilMedicoOrphanCheck = perfilpwa.getPerfilMedico();
            if (perfilMedicoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfilpwa (" + perfilpwa + ") cannot be destroyed since the PerfilMedico " + perfilMedicoOrphanCheck + " in its perfilMedico field has a non-nullable perfilpwa field.");
            }
            PerfilPreferencia perfilPreferenciaOrphanCheck = perfilpwa.getPerfilPreferencia();
            if (perfilPreferenciaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfilpwa (" + perfilpwa + ") cannot be destroyed since the PerfilPreferencia " + perfilPreferenciaOrphanCheck + " in its perfilPreferencia field has a non-nullable perfilpwa field.");
            }
            List<Registroactividad> registroactividadListOrphanCheck = perfilpwa.getRegistroactividadList();
            for (Registroactividad registroactividadListOrphanCheckRegistroactividad : registroactividadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfilpwa (" + perfilpwa + ") cannot be destroyed since the Registroactividad " + registroactividadListOrphanCheckRegistroactividad + " in its registroactividadList field has a non-nullable perfilpwa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cuidador cuidadorNombreusuario = perfilpwa.getCuidadorNombreusuario();
            if (cuidadorNombreusuario != null) {
                cuidadorNombreusuario.getPerfilpwaList().remove(perfilpwa);
                cuidadorNombreusuario = em.merge(cuidadorNombreusuario);
            }
            Estadocivil estadocivilTipoec = perfilpwa.getEstadocivilTipoec();
            if (estadocivilTipoec != null) {
                estadocivilTipoec.getPerfilpwaList().remove(perfilpwa);
                estadocivilTipoec = em.merge(estadocivilTipoec);
            }
            NivelEducativo nivelEducativoTipone = perfilpwa.getNivelEducativoTipone();
            if (nivelEducativoTipone != null) {
                nivelEducativoTipone.getPerfilpwaList().remove(perfilpwa);
                nivelEducativoTipone = em.merge(nivelEducativoTipone);
            }
            List<Familiar> familiarList = perfilpwa.getFamiliarList();
            for (Familiar familiarListFamiliar : familiarList) {
                familiarListFamiliar.getPerfilpwaList().remove(perfilpwa);
                familiarListFamiliar = em.merge(familiarListFamiliar);
            }
            em.remove(perfilpwa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfilpwa> findPerfilpwaEntities() {
        return findPerfilpwaEntities(true, -1, -1);
    }

    public List<Perfilpwa> findPerfilpwaEntities(int maxResults, int firstResult) {
        return findPerfilpwaEntities(false, maxResults, firstResult);
    }

    private List<Perfilpwa> findPerfilpwaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfilpwa.class));
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

    public Perfilpwa findPerfilpwa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfilpwa.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilpwaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfilpwa> rt = cq.from(Perfilpwa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
