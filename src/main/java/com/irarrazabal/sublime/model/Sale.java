package com.irarrazabal.sublime.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="sales")
@Data

public class Sale {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private double total;
    private LocalDateTime date;

    @OneToMany( mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items;
}
