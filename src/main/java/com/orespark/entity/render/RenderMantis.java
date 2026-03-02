package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityMantis;
import com.orespark.entity.model.ModelMantis;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMantis extends RenderLiving<EntityMantis> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/mantis.png");

    public RenderMantis(RenderManager manager) {
        super(manager, new ModelMantis(), 1f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMantis entityMantis) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityMantis p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.applyRotations(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

}
