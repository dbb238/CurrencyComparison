package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-provider", url = "https://api.coindesk.com/v1/bpi/currentprice.json")
public interface UserFeignClient {

	 @RequestMapping(method = RequestMethod.GET, value = "/", consumes = "application/json")
	 public String getData();

}
