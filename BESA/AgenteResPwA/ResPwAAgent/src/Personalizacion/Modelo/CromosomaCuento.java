/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Cuento;
import ResPwAEntities.Preferenciaxcuento;

/**
 *
 * @author ASUS
 */
public class CromosomaCuento extends Cromosoma{

    private Preferenciaxcuento cuento;

    public CromosomaCuento(Preferenciaxcuento cuento) {
        this.cuento = cuento;
    }
    
    @Override
    protected void calculateObjectiveValue() {
        objectiveValue =  this.cuento.getGusto();
    }

    public Preferenciaxcuento getCuento() {
        return cuento;
    }
    
}
