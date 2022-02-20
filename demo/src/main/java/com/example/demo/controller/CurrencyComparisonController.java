package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CurrencyApiModel;
import com.example.demo.service.CurrencyComparisonService;
import com.example.demo.support.RestResponse;

@RestController
@RequestMapping("/api/v1")
public class CurrencyComparisonController {

	@Autowired
	private CurrencyComparisonService currencyComparisonService;
	
	/**
	 * 貨幣中文對照表[查詢]功能
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getCurrencyComparison(@RequestParam(value = "code", required = false) String code) {
		
		return RestResponse.ok().body(currencyComparisonService.select(code)).toResponseEntity();
	}
	
	/**
	 * 貨幣中文對照表[新增]功能
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CurrencyApiModel req) {
		String result = currencyComparisonService.create(req);
		return RestResponse.ok().body(result).toResponseEntity();
	}
	
	/**
	 * 貨幣中文對照表[修改]功能
	 * @return
	 */
	@PutMapping
	public ResponseEntity<?> edit(@Valid @RequestBody CurrencyApiModel req) {
		currencyComparisonService.edit(req);
		return RestResponse.ok().body(currencyComparisonService.select(req.code)).toResponseEntity();
	}
	/**
	 * 貨幣中文對照表[刪除]功能
	 * @return
	 */
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody CurrencyApiModel req) {
		String result = currencyComparisonService.delete(req);
		return RestResponse.ok().body(result).toResponseEntity();
	}

}
