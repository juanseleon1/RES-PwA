/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import EmotionalAnalyzerAgent.EmotionPwA;
import RobotAgentBDI.Believes.PerfilPwA.Emocion;
import java.util.HashMap;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoEmocionalPwA implements Believes{

    private EmotionPwA tiempoEmocionPredominante;
    private HashMap<String,Integer> estadoEmocional;
    private long tiempoAtencion;
    private long tiempoRelajacion;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoEmocionalPwA update Received: "+si);
        return true;
    }

    public Object getEstadoEmocional() {
        return estadoEmocional;
    }


    
    
    
}
