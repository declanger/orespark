package com.orespark.entity;

import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    private static int id = 120;

    private static final int trackingRange = 120;

    public static void registerEntities() {
        registerEntity("mantis", EntityMantis.class,trackingRange);
        registerEntity("dirt_golem", EntityDirtGolem.class,trackingRange);
        registerEntity("stone_golem", EntityStoneGolem.class,trackingRange);
        registerEntity("tree_golem", EntityTreeGolem.class,trackingRange);
        registerEntity("grub", EntityGrub.class,trackingRange);
        registerEntity("rhino_beetle", EntityRhinoBeetle.class,trackingRange);
        registerEntity("detomato_projectile", EntityDetomato.class,trackingRange);
        registerEntity("detomato_tnt", EntityTomatoTnt.class,trackingRange);
        registerEntity("plant_arrow", EntityPlantArrow.class,trackingRange);
        registerEntity("bee", EntityBee.class,trackingRange);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int trackingRange) {
        EntityRegistry.registerModEntity(new ResourceLocation(Orespark.MODID + ":" + name), entity, name,id++, Orespark.instance, trackingRange,1, true);
    }
}
