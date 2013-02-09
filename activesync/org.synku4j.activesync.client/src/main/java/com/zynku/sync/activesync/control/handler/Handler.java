package com.zynku.sync.activesync.control.handler;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;

import com.zynku.sync.activesync.context.ActiveSyncContext;

public interface Handler<T> {

	T process(HttpClient httpClient, ActiveSyncContext context) throws HandlerException;
	
}
