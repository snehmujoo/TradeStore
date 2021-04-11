package com.deutschebank.trading.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.service.TradeValidationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/tradeService")
public class TradeController extends CommonExceptionController {

	@Autowired
	TradeValidationService tradeValidationService;

	@PostMapping("/trades")
	public TradeStore createTrade(@RequestBody TradeStore trade) throws Exception {
		log.info("Creating Trade... ");
		tradeValidationService.validateNewTradesMaturityDate(trade);
		log.info("Created trade - saved to DB");
		return tradeValidationService.insertNewTrade(trade);

	}

	@PutMapping("/trades")
	public TradeStore updateTrade(@RequestBody TradeStore trade) throws Exception {
		log.info("Updating Trade : {}  ", trade.getTradeId());
		tradeValidationService.validateNewTradesMaturityDate(trade);
		log.info("Updated trade - saved to DB");
		return tradeValidationService.updateTrade(trade);

	}

	@GetMapping("/trades/validateTradeMaturity")
	public void validateTradeMaturity() {
		log.info("Validating if the trade is expired or not ... ");
		tradeValidationService.validateTradeExpiry();
	}

	@GetMapping("/trades")
	public List<TradeStore> getTrades() {
		log.info("Get all trades ... ");
		return this.tradeValidationService.getTradeValues();
	}

	@GetMapping("/trades/{id}")
	public TradeStore getTrade(@PathVariable long id) {
		log.info("Get all trades ... ");
		return this.tradeValidationService.findTradeById(id);
	}

	public TradeValidationService getTradeValidationService() {
		return tradeValidationService;
	}

	public void setTradeValidationService(TradeValidationService tradeValidationService) {
		this.tradeValidationService = tradeValidationService;
	}

}
