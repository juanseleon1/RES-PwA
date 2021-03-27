/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmotionalAnalyzerAgent;

/**
 *
 * @author jsleon
 */
public class Personality {

    private double extraversion;
    private double agreeableness;
    private double materialism;

    public double getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(double extraversion) {
        this.extraversion = extraversion;
    }

    public double getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(double agreeableness) {
        this.agreeableness = agreeableness;
    }

    public double getMaterialism() {
        return materialism;
    }

    public void setMaterialism(double materialism) {
        this.materialism = materialism;
    }

    public double getExtrovertedTrait() {
        return ( extraversion/ (extraversion + agreeableness + materialism));
    }

    public double getAgreeablenessTrait() {
        return ( agreeableness/ (extraversion + agreeableness + materialism));

    }

    public double getMaterialismTrait() {
        return (materialism / (extraversion + agreeableness + materialism));

    }

}
