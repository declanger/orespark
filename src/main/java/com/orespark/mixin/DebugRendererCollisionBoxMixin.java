package com.orespark.mixin;

import net.minecraft.client.renderer.debug.DebugRendererCollisionBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugRendererCollisionBox.class)
public class DebugRendererCollisionBoxMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet.minecraft.client.renderer.RenderGlobal.drawSelectionBoundingBox"))
    private void render(float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {

    }
}
