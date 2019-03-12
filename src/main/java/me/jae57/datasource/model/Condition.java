package me.jae57.datasource.model;

public class Condition {
    private int id;
    private String product_name;
    private String market_name;
    private int price;
    private int amount;
    private long upd_date;

    public Condition(){

    }

    public Condition(int id,String product_name,String market_name, int price, long upd_date){
        this.product_name = product_name;
        this.market_name = market_name;
        this.price=price;
        this.upd_date = upd_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(long upd_date) {
        this.upd_date = upd_date;
    }
}
