/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Baile;
import ResPwAEntities.Preferenciaxbaile;

/**
 *
 * @author ASUS
 */
public class CromosomaBaile extends Cromosoma{
    private Preferenciaxbaile baile;

    public CromosomaBaile(Preferenciaxbaile baile) {
        this.baile = baile;
    }

    public Preferenciaxbaile getBaile() {
        return baile;
    }
    
    @Override
    protected void calculateObjectiveValue() {
        objectiveValue = this.baile.getGusto();
    }
    
}
