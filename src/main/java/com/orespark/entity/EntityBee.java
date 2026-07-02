package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.block.BlockBeeHive;
import com.orespark.util.OresparkUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityBee extends EntityLiving {

    private static final DataParameter<Float> HEADING = EntityDataManager.createKey(EntityBee.class, DataSerializers.FLOAT);
    private float lift = 0;
    private BlockBeeHive home;

    public EntityBee(World world) {
        super(world);
        this.setSize(0.1875f,0.25f);
        this.experienceValue = 4;
    }

    public EntityBee (World world, BlockBeeHive hive) {
        this(world);
        home = hive;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HEADING, 0.0f);
    }

    public float getHeading() {
        return this.dataManager.get(HEADING);
    }

    public void setHeading(float value) {
        this.dataManager.set(HEADING, value);
    }

    public void addHeading(float heading) {
        this.setHeading(MathHelper.wrapDegrees(heading + this.getHeading()));
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new BeeAIWander(this));
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05d);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        float h = getHeading();

        double headingX = MathHelper.sin(h / 180f * -3.14159f) * 0.5;
        double headingZ = MathHelper.cos(h / 180f * -3.14159f) * 0.5;


        motionX = motionX * 0.95 + headingX * 0.05;
        motionZ = motionZ * 0.95 + headingZ * 0.05;

        boolean g = true;

        RayTraceResult hit = world.rayTraceBlocks(new Vec3d(posX,posY,posZ),new Vec3d(posX,posY - 1.2,posZ), true, true, false);
        if (hit != null && hit.typeOfHit == RayTraceResult.Type.BLOCK) {
            if (posY - hit.hitVec.y < 0.8f) {
                motionY = MathHelper.clampedLerp(motionY,0.25f,0.5f);
                //lift = 0.12f;
                g = false;
            }
            else {
                lift += motionY < 0 ? 0.07f + rand.nextFloat() * 0.01f : 0.04f;
            }
        }
        else {
            lift *= 0.9f;
        }
        if (g) {
            motionY = lift - 0.15f;
        }

        motionY *= 0.98;

        rotationYaw = (float) (MathHelper.atan2(motionZ, motionX) * (180D / Math.PI)) - 90.0F;
        rotationYaw = MathHelper.wrapDegrees(rotationYaw);
        prevRotationYaw = rotationYaw;
    }

    @Override
    public float getEyeHeight() {
        return 0.0625f;
    }

    @Override
    public void fall(float distance, float damageMultiplier) {

    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {

    }

    static class BeeAIWander extends EntityAIBase {

        private int cooldown = 0;
        private final EntityBee entityBee;

        public BeeAIWander(EntityBee entityBeeIn) {
            entityBee = entityBeeIn;
            setMutexBits(0);
        }

        @Override
        public void updateTask() {
            int neg = entityBee.rand.nextFloat() < 0.5 ? -1 : 1;
            entityBee.addHeading((entityBee.rand.nextFloat() * 130f + 30) * neg);
            cooldown = 3 + entityBee.rand.nextInt(2);
        }

        @Override
        public boolean shouldExecute() {
            return cooldown-- <= 0;
        }
    }
}
