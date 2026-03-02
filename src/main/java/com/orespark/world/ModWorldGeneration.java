package com.orespark.world;

import com.orespark.block.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class ModWorldGeneration implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) { //Overworld
            generateOverworld(random,chunkX,chunkZ,world,chunkGenerator,chunkProvider);
        }
    }

    public void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        generateOre(ModBlocks.RUBY_ORE.getDefaultState(),world,random,chunkX * 16,chunkZ * 16,10,36, 3 + random.nextInt(6), 10);
        generateOre(Blocks.DIAMOND_BLOCK.getDefaultState(),world,random,chunkX * 16,chunkZ * 16,12,24, 1 + random.nextInt(2), 2);
        generateOre(Blocks.EMERALD_BLOCK.getDefaultState(),world,random,chunkX * 16,chunkZ * 16,12,30, 1 + random.nextInt(2), 2);
        generateOre(Blocks.GOLD_BLOCK.getDefaultState(),world,random,chunkX * 16,chunkZ * 16,12,30, 1 + random.nextInt(3), 2);
    }

    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
        int delta = maxY - minY;

        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16),minY + random.nextInt(delta),z + random.nextInt(16));

            WorldGenMinable genMinable = new WorldGenMinable(ore,size);
            genMinable.generate(world,random,pos);
        }
    }
}
