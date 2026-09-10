package com.orespark.mixin;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {

    @Override
    public void connect() {
        // Check if mod is loaded before registering?
        // Possible with zone.rong.mixinbooter.service.ModDiscoverer.isModPresent(String modId)
        Mixins.addConfiguration("mixins.orespark.json");
    }

}