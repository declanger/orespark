package com.orespark.world;

public class OctaveNoiseGenerator {

    private final int octaves;
    private final double amplitude;

    private final PerlinNoiseGenerator[] generators;

    public OctaveNoiseGenerator(long seed, int octaves, int scale, double amplitude) {
        this.octaves = octaves;
        this.amplitude = amplitude;

        generators = new PerlinNoiseGenerator[octaves];
        int s = scale;
        for (int i = 0; i < octaves; i++) {
            generators[i] = new PerlinNoiseGenerator(seed,s,s,amplitude / Math.pow(2,i));
            s /= 2;
        }
    }

    public double getValue(int blockX, int blockZ) {
        double accumulation = 0;
        for (PerlinNoiseGenerator generator : generators) {
            accumulation += generator.getValue(blockX,blockZ);
        }
        return  accumulation;
    }

    public double[] getChunkValues(int chunkX, int chunkZ) {
        double[] values = new double[256];
        for (PerlinNoiseGenerator generator : generators) {
            generator.addChunkValues(values,chunkX,chunkZ);
        }
        return values;
    }
}
