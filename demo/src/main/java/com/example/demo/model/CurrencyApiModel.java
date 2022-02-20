package com.example.demo.model;

public class CurrencyApiModel {

	public String code;
     public String ch_name;
     public String symbol;
     public String rate;
     public String description;
     public String rate_float;
     
	public CurrencyApiModel(String code, String ch_name, String symbol, String rate, String description,
			String rate_float) {
		super();
		this.code = code;
		this.ch_name = ch_name;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rate_float = rate_float;
	}

     
}
