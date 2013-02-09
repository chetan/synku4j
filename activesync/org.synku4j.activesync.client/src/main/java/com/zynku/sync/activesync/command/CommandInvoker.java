package com.zynku.sync.activesync.command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Map;

import com.zynku.core.model.User;

/**
 * The <code>SyncCommand</code> interface defines the interface between the
 * http server and the active sync implementation.
 * 
 * @author Jools Enticknap
 */
public interface CommandInvoker {
	
	/**
	 * @return The protocol versions supported by this command
	 */
	Collection<String> getProtocolVersions();
	
	/**
	 * @return the name of this command.
	 */
	String getName();

	/**
	 * Invoke the command, supplying the source request, an outputstream to write the 
	 * response and the parameters and headers canme with the request.
	 */
	Map<String, String[]> invoke(User user, InputStream is, OutputStream os, Map<String,String> headers, Map<String,String> parameters)
	throws IOException, CommandInvocationException;
}
