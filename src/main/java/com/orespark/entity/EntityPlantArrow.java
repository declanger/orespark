package com.orespark.entity;

import com.orespark.item.ModItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
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
        double pX = motionX;
        double pY = motionY;
        double pZ = motionZ;
        super.onUpdate();
        if (!this.inGround) {
            if (!this.hasNoGravity())
            {
                this.motionX = pX;
                this.motionZ = pZ;
                this.motionY = pY -0.01D;
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) {
        this.setDamage(1);
        super.onHit(raytraceResultIn);
    }
}

