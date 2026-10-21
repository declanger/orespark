package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityAnt;
import com.orespark.entity.EntityBee;
import com.orespark.entity.EntityTermite;
import com.orespark.entity.model.ModelAnt;
import com.orespark.entity.model.ModelBee;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderAnt extends RenderLiving<EntityAnt> {

    public static final ResourceLocation ANT = new ResourceLocation(Orespark.MODID + ":textures/entity/ant.png");
    public static final ResourceLocation TERMITE = new ResourceLocation(Orespark.MODID + ":textures/entity/termite.png");

    public RenderAnt(RenderManager manager) {
        super(manager, new ModelAnt(), 0.15f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAnt entityAnt) {
        return entityAnt instanceof EntityTermite ? TERMITE : ANT;
    }

    @Override
    protected void applyRotations(EntityAnt p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.applyRotations(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

}
