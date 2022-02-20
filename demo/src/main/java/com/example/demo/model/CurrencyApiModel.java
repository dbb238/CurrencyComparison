package com.example.demo.model;

import org.hibernate.validator.constraints.Length;

public class CurrencyApiModel {

	@Length(min = 3, max = 20)
	public String code;

	@Length(max = 50)
	public String ch_name;

	@Length(max = 50)
	public String symbol;

	@Length(max = 50)
	public String rate;

	@Length(max = 250)
	public String description;

	@Length(max = 50)
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
