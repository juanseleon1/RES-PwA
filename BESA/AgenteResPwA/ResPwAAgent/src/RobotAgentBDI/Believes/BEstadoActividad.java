/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Baile;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Preferenciaxbaile;
import ResPwAEntities.Preferenciaxcancion;
import ResPwAEntities.Preferenciaxcuento;
import ResPwAEntities.Registroactividad;
import ResPwAEntities.RegistroactividadPK;
import RobotAgentBDI.Utils.ResPwAStrategy;
import RobotAgentBDI.Utils.ResPwAActivity;
import SensorHandlerAgent.Guards.SensorData;
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
    private ResPwAActivity actividadActual = ResPwAActivity.CUENTERIA;
    private String estadoInit = null;
    private boolean actividadEnCurso = false;
    private boolean mejoraEmocional = false;
    private ResPwAStrategy estrategia;
    private Preferenciaxcancion cancionActual;
    private Preferenciaxcuento cuentoActual;
    private Preferenciaxbaile baileActual;
    private List<Baile> bailes;
    private String cedula;
    private Integer indexCuento = 0;
    private RobotAgentBelieves blvs = null;
    private boolean estaBailando;
    private boolean estaMoviendo;

    public BEstadoActividad(String cedula, RobotAgentBelieves blvs) {
        this.cedula = cedula;
        this.blvs = blvs;
        this.estaBailando = false;
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

        if (infoRecibida.getDataP().containsKey("finishAnim")) {
            estaBailando = (boolean) infoRecibida.getDataP().get("finishAnim");
        }
        if (infoRecibida.getDataP().containsKey("finishAnim")) {
            estaMoviendo = (boolean) infoRecibida.getDataP().get("finishAnim");

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

    public double getGustoActividad(ResPwAActivity actividad) {
        double gusto = 0;
        for (Actxpreferencia a : blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getActxpreferenciaList()) {
            if (a.getActividadpwa().getNombre().equalsIgnoreCase(actividad.toString())) {
                gusto = a.getGusto();
            }
        }
        return gusto;
    }

    public void setActividadActual(ResPwAActivity actividadActual) {
        this.actividadActual = actividadActual;
    }

    public boolean isFinalizoActividad() {
        return actividadEnCurso;
    }

    public Preferenciaxcancion getCancionActual() {
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

    public void setCancionActual(Preferenciaxcancion cancionActual) {
        this.cancionActual = cancionActual;
    }

    public void setCuentoActual(Preferenciaxcuento cuentoActual) {
        this.cuentoActual = cuentoActual;
    }

    public Preferenciaxcuento getCuentoActual() {
        return cuentoActual;
    }

    public List<Baile> getBailes() {
        return bailes;
    }

    public void setBailes(List<Baile> bailes) {
        this.bailes = bailes;
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

    public Preferenciaxbaile getBaileActual() {
        return baileActual;
    }

    public void setBaileActual(Preferenciaxbaile baileActual) {
        this.baileActual = baileActual;
    }

    public boolean isEstaBailando() {
        return estaBailando;
    }

    public void setEstaBailando(boolean estaBailando) {
        this.estaBailando = estaBailando;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }

    public boolean isEstaMoviendo() {
        return estaMoviendo;
    }

    public void setEstaMoviendo(boolean estaMoviendo) {
        this.estaMoviendo = estaMoviendo;
    }

}
