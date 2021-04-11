package com.deutschebank.trading.service;

import java.util.List;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.exception.LowerTradeVersionFoundException;

public interface TradeValidationService {
	public TradeStore insertNewTrade(TradeStore trade);

	public TradeStore updateTrade(TradeStore tradeStore) throws LowerTradeVersionFoundException;

	public void validateTradeExpiry();

	public void validateNewTradesMaturityDate(TradeStore trade) throws Exception;

	public List<TradeStore> getTradeValues();

	TradeStore findTradeById(long id);
}
