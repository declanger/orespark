package com.orespark.entity;

import com.orespark.Orespark;
import com.orespark.util.OresparkUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntitySolanum extends EntityMob implements IEntityMultiPart {

    public MultiPartEntityPart[] entityParts;
//    public MultiPartEntityPart stem1 = new MultiPartEntityPart(this,"stem1",14f/16f,21f/16f);
//    public MultiPartEntityPart stem2 = new MultiPartEntityPart(this,"stem2",12f/16f,12f/16f);
//    public MultiPartEntityPart stem3 = new MultiPartEntityPart(this,"stem3",10f/16f,17f/16f);
//    public MultiPartEntityPart stem4 = new MultiPartEntityPart(this,"stem4",8f/16f,21f/16f);
//    public MultiPartEntityPart stem5 = new MultiPartEntityPart(this,"stem5",6f/16f,20f/16f);
//    public MultiPartEntityPart neck = new MultiPartEntityPart(this,"neck",18f/16f,6f/16f);
//    public MultiPartEntityPart head = new MultiPartEntityPart(this,"head",12f/16f,24f/16f);
//    public MultiPartOblongPart north = new MultiPartOblongPart(this,"north",40f/16f,2f/16f, 18f/16f);
//    public MultiPartOblongPart south = new MultiPartOblongPart(this,"south",40f/16f,2f/16f, 18f/16f);
//    public MultiPartOblongPart east = new MultiPartOblongPart(this,"east",18f/16f,2f/16f, 40f/16f);
//    public MultiPartOblongPart west = new MultiPartOblongPart(this,"west",18f/16f,2f/16f, 40f/16f);

    public MultiPartFreePart test = new MultiPartFreePart(this,"test",1f,1f,1f,0,0,0);

    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntitySolanum.class, DataSerializers.VARINT);

    public static final int IDLE = 0;
    public static final int OPEN = 1;
    public static final int CLOSED = 2;
    public static final int SPIKED = 3;
    public static final int BLASTING = 4;
    public static final int CHARGING = 5;
    public static final int SHOOTING = 6;

    private final BossInfoServer bossInfoServer = new BossInfoServer(this.getDisplayName(), BossInfo.Color.GREEN,BossInfo.Overlay.NOTCHED_6);

    public EntitySolanum(World world) {
        super(world);
//        entityParts = new MultiPartEntityPart[]{stem1,stem2,stem3,stem4,stem5,neck,head,north,south,east,west};
        entityParts = new MultiPartEntityPart[]{test};
        this.setSize(4f,7f);
        this.ignoreFrustumCheck = true;
        this.experienceValue = 50;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return Orespark.PLANT;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(STATE,6);
    }

    @Override
    protected void initEntityAI() {

    }

    @Override
    public void onUpdate() {
        motionX = 0;
        motionY = 0;
        motionZ = 0;
        super.onUpdate();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        test.setPosition(posX,posY,posZ);
        test.setRotation(0,0,Math.PI / 4d);

//        switch (getState()) {
//            case IDLE:
//                stem1.setLocationAndAngles(this.posX,this.posY - 0.625f,this.posZ,0,0);
//                stem2.setLocationAndAngles(this.posX,this.posY + 0.6875f,this.posZ,0,0);
//                stem3.setLocationAndAngles(this.posX,this.posY + 1.4375f,this.posZ,0,0);
//                stem4.setLocationAndAngles(this.posX,this.posY + 2.5f,this.posZ,0,0);
//                stem5.setLocationAndAngles(this.posX,this.posY + 3.8125f,this.posZ,0,0);
//                neck.setLocationAndAngles(this.posX,this.posY + 5.0625f,this.posZ,0,0);
//                head.setLocationAndAngles(this.posX,this.posY + 5.4375f,this.posZ,0,0);
//
//                north.setLocationAndAngles(this.posX + 1.8125f,this.posY + 5.3125f,this.posZ,0,0);
//                south.setLocationAndAngles(this.posX - 1.8125f,this.posY + 5.3125f,this.posZ,0,0);
//                east.setLocationAndAngles(this.posX,this.posY + 5.3125f,this.posZ + 1.8125f,0,0);
//                west.setLocationAndAngles(this.posX,this.posY + 5.3125f,this.posZ - 1.8125f,0,0);
//                break;
//            case OPEN:
//                stem1.setLocationAndAngles(this.posX,this.posY - 0.625f,this.posZ,0,0);
//                stem2.setLocationAndAngles(this.posX,this.posY + 0.6875f,this.posZ,0,0);
//                stem3.setLocationAndAngles(this.posX,this.posY + 1.4375f,this.posZ,0,0);
//                stem4.setLocationAndAngles(this.posX,this.posY + 2.5f,this.posZ,0,0);
//                stem5.setLocationAndAngles(this.posX,this.posY + 3.8125f,this.posZ,0,0);
//                neck.setLocationAndAngles(this.posX,this.posY + 4.5625f,this.posZ,0,0);
//                head.setLocationAndAngles(this.posX,this.posY + 5.4375f,this.posZ,0,0);
//
//
//                north.setLocationAndAngles(this.posX + 1.8125f,this.posY + 4f,this.posZ,0,0);
//                south.setLocationAndAngles(this.posX - 1.8125f,this.posY + 4f,this.posZ,0,0);
//                east.setLocationAndAngles(this.posX,this.posY + 4f,this.posZ + 1.8125f,0,0);
//                west.setLocationAndAngles(this.posX,this.posY + 4f,this.posZ - 1.8125f,0,0);
//                break;
//            default:
//                Orespark.LOGGER.error("Solanum in unexpected state");
//                break;
//        }


        for (MultiPartEntityPart part : entityParts) {
            part.onUpdate();
        }

    }

    @Override
    protected void updateAITasks() {
        bossInfoServer.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        bossInfoServer.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        bossInfoServer.removePlayer(player);
    }

    @Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        bossInfoServer.setName(getDisplayName());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (hasCustomName()) {
            bossInfoServer.setName(getDisplayName());
        }
    }

    //    @Nullable
//    @Override
//    protected ResourceLocation getLootTable() {
//        return OresparkUtil.mobLootTable(this);
//    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0d);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5d);
        //this.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1d);
    }

    @Override
    public float getEyeHeight() {
        return 5.4f;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public void applyEntityCollision(Entity entity) {

    }


    @Override
    protected void collideWithEntity(Entity entityIn) {

    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public boolean attackEntityFromPart(MultiPartEntityPart part, DamageSource source, float damage) {

        if (!part.partName.equals("head")) {
            return false;
        }
        attackSolanum(source,damage);
        return true;
    }

    public void attackSolanum(DamageSource source, float damage) {
        super.attackEntityFrom(source,damage);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isExplosion() || source.isMagicDamage() || source.isFireDamage()) {
            return false;
        }
        Entity attacker = source.getImmediateSource();
        if (source.isProjectile() && (attacker.posY < posY + 5.4f - attacker.height / 2f || attacker.getDistanceSq(posX,attacker.posY,posZ) > 4)) {
            return false;
        }
        return super.attackEntityFrom(source, amount);
    }

    @Nullable
    @Override
    public Entity[] getParts() {
        return entityParts;
    }

    public int getState() {
        return dataManager.get(STATE);
    }

    public void setState(int state) {
        dataManager.set(STATE,state);
    }
}
