package com.example.demo.Exception;

@SuppressWarnings("serial")
public class NotEnoughStockException extends RuntimeException {
	
	public NotEnoughStockException(String message) {
		super(message);
	}

	
}
