package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityGrub;
import com.orespark.entity.EntityRhinoBeetle;
import com.orespark.entity.model.ModelGrub;
import com.orespark.entity.model.ModelRhinoBeetle;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderRhinoBeetle extends RenderLiving<EntityRhinoBeetle> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/rhinobeetle.png");

    public RenderRhinoBeetle(RenderManager manager) {
        super(manager, new ModelRhinoBeetle(), 1f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRhinoBeetle entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityRhinoBeetle p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.applyRotations(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

}
