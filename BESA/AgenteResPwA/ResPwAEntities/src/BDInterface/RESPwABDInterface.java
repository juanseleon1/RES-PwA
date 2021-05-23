/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDInterface;

import ResPwAEntities.*;
import ResPwAEntities.Controllers.*;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.EmotionalEntities.EmotionAxisConfig;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author juans
 */
public class RESPwABDInterface {

    private static final String EMF = "ResPwAEntitiesPU";

    public static Perfilpwa getProfile(String cedula) {
        PerfilpwaJpaController pjc = new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
        return pjc.findPerfilpwa(cedula);
    }

    public static void updateProfile(Perfilpwa perfilpwa) {
        try {
            PerfilpwaJpaController pjc = new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
            pjc.edit(perfilpwa);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createProfile(Perfilpwa p) {
        try {
            PerfilpwaJpaController pjc = new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
            pjc.create(p);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Cuidador getCarer(String s) {
        CuidadorJpaController cjc = new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
        return cjc.findCuidador(s);

    }

    public static void createCarer(Cuidador c) {
        CuidadorJpaController cjc = new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
        try {
            cjc.create(c);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateCarer(Cuidador c) {
        try {
            CuidadorJpaController cjc = new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
            cjc.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateCancion(Cancion c) {
        try {
            CancionJpaController cjc = new CancionJpaController(Persistence.createEntityManagerFactory(EMF));
            cjc.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateCuento(Cuento c) {
        try {
            CuentoJpaController cjc = new CuentoJpaController(Persistence.createEntityManagerFactory(EMF));
            cjc.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateActXPref(Actxpreferencia axp) {
        try {
            ActxpreferenciaJpaController axpc = new ActxpreferenciaJpaController(Persistence.createEntityManagerFactory(EMF));
            axpc.edit(axp);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updatePrefXBaile(Preferenciaxbaile axp) {
        try {
            PreferenciaxbaileJpaController axpc = new PreferenciaxbaileJpaController(Persistence.createEntityManagerFactory(EMF));
            axpc.edit(axp);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updatePrefXCuento(Preferenciaxcuento axp) {
        try {
            PreferenciaxcuentoJpaController axpc = new PreferenciaxcuentoJpaController(Persistence.createEntityManagerFactory(EMF));
            axpc.edit(axp);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updatePrefXCancion(Preferenciaxcancion axp) {
        try {
            PreferenciaxcancionJpaController axpc = new PreferenciaxcancionJpaController(Persistence.createEntityManagerFactory(EMF));
            axpc.edit(axp);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Cuento> getCuentos() {
        CuentoJpaController cjp = new CuentoJpaController(Persistence.createEntityManagerFactory(EMF));
        return cjp.findCuentoEntities();

    }

    public static List<Cancion> getCancion() {
        CancionJpaController cjp = new CancionJpaController(Persistence.createEntityManagerFactory(EMF));
        return cjp.findCancionEntities();

    }

    public static List<Actividadpwa> getActivities() {
        ActividadpwaJpaController ajp = new ActividadpwaJpaController(Persistence.createEntityManagerFactory(EMF));
        return ajp.findActividadpwaEntities();

    }

    public static List<Antecedente> getActecedents() {
        AntecedenteJpaController ajp = new AntecedenteJpaController(Persistence.createEntityManagerFactory(EMF));
        return ajp.findAntecedenteEntities();
    }

    public static List<Regla> getRules() {
        ReglaJpaController ajp = new ReglaJpaController(Persistence.createEntityManagerFactory(EMF));
        return ajp.findReglaEntities();
    }

    public static void createRegistroAct(Registroactividad ra) {
        try {
            RegistroactividadJpaController rapc = new RegistroactividadJpaController(Persistence.createEntityManagerFactory(EMF));
            rapc.create(ra);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Actxpreferencia getActXPref(ActxpreferenciaPK pk) {
        ActxpreferenciaJpaController ajp = new ActxpreferenciaJpaController(Persistence.createEntityManagerFactory(EMF));
        return ajp.findActxpreferencia(pk);

    }

    public static List<EmotionAxisConfig> getEmotionalAxisConfig() {
        EmotionAxisConfigJpaController eapc = new EmotionAxisConfigJpaController(Persistence.createEntityManagerFactory(EMF));
        return eapc.findEmotionAxisConfigEntities();
    }

    public static List<Accion> getAcciones() {
        AccionJpaController ajc = new AccionJpaController(Persistence.createEntityManagerFactory(EMF));
        return ajc.findAccionEntities();
    }

    public static List<Emocion> getEmociones() {
        EmocionJpaController ejc = new EmocionJpaController(Persistence.createEntityManagerFactory(EMF));
        return ejc.findEmocionEntities();
    }

}
