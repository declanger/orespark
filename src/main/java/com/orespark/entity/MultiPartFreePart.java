package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.util.NotAlignedBB;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class MultiPartFreePart extends MultiPartEntityPart {

    private float depth;
    private double offsetX, offsetY, offsetZ, rotX, rotY, rotZ;

    public MultiPartFreePart(IEntityMultiPart parent, String partName, float w, float h, float d, double ox, double oy, double oz) {
        super(parent, partName, w, h);
        depth = d;
        setSize(w,h,d);
        offsetX = ox;
        offsetY = oy;
        offsetZ = oz;
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        double[][] corners = ((NotAlignedBB)this.getEntityBoundingBox()).getCorners();
        for (int i = 0; i < 8; i++) {
            world.spawnParticle(EnumParticleTypes.WATER_BUBBLE,corners[i][0],corners[i][1],corners[i][2],0,0,0);
        }
    }

    private void setSize(float width, float height, float depth)
    {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.setEntityBoundingBox(new NotAlignedBB(width,height,depth,offsetX,offsetY,offsetZ,rotX,rotY,rotZ,new Vec3d(posX,posY,posZ)));
    }

    @Override
    public void setPosition(double x, double y, double z)
    {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        if (this.isAddedToWorld() && !this.world.isRemote) this.world.updateEntityWithOptionalForce(this, false); // Forge - Process chunk registration after moving.

        this.setEntityBoundingBox(new NotAlignedBB(width,height,depth,offsetX,offsetY,offsetZ,rotX,rotY,rotZ,new Vec3d(posX,posY,posZ)));
    }

    public void setRotation(double x, double y, double z)
    {
        this.rotX = x;
        this.rotY = y;
        this.rotZ = z;
        this.setEntityBoundingBox(new NotAlignedBB(width,height,depth,offsetX,offsetY,offsetZ,rotX,rotY,rotZ,new Vec3d(posX,posY,posZ)));
    }

    public void setOffset(double x, double y, double z)
    {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        this.setEntityBoundingBox(new NotAlignedBB(width,height,depth,offsetX,offsetY,offsetZ,rotX,rotY,rotZ,new Vec3d(posX,posY,posZ)));
    }
}
