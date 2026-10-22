package com.orespark.world;

import com.orespark.Orespark;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorSurface implements IChunkGenerator {

    private final Random rand;
    private final World world;
    private final long seed;

    private final NoiseGeneratorOctaves noiseGeneratorOctaves;
    private final PerlinNoiseGenerator perlinNoiseGenerator;

    private double[] chunkHeight;
    private double[] chunkTemp;
    private double[] chunkLushness;

    private final IBlockState[] debugBlocks = new IBlockState[]{
            Blocks.BEDROCK.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.GRASS.getDefaultState(),
            Blocks.SAND.getDefaultState(),
            Blocks.SNOW.getDefaultState(),
            Blocks.STAINED_GLASS.getStateFromMeta(13),
            Blocks.STAINED_GLASS.getStateFromMeta(12)
    };

    private final IBlockState[] visualizer = new IBlockState[]{
            Blocks.CONCRETE.getStateFromMeta(15),
            Blocks.CONCRETE.getStateFromMeta(7),
            Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(9),
            Blocks.CONCRETE.getStateFromMeta(8),
            Blocks.IRON_BLOCK.getStateFromMeta(0)
    };

    public ChunkGeneratorSurface(World world, long seed) {
        this.world = world;
        this.seed = seed;

        rand = new Random(seed);

        perlinNoiseGenerator = new PerlinNoiseGenerator(seed,32,32,2.49d);
        noiseGeneratorOctaves = new NoiseGeneratorOctaves(rand,5);
    }

    private float getLushness(int x, int z) {
        return MathHelper.clamp((z - x)/7.0710678118f, -100f, 100f);
    }

    private float getTemp(int x, int z) {
        return MathHelper.clamp(z / 10f,-100f,100f);
    }

    private void generateTerrain(int chunkX, int chunkZ, ChunkPrimer chunkPrimer) {
        for (int dx = 0; dx < 16; dx++) {
            int x = chunkX * 16 + dx;
            for (int dz = 0; dz < 16; dz++) {
                int z = chunkZ * 16 + dz;
                int height = 60;// + (int) chunkHeight[dx * 16 + dz];
                chunkPrimer.setBlockState(dx,0,dz,debugBlocks[0]);
                for (int y = 1; y < height; y++) {
                    chunkPrimer.setBlockState(dx,y,dz,debugBlocks[1]);
                }
                double testHeight = perlinNoiseGenerator.getValue(x,z);
                Orespark.LOGGER.info(testHeight);
                float temp = getTemp(x,z);
                float lushness = getLushness(x,z);
                chunkPrimer.setBlockState(dx,height,dz,visualizer[(int)Math.floor(testHeight + 2.5)]);
            }
        }
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);
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
                chunkLushness[x * 16 + z] = perlinNoiseGenerator.getValue(x + chunkX * 16, z + chunkZ * 16);
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
