/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Retroalimentacion.Modelo;

import ResPwAEntities.Antecedente;
import ResPwAEntities.Baile;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Regla;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ModeloRetroalimentacion<T> {
    private T activity;
    private List<Regla> reglas;

    public ModeloRetroalimentacion(T activity, List<Regla> reglas) {
        this.activity = activity;
        this.reglas = reglas;
    }
    
    private void activityFeedback(List<Antecedente> antecedentes, T activity){
        Regla reglaAplicada = findRule(antecedentes);
        if(activity instanceof Cancion){
            Cancion c = (Cancion) activity;
            c.setGusto( c.getGusto() + reglaAplicada.getFeedback() );
        } else if(activity instanceof Cuento){
            Cuento c = (Cuento) activity;
            c.setGusto( c.getGusto() + reglaAplicada.getFeedback() );
        } else if(activity instanceof Baile){
            Baile b = (Baile) activity;
            b.setGusto( b.getGusto() + reglaAplicada.getFeedback() );
        }    
    }
    
    private Regla findRule(List<Antecedente> antecedentes){
        boolean encontrada = false;
        boolean antecedenteEncontrado = true;
        List<Antecedente> antecedentesXRegla = null;
        Regla findRule = null;
        
        for (int i = 0; i < reglas.size() && !encontrada; i++) {
            antecedentesXRegla = reglas.get(i).getAntecedentesList();
            for (int j = 0; j < antecedentes.size() && antecedenteEncontrado; j++) {
                antecedenteEncontrado = false;
                for (int k = 0; k < antecedentesXRegla.size() && !antecedenteEncontrado; k++) {
                    if(antecedentes.get(j).equals(antecedentesXRegla.get(k))){
                        antecedenteEncontrado = true;
                    }
                }
            }
            if(antecedenteEncontrado){
                encontrada = true;
                findRule = reglas.get(i);
            }
        }
        
        return findRule;
    }
    
    
    
}
