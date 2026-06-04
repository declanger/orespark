package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityDetomato;
import com.orespark.entity.model.ModelDetomato;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;



public class RenderDetomato extends Render<EntityDetomato> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/detomato.png");
    private ModelDetomato model = new ModelDetomato();

    public RenderDetomato(RenderManager manager) {
        super(manager);
    }

    public void doRender(EntityDetomato entity, double x, double y, double z, float entityYaw, float partialTicks)
    {

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(-entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0f, -1f, 0f);
        float ageInTicks = (float)entity.ticksExisted + partialTicks;
        this.model.setRotationAngles(0, 0.0F, ageInTicks, entityYaw, entity.rotationPitch, 0.0625F, entity);
        this.model.render(entity, 0, 0.0F, ageInTicks, entityYaw, entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
        GlStateManager.enableCull();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDetomato entityDetomato) {
        return TEXTURES;
    }

}
