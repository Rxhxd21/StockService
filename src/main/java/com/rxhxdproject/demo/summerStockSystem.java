package com.rxhxdproject.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class summerStockSystem {
    public static Map<String, ItemStockWeight2> summeritems = new HashMap<>();
    public static ArrayList<String> choises = new ArrayList<>();
    public static Map<String, PickedData2> picked = new HashMap<>();
    public static GetUTCTS2 currentTime;
    public static IDincrement ID2 = new IDincrement();


    public static void InitializeSummerItems() {
        summeritems.put("Cooler Box", new ItemStockWeight2(100, 5));
        summeritems.put("Watermelon Plant", new ItemStockWeight2(50, 2));
        summeritems.put("Ice Cream Truck", new ItemStockWeight2(10, 1));
    }

    @Scheduled(cron = "0 0 * * * *")
    public static void generateSummerStock() {
        choises.clear();
        picked.clear();

        for (Map.Entry<String, ItemStockWeight2> summerentry : summeritems.entrySet()) {
            for (int i = 0; i < summerentry.getValue().weight; i += 1) {
                choises.add(summerentry.getKey());
            }
        }

        Collections.shuffle(choises);

        Set<String> used = new HashSet<>();

        int pickedcount = 0;

        for (String spawnername: choises) {
            if (pickedcount >= 1) break;

            if (!used.contains(spawnername)) {
                ItemStockWeight2 spawnerData = summeritems.get(spawnername);
                if (spawnerData != null) {
                    int amount;

                    amount = 1 + (int) (Math.random() * spawnerData.stock);

                    picked.put(spawnername, new PickedData2(amount));
                    used.add(spawnername);
                    pickedcount ++;
                }
            }
        }
        currentTime = new GetUTCTS2(0);
        ID2.idINC();
    }

    public static GetUTCTS2 getUPDtimestamp() {
        return currentTime;
    }


    //main table returning hashmap methods:
    public static Map<String, Object> getSummerStock() {
        Map<String, Object> summerstocktable = new HashMap<>();
        summerstocktable.put("SummerStock", picked);
        return Collections.unmodifiableMap(summerstocktable);
    }

    public static Map<String, Object> UPDTSmap() {
        Map<String, GetUTCTS2> SummerUTCMap = new HashMap<>();
        SummerUTCMap.put("NextEventStockUPD", getUPDtimestamp());
        return Collections.unmodifiableMap(SummerUTCMap);
    }
}
