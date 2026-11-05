package com.orespark.world;

import net.minecraft.util.math.MathHelper;

public class PerlinNoiseGenerator {

    private final long seed;
    private final int scaleX, scaleZ;
    protected final double amplitude;

    public PerlinNoiseGenerator(long seed, int scaleX, int scaleZ, double amplitude) {
        this.seed = seed;
        this.scaleX = scaleX;
        this.scaleZ = scaleZ;
        this.amplitude = amplitude;
    }

    // Stateless hash-based pseudo-random gradient generator (thread-safe)
    private double[] getVec(int gridX, int gridZ) {
        long h = seed ^ ((long) gridX * 341873128712L) ^ ((long) gridZ * 132897987541L);
        h = (h ^ (h >>> 30)) * 0xbf58476d1ce4e5b9L;
        h = (h ^ (h >>> 27)) * 0x94d049bb133111ebL;
        h = h ^ (h >>> 31);

        float angle = (h & 0xFFFFFF) / (float) 0xFFFFFF * (float) Math.PI * 2.0f;
        return new double[]{MathHelper.cos(angle), MathHelper.sin(angle)};
    }

    // Perlin fade function (6t^5 - 15t^4 + 10t^3) for smooth C2 continuity
    protected double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double a, double b, double t) {
        return a + t * (b - a);
    }

    protected double applyTransformations(double value) {
        return value * amplitude;
    }

    public double getValue(int blockX, int blockZ) {
        // Determine grid cell indices (handles negative coordinates safely)
        int gridX = Math.floorDiv(blockX, scaleX);
        int gridZ = Math.floorDiv(blockZ, scaleZ);

        // Local fractional coordinates within the cell [0.0, 1.0)
        double localX = (blockX - gridX * scaleX) / (double) scaleX;
        double localZ = (blockZ - gridZ * scaleZ) / (double) scaleZ;

        // Fetch gradient vectors for the 4 corners of the cell
        double[] g00 = getVec(gridX, gridZ);
        double[] g10 = getVec(gridX + 1, gridZ);
        double[] g11 = getVec(gridX + 1, gridZ + 1);
        double[] g01 = getVec(gridX, gridZ + 1);

        // Unnormalized distance vectors from the 4 corners to the sample point
        double[] d00 = {localX, localZ};
        double[] d10 = {localX - 1.0, localZ};
        double[] d11 = {localX - 1.0, localZ - 1.0};
        double[] d01 = {localX, localZ - 1.0};

        // Compute dot products (Gradient dot Distance)
        double dot00 = g00[0] * d00[0] + g00[1] * d00[1];
        double dot10 = g10[0] * d10[0] + g10[1] * d10[1];
        double dot11 = g11[0] * d11[0] + g11[1] * d11[1];
        double dot01 = g01[0] * d01[0] + g01[1] * d01[1];

        // Apply fade curves to smooth the interpolation weights
        double tx = fade(localX);
        double tz = fade(localZ);

        // Bilinear interpolation of dot products
        double nx0 = lerp(dot00, dot10, tx);
        double nx1 = lerp(dot01, dot11, tx);
        double value = lerp(nx0, nx1, tz);

        return applyTransformations(value);
    }

    public double[] getChunkValues(int chunkX, int chunkZ) {
        int minGridX = Math.floorDiv(chunkX * 16, scaleX);
        int minGridZ = Math.floorDiv(chunkZ * 16, scaleZ);

        int maxGridX = Math.floorDiv(chunkX * 16 + 15, scaleX);
        int maxGridZ = Math.floorDiv(chunkZ * 16 + 15, scaleZ);

        double[] values = new double[256];
        for (int gridX = minGridX; gridX <= maxGridX; gridX++) {
            for (int gridZ = minGridZ; gridZ <= maxGridZ; gridZ++) {
                fillGridValues(values, gridX, gridZ, chunkX, chunkZ);
            }
        }
        return values;
    }

    public void fillGridValues(double[] values, int gridX, int gridZ, int chunkX, int chunkZ) {
        // Fetch gradient vectors for the 4 corners of the cell
        double[] g00 = getVec(gridX, gridZ);
        double[] g10 = getVec(gridX + 1, gridZ);
        double[] g11 = getVec(gridX + 1, gridZ + 1);
        double[] g01 = getVec(gridX, gridZ + 1);

        // Find lower bounds (inclusize)
        int lowerBoundX = Math.max(chunkX * 16, gridX * scaleX);
        int lowerBoundZ = Math.max(chunkZ * 16, gridZ * scaleZ);

        // Find upper bounds (exclusive) of generation
        int upperBoundX = Math.min(chunkX * 16 + 16, (gridX + 1) * scaleX);
        int upperBoundZ = Math.min(chunkZ * 16 + 16, (gridZ + 1) * scaleZ);

        for (int x = lowerBoundX; x < upperBoundX; x++) {
            double localX = (x - gridX * scaleX) / (double) scaleX;
            for (int z = lowerBoundZ; z < upperBoundZ; z++) {
                double localZ = (z - gridZ * scaleZ) / (double) scaleZ;

                // Get Difference Vectors
                double[] d00 = {localX, localZ};
                double[] d10 = {localX - 1.0, localZ};
                double[] d11 = {localX - 1.0, localZ - 1.0};
                double[] d01 = {localX, localZ - 1.0};

                // Compute Dot Products
                double dot00 = g00[0] * d00[0] + g00[1] * d00[1];
                double dot10 = g10[0] * d10[0] + g10[1] * d10[1];
                double dot11 = g11[0] * d11[0] + g11[1] * d11[1];
                double dot01 = g01[0] * d01[0] + g01[1] * d01[1];

                // Apply fade curves to smooth the interpolation weights
                double tx = fade(localX);
                double tz = fade(localZ);

                // Bilinear interpolation of dot products
                double nx0 = lerp(dot00, dot10, tx);
                double nx1 = lerp(dot01, dot11, tx);
                values[(x - chunkX * 16) * 16 + (z - chunkZ * 16)] = applyTransformations(lerp(nx0, nx1, tz));
            }
        }
    }
    public double[] addChunkValues(double[] values, int chunkX, int chunkZ) {
        int minGridX = Math.floorDiv(chunkX * 16, scaleX);
        int minGridZ = Math.floorDiv(chunkZ * 16, scaleZ);

        int maxGridX = Math.floorDiv(chunkX * 16 + 15, scaleX);
        int maxGridZ = Math.floorDiv(chunkZ * 16 + 15, scaleZ);

        for (int gridX = minGridX; gridX <= maxGridX; gridX++) {
            for (int gridZ = minGridZ; gridZ <= maxGridZ; gridZ++) {
                addGridValues(values, gridX, gridZ, chunkX, chunkZ);
            }
        }
        return values;
    }

    public void addGridValues(double[] values, int gridX, int gridZ, int chunkX, int chunkZ) {
        // Fetch gradient vectors for the 4 corners of the cell
        double[] g00 = getVec(gridX, gridZ);
        double[] g10 = getVec(gridX + 1, gridZ);
        double[] g11 = getVec(gridX + 1, gridZ + 1);
        double[] g01 = getVec(gridX, gridZ + 1);

        // Find lower bounds (inclusize)
        int lowerBoundX = Math.max(chunkX * 16, gridX * scaleX);
        int lowerBoundZ = Math.max(chunkZ * 16, gridZ * scaleZ);

        // Find upper bounds (exclusive) of generation
        int upperBoundX = Math.min(chunkX * 16 + 16, (gridX + 1) * scaleX);
        int upperBoundZ = Math.min(chunkZ * 16 + 16, (gridZ + 1) * scaleZ);

        for (int x = lowerBoundX; x < upperBoundX; x++) {
            double localX = (x - gridX * scaleX) / (double) scaleX;
            for (int z = lowerBoundZ; z < upperBoundZ; z++) {
                double localZ = (z - gridZ * scaleZ) / (double) scaleZ;

                // Get Difference Vectors
                double[] d00 = {localX, localZ};
                double[] d10 = {localX - 1.0, localZ};
                double[] d11 = {localX - 1.0, localZ - 1.0};
                double[] d01 = {localX, localZ - 1.0};

                // Compute Dot Products
                double dot00 = g00[0] * d00[0] + g00[1] * d00[1];
                double dot10 = g10[0] * d10[0] + g10[1] * d10[1];
                double dot11 = g11[0] * d11[0] + g11[1] * d11[1];
                double dot01 = g01[0] * d01[0] + g01[1] * d01[1];

                // Apply fade curves to smooth the interpolation weights
                double tx = fade(localX);
                double tz = fade(localZ);

                // Bilinear interpolation of dot products
                double nx0 = lerp(dot00, dot10, tx);
                double nx1 = lerp(dot01, dot11, tx);
                values[(x - chunkX * 16) * 16 + (z - chunkZ * 16)] += applyTransformations(lerp(nx0, nx1, tz));
            }
        }
    }
}