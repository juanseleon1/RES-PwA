/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Baile;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Registroactividad;
import ResPwAEntities.RegistroactividadPK;
import RobotAgentBDI.ResPwAStrategy;
import RobotAgentBDI.ResPwAActivity;
import SensorHandlerAgent.SensorData;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author mafegarces
 */
public class BEstadoActividad implements Believes {

    private long tiempoInicioActividad = 0;
    private ResPwAActivity actividadActual=ResPwAActivity.CUENTERIA;
    private String estadoInit = null;
    private boolean actividadEnCurso = false;
    private boolean mejoraEmocional = false;
    private ResPwAStrategy estrategia;
    private Cancion cancionActual;
    private Cuento cuentoActual;
    private Baile baileActual;
    private List<Baile> bailes;
    private Integer boostActivarKaraoke = 0;
    private Integer boostAnimarElogiarPwA = 0;
    private Integer boostBailar = 0;
    private Integer boostCambiarEnriquecimientoHistoria = 0;
    private Integer boostCancelarActividad = 0;
    private Integer boostConversarEmpaticamente = 0;
    private Integer boostLogIn = 0;
    private Integer boostMantenerAtencionPwA = 0;
    private Integer boostPausarInteraccion = 0;
    private Integer boostPedirAyuda = 0;
    private Integer boostReanudarActividad = 0;
    private Integer boostRecargarBateria = 0;
    private Integer boostReiniciarActividad = 0;
    private Integer boostSeleccionarCancionGusto = 0;
    private Integer boostSeleccionarCuentoGusto = 0;
    private String cedula;
    private Integer indexCuento = 0;
    private RobotAgentBelieves blvs = null;

    public BEstadoActividad(String cedula, RobotAgentBelieves blvs) {
        this.cedula = cedula;
        this.blvs = blvs;
    }

    @Override
    public boolean update(InfoData si) {
        System.out.println("BEstadoActividad update Received: " + si);
        SensorData infoRecibida = (SensorData) si;
        if (infoRecibida.getDataP().containsKey("actividadEnCurso")) {
            actividadEnCurso = Boolean.valueOf((String) infoRecibida.getDataP().get("actividadEnCurso"));
            if (actividadEnCurso) {
                tiempoInicioActividad = System.currentTimeMillis();
//                estadoInit = blvs.getbEstadoEmocionalRobot().getEm().getState().getDominantEmotion().toString();
            } else {

                tiempoInicioActividad = 0;
                createNewInteResgistry();
            }
        }
        return true;
    }

    public long getTiempoInicioActividad() {
        return System.currentTimeMillis() - tiempoInicioActividad;
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
        long time = -1;
        if (tiempoInicioActividad != 0) {
            Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
            time = (ts.getTime() - tiempoInicioActividad) / 1000;
        }

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

    public List<Baile> getBailes() {
        return bailes;
    }

    public void setBailes(List<Baile> bailes) {
        this.bailes = bailes;
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

    public void createNewInteResgistry() {
        RegistroactividadPK ractPK = new RegistroactividadPK(Date.valueOf(LocalDate.now()), actividadActual.getTipo());
        Registroactividad ract = new Registroactividad(ractPK);
        List<Actividadpwa> list = RESPwABDInterface.getActivities();
        list.stream().filter((apwa) -> (apwa.getNombre().equalsIgnoreCase(actividadActual.toString()))).forEachOrdered((apwa) -> {
            ract.setActividadpwa(apwa);
        });
        ract.setEstadoinicial(cedula);
        ract.setEstadofinal(cedula);
        ract.setPerfilpwa(blvs.getbPerfilPwA().getPerfil());
        RESPwABDInterface.createRegistroAct(ract);
    }

    public String getEstadoInit() {
        return estadoInit;
    }

    public void setEstadoInit(String estadoInit) {
        this.estadoInit = estadoInit;
    }

    public boolean isActividadEnCurso() {
        return actividadEnCurso;
    }

    public void setActividadEnCurso(boolean actividadEnCurso) {
        this.actividadEnCurso = actividadEnCurso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getIndexCuento() {
        return indexCuento;
    }

    public void setIndexCuento(Integer indexCuento) {
        this.indexCuento = indexCuento;
    }

    public Baile getBaileActual() {
        return baileActual;
    }

    public void setBaileActual(Baile baileActual) {
        this.baileActual = baileActual;
    }
    
    
    

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

}
