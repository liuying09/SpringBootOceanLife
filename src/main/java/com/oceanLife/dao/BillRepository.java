package com.oceanLife.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceanLife.bean.BillModel;

public interface BillRepository extends JpaRepository<BillModel, Integer> {

}
