package com.orespark.mixin;

import com.orespark.util.OresparkUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemArmor.class)
public class ItemArmorMixin {

    @Shadow
    private ItemArmor.ArmorMaterial material;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructorReturn(CallbackInfo ci) {
        OresparkUtil.addMeltable(material.name(),(Item)(Object)this);
    }
}
