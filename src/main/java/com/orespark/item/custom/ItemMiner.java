package com.orespark.item.custom;

import com.orespark.Orespark;
import com.orespark.item.ItemBase;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class ItemMiner extends ItemBase {

    private int width; // from center to edge

    private int height; // from edge to edge

    public ItemMiner(String name, int width, int height) {
        super(name);
        super.setCreativeTab(CreativeTabs.TOOLS);
        this.width = width;
        this.height = height;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float px, float py, float pz) {
        if (facing == EnumFacing.DOWN) {
            if (!world.isRemote) {
                world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS,1.0f,0.2f);
            }
            return EnumActionResult.FAIL;
        }
        Orespark.LOGGER.info((MathHelper.floor((player.rotationYaw + 225f) / 90f) + 2) % 4);
        EnumFacing playerFacing = EnumFacing.HORIZONTALS[(MathHelper.floor((player.rotationYaw + 225f) / 90f) + 6) % 4];

        if (!world.isRemote) {
            world.playSound((EntityPlayer) null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
        Vec3i dVec = facing.getOpposite().getDirectionVec();
        Vec3i wVec = facing == EnumFacing.UP ? playerFacing.rotateY().getDirectionVec() : facing.rotateY().getDirectionVec();
        Vec3i hVec = facing == EnumFacing.UP ? playerFacing.getOpposite().getDirectionVec() : new Vec3i(0,1,0);

        Vec3i offset = new Vec3i(pos.getX() - wVec.getX() * width - hVec.getX() * (MathHelper.floor(height / 2.0f)+1), playerFacing == EnumFacing.UP ? pos.getY() : MathHelper.floor(player.posY)-1,pos.getZ() - wVec.getZ() * width - hVec.getZ() * (MathHelper.floor(height / 2.0f)+1));

        BlockPos pos1 = new BlockPos(offset);
        BlockPos pos2 = new BlockPos(offset);
        BlockPos pos3 = new BlockPos(offset);


        for (int z = 0; z < 32; z++) {
            for (int y = 0; y < height; y++) {
                pos2 = pos2.add(hVec);
                pos3 = new BlockPos(pos2.getX(),pos2.getY(),pos2.getZ());
                for (int x = 0; x <= width * 2; x++) {
                    if (facing == EnumFacing.UP) {
                        if (x == width && y == height-1) {
                            world.setBlockState(pos3,Blocks.LADDER.getDefaultState().withProperty(BlockLadder.FACING,playerFacing));
                        }
                        else if ((x==0 || x== width * 2) && y == MathHelper.floor(height/2.0f) && (z+2) % 4 == 0) {
                            world.setBlockState(pos3,Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING,x==0 ? playerFacing.rotateY() : playerFacing.rotateYCCW()));
                        }
                        else if (z==0) {
                            world.setBlockState(pos3,Blocks.TRAPDOOR.getDefaultState().withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.TOP));
                        }
                        else if (world.getBlockState(pos3).getBlock() == Blocks.STONE)  {
                            world.setBlockToAir(pos3);
                        }
                    }
                    else if (x == width && y == 0 && z % 4 == 0) {
                        world.setBlockState(pos3,Blocks.TORCH.getDefaultState(),0);
                    }
                    else if (world.getBlockState(pos3).getBlock() == Blocks.STONE) {
                        world.setBlockToAir(pos3);
                    }
                    pos3 = pos3.add(wVec);
                }
            }
            pos1 = pos1.add(dVec);
            pos2 = new BlockPos(pos1.getX(),pos1.getY(),pos1.getZ());
        }
        player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
        return EnumActionResult.SUCCESS;
    }
}
