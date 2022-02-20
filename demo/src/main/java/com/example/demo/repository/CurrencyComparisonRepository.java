package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.CurrencyComparison;

@Repository
public interface CurrencyComparisonRepository extends JpaRepository<CurrencyComparison, Long> {
	
	CurrencyComparison findByCode(String code);

}
