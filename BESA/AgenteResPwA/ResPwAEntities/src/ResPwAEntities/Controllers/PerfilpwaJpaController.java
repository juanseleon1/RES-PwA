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
import ResPwAEntities.Cuidador;
import ResPwAEntities.Estadocivil;
import ResPwAEntities.NivelEducativo;
import ResPwAEntities.PerfilMedico;
import ResPwAEntities.PerfilPreferencia;
import ResPwAEntities.Familiar;
import ResPwAEntities.Perfilpwa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author juans
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuidador cuidadornombre = perfilpwa.getCuidadornombre();
            if (cuidadornombre != null) {
                cuidadornombre = em.getReference(cuidadornombre.getClass(), cuidadornombre.getNombreusuario());
                perfilpwa.setCuidadornombre(cuidadornombre);
            }
            Estadocivil tipoestadocivil = perfilpwa.getTipoestadocivil();
            if (tipoestadocivil != null) {
                tipoestadocivil = em.getReference(tipoestadocivil.getClass(), tipoestadocivil.getTipoestado());
                perfilpwa.setTipoestadocivil(tipoestadocivil);
            }
            NivelEducativo tiponiveleducativo = perfilpwa.getTiponiveleducativo();
            if (tiponiveleducativo != null) {
                tiponiveleducativo = em.getReference(tiponiveleducativo.getClass(), tiponiveleducativo.getTiponiveleducativo());
                perfilpwa.setTiponiveleducativo(tiponiveleducativo);
            }
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
            List<Familiar> attachedFamiliarList = new ArrayList<Familiar>();
            for (Familiar familiarListFamiliarToAttach : perfilpwa.getFamiliarList()) {
                familiarListFamiliarToAttach = em.getReference(familiarListFamiliarToAttach.getClass(), familiarListFamiliarToAttach.getId());
                attachedFamiliarList.add(familiarListFamiliarToAttach);
            }
            perfilpwa.setFamiliarList(attachedFamiliarList);
            em.persist(perfilpwa);
            if (cuidadornombre != null) {
                cuidadornombre.getPerfilpwaList().add(perfilpwa);
                cuidadornombre = em.merge(cuidadornombre);
            }
            if (tipoestadocivil != null) {
                tipoestadocivil.getPerfilpwaList().add(perfilpwa);
                tipoestadocivil = em.merge(tipoestadocivil);
            }
            if (tiponiveleducativo != null) {
                tiponiveleducativo.getPerfilpwaList().add(perfilpwa);
                tiponiveleducativo = em.merge(tiponiveleducativo);
            }
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
            for (Familiar familiarListFamiliar : perfilpwa.getFamiliarList()) {
                familiarListFamiliar.getPerfilpwaList().add(perfilpwa);
                familiarListFamiliar = em.merge(familiarListFamiliar);
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
            Cuidador cuidadornombreOld = persistentPerfilpwa.getCuidadornombre();
            Cuidador cuidadornombreNew = perfilpwa.getCuidadornombre();
            Estadocivil tipoestadocivilOld = persistentPerfilpwa.getTipoestadocivil();
            Estadocivil tipoestadocivilNew = perfilpwa.getTipoestadocivil();
            NivelEducativo tiponiveleducativoOld = persistentPerfilpwa.getTiponiveleducativo();
            NivelEducativo tiponiveleducativoNew = perfilpwa.getTiponiveleducativo();
            PerfilMedico perfilMedicoOld = persistentPerfilpwa.getPerfilMedico();
            PerfilMedico perfilMedicoNew = perfilpwa.getPerfilMedico();
            PerfilPreferencia perfilPreferenciaOld = persistentPerfilpwa.getPerfilPreferencia();
            PerfilPreferencia perfilPreferenciaNew = perfilpwa.getPerfilPreferencia();
            List<Familiar> familiarListOld = persistentPerfilpwa.getFamiliarList();
            List<Familiar> familiarListNew = perfilpwa.getFamiliarList();
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cuidadornombreNew != null) {
                cuidadornombreNew = em.getReference(cuidadornombreNew.getClass(), cuidadornombreNew.getNombreusuario());
                perfilpwa.setCuidadornombre(cuidadornombreNew);
            }
            if (tipoestadocivilNew != null) {
                tipoestadocivilNew = em.getReference(tipoestadocivilNew.getClass(), tipoestadocivilNew.getTipoestado());
                perfilpwa.setTipoestadocivil(tipoestadocivilNew);
            }
            if (tiponiveleducativoNew != null) {
                tiponiveleducativoNew = em.getReference(tiponiveleducativoNew.getClass(), tiponiveleducativoNew.getTiponiveleducativo());
                perfilpwa.setTiponiveleducativo(tiponiveleducativoNew);
            }
            if (perfilMedicoNew != null) {
                perfilMedicoNew = em.getReference(perfilMedicoNew.getClass(), perfilMedicoNew.getPerfilpwaCedula());
                perfilpwa.setPerfilMedico(perfilMedicoNew);
            }
            if (perfilPreferenciaNew != null) {
                perfilPreferenciaNew = em.getReference(perfilPreferenciaNew.getClass(), perfilPreferenciaNew.getPerfilpwaCedula());
                perfilpwa.setPerfilPreferencia(perfilPreferenciaNew);
            }
            List<Familiar> attachedFamiliarListNew = new ArrayList<Familiar>();
            for (Familiar familiarListNewFamiliarToAttach : familiarListNew) {
                familiarListNewFamiliarToAttach = em.getReference(familiarListNewFamiliarToAttach.getClass(), familiarListNewFamiliarToAttach.getId());
                attachedFamiliarListNew.add(familiarListNewFamiliarToAttach);
            }
            familiarListNew = attachedFamiliarListNew;
            perfilpwa.setFamiliarList(familiarListNew);
            perfilpwa = em.merge(perfilpwa);
            if (cuidadornombreOld != null && !cuidadornombreOld.equals(cuidadornombreNew)) {
                cuidadornombreOld.getPerfilpwaList().remove(perfilpwa);
                cuidadornombreOld = em.merge(cuidadornombreOld);
            }
            if (cuidadornombreNew != null && !cuidadornombreNew.equals(cuidadornombreOld)) {
                cuidadornombreNew.getPerfilpwaList().add(perfilpwa);
                cuidadornombreNew = em.merge(cuidadornombreNew);
            }
            if (tipoestadocivilOld != null && !tipoestadocivilOld.equals(tipoestadocivilNew)) {
                tipoestadocivilOld.getPerfilpwaList().remove(perfilpwa);
                tipoestadocivilOld = em.merge(tipoestadocivilOld);
            }
            if (tipoestadocivilNew != null && !tipoestadocivilNew.equals(tipoestadocivilOld)) {
                tipoestadocivilNew.getPerfilpwaList().add(perfilpwa);
                tipoestadocivilNew = em.merge(tipoestadocivilNew);
            }
            if (tiponiveleducativoOld != null && !tiponiveleducativoOld.equals(tiponiveleducativoNew)) {
                tiponiveleducativoOld.getPerfilpwaList().remove(perfilpwa);
                tiponiveleducativoOld = em.merge(tiponiveleducativoOld);
            }
            if (tiponiveleducativoNew != null && !tiponiveleducativoNew.equals(tiponiveleducativoOld)) {
                tiponiveleducativoNew.getPerfilpwaList().add(perfilpwa);
                tiponiveleducativoNew = em.merge(tiponiveleducativoNew);
            }
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cuidador cuidadornombre = perfilpwa.getCuidadornombre();
            if (cuidadornombre != null) {
                cuidadornombre.getPerfilpwaList().remove(perfilpwa);
                cuidadornombre = em.merge(cuidadornombre);
            }
            Estadocivil tipoestadocivil = perfilpwa.getTipoestadocivil();
            if (tipoestadocivil != null) {
                tipoestadocivil.getPerfilpwaList().remove(perfilpwa);
                tipoestadocivil = em.merge(tipoestadocivil);
            }
            NivelEducativo tiponiveleducativo = perfilpwa.getTiponiveleducativo();
            if (tiponiveleducativo != null) {
                tiponiveleducativo.getPerfilpwaList().remove(perfilpwa);
                tiponiveleducativo = em.merge(tiponiveleducativo);
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
