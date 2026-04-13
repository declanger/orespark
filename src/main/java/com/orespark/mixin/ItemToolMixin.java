package com.orespark.mixin;

import com.orespark.util.OresparkUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemTool.class)
public class ItemToolMixin {

    @Shadow
    private Item.ToolMaterial toolMaterial;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructorReturn(CallbackInfo ci) {
        OresparkUtil.addMeltable(toolMaterial.name(),(Item)(Object)this);
    }
}
