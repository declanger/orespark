package com.orespark.world;

public class RadialPatternGenerator {

    private final double radius, thickness, amplitude, radialOffset;

    public RadialPatternGenerator(double radius, double thickness, double amplitude, double radialOffset) {
        // radius in block of each ripple
        this.radius = radius;
        // thickness in blocks of each ripple
        this.thickness = thickness;
        // height in blocks
        this.amplitude = amplitude;
        // the offset distance from 0,0 that the pattern begins
        this.radialOffset = radialOffset;
    }

    // https://www.desmos.com/calculator/mwxkct9ail
    // f(x) is the full calculation of dist
    // s(x) is smooth()
    // d is steepness (degree)
    // cubing it is to give ramp up
    public double getValue(int blockX, int blockZ) {
        double dist = Math.max(Math.sqrt((long) blockX * blockX + (long) blockZ * blockZ) - radialOffset, 0);
        dist %= radius;
        dist = Math.max(0.0, dist - radius + thickness) / thickness;
        return smooth(dist) * amplitude;
    }

    // Unwrapped t^8 bc math.pow is slow, equivalent to d=8 on desmos
    private double smooth(double dist) {
        double t = 2.0 * dist - 1.0;
        double t2 = t * t;
        double g = (1-t2);
        return g * g * g;
    }

    public double[] getChunkValues(int chunkX, int chunkZ) {
        double[] values = new double[256];
        int x0 = chunkX * 16;
        int z0 = chunkZ * 16;

        int idx = 0;
        for (int x = 0; x < 16; x++) {
            long wx = x + x0;
            long wxSq = wx * wx;

            for (int z = 0; z < 16; z++) {
                long wz = z + z0;
                double dist = Math.max(Math.sqrt(wxSq + wz * wz) - radialOffset,0);

                dist %= radius;
                dist = Math.max(0.0, dist - radius + thickness) / thickness;

                // Inlined smooth calculation, faster perchance
                double t = 2.0 * dist - 1.0;
                double t2 = t * t;
                //double t4 = t2 * t2;
                //double t8 = t4 * t4;

                double g = (1.0 - t2);

                values[idx++] = g * g * g * amplitude;
            }
        }
        return values;
    }

}
