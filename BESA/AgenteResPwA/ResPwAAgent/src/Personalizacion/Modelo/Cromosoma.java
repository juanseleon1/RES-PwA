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
    protected double objectiveValue;
    protected double selectionProbability;
    protected double averageSelectionProbability;
    
    abstract protected void calculateObjectiveValue(  );

    public double getObjectiveValue() {
        return objectiveValue;
    }

    public void setObjectiveValue(double objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public double getSelectionProbability() {
        return selectionProbability;
    }

    public void setSelectionProbability(double selectionProbability) {
        this.selectionProbability = selectionProbability;
    }

    public double getAverageSelectionProbability() {
        return averageSelectionProbability;
    }

    public void setAverageSelectionProbability(double averageSelectionProbability) {
        this.averageSelectionProbability = averageSelectionProbability;
    }
    
}
