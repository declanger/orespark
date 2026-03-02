package com.orespark.item.tool;

import com.orespark.Orespark;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ItemSwordBase extends ItemSword {
    private String name;

    public ItemSwordBase(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setTranslationKey(name);
        this.name = name;
    }

    @Override
    public ItemSwordBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
