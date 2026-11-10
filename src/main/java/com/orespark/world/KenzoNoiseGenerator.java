        package com.orespark.world;

        import java.util.ArrayList;
        import java.util.List;

        public class KenzoNoiseGenerator {

            private final static double ANGLE_DEADZONE = Math.cos(Math.toRadians(60d));
            private final static double COLLAPSE_RANGE = 5; // area around two points which they connect despite the angle
            private final static int[][] PAIRS = new int[][]{
                    {4,0},{4,1},{4,2},
                    {4,3},      {4,5}, // neighbors
                    {4,6},{4,7},{4,8},

                          {3,1},
                    {3,7},      {5,1}, // adjacent overlaps
                          {5,7}
            };

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

            private static long mix(long h) {
                h = (h ^ (h >>> 30)) * 0xbf58476d1ce4e5b9L;
                h = (h ^ (h >>> 27)) * 0x94d049bb133111ebL;
                return h ^ (h >>> 31);
            }

            private double[] getPoint(int gridX, int gridZ) {
                long h = mix(seed ^ ((long) gridX * 341873128712L) ^ ((long) gridZ * 132897987541L));
                long h2 = mix(h + 0x9E3779B97F4A7C15L);

                double x = (gridX + (h >>> 11) / (double) (1L << 53)) * gridSize * 16;
                double z = (gridZ + (h2 >>> 11) / (double) (1L << 53)) * gridSize * 16;
                return new double[]{x, z, Math.sqrt(x * x + z * z)};
            }

            public void connect(double[] a, double [] b, List<Segment> segments) {
                double[] close = a[2] < b[2] ? a : b;
                double[] far = a[2] < b[2] ? b : a;

                double[] diff = new double[]{far[0]-close[0],far[1]-close[1],0};
                double dist = Math.sqrt(diff[0] * diff[0] + diff[1] * diff[1]);
                diff[2] = dist;
                double dot = Math.abs(dot(close,diff)) / (dist * close[2]);
                if (dot > ANGLE_DEADZONE || dist < COLLAPSE_RANGE) {
                    segments.add(new Segment(close,diff));
                }
            }

            public double getValue(int blockX, int blockZ) {
                int gridX = Math.floorDiv(Math.floorDiv(blockX,16), gridSize);
                int gridZ = Math.floorDiv(Math.floorDiv(blockZ,16), gridSize);

                double[][] points = new double[9][];

                List<Segment> segments = new ArrayList<Segment>();

                for (int x = 0; x < 3 ; x++) {
                    for (int z = 0; z < 3; z++) {
                        points[x * 3 + z] = getPoint(gridX + x - 1, gridZ + z - 1);
                    }
                }

                for (int[] pair : PAIRS) {
                    double[] a = points[pair[0]], b = points[pair[1]];
                    connect(a,b,segments);
                }

                // now find the value at given coordinate
                double deepest = 0;
                for (Segment segment : segments) {
                    deepest = Math.max(segment.distance(blockX,blockZ),deepest);
                }

                return deepest * -amplitude;
            }

            public double[] getChunkValues(int chunkX, int chunkZ) {
                int gridX = Math.floorDiv(chunkX, gridSize);
                int gridZ = Math.floorDiv(chunkZ, gridSize);

                double[][] points = new double[9][];

                List<Segment> segments = new ArrayList<Segment>();

                for (int x = 0; x < 3 ; x++) {
                    for (int z = 0; z < 3; z++) {
                        points[x * 3 + z] = getPoint(gridX + x - 1, gridZ + z - 1);
                    }
                }

                for (int[] pair : PAIRS) {
                    double[] a = points[pair[0]], b = points[pair[1]];
                    connect(a,b,segments);
                }

                double[] values = new double[256];

                for (Segment segment : segments) {
                    segment.populateChunk(chunkX,chunkZ,values);
                }

                return values;
            }

            private double dot(double[] a, double[] b) {
                return ((a[0] * b[0]) + (a[1] * b[1]));
            }

            private class Segment {

                private final double[] point, direction;

                private Segment(double[] point, double[] direction) {
                    this.point = point;
                    // normalize to save on calculations later
                    this.direction = direction;
                }

                //https://www.desmos.com/calculator/voyyc9ugad
                // implementation of f(x) with b & d = 4, c = 0.75

                private double smooth(double x) {
                    if (x == 0 || x == 1) {return x;}
                    if (x < 0.75) {
                        return (x*x*x*x)/(0.75*0.75*0.75);
                    }
                    double t = x - 1;
                    return -(t*t*t*t)/(0.25*0.25*0.25) + 1;
                }

                private double distance(double sampleX, double sampleZ) {
                    // find the value of where the closest point is between the two points [0-1]
                    double t = ((sampleX - point[0]) * direction[0] + (sampleZ - point[1]) * direction[1]) / (direction[2] * direction[2]);
                    // clamp it so if falls between the two points
                    t = Math.max(0d,Math.min(1d,t));
                    // use the t value to find the closest point clamped between the start and finish
                    double cx = point[0] + direction[0] * t, cz = point[1] + direction[1] * t;
                    // now find the distance between the sample point and the closest point on the segment
                    double dx = cx - sampleX, dz = cz - sampleZ;
                    double distance = Math.sqrt(dx * dx + dz * dz);
                    // turn it into 0-1 value of distance
                    distance = Math.min(distance,riverThickness) / riverThickness;
                    // smooth it out
                    distance = smooth(distance);
                    // invert it so that 1 is the centre of the river and 0 is no river
                    return (1 - distance);
                }

                private void populateChunk(int chunkX, int chunkZ, double[] values) {
                    final int lx = chunkX * 16, lz = chunkZ * 16;
                    final int hx = lx + 16, hz = lz + 16;
                    final double lengthSquared = 1 / (direction[2] * direction[2]);
                    final double t0 = (-point[0] * direction[0] - point[1] * direction[1]) * lengthSquared;

                    int index = 0;
                    for (int x = lx; x < hx; x++) {
                        double tx = x * direction[0] * lengthSquared;
                        for (int z = lz; z < hz; z++) {
                            double tz = z * direction[1] * lengthSquared;
                            double t = t0 + tx + tz;

                            t = Math.max(0d,Math.min(1d,t));

                            double cx = point[0] + direction[0] * t, cz = point[1] + direction[1] * t;

                            double dx = cx - x, dz = cz - z;
                            double distance = Math.sqrt(dx * dx + dz * dz);

                            distance = Math.min(distance,riverThickness) / riverThickness;

                            distance = 1 - smooth(distance);

                            values[index] = Math.min(values[index], distance * -amplitude);

                            index++;
                        }
                    }
                }
            }

        }
