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

public class EntityGrub extends EntityMob {


    public EntityGrub(World world) {
        super(world);
        this.setSize(0.7f,0.3f);
        this.experienceValue = 20;

    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAIWanderAvoidWater(this,0.45d));
        this.tasks.addTask(1, new EntityAISwimming(this));

    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return OresparkUtil.mobLootTable(this);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05d);
    }

    @Override
    public float getEyeHeight() {
        return 0.25f;
    }

}
