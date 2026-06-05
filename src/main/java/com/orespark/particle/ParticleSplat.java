package com.orespark.particle;

import com.orespark.Orespark;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL40;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ParticleSplat extends Particle {

    private static final ResourceLocation SPLAT_TEXTURE = new ResourceLocation(Orespark.MODID + ":textures/particle/splat.png");
    private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
    private TextureManager textureManager;
    private final int lifeTime = 6;
    private int life = 0;
    private float size = 1.0f;


    public ParticleSplat(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        textureManager = Minecraft.getMinecraft().getTextureManager();
    }

    public ParticleSplat(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float size) {
        this(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.size = size;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        int i = (int)((this.life + partialTicks)/ (float)this.lifeTime * 5.0f);

        if (i < 7.99)
        {
            this.textureManager.bindTexture(SPLAT_TEXTURE);
            float texX1 = i / 5.0F;
            float texX2 = texX1 + 0.2F;
            float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
            float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
            float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516,0.1f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderHelper.disableStandardItemLighting();
            buffer.begin(7, VERTEX_FORMAT);
            buffer.pos((double)(f5 - rotationX * size - rotationXY * size), (double)(f6 - rotationZ * size), (double)(f7 - rotationYZ * size - rotationXZ * size)).tex((double)texX2, (double)1).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 - rotationX * size + rotationXY * size), (double)(f6 + rotationZ * size), (double)(f7 - rotationYZ * size + rotationXZ * size)).tex((double)texX2, (double)0).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 + rotationX * size + rotationXY * size), (double)(f6 + rotationZ * size), (double)(f7 + rotationYZ * size + rotationXZ * size)).tex((double)texX1, (double)0).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            buffer.pos((double)(f5 + rotationX * size - rotationXY * size), (double)(f6 - rotationZ * size), (double)(f7 + rotationYZ * size - rotationXZ * size)).tex((double)texX1, (double)1).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
            Tessellator.getInstance().draw();
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        ++this.life;

        if (this.life == this.lifeTime)
        {
            this.setExpired();
        }
    }

    @Override
    public int getFXLayer() {
        return 3;
    }

    @Override
    public int getBrightnessForRender(float partialTick)
    {
        return 61680;
    }

    @SideOnly(Side.CLIENT)
    public static class ParticleSplatFactory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new ParticleSplat(worldIn,xCoordIn,yCoordIn,zCoordIn,xSpeedIn,ySpeedIn,zSpeedIn);
        }
    }
}
