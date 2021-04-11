package com.deutschebank.trading.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutschebank.trading.service.TradeValidationServiceImpl;

@Component
public class TradeExpiryCheckScheduler {
	@Autowired
	TradeValidationServiceImpl tradeValidationServiceImpl;

	@Scheduled(fixedDelay = 5000)
	public void tradeExpiryCheck() {
		tradeValidationServiceImpl.validateTradeExpiry();
	}
}
