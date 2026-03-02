package com.orespark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.orespark.block.ModBlocks;
import com.orespark.item.ModItems;
import com.orespark.util.CustomModel;
import com.orespark.util.ToolModel;
import net.minecraft.item.Item;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class DataGenerators{

    private static Drop drop(String item, int count) {
        return new Drop(item,count,count);
    }

    private static Drop drop(String item, int minCount, int maxCount) {
        return new Drop(item,minCount,maxCount);
    }

    public static void main(String[] args) {
        System.out.println("Generating Item Models");
        generateItemModels();
        generateBlockStates();
        generateLootTables();
    }

    public static void generateLootTables() {
        mobLootTable("dirtgolem", new Pool(4,
                drop("minecraft:carrot",1,2),
                drop("minecraft:potato",1,2),
                drop("minecraft:melon_seeds",1,2),
                drop("orespark:rustbane_seed",2,3)));
        mobLootTable("mantis", new Pool(4, drop("orespark:mantis_flesh",6,7)));
        mobLootTable("stonegolem", new Pool(1, drop("orespark:miners_desire",1)),
                new Pool(2, drop("orespark:miners_dream",1,2)),
                new Pool(4, drop("orespark:miners_wish",1,2)));
    }

    public static void mobLootTable(String mob, Pool... pools) {
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
        try (FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/loot_tables/entities/" + mob + ".json")) {
            gson.toJson(root,writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateItemModels() {
        for (Field field : ModItems.class.getFields()) {
            if (field.getAnnotation(ToolModel.class) != null) {
                toolItem(field.getName().toLowerCase());
            }
            else if (field.getAnnotation(CustomModel.class) == null) {
                simpleItem(field.getName().toLowerCase());
            }
        }
    }

    public static void generateBlockStates() {
        for (Field field : ModBlocks.class.getFields()) {
            if (field.getAnnotation(CustomModel.class) == null) {
                simpleBlock(field.getName().toLowerCase());
            }
        }
    }

    public static void simpleItem(String item) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "item/generated");

        JsonObject texture = new JsonObject();
        texture.addProperty("layer0", Orespark.MODID + ":items/" + item);

        model.add("textures",texture);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/models/item/" + item + ".json")) {
            gson.toJson(model,writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toolItem(String item) {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "item/handheld");

        JsonObject texture = new JsonObject();
        texture.addProperty("layer0", Orespark.MODID + ":items/" + item);

        model.add("textures",texture);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/models/item/" + item + ".json")) {
            gson.toJson(model,writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void simpleBlock(String block) {
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
        try (FileWriter writer = new FileWriter("src/generated/resources/assets/orespark/blockstates/" + block + ".json")) {
            gson.toJson(model,writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

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

}
