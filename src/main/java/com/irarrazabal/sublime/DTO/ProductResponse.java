package com.irarrazabal.sublime.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private String name;
    private double totalCost;
    private double suggestedPrice;
    private int stock;

    public ProductResponse() {}

    public ProductResponse(String name, double totalCost, double suggestedPrice, int stock) {
        this.name = name;
        this.totalCost = totalCost;
        this.suggestedPrice = suggestedPrice;
        this.stock = stock;
    }
}
