package com.oceanLife.model.bean;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OCEAN_article")
public class ArticleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name="articleId")
	 private int articleId;
	
	@Column(name="valueEN")
	 private String valueEN;
	
	@Column(name="articleImg")
	 private byte[] articleImg;
	
	@Column(name="articleTitle")
	 private String articleTitle;
	
	@Column(name="articleContent")
	 private String articleContent;
	
	@Column(name="articleRemark")
	 private String articleRemark;
	
	@Column(name="articleStatus")
	 private String articleStatus;

	@Column(name="create_date")
	 private LocalDateTime createDate;
	
	@Column(name="update_date")
	 private LocalDateTime updateDate;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getValueEN() {
		return valueEN;
	}

	public void setValueEN(String valueEN) {
		this.valueEN = valueEN;
	}

	public byte[] getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(byte[] articleImg) {
		this.articleImg = articleImg;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleRemark() {
		return articleRemark;
	}

	public void setArticleRemark(String articleRemark) {
		this.articleRemark = articleRemark;
	}

	public String getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
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
