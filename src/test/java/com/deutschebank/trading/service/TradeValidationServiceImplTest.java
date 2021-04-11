package com.deutschebank.trading.service;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.exception.LowerTradeVersionFoundException;
import com.deutschebank.trading.repository.TradeStoreRepository;

@RunWith(MockitoJUnitRunner.class)
public class TradeValidationServiceImplTest {

	@Mock
	TradeStoreRepository tradeRepository;

	TradeValidationServiceImpl tradeValidationServiceImpl;
	TradeStore tradeStore;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.tradeValidationServiceImpl = new TradeValidationServiceImpl();
		this.tradeValidationServiceImpl.setTradeRepository(tradeRepository);
		this.tradeStore = new TradeStore();
		this.tradeStore.setVersion(1);
		this.tradeStore.setBookId("BK1");
		this.tradeStore.setCounterPartyId("CP1");
		this.tradeStore.setMaturityDate(LocalDate.of(2021, 04, 20));
		this.tradeStore.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.tradeStore.setExpired("N");

	}

	@Test
	public void testInsertNewTrade() {
		tradeValidationServiceImpl.insertNewTrade(tradeStore);
	}

	@Test
	public void testUpdateTrade() throws LowerTradeVersionFoundException {
		tradeValidationServiceImpl.updateTrade(tradeStore);
	}

	@Test
	public void testValidateTradeExpiry() {
		tradeValidationServiceImpl.validateTradeExpiry();
	}

	@Test
	public void testValidationNewTradesMaturityDate() throws Exception {
		tradeValidationServiceImpl.validateNewTradesMaturityDate(tradeStore);
	}

	@Test(expected = Exception.class)
	public void testvalidateNewTradesMaturityDateException() throws Exception {
		this.tradeStore = new TradeStore();
		this.tradeStore.setVersion(1);
		this.tradeStore.setBookId("BK1");
		this.tradeStore.setCounterPartyId("CP1");
		this.tradeStore.setMaturityDate(LocalDate.of(2014, 04, 20));
		this.tradeStore.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.tradeStore.setExpired("N");
		tradeValidationServiceImpl.validateNewTradesMaturityDate(tradeStore);
	}
}