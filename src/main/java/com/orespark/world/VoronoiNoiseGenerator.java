package com.orespark.world;

public class VoronoiNoiseGenerator {

    private final long seed;

    private final int gridSize;

    private final double amplitude;

    public VoronoiNoiseGenerator(long seed, int gridSize, double amplitude) {
        this.seed = seed;
        this.gridSize = gridSize;
        this.amplitude = amplitude;
    }

    private double[] getPoint(int gridX, int gridZ) {
        long h = seed ^ ((long) gridX * 341873128712L) ^ ((long) gridZ * 132897987541L);
        h = (h ^ (h >>> 30)) * 0xbf58476d1ce4e5b9L;

        double[] vec = new double[2];
        vec[0] = (h & 0xFFFFFF) / (double) 0xFFFFFF;

        h = (h ^ (h >>> 27)) * 0x94d049bb133111ebL;
        h = h ^ (h >>> 31);

        vec[1] = (h & 0xFFFFFF) / (double) 0xFFFFFF;

        return vec;
    }

    public double getValue(int blockX, int blockZ) {
        int gridX = Math.floorDiv(blockX, gridSize);
        int gridZ = Math.floorDiv(blockZ, gridSize);

        double tx = (blockX - gridX * gridSize) / (double) gridSize;
        double tz = (blockZ - gridZ * gridSize) / (double) gridSize;

        double nearest = 2;
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                double[] point = getPoint(gridX + x, gridZ + z);

                double dx = (tx - point[0] - x);
                double dz = (tz - point[1] - z);

                double d = dx * dx + dz * dz;

                nearest = Math.min(d,nearest);
            }
        }
        return Math.min(Math.sqrt(nearest),1d) * amplitude;
    }
}
