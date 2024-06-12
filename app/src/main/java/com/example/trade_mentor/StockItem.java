package com.example.trade_mentor;

public class StockItem {
    private String companyName;
    private String stockNumber;
    private String stockPrice;
    private String date,total_amount,chp;

    public StockItem(String companyName,String stockPrice, String chp,String stockNumber,String total_amount, String date) {
        this.companyName = companyName;
        this.stockNumber = stockNumber;
        this.stockPrice = stockPrice;
        this.date = date;
        this.total_amount=total_amount;
        this.chp=chp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public String getStockPrice() {
        return stockPrice;
    }
    public String getchp() {
        return chp;
    }
    public String gettotalamount() {
        return total_amount;
    }

    public String getDate() {
        return date;
    }
}
