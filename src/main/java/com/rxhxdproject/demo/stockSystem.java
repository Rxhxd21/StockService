package com.rxhxdproject.demo;


import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import java.util.*;

@Component
public class stockSystem {
    public static HashMap<String, ItemStockWeight> items = new HashMap<>(); //main hashmap
    public static ArrayList<String> choices = new ArrayList<>();
    public static Map<String, PickedData> picked = new HashMap<>(); //returning hashmap
    public static GetUTCTimeStamp lastUPD = new GetUTCTimeStamp(0);
    public static IDincrement ID = new IDincrement();
    public static boolean isFirstRun = true;


    static {
        InitializeItems();
        returnStockTable();
    } //????


    public static void InitializeItems() { //stock map
        items.put("Bread Machine", new ItemStockWeight(100, 15));
        items.put("Fridge", new ItemStockWeight(40, 12));
        items.put("Apple Tree", new ItemStockWeight(30, 11));
        items.put("Chicken Barn", new ItemStockWeight(25, 10));
        items.put("Cheese Stand", new ItemStockWeight(12, 7));
        items.put("Vending Machine", new ItemStockWeight(10, 6));
        items.put("Candy Machine", new ItemStockWeight(8, 5));
        items.put("Banana Tree", new ItemStockWeight(5, 3));
        items.put("Chicken Oven", new ItemStockWeight(3, 5));
        items.put("Pizza Oven",new ItemStockWeight(2, 3));
        items.put("Bee Hive", new ItemStockWeight(1, 1));
        items.put("Ice Cream Machine", new ItemStockWeight(1, 1));
    }


    @Scheduled(fixedRate = 300000)
    public static void generateNewStock(){ //stock generating logic

        if (isFirstRun) {
            isFirstRun = false;
            System.out.println("Skipping first scheduled run to avoid double increment");
            return;
        }
        choices.clear();
        picked.clear();

        for (Map.Entry<String, ItemStockWeight> entry : items.entrySet()) {
            for (int i = 0; i < entry.getValue().weight; i ++) {
                choices.add(entry.getKey());
            }
        }



        Collections.shuffle(choices);

        Set<String> used = new HashSet<>();

        int pickedCount = 0;


        for (String spawnerName : choices) {
            if (pickedCount >= 4) break;

            if (!used.contains(spawnerName)) {
                ItemStockWeight spawnerData = items.get(spawnerName);
                if (spawnerData != null) {
                    int amount;

                    if (spawnerName.trim().equalsIgnoreCase("Bread Machine")) {
                        amount = 6 + (int) (Math.random() * (spawnerData.stock - 6 + 1));
                    }
                    else {
                        amount = 1 + (int)(Math.random() * spawnerData.stock);
                    }

                    picked.put(spawnerName, new PickedData(amount));
                    used.add(spawnerName);
                    pickedCount += 1;

                }
            }
        }
        lastUPD = new GetUTCTimeStamp(0);
        ID.idINC();
    }

    public static GetUTCTimeStamp UTCtime() {
        return lastUPD;
    }

    //main table returning hashmaps:

    public static Map<String, Object> getCurrentStock() {
        Map<String, Object> stocktable = new HashMap<>();
        stocktable.put("New stock:",picked);
        return Collections.unmodifiableMap(stocktable);
    }

    public static Map<String, GetUTCTimeStamp> getUTCtime() {
        Map<String, GetUTCTimeStamp> UTCmap = new HashMap<>();
        UTCmap.put("Lastupdate", UTCtime());
        return UTCmap;
    }

    public static Map<String, Object> returnStockTable() {
            return Collections.unmodifiableMap(items);
    }
}
