package com.orespark.world;

import com.orespark.Orespark;

import java.util.Arrays;
import java.util.Random;

public class PerlinNoiseGenerator {

    private final Random rand;

    private final long seed;

    private final int scaleX, scaleZ;

    private final double amplitude;

    public PerlinNoiseGenerator(long seed, int scaleX, int scaleZ, double amplitude) {
        this.seed = seed;
        this.scaleX = scaleX;
        this.scaleZ = scaleZ;
        this.amplitude = amplitude;

        rand = new Random();
    }

    private double[] getVec(int gridX, int gridZ) {
        rand.setSeed(seed + (long)gridX * 341873128712L + (long)gridZ * 132897987541L);
        double vx = rand.nextDouble() * 2d - 1d;
        double vz = rand.nextDouble() * 2d - 1d;
        double l = Math.sqrt(vx*vx + vz*vz);
        if (l == 0) {
            return new double[]{0.5,0.5};
        }
        double[] vec = new double[]{vx/l,vz/l};
        return vec;
    }

    public double getValue(int blockX, int blockZ) {
        int gridX = (int) Math.floor(blockX / (double) scaleX);
        int gridZ = (int) Math.floor(blockZ / (double) scaleZ);
        double[][] gridVecs = new double[][]{getVec(gridX,gridZ),getVec(gridX+1,gridZ),getVec(gridX+1,gridZ+1),getVec(gridX,gridZ + 1)};
        double[][] diffVecs = new double[][]{
                new double[]{blockX - gridX * scaleX,blockZ - gridZ * scaleZ},
                new double[]{blockX - (gridX+1) * scaleX,blockZ - gridZ * scaleZ},
                new double[]{blockX - (gridX+1) * scaleX,blockZ - (gridZ+1) * scaleZ},
                new double[]{blockX - gridX * scaleX,blockZ - (gridZ+1) * scaleZ}
        };
        for (int i = 0; i < 4; i++) {
            double l = Math.sqrt(diffVecs[i][0] * diffVecs[i][0] + diffVecs[i][1] * diffVecs[i][1]);
            if (l == 0) {continue;}
            diffVecs[i][0] /= l;
            diffVecs[i][1] /= l;
        }
        double[] dots = new double[]{
                gridVecs[0][0] * diffVecs[0][0] + gridVecs[0][1] * diffVecs[0][1],
                gridVecs[1][0] * diffVecs[1][0] + gridVecs[1][1] * diffVecs[1][1],
                gridVecs[2][0] * diffVecs[2][0] + gridVecs[2][1] * diffVecs[2][1],
                gridVecs[3][0] * diffVecs[3][0] + gridVecs[3][1] * diffVecs[3][1]
        };
        double tx = Math.abs(blockX % scaleX) / (double) (scaleX-1);
        double tz = Math.abs(blockZ % scaleZ) / (double) (scaleZ-1);

        double value = ((1d-tz) * (tx * dots[1] + (1d-tx) * dots[0]) + (tz) * (tx * dots[2] + (1-tx) * dots[3])) * amplitude;
        return value;
    }
}
