package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_product")
public class ProductModel {

	@Id
	@Column(name="productId")
	 private String productId;
	
	@Column(name="productName")
	 private String productName;
	
	@Column(name="productPrice")
	 private String productPrice;
	
	@Column(name="productPriceSale")
	 private String productPriceSale;
	
	@Column(name="productImgId")
	 private String productImgId;
	
	@Column(name="productStockId")
	 private int productStockId;
	
	@Column(name="productContent")
	 private String productContent;
	
	@Column(name="productSpenMaterial")
	 private String productSpenMaterial; // 材質
	
	@Column(name="productSpenSize")
	 private String productSpenSize; // 尺寸
	
	@Column(name="productSpenMF")
	 private String productSpenMF; // 產地
	
	@Column(name="productRemark")
	 private String productRemark;
	
	@Column(name="productStatus")
	 private String productStatus;
	
	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductPriceSale() {
		return productPriceSale;
	}

	public void setProductPriceSale(String productPriceSale) {
		this.productPriceSale = productPriceSale;
	}

	public String getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(String productImgId) {
		this.productImgId = productImgId;
	}

	public int getProductStockId() {
		return productStockId;
	}

	public void setProductStockId(int productStockId) {
		this.productStockId = productStockId;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductSpenMaterial() {
		return productSpenMaterial;
	}

	public void setProductSpenMaterial(String productSpenMaterial) {
		this.productSpenMaterial = productSpenMaterial;
	}

	public String getProductSpenSize() {
		return productSpenSize;
	}

	public void setProductSpenSize(String productSpenSize) {
		this.productSpenSize = productSpenSize;
	}

	public String getProductSpenMF() {
		return productSpenMF;
	}

	public void setProductSpenMF(String productSpenMF) {
		this.productSpenMF = productSpenMF;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	
	

}
