/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Cuento;

/**
 *
 * @author ASUS
 */
public class CromosomaCuento extends Cromosoma{

    private Cuento cuento;

    public CromosomaCuento(Cuento cuento) {
        this.cuento = cuento;
    }
    

    
    @Override
    protected void calculateObjectiveValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void calculateSelectionProbability(float totalObjectiveValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
