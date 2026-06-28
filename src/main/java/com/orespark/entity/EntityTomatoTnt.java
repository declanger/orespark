package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.particle.ParticleSplat;
import com.orespark.particle.ParticleSplatBig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSplatBig(world,posX,posY + 0.3f,posZ,0,0,0, 3.5f));
        List<Entity> entities = this.world.getEntitiesWithinAABBExcludingEntity(owner,new AxisAlignedBB(posX - 2,posY - 2,posZ - 2,posX + 2,posY + 2,posZ + 2));
        DamageSource damageSource = owner == null ? DamageSource.causeExplosionDamage((Explosion) null) : DamageSource.causeExplosionDamage(owner);
        for (Entity entity : entities) {
            float d = entity.getDistance(this) + 0.01f;
            entity.attackEntityFrom(damageSource,(9f - d));
            double x = (entity.posX - posX) / d * 0.65;
            double y = (entity.posY - posY) / d * 0.45;
            double z = (entity.posZ - posZ) / d * 0.65;
            Orespark.LOGGER.info("x: " + x + " y: " + y + " z: " + z);
            entity.motionX += x;
            entity.motionY += y;
            entity.motionZ += z;
        }

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
