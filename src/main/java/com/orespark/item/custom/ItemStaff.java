package com.orespark.item.custom;

import com.orespark.Orespark;
import com.orespark.item.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ItemStaff extends ItemBase {
    public ItemStaff(String name) {
        super(name);
        setMaxStackSize(1);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.getCooldownTracker().setCooldown(this,20);
        player.swingArm(hand);
        Vec3d origin = player.getPositionVector().add(0,player.eyeHeight,0);
        RayTraceResult ray = world.rayTraceBlocks(origin,player.getLookVec().scale(100).add(origin));
        Vec3d hit = null;
        if (ray.typeOfHit == RayTraceResult.Type.MISS) {
            hit = player.getPositionVector().add(player.getLookVec().scale(100));
            Orespark.LOGGER.info("MISS");
        }
        else {
            hit = ray.hitVec;
            Orespark.LOGGER.info("HIT");
            if (ray.typeOfHit == RayTraceResult.Type.ENTITY) {
                ray.entityHit.attackEntityFrom(DamageSource.GENERIC,10);
            }
        }
        EntityLightningBolt bolt = new EntityLightningBolt(world,hit.x,hit.y,hit.z,false);
        origin = player.getPositionVector();
        int count = MathHelper.floor(player.getDistance(hit.x,hit.y,hit.z) * 5);
        float c = count;
        for (int i = 0; i < count; i++) {
            Vec3d pos = origin.scale((c - i) / c).add(hit.scale(i/c));
            world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,pos.x,pos.y,pos.z,0,0,0,10);
        }
        return new ActionResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.BOW;
    }

    @Override
    public ItemStaff setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }
}
