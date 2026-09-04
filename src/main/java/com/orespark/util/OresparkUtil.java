package com.orespark.util;

import com.google.common.reflect.ClassPath;
import com.orespark.Orespark;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class OresparkUtil {

    public static Dictionary<String, ArrayList<Item>> meltables = new Hashtable<>();
    public static final int GUI_CRUCIBLE = 1;

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

    public static void print(Object message, World world) {
        if (world != null) {
            Orespark.LOGGER.info(message.toString());
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

    public static void addMeltable(String s, Item i) {
        ArrayList<Item> l = meltables.get(s);
        if (l != null) {
            l.add(i);
        }
        else {
            l = new ArrayList<Item>();
            l.add(i);
            meltables.put(s,l);
        }
    }

    public static float clippedSin(float x) {
        return Math.max(MathHelper.sin(x),0);
    }

    public static float clippedCos(float x) {
        return Math.max(MathHelper.cos(x),0);
    }

    public static final float PI = (float) Math.PI;
}
