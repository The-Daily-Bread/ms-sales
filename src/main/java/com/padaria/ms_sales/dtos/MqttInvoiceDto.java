package com.padaria.ms_sales.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class MqttInvoiceDto {
    private int id;
    private String invoiceNumber;
    private Date invoiceDate;
    private Date dueDate;
    private String vendor;
    private String customer;
    private ArrayList<MqttInvoiceItemDto> items;
}
