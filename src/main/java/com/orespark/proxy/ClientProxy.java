package com.orespark.proxy;

import com.orespark.Orespark;
import com.orespark.particle.ParticleSplat;
import com.orespark.particle.ParticleSplatBig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Orespark.MODID + ":" + id, "inventory"));
    }

    public void registerParticle() {
        int id = 255;
        Minecraft.getMinecraft().effectRenderer.registerParticle(id++, new ParticleSplat.ParticleSplatFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(id++, new ParticleSplatBig.ParticleSplatBigFactory());
    }
}
