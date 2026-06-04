package com.orespark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orespark.block.ModBlocks;
import com.orespark.item.ModItems;
import com.orespark.util.CustomModel;
import com.orespark.util.ToolModel;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.Item;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataGenerators{

    public static void main(String[] args) throws NoSuchMethodException {
        generate("Directories");
        generate("LootTables");
        generate("ItemModels");
        generate("BlockStates");
        white();
    }

    public static void generateLootTables() throws IOException {
        mobLootTable("dirtgolem", pool(4,
                drop("minecraft:carrot",1,2),
                drop("minecraft:potato",1,2),
                drop("minecraft:melon_seeds",1,2),
                drop("orespark:rustbane_seed",2,3)));
        mobLootTable("mantis", pool(4, drop("orespark:mantis_flesh",6,7)));
        mobLootTable("stonegolem", pool(1, drop("orespark:miners_desire",1)),
                pool(2, drop("orespark:miners_dream",1,2)),
                pool(4, drop("orespark:miners_wish",1,2)));
    }


    public static void generateDirectories() throws IOException {
        List<String> directories = new ArrayList<>();
        directories.add("src/generated/resources/assets/orespark/loot_tables/entities");
        directories.add("src/generated/resources/assets/orespark/models/item");
        directories.add("src/generated/resources/assets/orespark/models/block");
        directories.add("src/generated/resources/assets/orespark/blockstates");
        directories.add("src/generated/resources/assets/orespark/particles");
        for (String directory : directories) {
            Files.createDirectories(Paths.get(directory));
        }
    }

    public static void mobLootTable(String mob, Pool... pools) throws IOException {
        JsonArray poolsObj = new JsonArray();
        for (int j = 0; j < pools.length; j++) {
            JsonObject pool = new JsonObject();
            pool.addProperty("name","pool" + j);
            pool.addProperty("rolls",pools[j].rolls);
            JsonArray entries = new JsonArray();
            Drop[] drops = pools[j].drops;
            for (int i = 0; i < drops.length; i++) {
                JsonObject drop = new JsonObject();
                drop.addProperty("type","item");
                drop.addProperty("name", drops[i].item);
                drop.addProperty("weight", 1);
                JsonArray functions = new JsonArray();
                // create set_count function
                JsonObject setCount = new JsonObject();
                setCount.addProperty("function","minecraft:set_count");
                JsonObject count = new JsonObject();
                count.addProperty("min",drops[i].minCount);
                count.addProperty("max",drops[i].maxCount);
                setCount.add("count",count);
                functions.add(setCount);
                // create looting_enchant function
                JsonObject looting = new JsonObject();
                looting.addProperty("function","minecraft:looting_enchant");
                JsonObject countL = new JsonObject();
                countL.addProperty("min",0);
                countL.addProperty("max",(int)Math.ceil(drops[i].maxCount/3f));
                looting.add("count",count);
                functions.add(looting);
                drop.add("functions",functions);
                entries.add(drop);
                pool.add("entries",entries);
            }
            poolsObj.add(pool);
        }



        JsonObject root = new JsonObject();
        root.add("pools", poolsObj);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/loot_tables/entities/" + mob + ".json");
        gson.toJson(root,writer);
        writer.close();
    }

    public static void generateItemModels() throws IOException {
        for (Field field : ModItems.class.getFields()) {
            if (field.getAnnotation(ToolModel.class) != null) {
                toolItem(field.getName().toLowerCase());
            }
            else if (field.getAnnotation(CustomModel.class) == null) {
                simpleItem(field.getName().toLowerCase());
            }
        }
    }

    public static void generateBlockStates() throws IOException {
        for (Field field : ModBlocks.class.getFields()) {
            if (field.getAnnotation(CustomModel.class) == null) {
                simpleBlock(field.getName().toLowerCase());
            }
        }
    }

    public static void simpleItem(String item) throws IOException {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "item/generated");

        JsonObject texture = new JsonObject();
        texture.addProperty("layer0", Orespark.MODID + ":items/" + item);

        model.add("textures",texture);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/models/item/" + item + ".json");
        gson.toJson(model,writer);
        writer.close();
    }

    public static void toolItem(String item) throws IOException {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "item/handheld");

        JsonObject texture = new JsonObject();
        texture.addProperty("layer0", Orespark.MODID + ":items/" + item);

        model.add("textures",texture);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/models/item/" + item + ".json");
        gson.toJson(model,writer);
        writer.close();
    }

    public static void simpleBlock(String block) throws IOException {
        JsonObject model = new JsonObject();
        model.addProperty("forge_marker",1);

        JsonObject textures = new JsonObject();
        textures.addProperty("all",Orespark.MODID + ":blocks/" + block);

        JsonObject defaults = new JsonObject();
        defaults.add("textures",textures);

        model.add("defaults",defaults);

        JsonObject normal = new JsonObject();
        normal.addProperty("model","cube_all");

        JsonObject inventory = new JsonObject();
        inventory.addProperty("model","cube_all");

        JsonObject variants = new JsonObject();
        variants.add("normal",normal);
        variants.add("inventory",inventory);

        model.add("variants",variants);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/blockstates/" + block + ".json");
        gson.toJson(model,writer);
        writer.close();
    }

    private static Drop drop(String item, int count) { return new Drop(item,count,count); }

    private static Drop drop(String item, int minCount, int maxCount) { return new Drop(item,minCount,maxCount); }

    private static Pool pool(int rolls,Drop... drops) { return new Pool(rolls, drops); }

    private static class Pool {


        public int rolls = 1;
        public Drop[] drops;

        public Pool (int rolls,Drop... drops) {
            this.rolls = rolls;
            this.drops = drops;
        }
    }

    private static class Drop {

        public String item;

        public int minCount;
        public int maxCount;

        public Drop (String item, int minCount, int maxCount) {
            this.item = item;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }

    }

    private static void yellow() { System.out.print("\u001B[93m"); System.out.flush(); }

    private static void green() { System.out.print("\r\u001B[92m\u001B[K"); System.out.flush();}

    private static void red() { System.out.print("\r\u001B[91m\u001B[K"); System.out.flush();}

    private static void white() { System.out.print("\r\u001B[97m"); System.out.flush();}

    private static void generate(String target) throws NoSuchMethodException {
        Method genRun = DataGenerators.class.getMethod("generate" + target);
        yellow();
        System.out.print("running: " + genRun.getName());
        System.out.flush();
        try {
            genRun.invoke(null);
            green();
            System.out.println("ran: " + genRun.getName());
            System.out.flush();
        }
        catch (Exception e) {
            red();
            System.out.println("failed: " + genRun.getName());
            e.printStackTrace();
            System.out.flush();
        }
    }
}
