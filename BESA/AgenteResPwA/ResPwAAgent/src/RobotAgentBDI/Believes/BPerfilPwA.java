package RobotAgentBDI.Believes;

import BDInterface.RESPwABDInterface;
import Personalizacion.Modelo.CromosomaCancion;
import Personalizacion.Modelo.ModeloSeleccion;
import ResPwAEntities.Actividadpwa;
import ResPwAEntities.Actxpreferencia;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Perfilpwa;
import ResPwAEntities.Preferenciaxcancion;
import RobotAgentBDI.Utils.ResPwAActivity;
import SensorHandlerAgent.Guards.SensorData;
import Tareas.AnimarElogiarPwA.OpcionesAnimar;
import Tareas.MantenerAtencionPwA.OpcionesAtencion;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
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
//        SensorData infoRecibida = (SensorData) si;
//        if (infoRecibida.getDataP().containsKey("retroalimCancion")) {
//            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimCancion")).getCambio();
//            double gustoC = blvs.getbEstadoActividad().getCancionActual().getGusto();
//            gustoC += (gustoC * porc);
//            blvs.getbEstadoActividad().getCancionActual().setGusto(gustoC);
//            Cancion c= blvs.getbEstadoActividad().getCancionActual();
//            RESPwABDInterface.updateCancion(c);
//        }
//        if (infoRecibida.getDataP().containsKey("retroalimCuento")) {
//            double gustoC = blvs.getbEstadoActividad().getCuentoActual().getGusto();
//            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimCuento")).getCambio();
//            gustoC += (gustoC * porc);
//            blvs.getbEstadoActividad().getCuentoActual().setGusto(gustoC);
//            Cuento c= blvs.getbEstadoActividad().getCuentoActual();
//            RESPwABDInterface.updateCuento(c);
//        }
//        if (infoRecibida.getDataP().containsKey("retroalimActividad")) {
//            ResPwAActivity act = blvs.getbEstadoActividad().getActividadActual();
//            List<Actividadpwa> acts = RESPwABDInterface.getActivities();
//            BigInteger bi = null;
//            for (Actividadpwa a : acts) {
//                if (a.getNombre().equalsIgnoreCase(act.toString())) {
//                    bi = a.getId();
//                    break;
//                }
//
//            }
//            List<Actxpreferencia> listap = perfil.getPerfilPreferencia().getActxpreferenciaList();
//            Actxpreferencia pref = null;
//            for (Actxpreferencia axp : listap) {
//                if (axp.getActxpreferenciaPK().getActividadpwaId().equals(bi)) {
//                    pref = axp;
//                    break;
//                }
//            }
//
//            double gustoC = pref.getGusto();
//            double porc = ReSPwARACategories.valueOf((String) infoRecibida.getDataP().get("retroalimActividad")).getCambio();
//            gustoC += (gustoC * porc);
//            pref.setGusto(gustoC);
//            RESPwABDInterface.updateActXPref(pref);
//        }
        
        return true;
    }

  public  Cancion selectSong( ) { // BEstadoEmocionalPwa estadoEmocional,
//        si la emocion es displacentera se traen las canciones aptas para dar el soporte y se toma aleatoriamente
        
//        si la emocion es placentera, la cancion se escoge por un factor
        
        List<Preferenciaxcancion> canciones = blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getPreferenciaxcancionList();
        ModeloSeleccion modeloSeleccionCancion = new ModeloSeleccion(canciones);
        
        CromosomaCancion cromCancion = (CromosomaCancion) modeloSeleccionCancion.selectCromosoma();
        Cancion songSelected = cromCancion.getCancion().getCancion();
          
        return songSelected;
    }
    
//    double getFactor( List<Cancion> canciones ){
//        double factor = Math.abs(Math.random() - 0.5);
//        double prom = 0.0;
//        int contadorCancionesMayoresFactor = 0;
//        for (Cancion cancion : canciones) {
//            if( cancion.getGusto() > factor ){
//                contadorCancionesMayoresFactor ++;
//            }
//            prom += cancion.getGusto();
//        }
//        if (contadorCancionesMayoresFactor <= 1 ){
//            prom = prom / canciones.size();
//            factor = prom;
//        }
//        
//        return factor;
//    }
    
 
        @Override
    public Believes clone() throws CloneNotSupportedException {
        super.clone();
        return this;
    }
    
    public OpcionesAtencion getAtencionStrategy()
    {
        Random rand = new Random();
        OpcionesAtencion[]opcs = OpcionesAtencion.values();
        return opcs[rand.nextInt(opcs.length)];
    }

    @Override
    public String toString() {
        return "BPerfilPwA{" + "perfil=" + perfil + ", blvs=" + blvs + '}';
    }
    
    
}
