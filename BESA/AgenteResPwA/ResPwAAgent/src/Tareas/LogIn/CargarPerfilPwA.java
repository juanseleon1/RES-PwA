/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.LogIn;

import static Init.InitRESPwA.emf;
import ResPwAEntities.Controllers.PerfilpwaJpaController;
import ResPwAEntities.Perfilpwa;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import RobotAgentBDI.ResPwaTask;
import java.util.HashMap;
import javax.persistence.Persistence;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class CargarPerfilPwA extends ResPwaTask{
    
    private HashMap<String,Object> infoServicio = new HashMap<>();
    

    public CargarPerfilPwA() {
        System.out.println("--- Task Seleccionar Estrategia Animar PwA Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Cargar Perfil PwA ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        //buscar BD perfil a partir de cedula
        PerfilpwaJpaController pjc= new PerfilpwaJpaController(Persistence.createEntityManagerFactory(emf));
        blvs.setbPerfilPwA(pjc.findPerfilpwa(blvs.getbPerfilPwA().getPerfil().getCedula()));
        
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Cargar Perfil PwA ---");
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Cargar Perfil PwA ---");
    }
    
    
}
