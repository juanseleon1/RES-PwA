package Init;

import BDInterface.RESPwABDInterface;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.System.AdmBESA;
import EmotionalAnalyzerAgent.EmotionalAnalyzerAgent;
import PepperPackage.PepperAdapter;
import PepperPackage.EmotionalModel.PepperEAStrategy;
import PepperPackage.EmotionalModel.PepperEmotionalModel;
import ResPwAEntities.Accion;
import ResPwAEntities.Emocion;
import ResPwAEntities.Joint;
import ResPwAEntities.Cuidador;
import ResPwAEntities.Perfilpwa;
import RobotAgentBDI.Metas.Cuenteria;
import RobotAgentBDI.RobotAgentBDI;
import RobotAgentBDI.Metas.LogIn;
import RobotAgentBDI.Metas.MantenerAtencionPwA;
import RobotAgentBDI.Metas.MusicoTerapia;
import RobotAgentBDI.Metas.PedirAyuda;
import RobotAgentBDI.Metas.RecargarBateria;
import RobotAgentBDI.Metas.Saludar;
import RobotAgentBDI.Metas.TestPlan;
import RobotAgentBDI.ServiceRequestDataBuilder.ServiceRequestBuilder;
import SensorHandlerAgent.SensorHandlerAgent;
import ServiceAgentResPwA.MovementServices.MovementServiceRequestType;
import ServiceAgentResPwA.RobotSPAgent;
import ServiceAgentResPwA.ServiceDataRequest;
import ServiceAgentResPwA.VoiceServices.VoiceServiceRequestType;
import Tareas.Test.TestTask;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans Cuidador--> crea perfil y crea pwas
 *
 * Autenticar. 1. Pepper se acerca. 2. Si identifica la cara, lo saluda e inicia
 * sesion. 3. Le avisa que no lo conoce y que hable con su cuidador para poder
 * hacerle un perfil. Conversacion Casual. "Hola, como estas hoy" y muestra en
 * tablet y espera respuesta oral. Como estas como te fue hoy. Y luego, lo
 * escucha por un rato. y le dice que hagan una actividad. * Luego empieza la
 * sesion. *
 */
public class InitRESPwA {

    public static String aliasRobotAgent = "RobotAgent";
    public static String aliasEAAgent = "EAAgent";
    public static String aliasSHAAgent = "SHAAgent";
    public static String aliasSPAgent = "SPAgent";
    public static String emf = "ResPwAEntitiesPU";
    private static int PLANID = 0;
    private static final double predefEmoState = 0.3;

