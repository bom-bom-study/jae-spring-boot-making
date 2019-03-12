package me.jae57.datasource.model;

public class Market {
    private int id;
    private String market_name;

    public Market(){

    }

    public Market(int id,String market_name){
        this.market_name = market_name;
    }

    public int getId() {
        return id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }


}
