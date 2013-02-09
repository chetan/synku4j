package com.zynku.sync.activesync.command.foldersync;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.core.model.User;
import com.zynku.sync.activesync.command.CommandInvocationException;
import com.zynku.sync.activesync.command.CommandInvoker;
import com.zynku.sync.activesync.model.Folder;
import com.zynku.sync.activesync.model.FolderHierachy;
import com.zynku.sync.activesync.model.FolderType;
import com.zynku.sync.activesync.wbxml.marshal.FolderHeirachyMarshaller;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

/**
 * The <code>FolderSyncCommandInvoker</code> manages the invocation of the
 * FolderSync activesync command. This command is used to provide the 
 * caller with a list of all the folders available, so they can be used
 * in Syn operations.
 *
 * @author Jools Enticknap
 * @version $Revision:$
 */
public class FolderSyncCommandInvoker implements CommandInvoker {
	
	private static final Log log = LogFactory.getLog(FolderSyncCommandInvoker.class);

	private static final String FOLDER_SYNC_KEY = "12345";
	
	private static final String INITIAL_SYNC_KEY = "0";
	
	private static final String NAME = "FolderSync";
	private static final Collection<String> VERSIONS = Collections.singleton("12.1");
	
	private FolderHeirachyMarshaller marshaller;
	
	public FolderSyncCommandInvoker() {
		if (log.isInfoEnabled()) {
			log.info("Created new instance of FolderSyncCommandInvoker");
		}
		
		marshaller = new FolderHeirachyMarshaller();
	}
	
	public String getName() {
		return NAME;
	}

	public Collection<String> getProtocolVersions() {
		return VERSIONS;
	}

	public Map<String, String[]> invoke(User user, InputStream is, OutputStream os, Map<String, String> headers, Map<String, String> parameters)
	throws IOException, CommandInvocationException 
	{
		final Map<String, String[]> returnHeaders = new HashMap<String, String[]>();
		
		
		final FolderHierachy request = marshaller.unmarshal(is);
		if (INITIAL_SYNC_KEY.equals(request.getSyncKey())) {
			WbxmlEncoder.writePreamble(os);
			marshaller.marshal(os, initialFolderHierachy());
		} else {
			// Check the synckey 
		}
		
		return returnHeaders;
	}
	
	private static FolderHierachy initialFolderHierachy() {
		final FolderHierachy fh = new FolderHierachy();
		fh.setStatus("1");
		fh.setSyncKey(FOLDER_SYNC_KEY);
		
		// TODO : These need to come from the store, and maybe based on the
		//        users setup.
		
		final Folder contacts = new Folder();
		contacts.setDisplayName("Contacts");
		contacts.setParentId("0");
		contacts.setServerId("Contact:DEFAULT");
		contacts.setType(FolderType.DEFAULT_CONTACTS);
		
		final Folder events = new Folder();
		events.setDisplayName("zynkusync@googlemail.com");
		events.setParentId("0");
		events.setServerId("Event:DEFAULT");
		events.setType(FolderType.DEFAULT_CALENDAR);
		
		fh.getAdded().add(contacts);
		fh.getAdded().add(events);
		
		return fh;
	}
}
