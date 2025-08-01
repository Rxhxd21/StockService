package com.rxhxdproject.demo;


import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class stockSystem {
    public static HashMap<String, ItemStockWeight> items = new HashMap<>();
    public static ArrayList<String> choices = new ArrayList<>();
    public static Map<String, PickedData> picked = new HashMap<>();



    static { //????
        InitializeItems();
        returnStockTable();
    }


    public static void InitializeItems() {
        items.put("Bread Machine ", new ItemStockWeight(100, 15));
        items.put("Fridge ", new ItemStockWeight(40, 12));
        items.put("Apple Tree ", new ItemStockWeight(30, 11));
        items.put("Chicken Barn ", new ItemStockWeight(25, 10));
        items.put("Cheese Stand", new ItemStockWeight(12, 7));
        items.put("Vending Machine", new ItemStockWeight(10, 6));
        items.put("Candy Machine", new ItemStockWeight(8, 5));
        items.put("Banana Tree", new ItemStockWeight(5, 3));
        items.put("Chicken Oven", new ItemStockWeight(3, 5));
        items.put("Pizza Oven",new ItemStockWeight(2, 3));
        items.put("Bee Hive", new ItemStockWeight(1, 1));
    }


    @Scheduled(fixedRate = 10000)
    public static void generateNewStock(){
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
    }


    //main table returning hashmaps:

    public static Map<String, Object> getCurrentStock() {
        Map<String, Object> stocktable = new HashMap<>();
        stocktable.put("New stock:",picked);
        return Collections.unmodifiableMap(stocktable);
    }

    public static Map<String, GetUTCTimeStamp> getUTCtime() {
        Map<String, GetUTCTimeStamp> UTCmap = new HashMap<>();
        UTCmap.put("Last Updated", new GetUTCTimeStamp(0));
        UTCmap.put("Next update", new GetUTCTimeStamp(300));
        return UTCmap;
    }

    public static Map<String, ItemStockWeight> returnStockTable() {
            return Collections.unmodifiableMap(items);
    }
}
