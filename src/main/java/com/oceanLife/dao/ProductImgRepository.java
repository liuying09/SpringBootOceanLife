package com.oceanLife.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oceanLife.model.bean.ProductImgModel;

import jakarta.transaction.Transactional;

public interface ProductImgRepository extends JpaRepository<ProductImgModel,Integer> {

	
	List<ProductImgModel> findByProductId(String productId);
	
//heroku_895d9448560c022.
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO OCEAN_productimg(PRODUCTID,PRODUCTIMGNAME,PRODUCTIMGBLOB,CREATE_DATE,UPDATE_DATE) "
			+ "VALUES (:PRODUCTID,:PRODUCTIMGNAME, :PRODUCTIMGBLOB,:CREATE_DATE,:UPDATE_DATE);", nativeQuery = true)
	public void addProductImg(@Param("PRODUCTID") String PRODUCTID,@Param("PRODUCTIMGNAME") String PRODUCTIMGNAME, 
			@Param("PRODUCTIMGBLOB") byte[] PRODUCTIMGBLOB,
			@Param("CREATE_DATE") String CREATE_DATE,@Param("UPDATE_DATE") String UPDATE_DATE);
	
	
	
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM OCEAN_productimg WHERE PRODUCTID=:PRODUCTID", nativeQuery = true)
	public int deleteProductImg(@Param("PRODUCTID") int PRODUCTID);
}
