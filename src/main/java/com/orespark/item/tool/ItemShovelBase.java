package com.orespark.item.tool;

import com.orespark.Orespark;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;

public class ItemShovelBase extends ItemSpade {

    private String name;

    public ItemShovelBase(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setTranslationKey(name);
        this.name = name;
    }

    @Override
    public ItemShovelBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
