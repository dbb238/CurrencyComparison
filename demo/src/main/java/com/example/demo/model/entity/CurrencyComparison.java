package com.example.demo.model.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCY_COMPARISON")
public class CurrencyComparison {

	@Id
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "CH_NAME")
	private String ch_name;
	
	@Column(name = "SYMBOL")
	private String symbol;
	
	@Column(name = "RATE")
	private String rate;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "RATE_FLOAT")
	private String rate_float;
	
	@Column(name = "UPDATED_AT")
	private Timestamp updated_at;
	
	@Column(name = "CREATED_AT")
	private Timestamp created_at;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRate_float() {
		return rate_float;
	}

	public void setRate_float(String rate_float) {
		this.rate_float = rate_float;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public CurrencyComparison() {}
	public CurrencyComparison(String code, String ch_name, String symbol, String rate, String description,
			String rate_float, Timestamp updated_at, Timestamp created_at) {
		super();
		this.code = code;
		this.ch_name = ch_name;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rate_float = rate_float;
		this.updated_at = updated_at;
		this.created_at = created_at;
	}


	@Override
	public String toString() {
		return "CurrencyComparison [code=" + code + ", ch_name=" + ch_name + ", symbol=" + symbol + ", rate=" + rate
				+ ", description=" + description + ", rate_float=" + rate_float + ", updated_at=" + updated_at
				+ ", created_at=" + created_at + "]";
	}

}