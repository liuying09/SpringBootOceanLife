package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.bean.PayModel;

public interface PayRepository extends JpaRepository<PayModel, Integer> {

}
