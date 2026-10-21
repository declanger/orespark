package com.orespark.block;

import com.orespark.Orespark;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

    protected String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setTranslationKey(name);
        setRegistryName(name);
    }


    public BlockBase setToolLevel(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockBase setBlockUnbreakable() {
        setHardness(-1.0f);
        return this;
    }

    @Override
    public BlockBase setHardness(float hardness) {
        super.setHardness(hardness);
        return this;
    }

    @Override
    public BlockBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
