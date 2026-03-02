package com.orespark.util;

import com.google.common.reflect.ClassPath;
import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.io.IOException;

public class OresparkUtil {

    public static void init() {
        // Loot Tables
        // Entity Loot Tables
        try {
            // Get the ClassPath for the current ClassLoader
            ClassPath cp = ClassPath.from(Thread.currentThread().getContextClassLoader());

            // Scan resources in your mod's loot_tables folder
            for (ClassPath.ResourceInfo info : cp.getResources()) {
                String path = info.getResourceName();

                if (path.startsWith("assets/orespark/loot_tables/") && path.endsWith(".json")) {
                    // Convert path to a clean ResourceLocation
                    String relativePath = path.substring("assets/orespark/loot_tables/".length(), path.length() - 5);

                    ResourceLocation loc = new ResourceLocation("orespark", relativePath);
                    LootTableList.register(loc);

                    //Orespark.LOGGER.info("Automatically registered loot table: " + loc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceLocation mobLootTable(Entity entity) {
        ResourceLocation location = new ResourceLocation("orespark","entities/" + entity.getClass().getName().substring(26));
        //Orespark.LOGGER.info("Assigned Loot Table " + location + " to " + entity.getClass().getName());
        return location;
    }

    public static double moveTowards(double from, double to, double step) {
        boolean negative = from > to;
        return negative ? Math.max(to,from - step) : Math.min(to,from + step);
    }
}
