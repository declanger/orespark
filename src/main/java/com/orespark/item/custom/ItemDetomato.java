package com.orespark.item.custom;

import com.orespark.Orespark;
import com.orespark.entity.EntityDetomato;
import com.orespark.item.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemDetomato extends ItemBase {

    public ItemDetomato(String name) {
        super(name);
        setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (!worldIn.isRemote) {
            EntityDetomato detomato = new EntityDetomato(worldIn,entityLiving);
            worldIn.spawnEntity(detomato);
            Vec3d look = entityLiving.getLookVec();
            detomato.shoot(look.x,look.y,look.z,Math.min(getMaxItemUseDuration(stack) - timeLeft, 40) / 20.0f + 0.5f,1f);
            if (entityLiving instanceof EntityPlayer && !((EntityPlayer) entityLiving).isCreative()) stack.shrink(1);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
}
