/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.Cuenteria;

import Personalizacion.Modelo.CromosomaCuento;
import Personalizacion.Modelo.ModeloSeleccion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Preferenciaxcuento;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import Utils.ResPwaUtils;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author mafegarces
 */
public class SeleccionarCuento extends Task {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public SeleccionarCuento() {
  System.out.println("--- Task Recomendar Cuento Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Recomendar Cuento ---");
        //buscar cuento
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now());
        blvs.getbEstadoActividad().setTiempoInicioActividad(ts.getTime());
        if (!blvs.getbEstadoRobot().isStoryMode()) {
            blvs.getbEstadoRobot().setStoryMode(true);
        }
        List<Preferenciaxcuento> cuentos = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxcuentoList();
        ModeloSeleccion<Preferenciaxcuento> modeloCuento = new ModeloSeleccion<Preferenciaxcuento>(cuentos);
        Preferenciaxcuento cuentoSelected = null;
        CromosomaCuento cromosoma = null;
        cromosoma = (CromosomaCuento) modeloCuento.selectCromosoma();

        if (cromosoma != null) {
            cuentoSelected = cromosoma.getCuento();
            blvs.getbEstadoActividad().setCuentoActual(cuentoSelected);
            infoServicio.put("SAY", "Voy a contarte el cuento de " + cuentoSelected.getCuento().getNombre());
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.SAY, infoServicio);
            ResPwaUtils.requestService(srb, blvs);
        }
        blvs.getbEstadoActividad().setIndexCuento(0);
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Recomendar Cuento ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setStoryMode(false);

    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Recomendar Cuento ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        blvs.getbEstadoRobot().setStoryMode(false);

    }

    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if (blvs.getbEstadoActividad().getCuentoActual() != null) {
            return true;
        }
        return false;
    }

}
