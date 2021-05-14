///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Retroalimentacion.Modelo;
//
//import Personalizacion.Modelo.CromosomaCancion;
//import Personalizacion.Modelo.ModeloSeleccion;
//import ResPwAEntities.Antecedente;
//import ResPwAEntities.Cancion;
//import ResPwAEntities.Regla;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author ASUS
// */
//public class ModeloRetroalimentacionTest {
//    
//     public static void main(String[] args){
//         pruebaModeloCancion(); 
//     }
//     
//     public static void pruebaModeloCancion(){
//        System.out.println("CANCION");
//        List<Cancion> canciones = new ArrayList<>();
//        Cancion cancion0 = new Cancion("awita0", 1.0);
//        List<Regla> reglas = new ArrayList<>();
//        List<Antecedente> antecedentesList0 = new ArrayList<>();
//        List<Antecedente> antecedentesList1 = new ArrayList<>();
//        List<Antecedente> antecedentesList2 = new ArrayList<>();
//        List<Antecedente> antecedentesListPrueba = new ArrayList<>();
//
//        Antecedente antecedente0 = new Antecedente("BAD_FEEDBACK_VOICE", 0.0);
//        Antecedente antecedente1 = new Antecedente("BAD_FEEDBACK_EMOTION", 0.0);
//        Antecedente antecedente2 = new Antecedente("REGULAR_FEEDBACK_VOICE", 0.5);
//        Antecedente antecedente3 = new Antecedente("REGULAR_FEEDBACK_EMOTION", 0.5);
//        Antecedente antecedente4 = new Antecedente("GOOD_FEEDBACK_VOICE", 1.0);
//        Antecedente antecedente5 = new Antecedente("GOOD_FEEDBACK_EMOTION", 1.0);
//        
//        Antecedente antecedentePruebaRetroalimentacion = new Antecedente("BAD_FEEDBACK_VOICE", 1.0);
//        Antecedente antecedentePruebaRetroalimentacion1 = new Antecedente("BAD_FEEDBACK_EMOTION", 1.0);
//        
//        antecedentesListPrueba.add(antecedentePruebaRetroalimentacion);
//        antecedentesListPrueba.add(antecedentePruebaRetroalimentacion1);
//        
//        antecedentesList0.add(antecedente0);
//        antecedentesList0.add(antecedente1);
//        antecedentesList1.add(antecedente2);
//        antecedentesList1.add(antecedente3);
//        antecedentesList2.add(antecedente4);
//        antecedentesList2.add(antecedente5);
//        
//        Regla regla = new Regla(0L, -0.15, "BAD_FEEDBACK_SONG", antecedentesList0);
//        Regla regla1 = new Regla(1L, 0.05, "REGULAR_FEEDBACK_SONG", antecedentesList1);
//        Regla regla2 = new Regla(2L, 0.15, "GOOD_FEEDBACK_SONG", antecedentesList2);
//        
//        
//        reglas.add(regla);
//        reglas.add(regla1);
//        reglas.add(regla2);
//        
//        canciones.add(cancion0);
//        
//        
//        ModeloRetroalimentacion<Cancion> retroalimentacionCancion = new ModeloRetroalimentacion<>(cancion0, reglas);
//        
//        retroalimentacionCancion.activityFeedback(antecedentesListPrueba);        
//        
//         System.out.println(cancion0.toString());
//    }
//}
