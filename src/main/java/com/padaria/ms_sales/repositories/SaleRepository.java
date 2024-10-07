package com.padaria.ms_sales.repositories;

import com.padaria.ms_sales.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<SaleModel, UUID> {

}
