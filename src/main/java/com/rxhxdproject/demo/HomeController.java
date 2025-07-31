package com.rxhxdproject.demo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class HomeController { //class used to send requests to the webroot
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/stockShow")
    public Map<String, PickedData> generateStockItems() {
        stockSystem.generateNewStock();
        return stockSystem.getCurrentStock();
    }

    @GetMapping("/stocktable")
    public String getStockTable() {
        return stockSystem.returnStockTable();
    }
 }