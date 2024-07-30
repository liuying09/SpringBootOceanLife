package com.oceanLife.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oceanLife.model.req.ProductCreateDTO;
import com.oceanLife.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/product")
@Tag(name = "商品") //swagger api標題
public class ProductController {

	@Autowired
	ProductService productService;
	
    @Operation(summary = "新增、更新商品", description = "新增、更新商品資料")
	@PostMapping()
	public ResponseEntity<Map<String, Object>> upsert(@RequestBody ProductCreateDTO productCreateDTO){
		
		return null;
	}
	
}
