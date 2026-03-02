package com.orespark.item;

import com.orespark.Orespark;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood {

    protected String name;

    public ItemFoodBase(String name, int hunger,  float saturation) {
        super(hunger,saturation,false);

        this.name = name;
        setTranslationKey(name);
        setRegistryName(name);
    }


    @Override
    public ItemFoodBase setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
