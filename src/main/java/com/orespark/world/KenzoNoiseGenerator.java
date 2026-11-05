package com.orespark.world;

import java.util.ArrayList;
import java.util.List;

public class KenzoNoiseGenerator {

    private final static double angleDeadzone = Math.cos(80.0d * Math.PI / 180.0d);
    private final static double rangeOverride = 5; // blocks per gridSize

    private final double riverThickness;

    private final long seed;

    // SIZE IN CHUNKS
    private final int gridSize;

    private final double amplitude;

    public KenzoNoiseGenerator( long seed, int gridSize, double amplitude, double riverThickness) {
        this.riverThickness = riverThickness;
        this.seed = seed;
        this.gridSize = gridSize;
        this.amplitude = amplitude;
    }

    private double[] getPoint(int gridX, int gridZ) {
        long h = seed ^ ((long) gridX * 341873128712L) ^ ((long) gridZ * 132897987541L);
        h = (h ^ (h >>> 30)) * 0xbf58476d1ce4e5b9L;

        double[] pos = new double[3];
        pos[0] = gridSize * (h & 0xFFFFFF) / (double) 0xFFFFFF;

        h = (h ^ (h >>> 27)) * 0x94d049bb133111ebL;
        h = h ^ (h >>> 31);

        pos[1] = gridSize * (h & 0xFFFFFF) / (double) 0xFFFFFF;

        pos[2] = Math.sqrt(pos[0] * pos[0] + pos[1] * pos[1]);
        return pos;
    }

    // so incredibly slow should never be used, always used getChunkValues() instead
    public double getValue(int blockX, int blockZ) {
        int gridX = Math.floorDiv(Math.floorDiv(blockX,16), gridSize);
        int gridZ = Math.floorDiv(Math.floorDiv(blockZ,16), gridSize);

        double[][] points = new double[9][];

        List<Line> lines = new ArrayList<Line>();

        for (int x = 0; x < 3 ; x++) {
            for (int z = 0; z < 3; z++) {
                points[x * 3 + z] = getPoint(gridX - x - 1, gridZ - z - 1);
            }
        }

        // get lines from main chunk
        double[] main = points[4];
        for (int i = 0; i < 9; i++) {
            if (i == 4) continue; // skip checking self
            double[] point = points[i];
            double[] diff = new double[]{point[0]-main[0],point[1]-main[1],0};
            double dist = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);
            diff[2] = dist;
            double dot = Math.abs(dot(main,diff)) / (dist * main[2]);
            if (dot < angleDeadzone || dist < rangeOverride * gridSize) {
                lines.add(new Line(main,diff));
            }
        }

        // get lines from cardinal neighbors to other cardinal neighbors (never east soggy wheat)
        for (int i = 0; i < 4; i++) {
            double[] self = points[i * 2 + 1];
            for (int j = 0; j < 4; j++) { // loop through other cardinals
                // i + j = 3 if they are opposite cardinal directions i.e. 0+3 or 1+2
                if (j == i || i + j == 3) continue; // this skips impossible checks and the self check
                double[] point = points[j * 2 + 1];

                double[] diff = new double[]{point[0]-self[0],point[1]-self[1],0};
                // check if the line would even pass through the main grid cell before the expensive maths
                // z = mx + b
                double m = diff[1] / diff[0]; // rise over run to find slope
                // using the self solve for b
                // -b = mx - z
                double b = -m * self[0] + self[1];
                // now solve for the z at the x of the grid cell border
                // must solve for closest x boundary
                // if either cell is west then check west border else check east border
                int boundary = i == 1 || j == 1 ? 0 : 1;
                double zIntercept = m * (gridX + boundary) + b;
                if (zIntercept >= gridZ && zIntercept <= gridZ) {
                    double dist = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);
                    diff[2] = dist;
                    double dot = Math.abs(dot(self,diff)) / (dist * self[2]);
                    if (dot < angleDeadzone || dist < rangeOverride * gridSize) {
                        lines.add(new Line(self,diff));
                    }
                }
            }
        }

        // now find the value at given coordinate
        double smallest = 0;
        for (Line line : lines) {
            smallest = Math.min(line.distance(new double[]{blockX,blockZ}),smallest);
        }

        return smallest * amplitude;
    }

    // Removes the magnitudes of the vectors
    private double dot(double[] a, double[] b) {
        return ((a[0] * b[0]) + (a[1] * b[1]));
    }

    private class Line {

        private final double[] offset, direction;

        private Line(double[] offset, double[] direction) {
            this.offset = offset;
            // normalize to save on calculations later
            this.direction = new double[]{direction[0] / direction[2], direction[1] / direction[2]};
        }

        //https://www.desmos.com/calculator/voyyc9ugad
        // implementation of f(x) with b & d = 4, c = 0.75

        private double smooth(double x) {
            if (x < 0.75) {
                return (x*x*x*x)/(0.75*0.75*0.75*0.75);
            }
            double t = x - 1;
            return (t*t*t*t)/(0.25*0.25*0.25*0.25) + 1;
        }

        private double distance(double[] point) {
            // find cross of the direction
            double[] cross = new double[]{direction[1],-direction[0]};
            // take the delta from the offset point to the sample point
            double[] diff = new double[]{point[0] - offset[0], point[1] - offset[1]};
            // project the difference vector onto the cross, no need to divide by magnitude bc the cross is normalized
            double dist = dot(cross,diff);
            // now for smoothing function
            dist = Math.min(1,dist/riverThickness); // converts to percent of thickness, clamped at 1
            // invert so that the returned value is how many blocks it needs to be lowered
            return smooth(dist) - 1;
        }
    }

}
