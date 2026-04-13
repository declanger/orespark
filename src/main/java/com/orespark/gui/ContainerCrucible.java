package com.orespark.gui;

import com.orespark.block.entity.BlockEntityCrucible;
import com.orespark.util.OresparkUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Enumeration;

public class ContainerCrucible extends Container {

    private final BlockEntityCrucible crucible;
    private final InventoryPlayer player;
    private final World world;
    private final BlockPos pos;

    public ContainerCrucible(InventoryPlayer inventory, BlockEntityCrucible crucible, World world, BlockPos pos) {
        this.crucible = crucible;
        this.player = inventory;
        this.world = world;
        this.pos = pos;
        this.addSlotToContainer(new SlotCrucible(inventory,0,12,12));
    }

    @Override
    public void addListener(IContainerListener listener) {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, player);
    }

    @Override
    public void updateProgressBar(int p_75137_1_, int p_75137_2_) {
        super.updateProgressBar(p_75137_1_, p_75137_2_);
    }

    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        if (!world.isRemote) {
            super.onContainerClosed(p_75134_1_);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack temp = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(i);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            temp = stack.copy();
            if (i < this.inventorySlots.size()) {
                if (!this.mergeItemStack(stack, this.inventorySlots.size(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stack, 0, this.inventorySlots.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return temp;
    }
}
