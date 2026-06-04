package com.orespark.block.entity;

import com.orespark.item.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockFletchrootCrop extends BlockCrops {

    public BlockFletchrootCrop() {
        setRegistryName("fletchroot");
        setTranslationKey("fletchroot");
    }

    @Override
    protected Item getSeed() {
        return ModItems.PLANT_ARROW_SEED;
    }

    @Override
    protected Item getCrop() {
        return ModItems.PLANT_ARROW;
    }
}
