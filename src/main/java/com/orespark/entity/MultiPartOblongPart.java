package com.orespark.entity;

import com.orespark.Orespark;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;

public class MultiPartOblongPart extends MultiPartEntityPart {

    private float depth;

    public MultiPartOblongPart(IEntityMultiPart parent, String partName, float x, float y, float z) {
        super(parent, partName, x, y);
        depth = z;
        setSize(x,y,z);
    }

//    @Override
//    protected void setSize(float x, float y) {
//        setSize(width,height,depth);
//    }


    @Override
    public void onUpdate() {
        world.spawnParticle(EnumParticleTypes.WATER_BUBBLE,posX,posY,posZ,0,0,0);
        super.onUpdate();
    }

    private void setSize(float width, float height, float depth)
    {
        if (width != this.width || depth != this.depth || height != this.height)
        {
            float f = this.width;
            float f0 = this.depth;
            this.width = width;
            this.depth = depth;
            this.height = height;

            if (this.width < f || this.depth < f0)
            {
                double d0 = (double)width / 2.0D;
                double d1 = (double)depth / 2.0D;
                this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d1, this.posX + d0, this.posY + (double)this.height, this.posZ + d1));
                return;
            }

            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
            this.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double)this.width, axisalignedbb.minY + (double)this.height, axisalignedbb.minZ + (double)this.depth));

            if ((this.width > f || this.depth > f0) && !this.firstUpdate && !this.world.isRemote)
            {
                this.move(MoverType.SELF, (double)(f - this.width), 0.0D, (double)(f0 - this.depth));
            }
        }
    }

    @Override
    public void setPosition(double x, double y, double z)
    {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        if (this.isAddedToWorld() && !this.world.isRemote) this.world.updateEntityWithOptionalForce(this, false); // Forge - Process chunk registration after moving.
        float f = this.width / 2.0F;
        float f0 = this.depth / 2.0f;
        float f1 = this.height;
        this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f0, x + (double)f, y + (double)f1, z + (double)f0));
    }
}
