package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.bean.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, String> {

}
