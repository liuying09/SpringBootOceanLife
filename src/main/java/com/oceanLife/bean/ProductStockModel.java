package com.oceanLife.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_productStock")
public class ProductStockModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="productStockId")
	 private int productStockId;
	
	@Column(name="productId")
	 private String productId;
	
	@Column(name="productType")
	 private String productType;
	
	@Column(name="productNum")
	 private String productNum;

	@Column(name="create_date")
	 private String createDate;
	
	@Column(name="update_date")
	 private String updateDate;
	
	public int getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(int productStockId) {
		this.productStockId = productStockId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
