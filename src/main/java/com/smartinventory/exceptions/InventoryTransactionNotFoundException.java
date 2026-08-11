package com.smartinventory.exceptions;

public class InventoryTransactionNotFoundException extends RuntimeException{
	public InventoryTransactionNotFoundException(String message) {
		super(message);
	}
}
