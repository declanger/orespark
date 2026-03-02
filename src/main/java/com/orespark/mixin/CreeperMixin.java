package com.orespark.mixin;

import com.orespark.Orespark;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EntityCreeper.class)
public class CreeperMixin {

//    @Shadow
//    private Random rand;
//
//    @Shadow
//    private World world;
//
//    @Shadow
//    private static final DataParameter<Boolean> POWERED= null;
//
//    @Shadow
//    private EntityDataManager dataManager;

    @Inject(at = @At("TAIL"), method = "entityInit")
    private void entityInit(CallbackInfo ci) {
        Orespark.LOGGER.error("Creeper aww man");
//        if (world.getDifficulty() != EnumDifficulty.EASY && rand.nextFloat() < (world.getDifficulty() == EnumDifficulty.HARD ? 0.2f : 0.05f)) {
//
//            this.dataManager.set(POWERED,true);
//        }
    }

}
