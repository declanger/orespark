package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.block.BlockBeeHive;
import com.orespark.util.OresparkUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class EntityBee extends EntityLiving {

    private static final DataParameter<Float> HEADING = EntityDataManager.createKey(EntityBee.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> POLLEN = EntityDataManager.createKey(EntityBee.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> HAS_TARGET = EntityDataManager.createKey(EntityBee.class, DataSerializers.BOOLEAN);
    private static final DataParameter<BlockPos> TARGET = EntityDataManager.createKey(EntityBee.class, DataSerializers.BLOCK_POS);
    private Path path;
    private boolean target = false;
    private double lift = 0;
    private BlockBeeHive home;

    public EntityBee(World world) {
        super(world);
        this.setSize(0.1875f,0.25f);
        this.moveHelper = new EntityFlyHelper(this);
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
        this.dataManager.register(POLLEN, false);
        this.dataManager.register(HAS_TARGET, false);
        this.dataManager.register(TARGET, new BlockPos(0,0,0));
    }

    @Override
    protected PathNavigate createNavigator(World worldIn) {
        PathNavigateFlying nav = new PathNavigateFlying(this,worldIn);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        nav.setCanEnterDoors(true);
        return nav;
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

    public boolean getPollen() {return  this.dataManager.get(POLLEN);}

    public void setPollen(boolean pollen) {this.dataManager.set(POLLEN,pollen);}

    public boolean getHasTarget() {return this.dataManager.get(HAS_TARGET);}

    public void setHasTarget(boolean hasTarget) {this.dataManager.set(HAS_TARGET,hasTarget);}

    public BlockPos getTarget() {return this.dataManager.get(TARGET);}

    public void setTarget(BlockPos target) {
        this.dataManager.set(TARGET,target);
        path = navigator.getPathToPos(target);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new BeeAIWander(this));
        tasks.addTask(1, new BeeAIPollenate(this));
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05d);

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.5d);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (path != null && path.isFinished()) {
            path = null;
            target = false;
        }
        else if (path == null && !target && getHasTarget()) {
            path = navigator.getPathToPos(getTarget());
            Orespark.LOGGER.info(path.getCurrentPathLength());
            target = true;
            Orespark.LOGGER.info("finding path");
        }

        setNoGravity(false);

        float h = getHeading();

        double speed = getHasTarget() ? 0.4 : 1;

        double headingX = MathHelper.sin(h / 180f * -3.14159f) * 0.5 * speed;
        double headingZ = MathHelper.cos(h / 180f * -3.14159f) * 0.5 * speed;


        motionX = motionX * 0.95 + headingX * 0.05;
        motionZ = motionZ * 0.95 + headingZ * 0.05;

        boolean g = true;

        RayTraceResult hit = world.rayTraceBlocks(new Vec3d(posX, posY, posZ), new Vec3d(posX, posY - 1.3, posZ), true, true, false);
        if (hit != null && hit.typeOfHit == RayTraceResult.Type.BLOCK) {
            double dist = (posY - hit.hitVec.y) / 1.3;
            lift += dist * 0.006 + 0.0025;
            if (dist < 0.2) {
                motionY += 0.15;
            }
            //lift += motionY < 0 ? 0.005f + rand.nextFloat() * 0.005f : 0.006f;
        } else {
            lift *= 0.7f;
        }
        if (g) {
            motionY += lift - 0.003f;
        }

        motionY *= 0.5;

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
            setMutexBits(3);
        }

        @Override
        public void updateTask() {
            if (entityBee.getHasTarget()) {
                Vec3d diff = entityBee.path.getCurrentPos().subtract(entityBee.posX - 0.5,entityBee.posY - 0.5,entityBee.posZ - 0.5);
                if (diff.lengthSquared() < 0.7) {
                    Orespark.LOGGER.info("next point");
                    entityBee.path.incrementPathIndex();
                    if (entityBee.path.isFinished()) {
                        Orespark.LOGGER.info("end point");
                        entityBee.setHasTarget(false);
                        entityBee.setPollen(true);
                        return;
                    }
                    diff = entityBee.path.getCurrentPos().subtract(entityBee.posX,entityBee.posY,entityBee.posZ);
                }
                float heading = (float) (MathHelper.atan2(diff.z,diff.x) * (180 / Math.PI)) - 90f;
                //Orespark.LOGGER.info("heading: " + heading);
                Orespark.LOGGER.info("to: " + entityBee.path.getCurrentPos().add(0.5,0.5,0.5));
                //heading += (entityBee.rand.nextFloat() - 0.5f) * 30;
                entityBee.setHeading(MathHelper.wrapDegrees(heading));
                cooldown = 4;
            }
            else {
                int neg = entityBee.rand.nextFloat() < 0.5 ? -1 : 1;
                entityBee.addHeading((entityBee.rand.nextFloat() * 130f + 30) * neg);
                cooldown = 3 + entityBee.rand.nextInt(2);
            }
        }

        @Override
        public boolean shouldContinueExecuting() {
            return false;
        }

        @Override
        public boolean shouldExecute() {
            return cooldown-- <= 0 && entityBee.rand.nextFloat() < 0.85f;
        }
    }

    static class BeeAIPollenate extends EntityAIBase {

        private final EntityBee entityBee;
        private boolean foundFlower;

        public BeeAIPollenate(EntityBee entityBee) {
            this.entityBee = entityBee;
            setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            //Orespark.LOGGER.info("attempt at flowers");
            return !entityBee.getHasTarget() && !entityBee.getPollen();
        }

        @Override
        public void startExecuting() {
            //Orespark.LOGGER.info("looking for flower");
        }

        @Override
        public boolean shouldContinueExecuting() {
            if (foundFlower) {
                foundFlower = false;
                return false;
            }
            return true;
        }

        @Override
        public void updateTask() {
            foundFlower = findFlower();
        }

        private boolean findFlower() {
            RayTraceResult hit = entityBee.world.rayTraceBlocks(new Vec3d(entityBee.posX,entityBee.posY,entityBee.posZ),new Vec3d(entityBee.posX,entityBee.posY - 5f,entityBee.posZ),true);
            if (hit == null || hit.typeOfHit == RayTraceResult.Type.MISS) {
                return false;
            }
            BlockPos startingPos = hit.getBlockPos().up(10).west(5).south(5);
            for (int x = 0; x < 10; x++) {
                BlockPos mPos = startingPos.east(x);
                for (int z = 0; z < 10; z++) {
                    BlockPos nPos = mPos.north(z);
                    for (int y = 0; y < 15; y++) {
                        BlockPos check = nPos.down(y);
                        IBlockState bs = entityBee.world.getBlockState(check);
                        Block b = bs.getBlock();
                        if (b.causesSuffocation(bs)) {
                            break;
                        } else if (b instanceof BlockFlower) {
                            entityBee.setTarget(check.up());
                            entityBee.setHasTarget(true);
                            Orespark.LOGGER.info(check);
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    }
}
