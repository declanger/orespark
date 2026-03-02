package com.orespark.item.tool;

import com.orespark.Orespark;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.core.util.SystemNanoClock;

import java.util.HashSet;
import java.util.Set;

public class ItemFellerAxe extends ItemAxeBase {

    private static Item.ToolMaterial fellerMaterial = EnumHelper.addToolMaterial("FELLER",2,5,20.0f,0.0f,25);

    public ItemFellerAxe(String name) {
        super(fellerMaterial, name);
    }

    @Override
    public ItemFellerAxe setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack itemStack, World world, IBlockState blockState, BlockPos blockPos, EntityLivingBase entity) {
        if (!world.isRemote) {
            int[] ids = OreDictionary.getOreIDs(new ItemStack(blockState.getBlock()));
            int woodId = OreDictionary.getOreID("logWood");
            boolean wood = false;
            for ( int id : ids) {
                if (id == woodId) {
                    wood = true;
                    break;
                }
            }
            if (wood) {
                Block log = blockState.getBlock();

                int leavesId = OreDictionary.getOreID("treeLeaves");
                Block leaves = null;
                for (int y = 0; y < 255 - blockPos.getY(); y++) {
                    Block target = world.getBlockState(blockPos.up(y)).getBlock();
                    if (target == Blocks.AIR) {
                        break;
                    }
                    if (target != log) {
                        ids = OreDictionary.getOreIDs(new ItemStack(target));
                        for ( int id : ids) {
                            if (id == leavesId) {
                                leaves = target;
                                break;
                            }
                        }
                        if (leaves != null) {
                            break;
                        }
                    }
                }
                SystemNanoClock clock = new SystemNanoClock();
                long time = clock.nanoTime();
                Set<BlockPos> positions = new HashSet<>();
                floodFill(positions,blockPos,0,world,log,leaves, EnumFacing.UP, true);
                time = clock.nanoTime() - time;
                Orespark.LOGGER.warn("Flood fill took " + time + " nano seconds and found " + positions.size() + " blocks");
                for (BlockPos pos : positions) {
                    world.setBlockToAir(pos);
                }
            }
        }

        return super.onBlockDestroyed(itemStack, world, blockState, blockPos, entity);
    }

    private void floodFill(Set<BlockPos> set, BlockPos pos, int depth, World world, Block log, Block leaves, EnumFacing facing, boolean prio) {
        if (depth < 25) {
            Block target = world.getBlockState(pos).getBlock();
            if (target == leaves || target == log) {
                if (set.add(pos)) {
                    if (facing == EnumFacing.UP) {
                        floodFill(set, pos.up(), depth + 1, world, log, leaves, EnumFacing.UP, true);
                        floodFill(set, pos.north(), depth + 1, world, log, leaves, EnumFacing.NORTH, true);
                        floodFill(set, pos.south(), depth + 1, world, log, leaves, EnumFacing.SOUTH, true);
                        floodFill(set, pos.east(), depth + 1, world, log, leaves, EnumFacing.EAST, true);
                        floodFill(set, pos.west(), depth + 1, world, log, leaves, EnumFacing.WEST, true);
                    }
                    else {
                        if (prio) {
                            floodFill(set,pos.offset(facing),depth+1,world,log,leaves,facing,true);
                            floodFill(set,pos.offset(facing.rotateY()),depth+1,world,log,leaves,facing.rotateY(),false);
                        }
                        else {
                            floodFill(set,pos.offset(facing),depth+1,world,log,leaves,facing,false);
                        }
                    }
                }
            }
        }
    }


}
