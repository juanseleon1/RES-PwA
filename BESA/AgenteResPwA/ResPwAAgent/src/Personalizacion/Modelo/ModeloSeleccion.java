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
        
        if ( percentSelected < auxCromosomas.get(0).getAverageSelectionProbability()  ){
            searched = true;
        }

        while (!searched) {

            cromosomaAnterior = auxCromosomas.get(auxCromosomas.size() / 2);

            cromosomaPosterior = auxCromosomas.get((auxCromosomas.size() / 2) + 1);

            if (cromosomaAnterior.getAverageSelectionProbability() < percentSelected && percentSelected <= cromosomaPosterior.getAverageSelectionProbability()) {
                searched = true;
            } else if (cromosomaAnterior.getAverageSelectionProbability() > percentSelected) {
                posSuperior = (auxCromosomas.size() / 2);
                auxCromosomas = auxCromosomas.subList(posAnterior, posSuperior);
            } else if (cromosomaPosterior.getAverageSelectionProbability() < percentSelected) {
                posAnterior = (auxCromosomas.size() / 2);
                auxCromosomas = auxCromosomas.subList(posAnterior, posSuperior);
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
        }
    }
    
    public void calculateSelectionProbability(float totalObjectiveValue, Cromosoma crom) {
        float selectionProbability = crom.getObjectiveValue() / totalObjectiveValue;
        crom.setSelectionProbability(selectionProbability);
    }

    void initialize() {

        for (T t : elementosASeleccionar) {
            if (t instanceof Cancion) {
                cromosomas.add(new CromosomaCancion((Cancion) t));
            } else if (t instanceof Cuento) {
                cromosomas.add(new CromosomaCuento((Cuento) t));
            }

        }
    }
}
