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
    private boolean estaReproduciendoCancion=false;
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoInteraccion update Received: "+si);
        return true;
    }

    public boolean isCambioDificultadVoz() {
        return cambioDificultadVoz;
    }

    public void setCambioDificultadVoz(boolean cambioDificultadVoz) {
        this.cambioDificultadVoz = cambioDificultadVoz;
    }

    public boolean isAyudaActividadSolicitada() {
        return ayudaActividadSolicitada;
    }

    public void setAyudaActividadSolicitada(boolean ayudaActividadSolicitada) {
        this.ayudaActividadSolicitada = ayudaActividadSolicitada;
    }

    public boolean isCambioEnriq() {
        return cambioEnriq;
    }

    public void setCambioEnriq(boolean cambioEnriq) {
        this.cambioEnriq = cambioEnriq;
    }

    public boolean isPausarInt() {
        return pausarInt;
    }

    public void setPausarInt(boolean pausarInt) {
        this.pausarInt = pausarInt;
    }

    public boolean isCancelarInt() {
        return cancelarInt;
    }

    public void setCancelarInt(boolean cancelarInt) {
        this.cancelarInt = cancelarInt;
    }

    public boolean isReiniciarInt() {
        return reiniciarInt;
    }

    public void setReiniciarInt(boolean reiniciarInt) {
        this.reiniciarInt = reiniciarInt;
    }

    public long getTiempoSinInt() {
        return tiempoSinInt;
    }

    public void setTiempoSinInt(long tiempoSinInt) {
        this.tiempoSinInt = tiempoSinInt;
    }

    public boolean isSistemaSuspendido() {
        return sistemaSuspendido;
    }

    public void setSistemaSuspendido(boolean sistemaSuspendido) {
        this.sistemaSuspendido = sistemaSuspendido;
    }

    public long getNivelEnriquecimiento() {
        return nivelEnriquecimiento;
    }

    public void setNivelEnriquecimiento(long nivelEnriquecimiento) {
        this.nivelEnriquecimiento = nivelEnriquecimiento;
    }

    public long getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(long velocidad) {
        this.velocidad = velocidad;
    }

    public long getDistanciaPwA() {
        return distanciaPwA;
    }

    public void setDistanciaPwA(long distanciaPwA) {
        this.distanciaPwA = distanciaPwA;
    }

    public boolean isEstaHablando() {
        return estaHablando;
    }

    public void setEstaHablando(boolean estaHablando) {
        this.estaHablando = estaHablando;
    }

    public boolean isEstaBailando() {
        return estaBailando;
    }

    public void setEstaBailando(boolean estaBailando) {
        this.estaBailando = estaBailando;
    }

    public boolean isHayInteraccionFisica() {
        return hayInteraccionFisica;
    }

    public void setHayInteraccionFisica(boolean hayInteraccionFisica) {
        this.hayInteraccionFisica = hayInteraccionFisica;
    }

    public boolean isDetectaPwA() {
        return detectaPwA;
    }

    public void setDetectaPwA(boolean detectaPwA) {
        this.detectaPwA = detectaPwA;
    }

    public boolean isEstaReproduciendoCancion() {
        return estaReproduciendoCancion;
    }

    public void setEstaReproduciendoCancion(boolean estaReproduciendoCancion) {
        this.estaReproduciendoCancion = estaReproduciendoCancion;
    }


    
}
