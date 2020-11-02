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

    
}
