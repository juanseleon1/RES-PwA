/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

import ResPwAEntities.Baile;

/**
 *
 * @author ASUS
 */
public class CromosomaBaile extends Cromosoma{
    private Baile baile;

    public CromosomaBaile(Baile baile) {
        this.baile = baile;
    }

    public Baile getBaile() {
        return baile;
    }
    
    @Override
    protected void calculateObjectiveValue() {
        objectiveValue = (float) this.baile.getGusto();
    }
    
}
