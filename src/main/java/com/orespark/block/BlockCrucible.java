package com.orespark.block;

import com.orespark.Orespark;
import com.orespark.block.entity.BlockEntityCrucible;
import com.orespark.util.OresparkUtil;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCrucible extends BlockBase implements ITileEntityProvider {
    public BlockCrucible(Material material, String name) {
        super(material, name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new BlockEntityCrucible();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        if (!world.isRemote) {
            player.openGui(Orespark.instance, OresparkUtil.GUI_CRUCIBLE,world,pos.getX(),pos.getY(), pos.getZ());
        }
        return true;
    }
}
