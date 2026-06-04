package com.orespark.entity;

import com.orespark.particle.ParticleSplat;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTomatoTnt extends Entity{
    
    private static final DataParameter<Integer> FUSE = EntityDataManager.<Integer>createKey(EntityTomatoTnt.class, DataSerializers.VARINT);
    @Nullable
    private EntityLivingBase owner;
    private int fuse;

    public EntityTomatoTnt(World worldIn)
    {
        super(worldIn);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.isImmuneToFire = true;
        this.setSize(0.4375F, 0.59375F);
    }

    public EntityTomatoTnt(World worldIn, double x, double y, double z, EntityLivingBase igniter)
    {
        this(worldIn);
        this.setPosition(x, y, z);
        double f = Math.random() * (Math.PI * 2D);
        this.motionX = -Math.sin(f) * 0.02;
        this.motionY = 5d;
        this.motionZ = -Math.cos(f) * 0.02;
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.owner = igniter;
    }

    protected void entityInit()
    {
        this.dataManager.register(FUSE, Integer.valueOf(80));
    }

    protected boolean canTriggerWalking()
    {
        return false;
    }

    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (!this.hasNoGravity())
        {
            this.motionY -= 0.03999999910593033D;
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        --this.fuse;

        if (this.fuse <= 0)
        {
            this.setDead();

            if (!this.world.isRemote)
            {
                this.explode();
            }
        }
        else
        {
            this.handleWaterMovement();
            this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void explode()
    {
        float f = 4.0F;
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSplat(world,posX,posY,posZ,0,0,0));
        this.world.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, 4.0F, true);
    }

    protected void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setShort("Fuse", (short)this.getFuse());
    }

    protected void readEntityFromNBT(NBTTagCompound compound)
    {
        this.setFuse(compound.getShort("Fuse"));
    }

    @Nullable
    public EntityLivingBase getOwner()
    {
        return this.owner;
    }

    public float getEyeHeight()
    {
        return 0.0F;
    }

    public void setFuse(int fuseIn)
    {
        this.dataManager.set(FUSE, Integer.valueOf(fuseIn));
        this.fuse = fuseIn;
    }

    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (FUSE.equals(key))
        {
            this.fuse = this.getFuseDataManager();
        }
    }

    public int getFuseDataManager()
    {
        return (Integer) this.dataManager.get(FUSE);
    }

    public int getFuse()
    {
        return this.fuse;
    }

}
