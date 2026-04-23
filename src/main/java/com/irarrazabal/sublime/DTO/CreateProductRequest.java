package com.irarrazabal.sublime.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {

    private String name;
    private double margin;
    private List<CostItemRequest> cost;

}
