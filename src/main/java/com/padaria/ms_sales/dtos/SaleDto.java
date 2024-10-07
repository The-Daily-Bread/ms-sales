package com.padaria.ms_sales.dtos;

import com.padaria.ms_sales.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class SaleDto {
    private ArrayList<ProductSaleDto> productSales;
    private String clientEmail;
    private PaymentMethod paymentMethod;
}