    public static void main(String[] args) {
        try {
            String cedula = obtenerUsuario();
            AdmBESA.getInstance();
            System.out.println("Iniciando RES-PwA");
            RobotAgentBDI RABDI = new RobotAgentBDI(aliasRobotAgent, createRobotAgentGoals(), cedula);
            EmotionalAnalyzerAgent EAA = new EmotionalAnalyzerAgent(aliasEAAgent, new PepperEAStrategy());
            SensorHandlerAgent SHA = new SensorHandlerAgent(aliasSHAAgent);
            PepperAdapter p = new PepperAdapter();
            RobotSPAgent SPA = RobotSPAgent.buildRobotSPAgent(aliasSPAgent, p);
            startAllAgents(RABDI, EAA, SHA, SPA);
            HashMap<String, Object> hm1 = new HashMap<>();
            hm1.put("MOVETOX", 5);
            hm1.put("MOVETOY", 2);
            ServiceDataRequest data = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.MOVETO, hm1);
            p.sendRequest(data);
            startConfig(p);
            System.out.println("Fin Main ");

        } catch (ExceptionBESA ex) {
            Logger.getLogger(InitRESPwA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InitRESPwA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String obtenerUsuario() {
        String cedula = null, user = "juleon", pwd = "12345";
        boolean login = false;
        Scanner scan = new Scanner(System.in);
        Cuidador c = null;

        do {
            System.out.println("Ingrese su nombre de usuario: ");
            //user=scan.nextLine();
            System.out.println("Ingrese su contrasena: ");
//          pwd= scan.nextLine();
            c = RESPwABDInterface.getCarer(user);
            if (c == null) {
                System.out.println("Usuario Inexistente");
            } else {
                login = c.getContraseña().equals(pwd);
                if (!login) {
                    System.out.println("Contrasena no coincide");
                }
            }

        } while (!login);
        List<Perfilpwa> pwalist = c.getPerfilpwaList();

        for (int i = 0; i < pwalist.size(); i++) {
            System.out.println(i + " Paciente: " + pwalist.get(i).getCedula());
        }
        System.out.println("Ingrese el numero del paciente que utilizara ResPwa");
        int selec = 0; //scan.nextInt();
        return pwalist.get(selec).getCedula();
    }

    public static int getPlanID() {
        return ++PLANID;
    }

    private static List<GoalBDI> createRobotAgentGoals() {
        List<GoalBDI> RAGoals = new ArrayList<>();
        //Crear Metas
//        Cuenteria cuenteriaGoal = Cuenteria.buildGoal();
//        MusicoTerapia musicoTGoal= MusicoTerapia.buildGoal();
//        TestPlan tp = TestPlan.buildGoal();
//        LogIn logInGoal = LogIn.buildGoal();
//        MantenerAtencionPwA mantenerAtencionPwAGoal=  MantenerAtencionPwA.buildGoal();
//        PausarInteraccion pausarInteraccionGoal=  PausarInteraccion.buildGoal();
//        ReanudarActividad reanudarActividadGoal=  ReanudarActividad.buildGoal();
//        RecargarBateria recargarBateriaGoal=  RecargarBateria.buildGoal();
//        PedirAyuda pedirAyudaGoal= PedirAyuda.buildGoal();
//        ReiniciarActividad reiniciarActividadGoal=  ReiniciarActividad.buildGoal();
//          Saludar saludar = Saludar.buildGoal();
        //Agregar a Lista
//        RAGoals.add(cuenteriaGoal);
//        RAGoals.add(tp);
//        RAGoals.add(musicoTGoal);
//        RAGoals.add(logInGoal);
//        RAGoals.add(mantenerAtencionPwAGoal);
//        RAGoals.add(pausarInteraccionGoal);
//        RAGoals.add(reanudarActividadGoal);
//        RAGoals.add(recargarBateriaGoal);
//        RAGoals.add(pedirAyudaGoal);
//        RAGoals.add(reiniciarActividadGoal);
//          RAGoals.add(saludar);
//      CambiarDificultad cambiarDificultadGoal=  CambiarDificultad.buildGoal();
//      EstimularEmocionalmente estimularEmocionalmenteGoal=  EstimularEmocionalmente.buildGoal();
//      RAGoals.add(cambiarDificultadGoal);
//      RAGoals.add(estimularEmocionalmenteGoal);

        return RAGoals;
    }

    private static void startAllAgents(RobotAgentBDI RABDI, EmotionalAnalyzerAgent EAA, SensorHandlerAgent SHA, RobotSPAgent SPA) throws ExceptionBESA {
        RABDI.start();
        SPA.start();
        EAA.start();
        SHA.start();
        SHA.subscribeServices();
        RABDI.startTimers();
    }

    public static void startConfig(PepperAdapter p) {
        List<Emocion> emociones = RESPwABDInterface.getEmociones();
        HashMap<String, Object> infoServicio = new HashMap<>();
        HashMap<String, Object> params = new HashMap<>();
        HashMap<String, Object> accion = new HashMap<>();
        HashMap<String, Object> joint = new HashMap<>();
        List<HashMap<String, Object>> joints = new ArrayList<>();
        List<HashMap<String, Object>> paramList = new ArrayList<>();

        for (Emocion e: emociones){
            params.put(e.getEmotionalTag(), new ArrayList<>());
        }

        for (String i : params.keySet()){
            for (Emocion e : emociones){
                if (i.equals(e.getEmotionalTag())){
                    accion = new HashMap<>();
                    for (Accion a : e.getAccionList()){
                        joints = new ArrayList<>();
                        for (Joint j : a.getJointList()){
                            joint = new HashMap<>();
                            joint.put("name",j.getNombre());
                            joint.put("time",j.getTiempo());
                            joint.put("key",j.getAngulo());
                            joints.add(joint);
                        }
                        accion.put(a.getNombre(), joints);
                    }
                    paramList = (List<HashMap<String,Object>>)params.get(e.getEmotionalTag());
                    paramList.add(accion);
                    params.put(e.getEmotionalTag(), paramList);
                }
            }
        }

        infoServicio.put("INITIALCONF", params);
        ServiceDataRequest srb = ServiceRequestBuilder.buildRequest(MovementServiceRequestType.INITIALCONF, infoServicio);
        p.sendRequest(srb);
    }

}
