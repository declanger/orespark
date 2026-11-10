package com.orespark.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorSurface implements IChunkGenerator {

    private final Random rand;
    private final World world;
    private final long seed;

    private final PerlinNoiseGenerator baseLayerNoise;

    private final OctaveNoiseGenerator hillLayerNoise;
    private final PerlinNoiseGenerator hillLayerMask;

    private final RadialPatternGenerator mountainLayerPattern;
    private final ClippedPerlinNoiseGenerator mountainLayerMask;
    private final OctaveNoiseGenerator mountainLayerNoise;

    private final VoronoiNoiseGenerator riverLayerMask;
    private final PerlinNoiseGenerator riverLayerNoise;
    private final KenzoNoiseGenerator riverTest;

    private double[] chunkHeight;
    private double[] chunkTemp;
    private double[] chunkLushness;

    private final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    private final IBlockState STONE = Blocks.STONE.getDefaultState();
    private final IBlockState GRASS = Blocks.GRASS.getDefaultState();
    private final IBlockState SNOW = Blocks.SNOW.getDefaultState();

    private final IBlockState[] debugBlocks = new IBlockState[]{
            Blocks.BEDROCK.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.GRASS.getDefaultState(),
            Blocks.SAND.getDefaultState(),
            Blocks.SNOW.getDefaultState(),
            Blocks.STAINED_GLASS.getStateFromMeta(13),
            Blocks.STAINED_GLASS.getStateFromMeta(12)
    };

    public ChunkGeneratorSurface(World world, long seed) {
        this.world = world;
        this.seed = seed;

        rand = new Random(seed);

        baseLayerNoise = new PerlinNoiseGenerator(rand.nextLong(),64,64,12d);

        hillLayerMask = new ClippedPerlinNoiseGenerator(rand.nextLong(),128,128,10d,0.2f, true);
        hillLayerNoise = new OctaveNoiseGenerator(rand.nextLong(),4,64,10d);

        mountainLayerPattern = new RadialPatternGenerator(600d,120d,100d, -100d);
        mountainLayerMask = new ClippedPerlinNoiseGenerator(rand.nextLong(),512,512,1,0,true);
        mountainLayerNoise = new OctaveNoiseGenerator(rand.nextLong(),7,64,20d);

        riverLayerMask = new VoronoiNoiseGenerator(rand.nextLong(), 512, 1);
        riverLayerNoise = new PerlinNoiseGenerator(rand.nextLong(),4,4,1/256d);
        riverTest = new KenzoNoiseGenerator(rand.nextLong(),32,6,8);
    }

    private float getLushness(int x, int z) {
        return MathHelper.clamp((z - x)/7.0710678118f, -100f, 100f);
    }

    private float getTemp(int x, int z) {
        return MathHelper.clamp(z / 10f,-100f,100f);
    }

    private void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunkPrimer) {
        double[] baseNoise  = baseLayerNoise.getChunkValues(chunkX, chunkZ);

        double[] hillMask = hillLayerMask.getChunkValues(chunkX,chunkZ);
        double[] hillNoise = hillLayerNoise.getChunkValues(chunkX,chunkZ);

        double[] mountainPattern = mountainLayerPattern.getChunkValues(chunkX,chunkZ);
        double[] mountainMask = mountainLayerMask.getChunkValues(chunkX,chunkZ);
        double[] mountainNoise = mountainLayerNoise.getChunkValues(chunkX,chunkZ);
        int index = 0;
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int variance = rand.nextInt(3);

                // The base noise that is present across the whole world
                double baseLayer = baseNoise[index];
                // The layer of hills
                double hillLayer = hillMask[index];
                if (hillLayer > 1) {
                    // smooth out the hillNoise at the base of the hill and add noise above the base
                    hillLayer += Math.abs(hillNoise[index]) * Math.min(hillLayer / 2d,1d);
                }
                // The layer for mountains
                double mountainLayer = mountainPattern[index] * mountainMask[index];
                if (mountainLayer > 1) {
                    mountainLayer += mountainNoise[index] * Math.min((mountainLayer-1 ) / 10d,1d);
                }

                double riverClip = 0.55;
                double riverLayer = riverLayerMask.getValue(dx + chunkX * 16, dz + chunkZ * 16);
                riverLayer -= riverLayerNoise.getValue(dx + chunkX * 16, dz + chunkZ * 16) + riverClip;
                riverLayer = Math.max(riverLayer,0) * -15 / (1-riverClip);

                double total = mountainLayer + hillLayer + baseLayer;
                int height = 60 + (int)riverTest.getValue(dx+chunkX*16,dz+chunkZ*16);
                for (int b = 0; b < variance + 1; b++) {
                    chunkPrimer.setBlockState(dx,b,dz,BEDROCK);
                }
                for (int y = variance + 1; y < height; y++) {
                    chunkPrimer.setBlockState(dx,y,dz,height + variance < 130 ? STONE : SNOW);
                }
                chunkPrimer.setBlockState(dx,height,dz,mountainLayer < 5 + variance ? GRASS : (height + variance < 130 ? STONE : SNOW));

                index++; // dont touch
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        this.rand.setSeed(seed + (long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        generateChunkValues(chunkX,chunkZ);

        generateTerrain(chunkX,chunkZ,chunkPrimer);

        Chunk chunk = new Chunk(world,chunkPrimer,chunkX,chunkZ);

        chunk.generateSkylightMap();

        return chunk;
    }

    private void generateChunkValues(int chunkX, int chunkZ) {
        //chunkHeight = noiseGeneratorOctaves.generateNoiseOctaves(chunkHeight,chunkX * 16, chunkZ * 16,16, 16,0.04,0.04,0d);
        chunkLushness = new double[256];
        chunkTemp = new double[256];
        chunkHeight = new double[256];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunkLushness[x * 16 + z] = getLushness(x + chunkX * 16, z + chunkZ * 16);
                chunkTemp[x * 16 + z] = getTemp(x + chunkX * 16, z + chunkZ * 16);
                chunkLushness[x * 16 + z] = baseLayerNoise.getValue(x + chunkX * 16, z + chunkZ * 16);
            }
        }
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return Collections.emptyList();
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
