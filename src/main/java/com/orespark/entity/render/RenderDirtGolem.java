package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityDirtGolem;
import com.orespark.entity.model.ModelDirtGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDirtGolem extends RenderLiving<EntityDirtGolem> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/dirtgolem.png");

    public RenderDirtGolem(RenderManager manager) {
        super(manager,new ModelDirtGolem(), 1.0f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDirtGolem entityDirtGolem) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityDirtGolem dirtGolem, float f1, float f2, float f3) {
        super.applyRotations(dirtGolem, f1, f2, f3);
    }

}
