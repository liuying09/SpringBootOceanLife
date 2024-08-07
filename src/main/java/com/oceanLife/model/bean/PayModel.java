package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_pay")
public class PayModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="payId")
	 private int payId;
	
	@Column(name="payDeliveryMethod")
	private String payDeliveryMethod;
	
	@Column(name="payDeliveryAdress")
	private String payDeliveryAdress;
	
	@Column(name="payMethod")
	private String payMethod;

	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;
	
	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getPayDeliveryMethod() {
		return payDeliveryMethod;
	}

	public void setPayDeliveryMethod(String payDeliveryMethod) {
		this.payDeliveryMethod = payDeliveryMethod;
	}

	public String getPayDeliveryAdress() {
		return payDeliveryAdress;
	}

	public void setPayDeliveryAdress(String payDeliveryAdress) {
		this.payDeliveryAdress = payDeliveryAdress;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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
