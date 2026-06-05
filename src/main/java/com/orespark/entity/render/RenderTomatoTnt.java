package com.orespark.entity.render;

import com.orespark.Orespark;
import com.orespark.entity.EntityDetomato;
import com.orespark.entity.EntityTomatoTnt;
import com.orespark.entity.model.ModelDetomato;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;


public class RenderTomatoTnt extends Render<EntityTomatoTnt> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(Orespark.MODID + ":textures/entity/detomato.png");
    private ModelDetomato model = new ModelDetomato();

    public RenderTomatoTnt(RenderManager manager) {
        super(manager);
    }

    public void doRender(EntityTomatoTnt entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        this.bindEntityTexture(entity);

        // 1. Move to the entity's position in the world
        GlStateManager.translate((float)x, (float)y, (float)z);

        // Calculate your scaling factor (keep your original logic, but base it around 1.0)
        float timeLeft = 1f - (entity.getFuse() - partialTicks) / 80f;
        float f = (MathHelper.sin(timeLeft * 3.14159265359f * 12.5f) + 1) * 0.5f;
        float white = f;

        float t = Math.max(0,timeLeft - 0.92f) * 12.5f;
        t *= t * t * t;

        f = f * 0.2f;
        f += 0.1f * timeLeft + t;

        float scaleModifier = 1.0f + f; // This scales it up and down from its normal size

        GlStateManager.scale(scaleModifier + t * 0.5f, f * 0.1f + 1 + t * 0.3f, scaleModifier + t * 0.5f);
        GlStateManager.rotate(180f, 1.0F, 0.0F, 0.0F);
        //GlStateManager.rotate(-entityYaw, 0.0F, 1.0F, 0.0F);

        GlStateManager.translate(0.0F, -24f/16f, 0.0F);

        // 5. Render the model using the standard Minecraft model scale factor (0.0625F or 1/16)
        this.model.render(entity, 0.0F, 0.0F, 0, entityYaw, entity.rotationPitch, 0.0625F);


        if (white > 0) {
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();

            // Standard blending for overlays
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            // Keep texture enabled so OpenGL knows where the transparent pixels are
            GlStateManager.enableTexture2D();

            // Set the color to white, using 'white' variable for intensity/opacity
            GlStateManager.color(1F, 0.15F, 0.05F, white);
//
//            // Configure texture environment to override RGB with the color, but KEEP the texture's original alpha
            GlStateManager.glTexEnvi(8960, 8704, 34160); // GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE
            GlStateManager.glTexEnvi(8960, 34161, 260);   // GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_REPLACE
//            GlStateManager.glTexEnvi(8960, 34168, 5121); // GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_PRIMARY_COLOR          Causes Error
//            GlStateManager.glTexEnvi(8960, 34162, 34162); // GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE            Causes Error
            GlStateManager.glTexEnvi(8960, 34176, 5890); // GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE
//            GlStateManager.glTexEnvi(8960, 34184, 770);  // GL_TEXTURE_ENV, GL_OPERAND0_ALPHA, GL_SRC_ALPHA           Causes Error
//
            // Render the second pass (the flash overlay)
            this.model.render(entity, 0.0F, 0.0F, 0, entityYaw, entity.rotationPitch, 0.0625F);

            // Reset texture environment back to normal Minecraft rendering defaults
            GlStateManager.glTexEnvi(8960, 8704, 8448);  // GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE
            GlStateManager.glTexEnvi(8960, 34161, 8448); // GL_TEXTURE_ENV, GL_COMBINE_RGB, GL_MODULATE
            GlStateManager.glTexEnvi(8960, 34162, 8448); // GL_TEXTURE_ENV, GL_COMBINE_ALPHA, GL_MODULATE
//            GlStateManager.glTexEnvi(8960, 34168, 5890); // GL_TEXTURE_ENV, GL_SOURCE0_RGB, GL_TEXTURE                Causes Error
            GlStateManager.glTexEnvi(8960, 34176, 5890); // GL_TEXTURE_ENV, GL_SOURCE0_ALPHA, GL_TEXTURE
//            GlStateManager.glTexEnvi(8960, 34184, 770);  // GL_TEXTURE_ENV, GL_OPERAND0_ALPHA, GL_SRC_ALPHA           Causes Error
            GlStateManager.glTexEnvi(8960, 34192, 768);  // GL_TEXTURE_ENV, GL_OPERAND0_RGB, GL_SRC_COLOR

            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }
        GlStateManager.disableAlpha();
        GlStateManager.popMatrix();
        GlStateManager.enableCull();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityTomatoTnt entityTomatoTnt) {
        return TEXTURES;
    }

}
