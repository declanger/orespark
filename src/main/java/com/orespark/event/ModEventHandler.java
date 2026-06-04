package com.orespark.event;

import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySkeletonHorse;
import net.minecraft.entity.passive.EntityZombieHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
            if (world.getDifficulty() != EnumDifficulty.EASY && world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.2f : 0.05f)) {
                creeper.onStruckByLightning(null);
                creeper.extinguish();
                creeper.setHealth(creeper.getMaxHealth());
            }
        }
        else if (event.getWorld().canBlockSeeSky(new BlockPos(event.getX(), event.getY(), event.getZ()))) {
            if (entity instanceof EntityZombie) {
                EntityZombie zombie = (EntityZombie) entity;
                if (world.getDifficulty() != EnumDifficulty.EASY && world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.4f : 0.1f)) {
                    AbstractHorse horse;
                    if (world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.4f : 0.1f)) {
                        horse = new EntityHorse(world);
                        horse.replaceItemInInventory(401, new ItemStack(world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.7f : 0.3f) ? (world.getDifficulty() == EnumDifficulty.HARD ? Items.DIAMOND_HORSE_ARMOR : Items.GOLDEN_HORSE_ARMOR) : (world.getDifficulty() == EnumDifficulty.HARD ? Items.GOLDEN_HORSE_ARMOR : Items.IRON_HORSE_ARMOR)));
                    }
                    else { horse = new EntityZombieHorse(world); }
                    horse.setPositionAndRotation(zombie.posX,zombie.posY,zombie.posZ,zombie.rotationYaw,zombie.rotationPitch);
                    world.spawnEntity(horse);
                    horse.setHorseTamed(true);
                    horse.replaceItemInInventory(400,new ItemStack(Items.SADDLE));
                    zombie.startRiding(horse);
                }
            }
            else if (entity instanceof EntitySkeleton) {
                EntitySkeleton skeleton = (EntitySkeleton) entity;
                if (world.getDifficulty() != EnumDifficulty.EASY && world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.4f : 0.1f)) {
                    AbstractHorse horse;
                    if (world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.4f : 0.1f)) {
                        horse = new EntityHorse(world);
                        horse.replaceItemInInventory(401, new ItemStack(world.rand.nextFloat() <= (world.getDifficulty() == EnumDifficulty.HARD ? 0.7f : 0.3f) ? (world.getDifficulty() == EnumDifficulty.HARD ? Items.DIAMOND_HORSE_ARMOR : Items.GOLDEN_HORSE_ARMOR) : (world.getDifficulty() == EnumDifficulty.HARD ? Items.GOLDEN_HORSE_ARMOR : Items.IRON_HORSE_ARMOR)));
                    }
                    else { horse = new EntitySkeletonHorse(world); }
                    horse.setPositionAndRotation(skeleton.posX,skeleton.posY,skeleton.posZ,skeleton.rotationYaw,skeleton.rotationPitch);
                    world.spawnEntity(horse);
                    horse.setHorseTamed(true);
                    horse.replaceItemInInventory(400,new ItemStack(Items.SADDLE));
                    skeleton.startRiding(horse);
                }
            }
        }
    }
}
