package com.smartinventory.exceptions;

public class OrderItemsNotFoundException extends RuntimeException{
	public OrderItemsNotFoundException(String message){
		super(message);
	}
}
