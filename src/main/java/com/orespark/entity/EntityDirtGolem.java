package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.util.OresparkUtil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityDirtGolem extends EntityMob {

    public EntityDirtGolem(World world) {
        super(world);
        this.setSize(1.1f,1.5f);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0,new EntityAIAttackMelee(this,1d,false));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this,0.6d));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class,6.0f));
        this.tasks.addTask(4,new EntityAILookIdle(this));
        this.targetTasks.addTask(0,new EntityAIHurtByTarget(this,false,new Class[0]));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, false));
    }

    @Override
    public float getEyeHeight() {
        return 1.25f;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return OresparkUtil.mobLootTable(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3d);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0d);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float damage) {
        if (source.getImmediateSource() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) source.getImmediateSource();
            if (entity.getHeldItemMainhand().getItem() instanceof ItemSpade) {
                return super.attackEntityFrom(source,damage);
            }
        }
        if (!this.world.isRemote) {
            this.playSound(SoundEvents.ITEM_SHIELD_BLOCK,1.0f,0.8F + this.rand.nextFloat() * 0.4F);
        }
        return false;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        if (source.getImmediateSource() instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) source.getImmediateSource();
            if (entity.getHeldItemMainhand().getItem() instanceof ItemSpade) {
                return SoundEvents.BLOCK_GRASS_BREAK;
            }
        }
        return SoundEvents.ITEM_SHIELD_BLOCK;
    }

}
