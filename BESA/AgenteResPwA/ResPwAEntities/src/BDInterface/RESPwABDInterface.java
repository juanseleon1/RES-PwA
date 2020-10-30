/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDInterface;

import ResPwAEntities.Cancion;
import ResPwAEntities.Controllers.CancionJpaController;
import ResPwAEntities.Controllers.CuentoJpaController;
import ResPwAEntities.Controllers.CuidadorJpaController;
import ResPwAEntities.Controllers.PerfilpwaJpaController;
import ResPwAEntities.Controllers.exceptions.NonexistentEntityException;
import ResPwAEntities.Cuento;
import ResPwAEntities.Cuidador;
import ResPwAEntities.Perfilpwa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author juans
 */
public class RESPwABDInterface {
    
    private static final String EMF="ResPwAEntitiesPU";
    public static Perfilpwa getProfile(String cedula)
    {
        PerfilpwaJpaController pjc=new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
      return pjc.findPerfilpwa(cedula);
    }
    public static void updateProfile(Perfilpwa perfilpwa)
    {
        try {
            PerfilpwaJpaController pjc=new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
            pjc.edit(perfilpwa);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createProfile(Perfilpwa p)
    {
        try {
            PerfilpwaJpaController pjc=new PerfilpwaJpaController(Persistence.createEntityManagerFactory(EMF));
            pjc.create(p);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static Cuidador getCarer(String s)
    {
        CuidadorJpaController cjc= new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
        return cjc.findCuidador(s);
        
    }
    public static void createCarer(Cuidador c)
    {
        CuidadorJpaController cjc= new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
        try {
            cjc.create(c);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateCarer(Cuidador c){
        try {
            CuidadorJpaController cjc= new CuidadorJpaController(Persistence.createEntityManagerFactory(EMF));
            cjc.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RESPwABDInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static List<Cuento> getCuentos()
    {
        CuentoJpaController cjp= new CuentoJpaController(Persistence.createEntityManagerFactory(EMF));
        return  cjp.findCuentoEntities();
        
    }
    
        public static List<Cancion> getCancion()
    {
        CancionJpaController cjp= new CancionJpaController(Persistence.createEntityManagerFactory(EMF));
        return  cjp.findCancionEntities();
        
    }
}
