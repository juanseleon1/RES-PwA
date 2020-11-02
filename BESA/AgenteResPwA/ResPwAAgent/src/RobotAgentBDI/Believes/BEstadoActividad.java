/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;




import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwAActivity;
import SensorHandlerAgent.SensorData;
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
    private boolean actividadEnCurso;
    private boolean mejoraEmocional;
    private ResPwAStrategy estrategia;
    private Cancion cancionActual;
    private Cuento cuentoActual;
    private Integer boostActivarKaraoke;
    private Integer boostAnimarElogiarPwA;
    private Integer boostBailar;
    private Integer boostCambiarEnriquecimientoHistoria;
    private Integer boostCancelarActividad;
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
        SensorData infoRecibida= (SensorData)si;
        if(infoRecibida.getDataP().containsKey("actividadEnCurso"))
        {
            actividadEnCurso= Boolean.valueOf((String)infoRecibida.getDataP().get("actividadEnCurso"));
            if(actividadEnCurso)
                tiempoInicioActividad=System.currentTimeMillis();
            else{
                
                tiempoInicioActividad=0;
            }
        }
        return true;
    }

    public long getTiempoInicioActividad() {
        return System.currentTimeMillis()-tiempoInicioActividad;
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
        return actividadEnCurso;
    }

    public Cancion getCancionActual() {
        return cancionActual;
    }

    public void setFinalizoActividad(boolean finalizoActividad) {
        this.actividadEnCurso = finalizoActividad;
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

    public Integer getBoostActivarKaraoke() {
        return boostActivarKaraoke;
    }

    public void setBoostActivarKaraoke(Integer boostActivarKaraoke) {
        this.boostActivarKaraoke = boostActivarKaraoke;
    }

    public Integer getBoostAnimarElogiarPwA() {
        return boostAnimarElogiarPwA;
    }

    public void setBoostAnimarElogiarPwA(Integer boostAnimarElogiarPwA) {
        this.boostAnimarElogiarPwA = boostAnimarElogiarPwA;
    }

    public Integer getBoostBailar() {
        return boostBailar;
    }

    public void setBoostBailar(Integer boostBailar) {
        this.boostBailar = boostBailar;
    }

    public Integer getBoostCambiarEnriquecimientoHistoria() {
        return boostCambiarEnriquecimientoHistoria;
    }

    public void setBoostCambiarEnriquecimientoHistoria(Integer boostCambiarEnriquecimientoHistoria) {
        this.boostCambiarEnriquecimientoHistoria = boostCambiarEnriquecimientoHistoria;
    }

    public Integer getBoostConversarEmpaticamente() {
        return boostConversarEmpaticamente;
    }

    public void setBoostConversarEmpaticamente(Integer boostConversarEmpaticamente) {
        this.boostConversarEmpaticamente = boostConversarEmpaticamente;
    }

    public Integer getBoostLogIn() {
        return boostLogIn;
    }

    public void setBoostLogIn(Integer boostLogIn) {
        this.boostLogIn = boostLogIn;
    }

    public Integer getBoostMantenerAtencionPwA() {
        return boostMantenerAtencionPwA;
    }

    public void setBoostMantenerAtencionPwA(Integer boostMantenerAtencionPwA) {
        this.boostMantenerAtencionPwA = boostMantenerAtencionPwA;
    }

    public Integer getBoostPausarInteraccion() {
        return boostPausarInteraccion;
    }

    public void setBoostPausarInteraccion(Integer boostPausarInteraccion) {
        this.boostPausarInteraccion = boostPausarInteraccion;
    }

    public Integer getBoostPedirAyuda() {
        return boostPedirAyuda;
    }

    public void setBoostPedirAyuda(Integer boostPedirAyuda) {
        this.boostPedirAyuda = boostPedirAyuda;
    }

    public Integer getBoostReanudarActividad() {
        return boostReanudarActividad;
    }

    public void setBoostReanudarActividad(Integer boostReanudarActividad) {
        this.boostReanudarActividad = boostReanudarActividad;
    }

    public Integer getBoostRecargarBateria() {
        return boostRecargarBateria;
    }

    public void setBoostRecargarBateria(Integer boostRecargarBateria) {
        this.boostRecargarBateria = boostRecargarBateria;
    }

    public Integer getBoostReiniciarActividad() {
        return boostReiniciarActividad;
    }

    public void setBoostReiniciarActividad(Integer boostReiniciarActividad) {
        this.boostReiniciarActividad = boostReiniciarActividad;
    }

    public Integer getBoostSeleccionarCancionGusto() {
        return boostSeleccionarCancionGusto;
    }

    public void setBoostSeleccionarCancionGusto(Integer boostSeleccionarCancionGusto) {
        this.boostSeleccionarCancionGusto = boostSeleccionarCancionGusto;
    }

    public Integer getBoostSeleccionarCuentoGusto() {
        return boostSeleccionarCuentoGusto;
    }

    public void setBoostSeleccionarCuentoGusto(Integer boostSeleccionarCuentoGusto) {
        this.boostSeleccionarCuentoGusto = boostSeleccionarCuentoGusto;
    }

    public Integer getBoostCancelarActividad() {
        return boostCancelarActividad;
    }

    public void setBoostCancelarActividad(Integer boostCancelarActividad) {
        this.boostCancelarActividad = boostCancelarActividad;
    }

    
}
