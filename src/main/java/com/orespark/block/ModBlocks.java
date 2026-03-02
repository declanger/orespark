package com.orespark.block;

import com.orespark.Orespark;
import com.orespark.util.CustomModel;
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

    @CustomModel
    @NoItemBlock
    public static BlockRustBaneCrop RUSTBANE = new BlockRustBaneCrop();

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
                Block block = (Block) field.get(null);
                registry.register(new ItemBlock(block).setRegistryName(field.getName().toLowerCase()));
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerModels() {
        try {
            for (Field field : ModBlocks.class.getFields()) {
                if (field.getAnnotation(NoItemBlock.class) == null) {
                    Orespark.proxy.registerItemRenderer(Item.getItemFromBlock((Block) field.get(null)), 0, field.getName().toLowerCase());
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
