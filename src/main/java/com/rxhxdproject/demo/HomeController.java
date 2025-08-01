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
        stockSystem.generateNewStock();
        stockSystem.getUTCtime();
    }


    @GetMapping("/stockShow")
    public Map<String, Object> generateStockItems() {

        Map<String, Object> maintable = new HashMap<>();
        maintable.put("Stock", stockSystem.getCurrentStock());
        maintable.put("timestamps", stockSystem.getUTCtime());
        return maintable;
    }

//java ignore
    @GetMapping("/stocktable")
    public Map<String, Object> getStockTable() {
        return stockSystem.getCurrentStock();
    }

 }