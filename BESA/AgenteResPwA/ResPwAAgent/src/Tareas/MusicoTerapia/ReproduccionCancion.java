/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas.MusicoTerapia;

import ResPwAEntities.Familiar;
import ResPwAEntities.Tags;
import ResPwaUtils.AudioUtils;
import ResPwaUtils.Imagen;
import ResPwaUtils.YTUtils;
import RobotAgentBDI.Believes.RobotAgentBelieves;
import rational.mapping.Believes;
import RobotAgentBDI.ResPwaTask;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import ServiceAgentResPwA.HumanServices.HumanServiceRequestType;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.TabletServices.TabletServiceRequestType;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mafegarces
 */
public class ReproduccionCancion extends ResPwaTask {

    private HashMap<String, Object> infoServicio = new HashMap<>();

    public ReproduccionCancion() {
//        System.out.println("--- Task Busqueda Cancion Iniciada ---");
    }

    @Override
    public void executeTask(Believes parameters) {
        System.out.println("--- Execute Task Busqueda Cancion ---");
        RobotAgentBelieves blvs = (RobotAgentBelieves) parameters;
        
        //Tener en cuenta opcion seleccionada para mandar info
        //Dependiendo de canción seleccionada, seleccionar baile->se mandan tags, cambio velocidad->segun cancion y activar letra

        if (1==0 && blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustokaraoke() > blvs.getbPerfilPwA().getPerfil().getPerfilPreferencia().getGustomusica()) {
            String urlcancion = YTUtils.searchYTVideo(blvs.getbEstadoActividad().getCancionActual().getNombre());
            infoServicio.put("SHOWVIDEO", urlcancion);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWVIDEO, infoServicio);
            requestService(srb,blvs);
        } else {

            List<Tags> tags = blvs.getbEstadoActividad().getCancionActual().getTagsList();
            List<Imagen> imagenes = new ArrayList<>();
            for (Tags t : tags) {
                imagenes.addAll(blvs.getImagexTag(t.getTag()));
            }

            //tags y parentesco -> si lo contiene tengo cuenta interes para usar esa foto
            List<Familiar> familiares = blvs.getbPerfilPwA().getPerfil().getFamiliarList();
            List<Imagen> imagenesFinal = new ArrayList<>();
            for (Familiar f : familiares) {
                if (f.getInteres() > 0.6) {
                    imagenesFinal.addAll(getImgxTag(f.getParentesco(), imagenes));   
                }
            }

            infoServicio = new HashMap<>();
            infoServicio.put("SHOWIMG", imagenesFinal);
            ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(TabletServiceRequestType.SHOWIMG, infoServicio);
            requestService(srb,blvs);
            
            infoServicio = new HashMap<>();
            String cancion = AudioUtils.getCancion("Feliz cumpleaños");
            infoServicio.put("PLAYSOUND", cancion);
            srb = ServiceRequestBuilder.buildRequest(VoiceServiceRequestType.PLAYSOUND, infoServicio);
            requestService(srb,blvs);
        }
    }

    @Override
    public void interruptTask(Believes believes) {
        System.out.println("--- Interrupt Task Busqueda Cancion ---");
        
    }

    @Override
    public void cancelTask(Believes believes) {
        System.out.println("--- Cancel Task Busqueda Cancion ---");
        
    }
    
    @Override
    public boolean checkFinish(Believes believes) {
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoInteraccion().isConfirmacionRepDisp() && !blvs.getbEstadoInteraccion().isConfirmacionRepAud()) {
            return true;
        }
        return false;
    }

    public List<Imagen> getImgxTag(String tag, List<Imagen> imgs) {
        List<Imagen> imagenes = new ArrayList<>();

        for (Imagen i : imgs) {
            for (String t : i.getTags()) {
                if (t.equalsIgnoreCase(tag)) {
                    imagenes.add(i);
                }
            }
        }
        return imagenes;
    }

}
