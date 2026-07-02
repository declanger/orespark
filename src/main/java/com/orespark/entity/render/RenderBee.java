package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityBee;
import com.orespark.entity.EntityGrub;
import com.orespark.entity.model.ModelBee;
import com.orespark.entity.model.ModelGrub;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderBee extends RenderLiving<EntityBee> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/bee.png");

    public RenderBee(RenderManager manager) {
        super(manager, new ModelBee(), 0.15f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBee entityBee) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityBee p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.applyRotations(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

}
