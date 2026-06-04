package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.particle.ParticleSplat;
import com.orespark.util.OresparkUtil;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityDetomato extends Entity implements IProjectile {

    private EntityLivingBase owner;
    private NBTTagCompound ownerNbt;

    public EntityDetomato(World world) {
        super(world);
        this.setSize(.5f,.5f);
    }

    public EntityDetomato(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x,y,z);
    }

    public EntityDetomato(World worldIn, EntityLivingBase throwerIn) {
        this(worldIn,throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight(), throwerIn.posZ);
        Vec3d offset = throwerIn.getLookVec();
        this.posX += offset.x;
        this.posY += offset.y;
        this.posZ += offset.z;
        this.rotationYaw = throwerIn.rotationYaw;
        this.prevRotationYaw = rotationYaw;
        this.owner = throwerIn;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ownerNbt != null)
        {
            this.restoreOwnerFromSave();
        }

        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null)
        {
            vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        if (!world.isRemote) { // this is done bc owner is only stored in the server detomato so it would make particles when hitting the owner
            entity = this.getHitEntity(vec3d, vec3d1);
        }

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
        {
            if(raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
                if (world.getBlockState(raytraceresult.getBlockPos()).isFullCube())
                {
                    if (!world.isRemote) {
                        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSplat(world,posX + raytraceresult.sideHit.getXOffset() * 0.7,posY + raytraceresult.sideHit.getYOffset() * 0.7,posZ + raytraceresult.sideHit.getZOffset() * 0.7,0,0,0));
                        this.setDead();
                    }
                    return;
                }
            }
            else {
                this.onHit(raytraceresult);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
            this.motionX *= 0.9900000095367432D;
            this.motionY *= 0.9900000095367432D;
            this.motionZ *= 0.9900000095367432D;

            if (!this.hasNoGravity())
            {
                this.motionY -= 0.05999999865889549D;
            }

            this.setPosition(this.posX, this.posY, this.posZ);

    }

    private void onHit(RayTraceResult raytraceresult) {
        if (raytraceresult.entityHit != null)
        {
            raytraceresult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this,this.owner != null ? this.owner : null).setProjectile(), 3.0F);
            if (raytraceresult.entityHit == owner) return;
            this.setDead();
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSplat(world,posX,posY,posZ,0,0,0));
        }
    }

    @Nullable
    private Entity getHitEntity(Vec3d p_190538_1_, Vec3d p_190538_2_)
    {
        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABB (EntityLivingBase.class, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for (Entity entity1 : list)
        {
            if (entity1 != this.owner)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(p_190538_1_, p_190538_2_);

                if (raytraceresult != null)
                {
                    double d1 = p_190538_1_.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }

    @Override
    public void setDead() {
        super.setDead();
        if (!world.isRemote) {
            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class,new AxisAlignedBB(posX-  2,posY - 2,posZ - 2,posX + 2,posY + 2,posZ + 2));
            for (EntityLivingBase e : entities) {
                if (e != owner) {
                    e.attackEntityFrom(DamageSource.causeThrownDamage(this,this.owner != null ? this.owner : null).setProjectile(), 2.0F);
                }
            }
        }
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("Owner", 10))
        {
            this.ownerNbt = compound.getCompoundTag("Owner");
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        if (this.owner != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            UUID uuid = this.owner.getUniqueID();
            nbttagcompound.setUniqueId("OwnerUUID", uuid);
            compound.setTag("Owner", nbttagcompound);
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (-180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (-180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    private void restoreOwnerFromSave()
    {
        if (this.ownerNbt != null && this.ownerNbt.hasUniqueId("OwnerUUID"))
        {
            UUID uuid = this.ownerNbt.getUniqueId("OwnerUUID");

            for (EntityLivingBase entity : this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(15.0D)))
            {
                if (entity.getUniqueID().equals(uuid))
                {
                    this.owner = entity;
                    break;
                }
            }
        }

        this.ownerNbt = null;
    }
}
