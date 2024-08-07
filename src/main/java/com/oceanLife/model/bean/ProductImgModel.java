package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_productimg")
public class ProductImgModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="productImgId")
	 private int productImgId;
	
	@Column(name="productId")
	 private String productId;
	
	@Column(name="productImgName")
	 private String productImgName;
	
	@Column(name="productImgBlob")
	 private byte[] productImgBlob;
	
	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;

	public int getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(int productImgId) {
		this.productImgId = productImgId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public byte[] getProductImgBlob() {
		return productImgBlob;
	}

	public void setProductImgBlob(byte[] productImgBlob) {
		this.productImgBlob = productImgBlob;
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
