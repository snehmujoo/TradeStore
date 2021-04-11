package com.deutschebank.trading.service;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.exception.LowerTradeVersionFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TradingTransformer {

	public static TradeStore validateTrade(TradeStore newTradeValue, TradeStore storedTradeValue)
			throws LowerTradeVersionFoundException {
		log.info("Checking if the new trade value has lower version as compared to the version already present");
		if (newTradeValue.getVersion() >= storedTradeValue.getVersion()) {
			storedTradeValue.setVersion(newTradeValue.getVersion());
			storedTradeValue.setBookId(newTradeValue.getBookId());
			storedTradeValue.setCounterPartyId(newTradeValue.getCounterPartyId());
			storedTradeValue.setMaturityDate(newTradeValue.getMaturityDate());
			storedTradeValue.setCreatedDate(newTradeValue.getCreatedDate());
			storedTradeValue.setExpired(newTradeValue.getExpired());
			return storedTradeValue;
		} else {
			throw new LowerTradeVersionFoundException("Lower Trade Version : Cannot insert this trade value.");
		}
	}

}
