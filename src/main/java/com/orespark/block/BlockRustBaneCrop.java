package com.orespark.block;

import com.orespark.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockRustBaneCrop extends BlockCrops {

    public BlockRustBaneCrop() {
        setRegistryName("rustbane");
        setTranslationKey("rustbane");
    }

    @Override
    protected Item getSeed() {
        return ModItems.RUSTBANE_SEED;
    }

    @Override
    protected Item getCrop() {
        return ModItems.RUSTBANE_SEED;
    }
}
