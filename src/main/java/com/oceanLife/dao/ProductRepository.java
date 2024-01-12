package com.oceanLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oceanLife.bean.ProductModel;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<ProductModel, String> {

	List<ProductModel> findAll();
	List<ProductModel> findByProductName(String productName);
	ProductModel findByProductId(String productId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM OCEAN_product WHERE PRODUCTID=:PRODUCTID", nativeQuery = true)
	public int deleteProduct(@Param("PRODUCTID") int PRODUCTID);
	
}