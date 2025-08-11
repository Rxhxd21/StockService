package com.rxhxdproject.demo;

import java.util.ArrayList;

public class IDincrement {
    private static int counter = -2;

    public int idINC() {
        counter++;
        return counter;
    }

    public int getLastID() {
        return counter >= 0 ? counter : 0;
    }
}