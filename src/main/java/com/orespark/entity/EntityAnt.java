package com.orespark.entity;

import com.orespark.util.OresparkUtil;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityAnt extends EntityCreature {

    protected static final DataParameter<Float> SIZE = EntityDataManager.createKey(EntityAnt.class, DataSerializers.FLOAT);

    public EntityAnt(World world) {
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
        this.tasks.addTask(0, new EntityAIWanderAvoidWater(this,1d,0f));
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class,5.0f));

    }

    public float getSize() {
        return dataManager.get(SIZE);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(SIZE,rand.nextFloat() / 2f + 0.75f);
        super.entityInit();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.rotationYaw = rotationYaw * 0.94f + rotationYawHead * 0.06f;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        try
        {
            compound.setFloat("Size", dataManager.get(SIZE));
            return compound;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Saving entity NBT");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity being saved");
            this.addEntityCrashInfo(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        dataManager.set(SIZE,compound.getFloat("Size"));
    }

    //    @Nullable
//    @Override
//    protected ResourceLocation getLootTable() {
//        return OresparkUtil.mobLootTable(this);
//    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25d);
    }

    @Override
    public float getEyeHeight() {
        return 0.25f;
    }

}
