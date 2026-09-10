package com.orespark.mixin;

import com.orespark.Orespark;
import com.orespark.util.NotAlignedBB;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.debug.DebugRendererCollisionBox;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RenderManager.class)
@SideOnly(Side.CLIENT)
public class RenderManagerMixin {

    @Shadow
    private double renderPosX;
    @Shadow
    private double renderPosY;
    @Shadow
    private double renderPosZ;

    @Inject(method = "renderDebugBoundingBox(Lnet/minecraft/entity/Entity;DDDFF)V" , at = @At(value = "HEAD"), cancellable = true)
    private void renderDebugBoundingBox(Entity entityIn, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo callbackInfo) {
        GlStateManager.depthMask(false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        float f = entityIn.width / 2.0F;
        AxisAlignedBB axisalignedbb = entityIn.getEntityBoundingBox();
        boolean red = false;
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase ebl = (EntityLivingBase) entityIn;
            red = ebl.hurtTime > 0 || ebl.deathTime > 0;
        }
        RenderGlobal.drawBoundingBox(axisalignedbb.minX - entityIn.posX + x, axisalignedbb.minY - entityIn.posY + y, axisalignedbb.minZ - entityIn.posZ + z, axisalignedbb.maxX - entityIn.posX + x, axisalignedbb.maxY - entityIn.posY + y, axisalignedbb.maxZ - entityIn.posZ + z, 1.0F, red ? 0.25f : 1.0F, red ? 0.25f : 1.0F, 1.0F);
        Entity[] aentity = entityIn.getParts();

        if (aentity != null)
        {
            for (Entity entity : aentity)
            {
                double d0 = (entity.posX - entity.prevPosX) * (double)partialTicks;
                double d1 = (entity.posY - entity.prevPosY) * (double)partialTicks;
                double d2 = (entity.posZ - entity.prevPosZ) * (double)partialTicks;
                AxisAlignedBB axisalignedbb1 = entity.getEntityBoundingBox();
                if (axisalignedbb1 instanceof NotAlignedBB) {
                    double[][] points = ((NotAlignedBB) axisalignedbb1).getCorners();
                    Tessellator tessellator = Tessellator.getInstance();
                    BufferBuilder bufferbuilder = tessellator.getBuffer();
                    bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);

                    bufferbuilder.pos(points[0][0] + d0 - renderPosX,points[0][1] + d1 - renderPosY,points[0][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[1][0] + d0 - renderPosX,points[1][1] + d1 - renderPosY,points[1][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[1][0] + d0 - renderPosX,points[1][1] + d1 - renderPosY,points[1][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[2][0] + d0 - renderPosX,points[2][1] + d1 - renderPosY,points[2][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[2][0] + d0 - renderPosX,points[2][1] + d1 - renderPosY,points[2][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[3][0] + d0 - renderPosX,points[3][1] + d1 - renderPosY,points[3][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[3][0] + d0 - renderPosX,points[3][1] + d1 - renderPosY,points[3][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[0][0] + d0 - renderPosX,points[0][1] + d1 - renderPosY,points[0][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[4][0] + d0 - renderPosX,points[4][1] + d1 - renderPosY,points[4][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[5][0] + d0 - renderPosX,points[5][1] + d1 - renderPosY,points[5][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[5][0] + d0 - renderPosX,points[5][1] + d1 - renderPosY,points[5][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[6][0] + d0 - renderPosX,points[6][1] + d1 - renderPosY,points[6][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[6][0] + d0 - renderPosX,points[6][1] + d1 - renderPosY,points[6][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[7][0] + d0 - renderPosX,points[7][1] + d1 - renderPosY,points[7][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[7][0] + d0 - renderPosX,points[7][1] + d1 - renderPosY,points[7][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[4][0] + d0 - renderPosX,points[4][1] + d1 - renderPosY,points[4][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[0][0] + d0 - renderPosX,points[0][1] + d1 - renderPosY,points[0][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[4][0] + d0 - renderPosX,points[4][1] + d1 - renderPosY,points[4][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[1][0] + d0 - renderPosX,points[1][1] + d1 - renderPosY,points[1][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[5][0] + d0 - renderPosX,points[5][1] + d1 - renderPosY,points[5][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[2][0] + d0 - renderPosX,points[2][1] + d1 - renderPosY,points[2][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[6][0] + d0 - renderPosX,points[6][1] + d1 - renderPosY,points[6][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();

                    bufferbuilder.pos(points[3][0] + d0 - renderPosX,points[3][1] + d1 - renderPosY,points[3][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();
                    bufferbuilder.pos(points[7][0] + d0 - renderPosX,points[7][1] + d1 - renderPosY,points[7][2] + d2 - renderPosZ).color(0.0f,0.7f,1.0f,1.0f).endVertex();


                    tessellator.draw();
                }
                else {
                    RenderGlobal.drawBoundingBox(axisalignedbb1.minX - this.renderPosX + d0, axisalignedbb1.minY - this.renderPosY + d1, axisalignedbb1.minZ - this.renderPosZ + d2, axisalignedbb1.maxX - this.renderPosX + d0, axisalignedbb1.maxY - this.renderPosY + d1, axisalignedbb1.maxZ - this.renderPosZ + d2, 0.25F, 1.0F, 0.0F, 1.0F);
                }
            }
        }

        if (entityIn instanceof EntityLivingBase)
        {
            float f1 = 0.01F;
            RenderGlobal.drawBoundingBox(x - (double)f, y + (double)entityIn.getEyeHeight() - 0.009999999776482582D, z - (double)f, x + (double)f, y + (double)entityIn.getEyeHeight() + 0.009999999776482582D, z + (double)f, 1.0F, 0.7f, 0F, 1.0F);
        }

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        Vec3d vec3d = entityIn.getLook(partialTicks);
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x, y + (double)entityIn.getEyeHeight(), z).color(1.0f, 0.7f, 0, 1.0f).endVertex();
        bufferbuilder.pos(x + vec3d.x * (f * 2 + 0.5f), y + (double)entityIn.getEyeHeight() + vec3d.y * 2.0D, z + vec3d.z * (f * 2 + 0.5f)).color(255, 120, 120, 255).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        callbackInfo.cancel();
    }
}
