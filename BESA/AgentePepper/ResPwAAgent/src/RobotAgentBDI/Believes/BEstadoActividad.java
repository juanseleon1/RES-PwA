/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;


import RobotAgentBDI.Believes.PerfilPwA.Cancion;
import RobotAgentBDI.Believes.PerfilPwA.Cuento;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwAActivity;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoActividad implements Believes{
    
    private long tiempoInicioActividad;
    private ResPwAActivity actividadActual;
    private boolean finalizoActividad;
    private boolean mejoraEmocional;
    private ResPwAStrategy estrategia;
    private Cancion cancionActual;
    private Cuento cuentoActual;
    private Integer boostActivarKaraoke;
    private Integer boostAnimarElogiarPwA;
    private Integer boostBailar;
    private Integer boostCambiarEnriquecimientoHistoria;
    private Integer boostConversarEmpaticamente;
    private Integer boostLogIn;
    private Integer boostMantenerAtencionPwA;
    private Integer boostPausarInteraccion;
    private Integer boostPedirAyuda;
    private Integer boostReanudarActividad;
    private Integer boostRecargarBateria;
    private Integer boostReiniciarActividad;
    private Integer boostSeleccionarCancionGusto;
    private Integer boostSeleccionarCuentoGusto;
    
    
    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoActividad update Received: "+si);
        return true;
    }

    public long getTiempoInicioActividad() {
        return tiempoInicioActividad;
    }

    public void setTiempoInicioActividad(long tiempoInicioActividad) {
        this.tiempoInicioActividad = tiempoInicioActividad;
    }

    public ResPwAActivity getActividadActual() {
        return actividadActual;
    }

    public void setActividadActual(ResPwAActivity actividadActual) {
        this.actividadActual = actividadActual;
    }

    public boolean isFinalizoActividad() {
        return finalizoActividad;
    }

    public Cancion getCancionActual() {
        return cancionActual;
    }

    public void setFinalizoActividad(boolean finalizoActividad) {
        this.finalizoActividad = finalizoActividad;
    }

    public boolean isMejoraEmocional() {
        return mejoraEmocional;
    }

    public void setMejoraEmocional(boolean mejoraEmocional) {
        this.mejoraEmocional = mejoraEmocional;
    }

    public ResPwAStrategy getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(ResPwAStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public long calcTiempoActividad() {
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());  
        long time = (ts.getTime() - tiempoInicioActividad)/1000;
        return time;
    }

    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }

    public void setCuentoActual(Cuento cuentoActual) {
        this.cuentoActual = cuentoActual;
    }

    public Cuento getCuentoActual() {
        return cuentoActual;
    }

    
}
