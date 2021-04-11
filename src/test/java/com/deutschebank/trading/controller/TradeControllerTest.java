package com.deutschebank.trading.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.deutschebank.trading.domain.TradeStore;
import com.deutschebank.trading.service.TradeValidationService;

@RunWith(MockitoJUnitRunner.class)
public class TradeControllerTest {

	TradeController tradeControllerMock;

	@Mock
	TradeValidationService tradeValidationService;

	private MockMvc mockMvc;
	TradeStore tradeStore;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.tradeControllerMock = new TradeController();
		tradeControllerMock.setTradeValidationService(tradeValidationService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(tradeControllerMock).build();
		this.tradeStore = new TradeStore();
		this.tradeStore.setVersion(1);
		this.tradeStore.setBookId("BK1");
		this.tradeStore.setCounterPartyId("CP1");
		this.tradeStore.setMaturityDate(LocalDate.of(2014, 04, 20));
		this.tradeStore.setCreatedDate(LocalDate.of(2014, 04, 11));
		this.tradeStore.setExpired("N");
	}

	@Test
	public void testGetTrades() throws Exception {
		List<TradeStore> tradeList = new ArrayList<TradeStore>();
		tradeList.add(this.tradeStore);
		when(tradeControllerMock.getTrades()).thenReturn(tradeList);
		this.mockMvc.perform(get("/tradeService/trades")).andExpect(status().isOk());
	}

	@Test
	public void testGetTrade() throws Exception {
		when(tradeControllerMock.getTrade(1)).thenReturn(this.tradeStore);
		this.mockMvc.perform(get("/tradeService/trades/1")).andExpect(status().isOk());
	}

	@Test
	public void testValidateTradeMaturitye() throws Exception {
		this.mockMvc.perform(get("/tradeService/trades/validateTradeMaturity")).andExpect(status().isOk());
	}

}