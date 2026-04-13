package com.orespark.block.entity;

import com.orespark.Orespark;
import com.orespark.block.ModBlocks;
import com.orespark.gui.ContainerCrucible;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class BlockEntityCrucible extends TileEntity {

    private int progress[];


    public BlockEntityCrucible() {
        super();
    }

    public static class InterfaceCrucible implements IInteractionObject {

        private final BlockEntityCrucible crucible;
        private final World world;
        private final BlockPos pos;

        public InterfaceCrucible(BlockEntityCrucible crucible, World world, BlockPos pos) {
            this.crucible = crucible;
            this.world = world;
            this.pos = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer entityPlayer) {
            return new ContainerCrucible(inventoryPlayer, crucible,world,pos);
        }

        @Override
        public String getGuiID() {
            return Orespark.MODID + ":crucible";
        }

        @Override
        public String getName() {
            return "crucible";
        }

        @Override
        public boolean hasCustomName() {
            return false;
        }

        @Override
        public ITextComponent getDisplayName() {
            return new TextComponentTranslation(ModBlocks.CRUCIBLE.getTranslationKey());
        }
    }

}
