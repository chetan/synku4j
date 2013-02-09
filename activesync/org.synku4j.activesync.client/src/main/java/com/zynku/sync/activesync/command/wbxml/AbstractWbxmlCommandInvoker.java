package com.zynku.sync.activesync.command.wbxml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import com.zynku.core.model.User;
import com.zynku.sync.activesync.command.CommandInvocationException;
import com.zynku.sync.activesync.command.CommandInvoker;

public abstract class AbstractWbxmlCommandInvoker implements CommandInvoker {

	private final String name;
	private final Collection<String> versions;

	protected AbstractWbxmlCommandInvoker(final String name, final Collection<String> versions) {
		this.name = name;
		this.versions = versions;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<String> getProtocolVersions() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String[]> invoke(User user, InputStream is, OutputStream os, Map<String, String> headers,
			Map<String, String> parameters) throws IOException, CommandInvocationException {
		// TODO Auto-generated method stub
		return null;
	}

}
