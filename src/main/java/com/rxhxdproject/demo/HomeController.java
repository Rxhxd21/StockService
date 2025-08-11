package com.rxhxdproject.demo;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.openmbean.OpenMBeanConstructorInfo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class HomeController { //class used to send requests to the webroot
    @Value("${spring.application.name}")
    private String appName;



    @PostConstruct
    public void initStockAndUTC() {
        stockSystem.InitializeItems();
        stockSystem.getUTCtime();
        summerStockSystem.InitializeSummerItems();
        stockSystem.generateNewStock();
    }


    @GetMapping("/stockShow")
    public Map<String, Object> generateStockItems() {
        Map<String, Object> maintable = new HashMap<>();
        maintable.put("Stock", stockSystem.getCurrentStock());
        maintable.put("timestamps", stockSystem.UTCtime().getTS());
        maintable.put("StockID:", stockSystem.ID.getLastID());
        return maintable;
    }

    @GetMapping("/summerstock")
    public Map<String, Object> generateSS() {
        Map<String, Object> mainSummerTable = new HashMap<>();
        mainSummerTable.putAll(summerStockSystem.UPDTSmap());
        mainSummerTable.putAll(summerStockSystem.getSummerStock());
        mainSummerTable.put("SummerStockID:", summerStockSystem.ID2.getLastID());
        return mainSummerTable;
    }




//java ignore
    @GetMapping("/stocktable")
    public Map<String, Object> getStockTable() {
        return stockSystem.returnStockTable();
    }

 }