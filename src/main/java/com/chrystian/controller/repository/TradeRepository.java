package com.chrystian.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrystian.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long>{
	
	List<Trade> findByInstrument(String instrument);

}
