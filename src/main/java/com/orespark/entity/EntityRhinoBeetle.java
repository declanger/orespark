package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.util.OresparkUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityRhinoBeetle extends EntityMob {

    private int attackCooldown;

    public EntityRhinoBeetle(World world) {
        super(world);
        this.setSize(2f,1.8f);
        this.experienceValue = 50;

    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0,new EntityAIAttackMelee(this,1.5d,false));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this,1.0d));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class,6.0f));
        this.tasks.addTask(4,new EntityAILookIdle(this));
        this.targetTasks.addTask(0,new EntityAIHurtByTarget(this,false,new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, false));

    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (attackCooldown >= 0) {return false;}
        boolean passed = super.attackEntityAsMob(entity);
        if (passed) {
            Vec3d lv = getLookVec();
            entity.setVelocity(lv.x * 0.7, 1.3, lv.z * 0.7);
            this.attackCooldown = 85;
            addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 80, 255,true,false));
        }
        return passed;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!world.isRemote) {
            attackCooldown--;
            Orespark.LOGGER.info(attackCooldown);
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return OresparkUtil.mobLootTable(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5d);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(4d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4d);
    }

    @Override
    public float getEyeHeight() {
        return 1.2f;
    }

}
