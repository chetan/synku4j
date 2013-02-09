package com.zynku.sync.activesync.control.handler;

public class HandlerException extends Exception {

	
	public HandlerException(String message, Exception e) {
		super(message, e);
	}

	public HandlerException(String message) {
		super(message);
	}
}
