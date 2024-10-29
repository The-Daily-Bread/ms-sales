package com.padaria.ms_sales.controllers;

import com.padaria.ms_sales.dtos.PaymentRequestDto;
import com.padaria.ms_sales.dtos.PaymentResponseDto;
import com.padaria.ms_sales.dtos.ProductSaleDto;
import com.padaria.ms_sales.dtos.SaleDto;
import com.padaria.ms_sales.models.ProductModel;
import com.padaria.ms_sales.models.ProductSaleModel;
import com.padaria.ms_sales.models.SaleModel;
import com.padaria.ms_sales.queue.MessagingRabbitmqApplication;
import com.padaria.ms_sales.queue.Receiver;
import com.padaria.ms_sales.queue.Runner;
import com.padaria.ms_sales.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class SaleController {
    final SaleService saleService;
    final RestTemplate restTemplate;

    @Value("${payment.service.url}")
    private String paymentServiceUrl;

    public SaleController(SaleService saleService, RestTemplate restTemplate) {
        this.saleService = saleService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/sale")
    public ResponseEntity<SaleModel> saveSale(@RequestBody @Valid SaleDto saleDto) {
        System.out.println(saleDto.toString());

        SaleModel saleModel = new SaleModel();
        BeanUtils.copyProperties(saleDto, saleModel);

        List<ProductSaleModel> productSaleModels = new ArrayList<>();
        double totalValue = 0;

        for (ProductSaleDto productSaleDto : saleDto.getProductSales()) {
            ProductSaleModel productSaleModel = new ProductSaleModel();

            BeanUtils.copyProperties(productSaleDto, productSaleModel);
            productSaleModel.setSale(saleModel);

            ProductModel productModel = new ProductModel();
            BeanUtils.copyProperties(productSaleDto.getProduct(), productModel);

            productSaleModel.setProduct(productModel);

            totalValue += productModel.getPrice() * productSaleDto.getQuantity();

            productSaleModels.add(productSaleModel);
        }

        saleModel.setProductSales(productSaleModels);
        saleModel.setTotalValue(totalValue);

        PaymentRequestDto paymentRequest = new PaymentRequestDto();
        paymentRequest.setTotalValue(saleModel.getTotalValue());
        paymentRequest.setPaymentMethod(saleModel.getPaymentMethod());

        try {
            ResponseEntity<PaymentResponseDto> paymentResponse = restTemplate.postForEntity(
                    paymentServiceUrl + "/payments",
                    paymentRequest,
                    PaymentResponseDto.class
            );

            System.out.println(paymentResponse.getBody());
            if (Objects.requireNonNull(paymentResponse.getBody()).getMessage().equals("Approved")) {
                Runner.sendMessage(" Sale created ");
                return ResponseEntity.status(HttpStatus.CREATED).body(saleService.save(saleModel));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e) {
            Runner.sendMessage(" error ");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
