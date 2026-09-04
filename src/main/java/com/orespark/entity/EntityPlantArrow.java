package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityPlantArrow extends EntityArrow {
    public EntityPlantArrow(World worldIn) {
        super(worldIn);
    }

    public EntityPlantArrow(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    public EntityPlantArrow(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ModItems.PLANT_ARROW);
    }

    @Override
    public void onUpdate() {
        if (getIsCritical()) {
            setIsCritical(false);
        }
        super.onUpdate();
        if (!this.inGround) {
            if (!this.hasNoGravity())
            {
                this.motionY += 0.035D;

                //this.motionX = pX;
                //this.motionZ = pZ;

            }
        }
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        this.setDamage(1);
        super.onHit(raytraceResultIn);
    }

    @Override
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity * 2f, inaccuracy);
        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
            this.motionY += shooter.motionY;
        }
    }
}

