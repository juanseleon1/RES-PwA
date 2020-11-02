/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import SensorHandlerAgent.SensorData;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *  
 * @author mafegarces
 */
public class BEstadoInteraccion implements Believes{
    private boolean logged=false;
    private boolean cambioDificultadVoz=false;
    private boolean ayudaActividadSolicitada=false;
    private boolean quiereEnriquec=false;
    private boolean pausarInt=false;
    private boolean cancelarInt=false;
    private boolean reiniciarInt=false;
    private long tiempoSinInt=0;
    private boolean sistemaSuspendido=false;
    private boolean sistemaSuspendidoInt=false;
    private long nivelEnriquecimiento=0;
    private long velocidadAnim=0;
    private long distanciaPwA=0;
    private boolean estaHablando=false;
    private boolean estaMoviendo=false;
    private boolean desplazandose=false;
    private boolean quiereCantar=false;
    private boolean hayInteraccionFisica = false;
    private boolean detectaPwA = false;
    private boolean confirmacionRepDisp=false;
    private boolean confirmacionRepAud=false;
    private static final long MAXENRIQ=4;
    private String keyNameConf= "confReproduccion";

    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoInteraccion update Received: "+si);
        SensorData infoRecibida= (SensorData)si;
        if(infoRecibida.getDataP().containsKey(keyNameConf+"Display"))
        {
            confirmacionRepDisp= Boolean.valueOf((String)infoRecibida.getDataP().get(keyNameConf+"Display"));
        }
        if(infoRecibida.getDataP().containsKey(keyNameConf+"Audio"))
        {
            confirmacionRepAud= Boolean.valueOf((String)infoRecibida.getDataP().get(keyNameConf+"Audio"));
        }
        if(infoRecibida.getDataP().containsKey("hayPwA")){
            detectaPwA= Boolean.valueOf((String)infoRecibida.getDataP().get("hayPwA"));
            if(detectaPwA)
                tiempoSinInt=0;
            else
            tiempoSinInt=System.currentTimeMillis();
        }
        if(infoRecibida.getDataP().containsKey("canto")){
            quiereCantar= Boolean.valueOf((String)infoRecibida.getDataP().get("canto"));

        }if(infoRecibida.getDataP().containsKey("enriq")){
            quiereEnriquec= Boolean.valueOf((String)infoRecibida.getDataP().get("enriq"));
            if(quiereEnriquec && nivelEnriquecimiento < MAXENRIQ)
                nivelEnriquecimiento++;
            else if(!quiereEnriquec && nivelEnriquecimiento>0)
                nivelEnriquecimiento--;
        }if(infoRecibida.getDataP().containsKey("suspension")){
            sistemaSuspendido= Boolean.valueOf((String)infoRecibida.getDataP().get("suspension"));
            
        }if(infoRecibida.getDataP().containsKey("pausarint")){
           pausarInt = Boolean.valueOf((String)infoRecibida.getDataP().get("pausarint"));
            
        }if(infoRecibida.getDataP().containsKey("cancelarint")){
           cancelarInt = Boolean.valueOf((String)infoRecibida.getDataP().get("cancelarint"));
            
        }if(infoRecibida.getDataP().containsKey("reiniciarint")){
           reiniciarInt = Boolean.valueOf((String)infoRecibida.getDataP().get("reiniciarint"));
            
        }if(infoRecibida.getDataP().containsKey("distancia")){
          distanciaPwA  = Long.valueOf((String)infoRecibida.getDataP().get("distancia"));
           
        }if(infoRecibida.getDataP().containsKey("hablando")){
          estaHablando = Boolean.valueOf((String)infoRecibida.getDataP().get("hablando"));
            
        }if(infoRecibida.getDataP().containsKey("moviendo")){
          estaMoviendo = Boolean.valueOf((String)infoRecibida.getDataP().get("moviendo"));
            
        }if(infoRecibida.getDataP().containsKey("desplazandose")){
          desplazandose = Boolean.valueOf((String)infoRecibida.getDataP().get("desplazandose"));
            
        }if(infoRecibida.getDataP().containsKey("fisicaint")){
           hayInteraccionFisica = Boolean.valueOf((String)infoRecibida.getDataP().get("fisicaint"));
        }     
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

    public boolean isQuiereEnriquec() {
        return quiereEnriquec;
    }

    public void setQuiereEnriquec(boolean quiereEnriquec) {
        this.quiereEnriquec = quiereEnriquec;
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
        return System.currentTimeMillis()- tiempoSinInt;
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
        return velocidadAnim;
    }

    public void setVelocidad(long velocidad) {
        this.velocidadAnim = velocidad;
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
        return estaMoviendo;
    }

    public void setEstaBailando(boolean estaBailando) {
        this.estaMoviendo = estaBailando;
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

    public boolean isQuiereCantar() {
        return quiereCantar;
    }

    public void setQuiereCantar(boolean quiereCantar) {
        this.quiereCantar = quiereCantar;
    }

    public long getVelocidadAnim() {
        return velocidadAnim;
    }

    public void setVelocidadAnim(long velocidadAnim) {
        this.velocidadAnim = velocidadAnim;
    }

    public boolean isEstaMoviendo() {
        return estaMoviendo;
    }

    public void setEstaMoviendo(boolean estaMoviendo) {
        this.estaMoviendo = estaMoviendo;
    }

    public boolean isDesplazandose() {
        return desplazandose;
    }

    public void setDesplazandose(boolean desplazandose) {
        this.desplazandose = desplazandose;
    }

    public boolean isConfirmacionRepDisp() {
        return confirmacionRepDisp;
    }

    public void setConfirmacionRepDisp(boolean confirmacionRepDisp) {
        this.confirmacionRepDisp = confirmacionRepDisp;
    }

    public boolean isConfirmacionRepAud() {
        return confirmacionRepAud;
    }

    public void setConfirmacionRepAud(boolean confirmacionRepAud) {
        this.confirmacionRepAud = confirmacionRepAud;
    }

    public String getKeyNameConf() {
        return keyNameConf;
    }

    public void setKeyNameConf(String keyNameConf) {
        this.keyNameConf = keyNameConf;
    }

    public boolean isSistemaSuspendidoInt() {
        return sistemaSuspendidoInt;
    }

    public void setSistemaSuspendidoInt(boolean sistemaSuspendidoInt) {
        this.sistemaSuspendidoInt = sistemaSuspendidoInt;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    
    
}
