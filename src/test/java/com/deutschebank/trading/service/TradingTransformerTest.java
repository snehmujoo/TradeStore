package com.deutschebank.trading.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.exception.LowerTradeVersionFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TradingTransformerTest {

	TradingTransformer tradingTransformer;
	TradeStore newTradeValue;
	TradeStore storedTradeValue;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.tradingTransformer = new TradingTransformer();
		this.newTradeValue = new TradeStore();
		this.newTradeValue.setTradeId(1);
		this.newTradeValue.setVersion(1);
		this.newTradeValue.setBookId("BK1");
		this.newTradeValue.setCounterPartyId("CP1");
		this.newTradeValue.setMaturityDate(LocalDate.of(2014, 04, 20));
		this.newTradeValue.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.newTradeValue.setExpired("N");

		this.storedTradeValue = new TradeStore();
		this.newTradeValue.setTradeId(1);
		this.storedTradeValue.setVersion(1);
		this.storedTradeValue.setBookId("BK1");
		this.storedTradeValue.setCounterPartyId("CP1");
		this.storedTradeValue.setMaturityDate(LocalDate.of(2014, 04, 20));
		this.storedTradeValue.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.storedTradeValue.setExpired("N");

	}

	@Test
	public void testValidateTrade() throws LowerTradeVersionFoundException {
		TradingTransformer.validateTrade(this.newTradeValue, this.storedTradeValue);
	}

	@Test(expected = LowerTradeVersionFoundException.class)
	public void testValidateTradeExcption() throws LowerTradeVersionFoundException {
		this.newTradeValue = new TradeStore();
		this.newTradeValue.setTradeId(1);
		this.newTradeValue.setVersion(0);
		this.newTradeValue.setBookId("BK1");
		this.newTradeValue.setCounterPartyId("CP1");
		this.newTradeValue.setMaturityDate(LocalDate.of(2014, 04, 20));
		this.newTradeValue.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.newTradeValue.setExpired("N");
		TradingTransformer.validateTrade(this.newTradeValue, this.storedTradeValue);
	}
}