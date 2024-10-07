package com.padaria.ms_sales.models;

import com.padaria.ms_sales.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "sales")
public class SaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID salesId;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSaleModel> productSales;

    private String clientEmail;

    @Enumerated(EnumType.STRING) // Se for um enum
    private PaymentMethod paymentMethod;

    private Double totalValue;
}
