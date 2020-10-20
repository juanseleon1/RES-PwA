/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *  
 * @author mafegarces
 */
public class BEstadoInteraccion implements Believes{

    private boolean cambioDificultadVoz=false;
    private boolean ayudaActividadSolicitada=false;
    private boolean cambioEnriq=false;
    private boolean pausarInt=false;
    private boolean cancelarInt=false;
    private boolean reiniciarInt=false;
    private long tiempoSinInt=0;
    private boolean sistemaSuspendido=false;
    private long nivelEnriquecimiento=0;
    private long velocidad=0;
    private long distanciaPwA=0;
    private boolean estaHablando=false;
    private boolean estaBailando=false;
    private boolean hayInteraccionFisica = false;
    private boolean detectaPwA = false;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoInteraccion update Received: "+si);
        return true;
    }

    public boolean isCambioDificultadVoz() {
        return cambioDificultadVoz;
    }

    public boolean isAyudaActividadSolicitada() {
        return ayudaActividadSolicitada;
    }

    public boolean isCambioEnriq() {
        return cambioEnriq;
    }

    public boolean isPausarInt() {
        return pausarInt;
    }

    public boolean isCancelarInt() {
        return cancelarInt;
    }

    public boolean isReiniciarInt() {
        return reiniciarInt;
    }

    public long getTiempoSinInt() {
        return tiempoSinInt;
    }
    
}
