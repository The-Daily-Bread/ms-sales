package com.padaria.ms_sales.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products_sales")
public class ProductSaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleModel sale;

    private Integer quantity;
}
