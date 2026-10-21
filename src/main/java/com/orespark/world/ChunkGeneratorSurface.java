package com.orespark.world;

import net.minecraft.block.Block;
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
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.structure.*;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorSurface implements IChunkGenerator {

    private final Random random = new Random();
    private final World world;
    private final long seed;

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
    }

    private int getHeight(int x, int z) {
        return 60;
    }

    private float getLushness(int x, int z) {
        return MathHelper.clamp((z - x)/7.0710678118f, -100f, 100f);
    }

    private float getTemp(int x, int z) {
        return MathHelper.clamp(z / 10f,-100f,100f);
    }

    private void generateGround(int x, int z, ChunkPrimer chunkPrimer) {
        for (int dx = 0; dx < 16; dx++) {
            int px = x * 16 + dx;
            for (int dz = 0; dz < 16; dz++) {
                int pz = z * 16 + dz;
                int height = getHeight(px,pz);
                chunkPrimer.setBlockState(dx,0,dz,debugBlocks[0]);
                for (int y = 1; y < height; y++) {
                    chunkPrimer.setBlockState(dx,y,dz,debugBlocks[1]);
                }
                float temp = getTemp(px,pz);
                float lushness = getLushness(px,pz);
                if (temp > 25f) {
                    chunkPrimer.setBlockState(dx,height,dz,debugBlocks[3]);
                }
                else if (temp < -25f) {
                    chunkPrimer.setBlockState(dx,height,dz,debugBlocks[4]);
                }
                else {
                    chunkPrimer.setBlockState(dx,height,dz,debugBlocks[2]);
                }

                if (lushness > 25f) {
                    chunkPrimer.setBlockState(dx,height+1,dz,debugBlocks[5]);
                } else if (lushness < -25f) {
                    chunkPrimer.setBlockState(dx,height+1,dz,debugBlocks[6]);
                }
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        this.random.setSeed((long)x * 341873128712L + (long)z * 132897987541L);
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        generateGround(x,z,chunkPrimer);

        Chunk chunk = new Chunk(world,chunkPrimer,x,z);

        chunk.generateSkylightMap();

        return chunk;
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
