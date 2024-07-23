package com.oceanLife.utils.enumlist;

public enum UserStatus {

	ON("啟用"),
	OFF("停用");
	
	
    private String description;

    // 構造函數，用於初始化描述
    UserStatus(String description) {
        this.description = description;
    }

    // 取得描述
    public String getDescription() {
        return description;
    }
}
