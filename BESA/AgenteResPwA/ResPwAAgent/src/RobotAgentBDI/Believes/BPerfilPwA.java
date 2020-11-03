package RobotAgentBDI.Believes;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Perfilpwa;
import RobotAgentBDI.ResPwAActivity;
import SensorHandlerAgent.SensorData;
import java.math.BigInteger;
import java.util.List;
import rational.data.InfoData;
import rational.mapping.Believes;

public class BPerfilPwA implements Believes {

    private Perfilpwa perfil;
    private RobotAgentBelieves blvs;

    public BPerfilPwA(RobotAgentBelieves blvs) {
        this.blvs = blvs;
    }

    public Perfilpwa getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfilpwa perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean update(InfoData si) {
        SensorData infoRecibida = (SensorData) si;
        if (infoRecibida.getDataP().containsKey("retroalimCancion")) {
            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimCancion")).getCambio();
            double gustoC = blvs.getbEstadoActividad().getCancionActual().getGusto();
            gustoC += (gustoC * porc);
            blvs.getbEstadoActividad().getCancionActual().setGusto(gustoC);
            Cancion c= blvs.getbEstadoActividad().getCancionActual();
            RESPwABDInterface.updateCancion(c);
        }
        if (infoRecibida.getDataP().containsKey("retroalimCuento")) {
            double gustoC = blvs.getbEstadoActividad().getCuentoActual().getGusto();
            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimCuento")).getCambio();
            gustoC += (gustoC * porc);
            blvs.getbEstadoActividad().getCuentoActual().setGusto(gustoC);
            Cuento c= blvs.getbEstadoActividad().getCuentoActual();
            RESPwABDInterface.updateCuento(c);
        }
        if (infoRecibida.getDataP().containsKey("retroalimActividad")) {
            ResPwAActivity act = blvs.getbEstadoActividad().getActividadActual();
            List<Actividadpwa> acts = RESPwABDInterface.getActivities();
            BigInteger bi = null;
            for (Actividadpwa a : acts) {
                if (a.getNombre().equalsIgnoreCase(act.toString())) {
                    bi = a.getId();
                    break;
                }

            }
            List<Actxpreferencia> listap = perfil.getPerfilPreferencia().getActxpreferenciaList();
            Actxpreferencia pref = null;
            for (Actxpreferencia axp : listap) {
                if (axp.getActxpreferenciaPK().getActividadpwaId().equals(bi)) {
                    pref = axp;
                    break;
                }
            }

            double gustoC = pref.getGusto();
            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimActividad")).getCambio();
            gustoC += (gustoC * porc);
            pref.setGusto(gustoC);
            RESPwABDInterface.updateActXPref(pref);
        }
        return true;
    }

    void getFromDB(String cedula) {
        perfil = RESPwABDInterface.getProfile(cedula);
    }
        @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }
}
