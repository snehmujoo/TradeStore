package com.deutschebank.trading.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.deutschebank.trading.exception.LowerTradeVersionFoundException;

public class CommonExceptionController {
	private MappingJackson2JsonView jsonView = new MappingJackson2JsonView();

	@ExceptionHandler(LowerTradeVersionFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleCustomException(final LowerTradeVersionFoundException ex) {
		Map<String, Object> errorMap = new HashMap<>();
		createErrorMap(errorMap, ex);
		return new ModelAndView(this.jsonView, errorMap);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ModelAndView handleCustomException(final Exception ex) {
		Map<String, Object> errorMap = new HashMap<>();
		createErrorMap(errorMap, ex);
		return new ModelAndView(this.jsonView, errorMap);
	}

	private void createErrorMap(Map<String, Object> errorMap, final Exception ex) {
		errorMap.put("status", "400");
		errorMap.put("error_code", ex.getMessage());
		errorMap.put("error_class", ex.getClass().getName());
	}

}
