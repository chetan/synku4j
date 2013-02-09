package com.zynku.sync.activesync.control.handler;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpMethodBase;

import com.zynku.sync.activesync.context.ActiveSyncContext;

public abstract class AbstractHttpHandler<T> implements Handler<T> {
	
	
	// Add logic to make the call to the remote service
	
	
	
	protected String createRequestURL(final ActiveSyncContext context, final String command) {
		final StringBuilder sb = new StringBuilder();
		sb.append(context.getServerURL())
		  .append("?")
		  .append("Cmd=").append(command).append("&")
		  .append("User=").append(context.getUserName()).append("&")
		  .append("DeviceId=").append(context.getDeviceId()).append("&")
		  .append("DeviceType=").append(context.getDeviceType());
		
		return sb.toString();
	}

	protected void populateHeaders(final ActiveSyncContext context, final HttpMethodBase method) {
		// Need to add these to the context
		method.addRequestHeader("MS-ASProtocolVersion", "12.1");
		method.addRequestHeader("User-Agent", "Apple-iPod/701.341");
		method.addRequestHeader("Accept", "*/*");
		method.addRequestHeader("X-Ms-Policykey", "0");
		method.addRequestHeader("Accept-Language", "en-us");
		method.addRequestHeader("Accept-Encoding", "gzip, deflate");
		method.addRequestHeader("Content-Type",	"application/vnd.ms-sync.wbxml");
		
		final String auth = "Basic "+new String(Base64.encodeBase64(new StringBuilder(context.getUserName()+":"+context.getPassword()).toString().getBytes()));
		method.addRequestHeader("Authorization", auth);
	}
}
