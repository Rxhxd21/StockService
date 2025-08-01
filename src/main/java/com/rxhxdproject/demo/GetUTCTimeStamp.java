package com.rxhxdproject.demo;


import java.time.Instant;

public class GetUTCTimeStamp {
    private long inst;
    public GetUTCTimeStamp(int addedAmount) {
        this.inst = (System.currentTimeMillis() / 1000) + addedAmount;
    }

    public long GetUTCTimeStamp2() {
        return inst + 300;
    }

    public long getTS() {
        return inst;
    }

    public void setTS(long ES) {
        this.inst = ES;
    }
}
