package com.orespark.item;

import com.orespark.Orespark;
import com.orespark.item.custom.ItemFertilizer;
import com.orespark.item.custom.ItemMiner;
import com.orespark.item.custom.ItemRustBaneSeed;
import com.orespark.item.custom.ItemStaff;
import com.orespark.item.tool.*;
import com.orespark.util.ToolModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.registries.IForgeRegistry;

import java.lang.reflect.Field;

public class ModItems {

    public static ItemFoodBase BLT = new ItemFoodBase("blt",8,4.0f).setCreativeTab(CreativeTabs.FOOD);

    public static ItemMiner MINERS_DESIRE = new ItemMiner("miners_desire",5,7);
    public static ItemMiner MINERS_DREAM = new ItemMiner("miners_dream",3,5);
    public static ItemMiner MINERS_WISH = new ItemMiner("miners_wish",1,3);

    public static ItemBase RUBY = new ItemBase("ruby").setCreativeTab(CreativeTabs.MATERIALS);

    public static ItemFellerAxe FELLER_AXE = new ItemFellerAxe("feller_axe").setCreativeTab(CreativeTabs.TOOLS);
    public static ItemFertilizer FERTILIZER = new ItemFertilizer("fertilizer").setCreativeTab(CreativeTabs.MISC);

    @ToolModel
    public static ItemSwordBase RUBY_SWORD = new ItemSwordBase(Orespark.gemToolMaterial,"ruby_sword").setCreativeTab(CreativeTabs.COMBAT);
    @ToolModel
    public static ItemPickaxeBase RUBY_PICKAXE = new ItemPickaxeBase(Orespark.gemToolMaterial,"ruby_pickaxe").setCreativeTab(CreativeTabs.TOOLS);
    @ToolModel
    public static ItemAxeBase RUBY_AXE = new ItemAxeBase(Orespark.gemToolMaterial,"ruby_axe").setCreativeTab(CreativeTabs.TOOLS);
    @ToolModel
    public static ItemShovelBase RUBY_SHOVEL = new ItemShovelBase(Orespark.gemToolMaterial,"ruby_shovel").setCreativeTab(CreativeTabs.TOOLS);
    @ToolModel
    public static ItemHoeBase RUBY_HOE = new ItemHoeBase(Orespark.gemToolMaterial,"ruby_hoe").setCreativeTab(CreativeTabs.TOOLS);

    public static ItemArmorBase RUBY_HELMET = new ItemArmorBase(Orespark.gemArmorMaterial, EntityEquipmentSlot.HEAD ,"ruby_helmet");
    public static ItemArmorBase RUBY_CHESTPLATE = new ItemArmorBase(Orespark.gemArmorMaterial, EntityEquipmentSlot.CHEST ,"ruby_chestplate");
    public static ItemArmorBase RUBY_LEGGINGS = new ItemArmorBase(Orespark.gemArmorMaterial, EntityEquipmentSlot.LEGS ,"ruby_leggings");
    public static ItemArmorBase RUBY_BOOTS = new ItemArmorBase(Orespark.gemArmorMaterial, EntityEquipmentSlot.FEET ,"ruby_boots");

    public static ItemArmorBase MANTIS_HELMET = new ItemArmorBase(Orespark.mantisArmorMaterial, EntityEquipmentSlot.HEAD, "mantis_helmet");
    public static ItemArmorBase MANTIS_CHESTPLATE = new ItemArmorBase(Orespark.mantisArmorMaterial, EntityEquipmentSlot.CHEST, "mantis_chestplate");
    public static ItemArmorBase MANTIS_LEGGINGS = new ItemArmorBase(Orespark.mantisArmorMaterial, EntityEquipmentSlot.LEGS, "mantis_leggings");
    public static ItemArmorBase MANTIS_BOOTS = new ItemArmorBase(Orespark.mantisArmorMaterial, EntityEquipmentSlot.FEET, "mantis_boots");

    public static ItemBase MANTIS_FLESH = new ItemBase("mantis_flesh").setCreativeTab(CreativeTabs.MATERIALS);

    @ToolModel
    public static ItemStaff THUNDER_STAFF = new ItemStaff("thunder_staff").setCreativeTab(CreativeTabs.COMBAT);

    public static ItemRustBaneSeed RUSTBANE_SEED = new ItemRustBaneSeed();
    public static ItemBase RUSTBANE = new ItemBase("rustbane").setCreativeTab(CreativeTabs.MATERIALS);

    public static void register(IForgeRegistry<Item> registry) {

        try {
            for (Field field : ModItems.class.getFields()) {
                Orespark.LOGGER.info(field.getName());
                registry.register((Item)field.get(null));
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void registerModels() {
        try {
            for (Field field : ModItems.class.getFields()) {
                Orespark.proxy.registerItemRenderer((Item) field.get(null), 0, field.getName().toLowerCase());
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
