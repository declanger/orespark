package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityPlantArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderPlantArrow extends RenderArrow<EntityPlantArrow> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/plantarrow.png");

    public RenderPlantArrow(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityPlantArrow entity) {
        return TEXTURES;
    }
}
