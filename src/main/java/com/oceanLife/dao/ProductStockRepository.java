package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.model.bean.ProductStockModel;

public interface ProductStockRepository extends JpaRepository<ProductStockModel, Integer> {

}
