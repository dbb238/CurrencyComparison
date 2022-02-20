package com.example.demo.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CoinService;
import com.example.demo.support.RestResponse;

@RestController
public class CoinController {

	@Autowired
	private CoinService coinService;
	
	/**
	 * 取得CoinApi
	 * @return
	 */
	@GetMapping(value = "/")
	public ResponseEntity<?> getCoinApi() {

		return RestResponse.ok().body(coinService.getCoinApidata()).toResponseEntity();
	}
	
	/**
	 * 取得CoinApi並做資料轉換
	 * @return
	 */
	@GetMapping(value = "/transform")
	public ResponseEntity<?> getCoinApitransform() throws ParseException {

		return RestResponse.ok().body(coinService.getCoinApidataConversion()).toResponseEntity();
	}

}
