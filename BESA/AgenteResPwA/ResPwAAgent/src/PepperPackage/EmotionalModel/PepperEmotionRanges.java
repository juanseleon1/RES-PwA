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
    VSAD(0x8BCCEC, -1, -0.6), SAD(0xFA3421, -0.6, -0.2), NEUTRAL(0xFFFFFF, -0 - 2, 0.2), HAPPY(0x7FF764, 0.2, 0.6), VHAPPY(0xF8FE2E, 0.6, 1);

    private int hexa;
    private double min;
    private double max;

    private PepperEmotionRanges(int hexa, double min, double max) {
        this.hexa = hexa;
        this.min = min;
        this.max = max;
    }

    public int getHexa() {
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
        } else if (state >= PepperEmotionRanges.NEUTRAL.getMin() && state < PepperEmotionRanges.NEUTRAL.getMax()) {
            return PepperEmotionRanges.NEUTRAL;
        } else if (state >= PepperEmotionRanges.HAPPY.getMin() && state < PepperEmotionRanges.HAPPY.getMax()) {
            return PepperEmotionRanges.HAPPY;
        } else if (state >= PepperEmotionRanges.SAD.getMin() && state < PepperEmotionRanges.SAD.getMax()) {
            return PepperEmotionRanges.SAD;
        } else if (state >= PepperEmotionRanges.VHAPPY.getMin() && state <= PepperEmotionRanges.VHAPPY.getMax()) {
            return PepperEmotionRanges.VHAPPY;
        }

        return PepperEmotionRanges.NEUTRAL;

    }
}
