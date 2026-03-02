package com.orespark;

import com.orespark.block.ModBlocks;
import com.orespark.entity.ModEntities;
import com.orespark.entity.render.EntityRenderers;
import com.orespark.item.ModItems;
import com.orespark.proxy.CommonProxy;
import com.orespark.util.OresparkUtil;
import com.orespark.world.ModWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
	modid = Orespark.MODID,
	name = Orespark.NAME,
	version = Orespark.VERSION
)
public class Orespark {
	public static final String MODID = "orespark";
	public static final String NAME = "Orespark 1.12.2";
	public static final String VERSION = "1.0";
	
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	@Mod.Instance(MODID)
	public static Orespark instance;

	@SidedProxy(serverSide = "com.orespark.proxy.CommonProxy", clientSide = "com.orespark.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.EventBusSubscriber
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModItems.register(event.getRegistry());
			ModBlocks.registerItemBlocks(event.getRegistry());
		}
		@SubscribeEvent
		public static void registerItems(ModelRegistryEvent event) {
			ModItems.registerModels();
			ModBlocks.registerModels();
		}

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}
	}

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent preinit) {
		LOGGER.info("Hello, world!");
		GameRegistry.registerWorldGenerator(new ModWorldGeneration(),3);

		ModEntities.registerEntities();
		EntityRenderers.registerRenderers();
		OresparkUtil.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	public static final Item.ToolMaterial gemToolMaterial = EnumHelper.addToolMaterial("GEM",4,2670,10.0f,8f, 25);
	public static final ItemArmor.ArmorMaterial gemArmorMaterial = EnumHelper.addArmorMaterial("GEM", MODID + ":ruby", 41, new int[]{4,7,9,4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5f);
	public static final ItemArmor.ArmorMaterial mantisArmorMaterial = EnumHelper.addArmorMaterial("MANTIS", MODID + ":mantis", 41, new int[]{4,7,9,4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.5f);

}
