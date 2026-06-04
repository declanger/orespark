package com.orespark.item.tool;

import com.orespark.entity.EntityPlantArrow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPlantArrow extends ItemArrow {

    private String name;

    public ItemPlantArrow(String name) {
        super();
        this.name = name;
        setTranslationKey(name);
        setRegistryName(name);

    }

    @Override
    public EntityArrow createArrow(World worldIn, ItemStack stack, EntityLivingBase shooter)
    {
        EntityPlantArrow arrow = new EntityPlantArrow(worldIn, shooter);
        return arrow;
    }

}
