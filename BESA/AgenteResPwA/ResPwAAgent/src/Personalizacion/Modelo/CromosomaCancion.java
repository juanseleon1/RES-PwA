/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Cancion;

/**
 *
 * @author ASUS
 */
public class CromosomaCancion extends Cromosoma{
    
    private Cancion cancion;

    public CromosomaCancion( Cancion cancion ) {
      this.cancion = cancion;
    }
    
    @Override
    protected void calculateObjectiveValue() {
        objectiveValue = (float) this.cancion.getGusto();
        
        if ( cancion.isReminiscencia() ){
            objectiveValue += 0.4;
        }
    }

    public Cancion getCancion() {
        return cancion;
    }
    
     
    
}
