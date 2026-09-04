package com.orespark.util;

import com.orespark.Orespark;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3d;

public class NotAlignedBB extends AxisAlignedBB {

    private static final int[][] cornerMults = new int[][]{{1,1,1},{-1,1,1},{-1,1,-1},{1,1,-1},{1,-1,1},{-1,-1,1},{-1,-1,-1},{1,-1,-1}};

    public final double rotX, rotY, rotZ;

    public final double height, depth, width;

    public final double offsetX, offsetY, offsetZ;

    public final Vec3d pos;
    
    public final Matrix3d matrix;

    public NotAlignedBB(double width, double height, double depth, double offsetX, double offsetY, double offsetZ, double rotX, double rotY, double rotZ, Vec3d pos) {
        super(pos.x + width/2, pos.y + height/2, pos.z + depth/2,pos.x - width/2, pos.y - height/2, pos.z - depth/2);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.pos = pos;

        double sin = Math.sin(rotX);
        double cos = Math.cos(rotX);
        Matrix3d x = new Matrix3d(1,0,0
                ,0,cos,-sin
                ,0,sin,cos);
        sin = Math.sin(rotY);
        cos = Math.cos(rotY);
        Matrix3d y = new Matrix3d(cos,0,sin
                ,0,1,0
                ,-sin,0,cos);
        sin = Math.sin(rotZ);
        cos = Math.cos(rotZ);
        Matrix3d z = new Matrix3d(cos,-sin,0
                ,sin,cos,0
                ,0,0,1);

        x.mul(y);
        x.mul(z);

        this.matrix = x;
    }

    @Override
    public AxisAlignedBB contract(double x, double y, double z)
    {
        return new NotAlignedBB(width - x, height - y, depth - z, offsetX, offsetY, offsetZ, rotX, rotY ,rotZ, pos);
    }

    public AxisAlignedBB expand(double x, double y, double z)
    {
        return new NotAlignedBB(width + x, height + y, depth + z, offsetX, offsetY, offsetZ, rotX, rotY, rotZ, pos);
    }

    public AxisAlignedBB grow(double x, double y, double z)
    {
        return expand(x,y,z);
    }

    public AxisAlignedBB grow(double value)
    {
        return this.grow(value, value, value);
    }

    public AxisAlignedBB offset(double x, double y, double z)
    {
        return new NotAlignedBB(width,height,depth,offsetX,offsetY,offsetZ,rotX,rotY,rotZ,pos.add(x,y,z));
    }

    public AxisAlignedBB offset(BlockPos pos)
    {
        return this.offset(pos.getX(),pos.getY(),pos.getZ());
    }

    public AxisAlignedBB offset(Vec3d vec)
    {
        return this.offset(vec.x, vec.y, vec.z);
    }

    public boolean contains(Vec3d vec) {
        Vec3d offset = vec.subtract(pos);

        // tranpose the matrix by mirroring it along its diagonal so m10 -> m01 (just swap the row and column) then multiply basically bring the bb into world space
        // also remove the offset to make calcs easier
        offset = new Vec3d(offset.x * matrix.m00 + offset.y * matrix.m10 + offset.z * matrix.m20 + pos.x - offsetX,
                offset.x * matrix.m01 + offset.y * matrix.m11 + offset.z * matrix.m21 + pos.y - offsetY,
                offset.x * matrix.m02 + offset.y * matrix.m12 + offset.z * matrix.m22 + pos.z - offsetZ);

        // offset removed so each number is now the distance from the centre along it's axis
        return Math.abs(offset.x) < width / 2d && Math.abs (offset.y) < height / 2d && Math.abs(offset.z) < depth / 2;
    }

    public double[][] getCorners() {
        double[][] corners = new double[8][3];

        double w = width / 2f;
        double h = height / 2f;
        double d = depth / 2f;

        for (int i = 0; i < 8; i++) {
            double x = cornerMults[i][0] * w + offsetX;
            double y = cornerMults[i][1] * h + offsetY;
            double z = cornerMults[i][2] * d + offsetZ;

            corners[i][0] =  x * matrix.m00 + y * matrix.m01 + z * matrix.m02 + pos.x;
            corners[i][1] =  x * matrix.m10 + y * matrix.m11 + z * matrix.m12 + pos.y;
            corners[i][2] =  x * matrix.m20 + y * matrix.m21 + z * matrix.m22 + pos.z;
        }
        return corners;
    }

    public NotAlignedBB(double width, double height, double depth, double rotX, double rotY, double rotZ, Vec3d pos) {
        this(width,height,depth,0,0,0,rotX,rotY,rotZ,pos);
    }

    public NotAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, z1, x2, y2, z2);
        Orespark.LOGGER.error("NABB set doubles");
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetZ = 0;
        this.rotX = 0;
        this.rotY = 0;
        this.rotZ = 0;
        this.pos = Vec3d.ZERO;
        this.matrix = null;
    }

    public NotAlignedBB(BlockPos pos) {
        super(pos);
        Orespark.LOGGER.error("NABB set blockpos");
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetZ = 0;
        this.rotX = 0;
        this.rotY = 0;
        this.rotZ = 0;
        this.pos = Vec3d.ZERO;
        this.matrix = null;
    }

    public NotAlignedBB(BlockPos pos1, BlockPos pos2) {
        super(pos1, pos2);
        Orespark.LOGGER.error("NABB set 2 blockpos");
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetZ = 0;
        this.rotX = 0;
        this.rotY = 0;
        this.rotZ = 0;
        this.pos = Vec3d.ZERO;
        this.matrix = null;
    }

    public NotAlignedBB(Vec3d min, Vec3d max) {
        super(min, max);
        Orespark.LOGGER.error("NABB set vec3d");
        this.width = 0;
        this.height = 0;
        this.depth = 0;
        this.offsetX = 0;
        this.offsetY = 0;
        this.offsetZ = 0;
        this.rotX = 0;
        this.rotY = 0;
        this.rotZ = 0;
        this.pos = Vec3d.ZERO;
        this.matrix = null;
    }
}
