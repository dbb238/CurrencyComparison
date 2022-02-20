package com.example.demo.model;

public class NewCoinApiModel {

	public Time time = new Time();
	public Bpi bpi = new Bpi();

	public static class Time {
		public String updated;
		public String transform_updated;
		public String updatedISO;
		public String transform_updatedISO;
		public String updateduk;
		public String transform_updateduk;

	}

	public static class Bpi {
		public CoinInfo USD = new CoinInfo();
		public CoinInfo GBP = new CoinInfo();
		public CoinInfo EUR = new CoinInfo();

		public static class CoinInfo {
			public String code;
			public String ch_name;
			public String symbol;
			public String rate;
			public String description;
			public String rate_float;

		}
	}
}
