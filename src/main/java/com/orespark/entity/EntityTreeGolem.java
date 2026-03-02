package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.util.OresparkUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTreeGolem extends EntityMob {

    private static final DataParameter<Boolean> STILL = EntityDataManager.createKey(EntityTreeGolem.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> PREV_STILL = EntityDataManager.createKey(EntityTreeGolem.class, DataSerializers.BOOLEAN);
    public int stillCooldown = 200;

    public float stillFrame = 0;
    public float stillProgess = 0;
    public float lastTick = 0;

    private void setStill(boolean s) {
        if (isStill() != s) { dataManager.set(PREV_STILL,isStill()); stillCooldown = s ? 0 : 240;}
        else { stillCooldown = s ? 0 : Math.max(200,stillCooldown);}
        dataManager.set(STILL,Boolean.valueOf(s));
    }

    public boolean isStill() {
        return this.dataManager.get(STILL);
    }

    public boolean prevStill() {
        return this.dataManager.get(PREV_STILL);
    }

    public EntityTreeGolem(World world) {
        super(world);
        this.setSize(1f,6f);
    }

    @Override
    public float getEyeHeight() {
        return 4.5f;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (dataManager.get(STILL)) {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            double x = Math.floor(this.posX) + 0.5f;
            double y = Math.floor(this.posY);
            double z = Math.floor(this.posZ) + 0.5f;
            x = OresparkUtil.moveTowards(this.posX,x,0.05);
            y = OresparkUtil.moveTowards(this.posY,y,0.05);
            z = OresparkUtil.moveTowards(this.posZ,z,0.05);
            if (!(this.posX == x && this.posY == y && this.posZ == z)) {
                this.setPositionAndRotation(x,y,z, MathHelper.floor(this.rotationYaw / 4f)*90f,0f);
            }
        }
    }

    @Override
    public void updateAITasks() {
        if (!world.isRemote && !this.isStill()) {
            EntityLivingBase target = this.getAttackTarget();
            if (stillCooldown > 0) {
                if (target == null) {
                    stillCooldown--;
                }
                else {
                    setStill(false);
                }
            }
            else {
                setStill(true);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getImmediateSource() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) source.getImmediateSource();
            if (entity.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                boolean a = super.attackEntityFrom(source,damage);
                if (a) { setStill(false); } // if (this.getAttackTarget() == null) {this.setAttackTarget((EntityLivingBase) source.getTrueSource());}}
                return a;
            }
        }
        if (!this.world.isRemote) {
            this.playSound(SoundEvents.ITEM_SHIELD_BLOCK,1.0f,0.8F + this.rand.nextFloat() * 0.4F);
        }
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataManager.register(STILL, Boolean.FALSE);
        this.dataManager.register(PREV_STILL, Boolean.FALSE);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAIAttackMelee(this,1f,false));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this,false,new Class[0]));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5d);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4d);
    }

    @Override
    public int getVerticalFaceSpeed() {
        return 180;
    }

    @Override
    public int getHorizontalFaceSpeed() {
        return 180;
    }

    @Override
    public float getCollisionBorderSize() {
        return 0.0f;
    }

    @Override
    public void applyEntityCollision(Entity entity) {

    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getEntityBoundingBox();
    }


}
