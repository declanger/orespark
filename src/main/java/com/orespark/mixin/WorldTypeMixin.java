package com.orespark.mixin;

import com.orespark.Orespark;
import com.orespark.world.ChunkGeneratorSurface;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkGeneratorDebug;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.WorldType.DEBUG_ALL_BLOCK_STATES;
import static net.minecraft.world.WorldType.FLAT;

@Mixin(WorldType.class)
public class WorldTypeMixin {

    @Inject(method = "getChunkGenerator(Lnet/minecraft/world/World;Ljava/lang/String;)Lnet/minecraft/world/gen/IChunkGenerator;", at = @At("HEAD"), remap = false, cancellable = true)
    private void getChunkGenerator(World world, String generatorOptions, CallbackInfoReturnable<IChunkGenerator> cir) {
        Orespark.LOGGER.info("GOYIM EXTINCTION BOMB");
        WorldType thiz = (WorldType) (Object) this;
        if (thiz == FLAT) cir.setReturnValue(new ChunkGeneratorFlat(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions));
        if (thiz == DEBUG_ALL_BLOCK_STATES) cir.setReturnValue(new ChunkGeneratorDebug(world));
        cir.setReturnValue(new ChunkGeneratorSurface(world, world.getSeed()));
    }
}
