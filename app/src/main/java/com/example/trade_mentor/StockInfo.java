package com.example.trade_mentor;

public class StockInfo {
    private String name;
    private String code;
    private double lp;
    private double ch;
    private double chp;

    public StockInfo(String name, String code, double lp, double ch, double chp) {
        this.name = name;
        this.code = code;
        this.lp = lp;
        this.ch = ch;
        this.chp = chp;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getLp() {
        return lp;
    }

    public double getCh() {
        return ch;
    }

    public double getChp() {
        return chp;
    }
}
