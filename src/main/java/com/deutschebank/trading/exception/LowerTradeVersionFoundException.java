package com.deutschebank.trading.exception;

public class LowerTradeVersionFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LowerTradeVersionFoundException(String message) {
		super(message);
	}
}