/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personalizacion.Modelo;

/**
 *
 * @author ASUS
 */
public abstract class Cromosoma {
    protected float objectiveValue;
    protected float selectionProbability;
    protected float averageSelectionProbability;
    
    abstract protected void calculateObjectiveValue(  );

    public float getObjectiveValue() {
        return objectiveValue;
    }

    public void setObjectiveValue(float objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public float getSelectionProbability() {
        return selectionProbability;
    }

    public void setSelectionProbability(float selectionProbability) {
        this.selectionProbability = selectionProbability;
    }

    public float getAverageSelectionProbability() {
        return averageSelectionProbability;
    }

    public void setAverageSelectionProbability(float averageSelectionProbability) {
        this.averageSelectionProbability = averageSelectionProbability;
    }
    
}
