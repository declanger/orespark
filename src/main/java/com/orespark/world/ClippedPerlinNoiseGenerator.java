package com.orespark.world;

public class ClippedPerlinNoiseGenerator extends PerlinNoiseGenerator {

    private final double cutOff;
    private final boolean smoothStep;

    public ClippedPerlinNoiseGenerator(long seed, int scaleX, int scaleZ, double amplitude, double cutOff, boolean smoothStep) {
        super(seed, scaleX, scaleZ, amplitude);
        this.cutOff = cutOff;
        this.smoothStep = smoothStep;
    }

    // https://www.desmos.com/calculator/pgaru0dkr9
    // thats the thing
    // b(x) & d(x) are the same but different factoring, smooth at both ends
    // c(x) has smooth top and no smooth base
    private static final double a = 2.5;
    private static final double twoASquared = 2d*a*a;
    private static final double limit1 = 1/(2*a);
    private static final double limit2  = 1/a;
    // d(x) implementation
    private double smoothStep(double x) {
        return x < limit1 ? (twoASquared * x * x) : (x < limit2 ? (-twoASquared * x * x + 4 * x * a - 1) : 1d);
    }

    @Override
    protected double applyTransformations(double value) {
        double v = Math.max(value - cutOff,0) / (1 - cutOff);
        if (smoothStep) {
            v = smoothStep(v);
        }
        return v * amplitude;
    }
}
