package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityGrub;
import com.orespark.entity.EntitySolanum;
import com.orespark.entity.model.ModelBee;
import com.orespark.entity.model.ModelGrub;
import com.orespark.entity.model.ModelSolanum;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderSolanum extends RenderLiving<EntitySolanum> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/solanum.png");

    public RenderSolanum(RenderManager manager) {
        super(manager, new ModelSolanum(), 3.25f);
    }

    @Override
    public void doRender(EntitySolanum entity, double x, double y, double z, float entityYaw, float partialTicks) {
        switch (entity.getState()) {
            case EntitySolanum.IDLE:
                shadowSize = 3.20f;
                break;
            case EntitySolanum.OPEN:
                shadowSize = 2.65f;
                break;
            case EntitySolanum.CLOSED:
                shadowSize = 1.2f;
                break;
            default:
                break;
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySolanum entitySolanum) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntitySolanum p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.applyRotations(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

}
