/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Baile;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Preferenciaxbaile;
import ResPwAEntities.Preferenciaxcancion;
import ResPwAEntities.Preferenciaxcuento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ModeloSeleccion<T> {

    private List<Cromosoma> cromosomas;
    private List<T> elementosASeleccionar;

    public ModeloSeleccion(List<T> elementos) {
        elementosASeleccionar = elementos;
        cromosomas = new ArrayList<>();
        initialize();
    }

    public Cromosoma selectCromosoma() {
        float valObjAcum = (float) 0.0;

        for (Cromosoma cromosoma : cromosomas) {
            cromosoma.calculateObjectiveValue();
            valObjAcum += cromosoma.getObjectiveValue();
        }

        for (Cromosoma cromosoma : cromosomas) {
            calculateSelectionProbability(valObjAcum, cromosoma);
        }

        //SE REALIZA EL PUNTO 4 DEL DOCUMENTO EN ESTE PUNTO, EL RESTO ESTA BIEN
        calculateProbabilitiesForSelection();

        return getChromosomeSelected();

    }

    public Cromosoma getChromosomeSelected() {
        double percentSelected;
        double chromosomeValue = 0.0;
        boolean searched = false;
        Cromosoma cromosomaAnterior = null;
        Cromosoma cromosomaPosterior = null;
        int posAnterior = 0;
        int posSuperior = cromosomas.size();
        List<Cromosoma> auxCromosomas = cromosomas;

        percentSelected = Math.random();
//        System.out.println("percentSelected: " + percentSelected);
        if (percentSelected < auxCromosomas.get(0).getAverageSelectionProbability()) {
            searched = true;
            cromosomaPosterior = auxCromosomas.get(0);
        }

        while (!searched) {
            
            if (auxCromosomas.size() > 1) {
                cromosomaAnterior = auxCromosomas.get((auxCromosomas.size() / 2) - 1);

                cromosomaPosterior = auxCromosomas.get((auxCromosomas.size() / 2));

                if (cromosomaAnterior.getAverageSelectionProbability() < percentSelected && percentSelected <= cromosomaPosterior.getAverageSelectionProbability()) {
                    searched = true;
                } else if (cromosomaAnterior.getAverageSelectionProbability() > percentSelected) {
                    posSuperior = (auxCromosomas.size() / 2);
                    auxCromosomas = auxCromosomas.subList(posAnterior, posSuperior);
                } else if (cromosomaPosterior.getAverageSelectionProbability() < percentSelected) {
                    posAnterior = (auxCromosomas.size() / 2);
                    auxCromosomas = auxCromosomas.subList(posAnterior, posSuperior);
                    posSuperior = auxCromosomas.size();
                    posAnterior = 0;
                }
            }
            else{
                cromosomaPosterior = auxCromosomas.get(0);
                searched = true;
            }
        }

        return cromosomaPosterior;
    }

    public void calculateProbabilitiesForSelection() {
        float probaCromAcum = (float) 0.0;
        if (cromosomas.get(0) instanceof CromosomaCancion) {
            CromosomaCancion cromosomaCancion;
            for (Cromosoma cromosoma : cromosomas) {
                cromosomaCancion = (CromosomaCancion) cromosoma;
                probaCromAcum += cromosoma.getSelectionProbability();
                cromosomaCancion.setAverageSelectionProbability(probaCromAcum);
            }
        } else if (cromosomas.get(0) instanceof CromosomaCuento) {
            CromosomaCuento cromosomaCuento;
            for (Cromosoma cromosoma : cromosomas) {
                cromosomaCuento = (CromosomaCuento) cromosoma;
                probaCromAcum += cromosoma.getSelectionProbability();
                cromosomaCuento.setAverageSelectionProbability(probaCromAcum);
            }
        } else if (cromosomas.get(0) instanceof CromosomaBaile) {
            CromosomaBaile cromosomaBaile;
            for (Cromosoma cromosoma : cromosomas) {
                cromosomaBaile = (CromosomaBaile) cromosoma;
                probaCromAcum += cromosoma.getSelectionProbability();
                cromosomaBaile.setAverageSelectionProbability(probaCromAcum);
            }
        }
        
    }

    public void calculateSelectionProbability(double totalObjectiveValue, Cromosoma crom) {
        double selectionProbability = crom.getObjectiveValue() / totalObjectiveValue;
        crom.setSelectionProbability(selectionProbability);
    }

    void initialize() {

        for (T t : elementosASeleccionar) {
            if (t instanceof Preferenciaxcancion) {
                cromosomas.add(new CromosomaCancion((Preferenciaxcancion) t));
            } else if (t instanceof Preferenciaxcuento) {
                cromosomas.add(new CromosomaCuento((Preferenciaxcuento) t));
            } else if (t instanceof Preferenciaxbaile) {
                cromosomas.add(new CromosomaBaile((Preferenciaxbaile) t));
            }
            

        }
    }
}
