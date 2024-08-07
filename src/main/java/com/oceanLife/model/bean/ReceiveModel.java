package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_receive")
public class ReceiveModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="receiveId")
	 private int receiveId;
	
	@Column(name="receiveName")
	private String receiveName;
	
	@Column(name="receivePhone")
	 private int receivePhone;
	
	@Column(name="receiveMail")
	private String receiveMail;
	
	@Column(name="receiveRemark")
	private String receiveRemark;

	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;
	
	public int getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public int getReceivePhone() {
		return receivePhone;
	}

	public void setReceivePhone(int receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getReceiveMail() {
		return receiveMail;
	}

	public void setReceiveMail(String receiveMail) {
		this.receiveMail = receiveMail;
	}

	public String getReceiveRemark() {
		return receiveRemark;
	}

	public void setReceiveRemark(String receiveRemark) {
		this.receiveRemark = receiveRemark;
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
