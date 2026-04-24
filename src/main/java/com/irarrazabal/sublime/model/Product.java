package com.irarrazabal.sublime.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="products")
@Data

public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double margin;

    private double priceSuggested;

    private int stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CostItem> cost;

}
