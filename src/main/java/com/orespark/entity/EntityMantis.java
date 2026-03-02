package com.orespark.entity;

import com.orespark.util.OresparkUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMantis extends EntityMob {


    public EntityMantis(World world) {
        super(world);
        this.setSize(1f,3f);
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

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return OresparkUtil.mobLootTable(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5d);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4d);
    }

    @Override
    public float getEyeHeight() {
        return 2.3f;
    }

}
