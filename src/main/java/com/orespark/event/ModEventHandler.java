package com.orespark.event;

import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Orespark.MODID)
public class ModEventHandler {

    @SubscribeEvent
    public static void onSpawn(LivingSpawnEvent.SpecialSpawn event) {
        World world = event.getWorld();
        if (world.isRemote) { return; }
        Entity entity = event.getEntity();
        if (entity instanceof EntityCreeper) {
            EntityCreeper creeper = (EntityCreeper) entity;
            if (world.getDifficulty() != EnumDifficulty.EASY && world.rand.nextFloat() < (world.getDifficulty() == EnumDifficulty.HARD ? 0.2f : 0.05f)) {
                creeper.onStruckByLightning(null);
                creeper.extinguish();
                creeper.setHealth(creeper.getMaxHealth());
            }
        }
    }
}
