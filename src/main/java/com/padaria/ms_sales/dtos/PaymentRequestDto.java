package com.padaria.ms_sales.dtos;

import com.padaria.ms_sales.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PaymentRequestDto {
    private Double totalValue;
    private PaymentMethod paymentMethod;
}
