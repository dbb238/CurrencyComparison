package com.example.demo.model;

public class CoinApiModel {

   
     public Time time;
     public String disclaimer;
     public String chartName;
     public Bpi bpi;

     
     public static class Time{
    	 public String updated;
    	 public String updatedISO;
    	 public String updateduk;
     }
     
     public static class Bpi{
    	 public CoinInfo USD;
    	 public CoinInfo GBP;
    	 public CoinInfo EUR;
    	 
    	 public static class CoinInfo{
        	 public String code;
        	 public String symbol;
        	 public String rate;
        	 public String description;
        	 public String rate_float;
        	         	 
         }
     }
}
