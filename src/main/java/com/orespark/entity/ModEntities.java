package com.orespark.entity;

import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    private static int id = 120;
    public static void registerEntities() {
        registerEntity("mantis", EntityMantis.class,50);
        registerEntity("dirt_golem", EntityDirtGolem.class,20);
        registerEntity("stone_golem", EntityStoneGolem.class,20);
        registerEntity("tree_golem", EntityTreeGolem.class,20);
        registerEntity("grub", EntityGrub.class,20);
        registerEntity("rhino_beetle", EntityRhinoBeetle.class,120);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int trackingRange) {
        EntityRegistry.registerModEntity(new ResourceLocation(Orespark.MODID + ":" + name), entity, name,id++, Orespark.instance, trackingRange,1, true);
    }
}
