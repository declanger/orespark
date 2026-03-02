package com.orespark.item.tool;

import com.orespark.Orespark;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeBase extends ItemPickaxe {

    private String name;

    public ItemPickaxeBase(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setTranslationKey(name);
        this.name = name;
    }

    @Override
    public ItemPickaxeBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
