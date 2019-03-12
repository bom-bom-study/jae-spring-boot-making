package me.jae57.datasource.dto;

public class ItemDto {
    private int id;
    private String product_name;
    private String company;
    private String category;

    public ItemDto() {

    }

    public ItemDto(int id, String product_name, String company, String category){
        this.id = id;
        this.product_name = product_name;
        this.company = company;
        this.category = category;
    }
    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getCompany() {
        return company;
    }

    public String getCategory() {
        return category;
    }
}
