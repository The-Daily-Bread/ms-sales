package com.padaria.ms_sales.dtos;

import lombok.Data;

@Data
public class MqttInvoiceItemDto {
    private int id;
    private String description;
    private int quantity;
    private double rate;
}
