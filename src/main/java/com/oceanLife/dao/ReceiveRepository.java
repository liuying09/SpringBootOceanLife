package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.bean.ReceiveModel;

public interface ReceiveRepository extends JpaRepository<ReceiveModel, Integer> {

}
