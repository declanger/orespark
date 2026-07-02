package com.orespark.block;

import com.orespark.Orespark;
import com.orespark.block.entity.BlockFletchrootCrop;
import com.orespark.util.CustomModel;
import com.orespark.util.IconItemBlock;
import com.orespark.util.NoItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;

public class ModBlocks {

    public static BlockOre RUBY_ORE = new BlockOre("ruby_ore").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockBase RUBY_BLOCK = new BlockBase(Material.ROCK, "ruby_block").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    public static BlockCrucible CRUCIBLE = new BlockCrucible(Material.IRON, "crucible");

    @CustomModel
    public static BlockBeeHive BEEHIVE = new BlockBeeHive("beehive");

    @CustomModel
    @NoItemBlock
    public static BlockRustBaneCrop RUSTBANE = new BlockRustBaneCrop();

    @CustomModel
    @NoItemBlock
    public static BlockFletchrootCrop FLETCHROOT = new BlockFletchrootCrop();

    public static void register(IForgeRegistry<Block> registry) {
        try {
            for (Field field : ModBlocks.class.getFields()) {
                registry.register((Block)field.get(null));
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        try {
            for (Field field : ModBlocks.class.getFields()) {
                if (field.getAnnotation(NoItemBlock.class) == null) {
                    Block block = (Block) field.get(null);
                    registry.register(new ItemBlock(block).setRegistryName(field.getName().toLowerCase()));
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerModels() {
        try {
            for (Field field : ModBlocks.class.getFields()) {
                if (field.getAnnotation(IconItemBlock.class) != null) {
                    Orespark.proxy.registerItemRenderer(Item.getItemFromBlock((Block) field.get(null)),0, field.getName().toLowerCase());
                }
                else if (field.getAnnotation(NoItemBlock.class) == null) {
                    Orespark.proxy.registerItemRenderer(Item.getItemFromBlock((Block) field.get(null)), 0, field.getName().toLowerCase());
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
