package com.orespark.item.custom;

import com.orespark.Orespark;
import com.orespark.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemRustBaneSeed extends ItemSeeds {

    public ItemRustBaneSeed() {
        super(ModBlocks.RUSTBANE, Blocks.FARMLAND);
        setTranslationKey("rustbane_seed");
        setRegistryName("rustbane_seed");
        super.setCreativeTab(CreativeTabs.MISC);
    }

}
