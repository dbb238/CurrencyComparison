package com.example.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.UserFeignClient;
import com.example.demo.model.CoinApiModel;
import com.example.demo.model.NewCoinApiModel;
import com.example.demo.model.entity.CurrencyComparison;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Transactional
public class CoinService {

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private CurrencyComparisonService currencyComparisonService;

	/**
	 * 資料轉換
	 * 
	 * @param
	 * @return CoinApiModel
	 */
	public CoinApiModel getCoinApidata() {
		return StringToJson();
	}

	/**
	 * 資料轉換
	 * 
	 * @param
	 * @return CoinApiModel
	 * @throws ParseException
	 */
	public NewCoinApiModel getCoinApidataConversion() {

		NewCoinApiModel response = new NewCoinApiModel();
		CoinApiModel model = StringToJson();
		response.time.updated = model.time.updated;
		response.time.updatedISO = model.time.updatedISO;
		response.time.updateduk = model.time.updateduk;
//		"updated": "Feb 18, 2022 14:39:00 UTC",
//		"updatedISO": "2022-02-18T14:39:00+00:00",
//		"updateduk": "Feb 18, 2022 at 14:39 GMT"
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		SimpleDateFormat sf = new SimpleDateFormat("MMM d, yyyy HH:mm:ss z");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		SimpleDateFormat sf3 = new SimpleDateFormat("MMM d, yyyy 'at' HH:mm z");

		Date date = new Date();
		Date date2 = new Date();
		Date date3 = new Date();

		try {
			date = sf.parse(model.time.updated);
			date2 = sf2.parse(model.time.updatedISO);
			date3 = sf3.parse(model.time.updateduk);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.time.transform_updated = df.format(date);
		response.time.transform_updatedISO = df.format(date2);
		response.time.transform_updateduk = df.format(date3);
		
		response.bpi.USD.code = model.bpi.USD.code;
		CurrencyComparison USD_Ent =currencyComparisonService.find(model.bpi.USD.code);
		if(USD_Ent != null)
			response.bpi.USD.ch_name = USD_Ent.getCh_name();
		response.bpi.USD.symbol =model.bpi.USD.symbol;
		response.bpi.USD.rate = model.bpi.USD.rate;
		response.bpi.USD.description = model.bpi.USD.description;
		response.bpi.USD.rate_float = model.bpi.USD.rate_float;
		
		response.bpi.GBP.code = model.bpi.GBP.code;
		CurrencyComparison GBP_Ent =currencyComparisonService.find(model.bpi.GBP.code);
		if(GBP_Ent != null)
			response.bpi.GBP.ch_name = GBP_Ent.getCh_name();
		response.bpi.GBP.symbol =model.bpi.GBP.symbol;
		response.bpi.GBP.rate = model.bpi.GBP.rate;
		response.bpi.GBP.description = model.bpi.GBP.description;
		response.bpi.GBP.rate_float = model.bpi.GBP.rate_float;
		
		response.bpi.EUR.code = model.bpi.EUR.code;
		CurrencyComparison EUR_Ent =currencyComparisonService.find(model.bpi.EUR.code);
		if(EUR_Ent != null)
			response.bpi.EUR.ch_name = EUR_Ent.getCh_name();
		response.bpi.EUR.symbol =model.bpi.EUR.symbol;
		response.bpi.EUR.rate = model.bpi.EUR.rate;
		response.bpi.EUR.description = model.bpi.EUR.description;
		response.bpi.EUR.rate_float = model.bpi.EUR.rate_float;
		return response;
	}

	public CoinApiModel StringToJson() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		CoinApiModel response = gson.fromJson(userFeignClient.getData(), CoinApiModel.class);
		return response;
	}
}
