package com.padaria.ms_sales.services;

import com.padaria.ms_sales.models.SaleModel;
import com.padaria.ms_sales.repositories.SaleRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleModel save(SaleModel saleModel) {
        return saleRepository.save(saleModel);
    }
}
