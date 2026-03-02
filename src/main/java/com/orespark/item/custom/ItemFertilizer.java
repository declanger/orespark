package com.orespark.item.custom;

import com.orespark.item.ItemBase;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nullable;

public class ItemFertilizer extends ItemBase {

    public ItemFertilizer(String name) {
        super(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos blockPos, EnumHand hand, EnumFacing facing, float f1, float f2, float f3) {
        ItemStack itemStack = player.getHeldItem(hand);
        if (!player.canPlayerEdit(blockPos.offset(facing), facing, itemStack)) {
            return EnumActionResult.FAIL;
        }
        if (applyBonemeal(itemStack, world, blockPos, player, hand)) {
            if (!world.isRemote) {
                world.playEvent(2005, blockPos, 0);
            }

            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    public static boolean applyBonemeal(ItemStack itemStack, World world, BlockPos blockPos, EntityPlayer player, @Nullable EnumHand hand) {
        IBlockState iblockstate = world.getBlockState(blockPos);
        int hook = ForgeEventFactory.onApplyBonemeal(player, world, blockPos, iblockstate, itemStack, hand);
        if (hook != 0) {
            return hook > 0;
        } else {
            if (iblockstate.getBlock() instanceof BlockSapling) {
                BlockSapling sapling = (BlockSapling)iblockstate.getBlock();
                if (!world.isRemote) {
                    itemStack.shrink(1);
                    sapling.generateTree(world,blockPos,iblockstate,world.rand);
                }
                return true;
            }
            else if (iblockstate.getBlock() instanceof IGrowable) {
                IGrowable igrowable = (IGrowable)iblockstate.getBlock();
                if (igrowable.canGrow(world, blockPos, iblockstate, world.isRemote)) {
                    if (!world.isRemote) {
                        if (igrowable.canUseBonemeal(world, world.rand, blockPos, iblockstate)) {
                            igrowable.grow(world, world.rand, blockPos, iblockstate);
                        }

                        itemStack.shrink(1);
                    }

                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public ItemFertilizer setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
