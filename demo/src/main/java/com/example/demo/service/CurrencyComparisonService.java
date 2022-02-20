package com.example.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.CurrencyApiModel;
import com.example.demo.model.entity.CurrencyComparison;
import com.example.demo.repository.CurrencyComparisonRepository;

@Service
@Transactional
public class CurrencyComparisonService {
	
	@Autowired
	private CurrencyComparisonRepository currencyComparisonRepository;

	
	public CurrencyComparison find(String code) {
		return currencyComparisonRepository.findByCode(code);
	}
	
	/**
	 * 查詢
	 * @param code
	 * @return
	 */
	public List<CurrencyComparison> select(String code) {
		
		List<CurrencyComparison> response = new ArrayList<>();
		if(code != null){
			response.add(find(code));
		}
		else {
			response=currencyComparisonRepository.findAll();
		}
		return response;
	}
	
	/**
	 * 新增
	 * @param req
	 * @return
	 */
	public String create(CurrencyApiModel req) {
		// 1.check code
		CurrencyComparison currencyComparisonEnt = currencyComparisonRepository.findByCode(req.code);
		if(currencyComparisonEnt != null)
		{
			return "[ERROR]The Code is duplication!";
		}
		
		// 2. set data to db
		CurrencyComparison currencyComparisonNewEnt = new CurrencyComparison(req.code, req.ch_name, req.symbol, req.rate, req.description, req.rate_float, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
		currencyComparisonRepository.save(currencyComparisonNewEnt);
		
		return "OK";
	}
	
	/**
	 * 修改
	 * @param req
	 * @return
	 */
	public String edit(CurrencyApiModel req) {
		
		// 1.check code
		CurrencyComparison currencyComparisonEnt = currencyComparisonRepository.findByCode(req.code);
		if(currencyComparisonEnt == null)
		{
			return "[ERROR]not find this code!";
		}
		

		// 2. set data to db
		currencyComparisonEnt.setCode(req.code);
		currencyComparisonEnt.setCh_name(req.ch_name);
		currencyComparisonEnt.setSymbol(req.symbol);
		currencyComparisonEnt.setRate(req.rate);
		currencyComparisonEnt.setDescription(req.description);
		currencyComparisonEnt.setRate_float(req.rate_float);
		currencyComparisonEnt.setUpdated_at(new Timestamp(System.currentTimeMillis()));
		currencyComparisonRepository.save(currencyComparisonEnt);
		return "OK";
	}
	
	/**
	 * 刪除
	 * @param req
	 * @return
	 */
	public String delete(CurrencyApiModel req) {
		// 1.check code
		CurrencyComparison currencyComparisonEnt = currencyComparisonRepository.findByCode(req.code);
		if(currencyComparisonEnt == null)
		{
			return "[ERROR]not find this code!";
		}
		
		// 2. delete this code
		currencyComparisonRepository.delete(currencyComparisonEnt);
		return "OK";
	}
	
}
