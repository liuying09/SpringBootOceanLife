package com.oceanLife.model.req;

import org.springframework.web.multipart.MultipartFile;

import com.oceanLife.model.bean.ProductModel;
import com.oceanLife.model.bean.ProductStockModel;

public class ProductCreateDTO {

	private ProductModel productModel;
	private MultipartFile fileField;
	private ProductStockModel productStockModel;
	
	public ProductModel getProductModel() {
		return productModel;
	}
	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}
	public MultipartFile getFileField() {
		return fileField;
	}
	public void setFileField(MultipartFile fileField) {
		this.fileField = fileField;
	}
	public ProductStockModel getProductStockModel() {
		return productStockModel;
	}
	public void setProductStockModel(ProductStockModel productStockModel) {
		this.productStockModel = productStockModel;
	}
	
	
	
}
