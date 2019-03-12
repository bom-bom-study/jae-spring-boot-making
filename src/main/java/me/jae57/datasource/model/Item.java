package me.jae57.datasource.model;

public class Item {
    private int id;
    private String product_name;
    private String company;
    private String category;

    public Item() {

    }

    public Item(int id,String product_name, String company,String category){
        this.id=id;
        this.product_name=product_name;
        this.company = company;
        this.category=category;
    }


    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
