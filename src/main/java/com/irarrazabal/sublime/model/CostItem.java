package com.irarrazabal.sublime.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="cost_items")
@Data
public class CostItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;
}
