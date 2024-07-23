package com.oceanLife.model.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_bill")
public class BillModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="billId")
	private int billId;
	
	@Column(name="billType")
	private String billType;
	
	@Column(name="billVehicle")
	private String billVehicle;
	
	@Column(name="billBarcode")
	private String billBarcode;

	@Column(name="create_date")
	 private String createDate;
	
	@Column(name="update_date")
	 private String updateDate;
	
	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillVehicle() {
		return billVehicle;
	}

	public void setBillVehicle(String billVehicle) {
		this.billVehicle = billVehicle;
	}

	public String getBillBarcode() {
		return billBarcode;
	}

	public void setBillBarcode(String billBarcode) {
		this.billBarcode = billBarcode;
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
