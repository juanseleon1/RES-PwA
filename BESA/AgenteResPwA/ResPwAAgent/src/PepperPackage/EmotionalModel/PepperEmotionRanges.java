/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.EmotionalModel;

/**
 *
 * @author mafegarces
 */
public enum PepperEmotionRanges {
    VSAD("0x00ebff", -1, -0.6), SAD("0xaff6ff", -0.6, -0.2), NORMAL("0xFFFFFF", -0.2, 0.2), HAPPY("0xffff7a", 0.2, 0.6), VHAPPY("0xffff00", 0.6, 1);

    private String hexa;
    private double min;
    private double max;

    private PepperEmotionRanges(String hexa, double min, double max) {
        this.hexa = hexa;
        this.min = min;
        this.max = max;
    }

    public String getHexa() {
        return hexa;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public static PepperEmotionRanges getFromEmotionalValue(float state) {

        if (state >= PepperEmotionRanges.VSAD.getMin() && state < PepperEmotionRanges.VSAD.getMax()) {
            return PepperEmotionRanges.VSAD;
        } else if (state >= PepperEmotionRanges.NORMAL.getMin() && state < PepperEmotionRanges.NORMAL.getMax()) {
            return PepperEmotionRanges.NORMAL;
        } else if (state >= PepperEmotionRanges.HAPPY.getMin() && state < PepperEmotionRanges.HAPPY.getMax()) {
            return PepperEmotionRanges.HAPPY;
        } else if (state >= PepperEmotionRanges.SAD.getMin() && state < PepperEmotionRanges.SAD.getMax()) {
            return PepperEmotionRanges.SAD;
        } else if (state >= PepperEmotionRanges.VHAPPY.getMin() && state <= PepperEmotionRanges.VHAPPY.getMax()) {
            return PepperEmotionRanges.VHAPPY;
        }

        return PepperEmotionRanges.NORMAL;

    }
}
