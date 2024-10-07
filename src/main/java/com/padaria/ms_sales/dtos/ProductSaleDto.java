package com.padaria.ms_sales.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductSaleDto {
    @NotBlank
    private ProductDto product;
    @NotBlank
    @Positive
    private Integer quantity;
}
