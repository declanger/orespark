package com.orespark.item.custom;

import com.orespark.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;

public class ItemPlantArrowSeed extends ItemSeeds {

    public ItemPlantArrowSeed() {
        super(ModBlocks.FLETCHROOT, Blocks.FARMLAND);
        setTranslationKey("plant_arrow_seed");
        setRegistryName("plant_arrow_seed");
        super.setCreativeTab(CreativeTabs.MISC);
    }

}
