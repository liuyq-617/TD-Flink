package com.taosdata.model;

public class Sensor {

    public long ts;
    public int val;
    public float current;
    public String location;

    public Sensor() {

    }

    public Sensor(long ts, int val, float current, String location) {
        this.ts = ts;
        this.val = val;
        this.current = current;
        this.location = location;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "ts=" + ts +
                ", val=" + val +
                ", current=" + current +
                ", location='" + location + '\'' +
                '}';
    }
}