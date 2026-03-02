package com.orespark.item.tool;

import com.google.common.collect.Sets;
import com.orespark.Orespark;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

public class ItemAxeBase extends ItemAxe {

    private String name;

    public ItemAxeBase(Item.ToolMaterial material, String name) {
        super(material,material.getAttackDamage(),-3.0f);
        setRegistryName(name);
        setTranslationKey(name);
        this.name = name;
    }

    @Override
    public ItemAxeBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}
