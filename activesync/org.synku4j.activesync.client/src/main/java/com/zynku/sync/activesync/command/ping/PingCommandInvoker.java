package com.zynku.sync.activesync.command.ping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.core.model.User;
import com.zynku.sync.activesync.command.CommandInvocationException;
import com.zynku.sync.activesync.command.CommandInvoker;

public class PingCommandInvoker implements CommandInvoker{

	private static final Log log = LogFactory.getLog(PingCommandInvoker.class);

	private static final String NAME = "Ping";
	private static final Collection<String> VERSIONS = Collections.singleton("12.1");
	
	public PingCommandInvoker() {
		if (log.isInfoEnabled()) {
			log.info("Created new instance of PingCommandInvoker");
		}
	}
	
	public String getName() {
		return NAME;
	}

	public Collection<String> getProtocolVersions() {
		return VERSIONS;
	}

	public Map<String, String[]> invoke(User user, InputStream is, OutputStream os, Map<String, String> headers,
			Map<String, String> parameters) 
	throws IOException, CommandInvocationException 
	{
		
		
		
		return null;
	}
}
