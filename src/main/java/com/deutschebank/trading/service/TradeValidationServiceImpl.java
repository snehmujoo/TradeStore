package com.deutschebank.trading.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.exception.LowerTradeVersionFoundException;
import com.deutschebank.trading.repository.TradeStoreRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TradeValidationServiceImpl implements TradeValidationService {

	@Autowired
	private TradeStoreRepository tradeRepository;

	@Override
	public TradeStore insertNewTrade(TradeStore trade) {
		log.info("Inserting the new trade value");
		return tradeRepository.save(trade);
	}

	@Override
	public TradeStore updateTrade(TradeStore trade) throws LowerTradeVersionFoundException {
		log.info("Updating the existing trade values with validation");
		TradeStore storedTradeValue = tradeRepository.findById(trade.getTradeId());
		if (storedTradeValue != null) {
			TradeStore validatedTrade = TradingTransformer.validateTrade(trade, storedTradeValue);
			trade = validatedTrade;
		}
		return tradeRepository.save(trade);
	}

	@Override
	public void validateTradeExpiry() {
		log.info("Validating if the Trade Maturity has reached, update if expired");
		List<TradeStore> trades = tradeRepository.findAll();
		LocalDate maturityDate = LocalDate.now();
		trades.stream().filter(t -> t.getMaturityDate().isEqual(maturityDate)).forEach(t -> t.setExpired("Y"));
		tradeRepository.saveAll(trades);
	}

	@Override
	public void validateNewTradesMaturityDate(TradeStore trade) throws Exception {
		log.info("Validating if new trade value has maturity date less than Today");
		LocalDate maturityDate = LocalDate.now();
		if (trade.getMaturityDate().isBefore(maturityDate))
			throw new Exception("Maturity date is less than current date");
	}

	@Override
	public List<TradeStore> getTradeValues() {
		return this.tradeRepository.findAll();
	}

	@Override
	public TradeStore findTradeById(long id) {
		return this.tradeRepository.findById(id);
	}

	public TradeStoreRepository getTradeRepository() {
		return tradeRepository;
	}

	public void setTradeRepository(TradeStoreRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

}