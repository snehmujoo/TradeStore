package com.deutschebank.trading.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.deutschebank.trading.exception.LowerTradeVersionFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CommonExceptionControllerTest {

	CommonExceptionController commonExceptionController;

	@Mock
	MappingJackson2JsonView mappingJackson2JsonView;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.commonExceptionController = new CommonExceptionController();
		this.mappingJackson2JsonView = new MappingJackson2JsonView();
	}

	@Test
	public void testLowerTradeVersionFoundException() throws Exception {
		LowerTradeVersionFoundException ex = new LowerTradeVersionFoundException(
				"Lower Trade Version : Cannot insert this trade value.");
		this.commonExceptionController.handleCustomException(ex);
	}

	@Test
	public void testHandleCustomException() throws Exception {
		Exception ex = new Exception("Maturity date is less than current date");
		this.commonExceptionController.handleCustomException(ex);
	}

}