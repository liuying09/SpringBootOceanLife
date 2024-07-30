package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_order")
public class OrderModel {

	
	@Id
	@Column(name="orderId")
	 private String orderId;
	
	@Column(name="userId")
	 private int userId;
	
	@Column(name="productId")
	 private String productId;
	
	@Column(name="productName")
	 private String productName;
	
	@Column(name="orderPayStatus")
	private int orderPayStatus;
	
	@Column(name="orderStatus")
	private String orderStatus;
	
	@Column(name="orderDelivered")
	private int orderDelivered;
	
	@Column(name="orderShip")
	private int orderShip;
	
	@Column(name="receiveID")
	private int receiveID;
	
	@Column(name="payID")
	private int payID;
	
	@Column(name="billID")
	private int billID;
	
	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public int getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(int orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderDelivered() {
		return orderDelivered;
	}

	public void setOrderDelivered(int orderDelivered) {
		this.orderDelivered = orderDelivered;
	}

	public int getOrderShip() {
		return orderShip;
	}

	public void setOrderShip(int orderShip) {
		this.orderShip = orderShip;
	}

	public int getReceiveID() {
		return receiveID;
	}

	public void setReceiveID(int receiveID) {
		this.receiveID = receiveID;
	}

	public int getPayID() {
		return payID;
	}

	public void setPayID(int payID) {
		this.payID = payID;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
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
