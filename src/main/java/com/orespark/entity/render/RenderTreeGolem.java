package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityTreeGolem;
import com.orespark.entity.model.ModelTreeGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderTreeGolem extends RenderLiving<EntityTreeGolem> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/treegolem.png");

    public RenderTreeGolem(RenderManager manager) {
        super(manager, new ModelTreeGolem(), 0.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTreeGolem entityTreeGolem) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityTreeGolem treeGolem, float f1, float f2, float f3) {
        super.applyRotations(treeGolem, f1, f2, f3);
    }
}
