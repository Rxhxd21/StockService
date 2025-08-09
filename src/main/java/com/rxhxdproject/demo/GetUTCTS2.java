package com.rxhxdproject.demo;
import java.time.Instant;


public class GetUTCTS2 {
    private long inst;
    private long nextUPD;

    public GetUTCTS2(int Addedamount){
        this.inst = (System.currentTimeMillis() / 1000);
        nextUPD = (long) Math.ceil(inst / 3600) * 3600;
    }

    public long getTS(){return nextUPD;}
    public void setTS(long ES) {this.nextUPD = ES;}
}
