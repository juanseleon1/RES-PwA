/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class TestingModeloSeleccion {

    public static void main(String args[]) {
        pruebaModeloCuento();
        pruebaModeloCancion();

    }
    
    public static void pruebaModeloCuento(){
        System.out.println("CUENTO");
        List<Cuento> cuentos = new ArrayList<>();
        Cuento cuento0 = new Cuento("awita0","Rafael Pombo", 0.6);
        Cuento cuento1 = new Cuento("awita1","Rafael Pombo", 0.6);
        Cuento cuento2 = new Cuento("awita2","Rafael Pombo", 0.6);
        Cuento cuento3 = new Cuento("awita3","Rafael Pombo", 1.0);
        Cuento cuento4 = new Cuento("awita4","Rafael Pombo", 0.1);

        cuentos.add(cuento0);
        cuentos.add(cuento1);
        cuentos.add(cuento2);
        cuentos.add(cuento3);
        cuentos.add(cuento4);

        ModeloSeleccion<Cuento> modeloCuento = new ModeloSeleccion<Cuento>(cuentos);
        Cuento cuentoSelected = null;
        CromosomaCuento cromosoma = null;
        do {
            cromosoma = (CromosomaCuento) modeloCuento.selectCromosoma();
            if (cromosoma != null) {
                cuentoSelected = cromosoma.getCuento();
                System.out.println(cuentoSelected.getNombre());
            } else {
                System.out.println("NULL");
            }
        } while (cuentoSelected.getNombre() != "awita4");
    }
    
    public static void pruebaModeloCancion(){
        System.out.println("CANCION");
        List<Cancion> canciones = new ArrayList<>();
        Cancion cancion0 = new Cancion("awita0", 1.0);
        Cancion cancion1 = new Cancion("awita1", 1.0);
        Cancion cancion2 = new Cancion("awita2", 1.0);
        Cancion cancion3 = new Cancion("awita3", 1.0);
        Cancion cancion4 = new Cancion("awita4", 1.0);

        canciones.add(cancion0);
        canciones.add(cancion1);
        canciones.add(cancion2);
        canciones.add(cancion3);
        canciones.add(cancion4);

        ModeloSeleccion<Cancion> modeloCancion = new ModeloSeleccion<Cancion>(canciones);
        Cancion cancionSelected = null;
        CromosomaCancion cromosoma = null;
        do {
            cromosoma = (CromosomaCancion) modeloCancion.selectCromosoma();
            if (cromosoma != null) {
                cancionSelected = cromosoma.getCancion();
                System.out.println(cancionSelected.getNombre());
            } else {
                System.out.println("NULL");
            }
        } while (cancionSelected.getNombre() != "awita4");
    }
}
