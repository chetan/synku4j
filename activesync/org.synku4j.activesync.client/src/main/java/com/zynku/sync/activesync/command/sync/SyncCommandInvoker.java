package com.zynku.sync.activesync.command.sync;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.core.model.Contact;
import com.zynku.core.model.User;
import com.zynku.sync.activesync.command.CommandInvocationException;
import com.zynku.sync.activesync.command.CommandInvoker;
import com.zynku.sync.activesync.model.AirSync;
import com.zynku.sync.activesync.model.AirSyncCollection;
import com.zynku.sync.activesync.model.AirSyncCommand;
import com.zynku.sync.activesync.model.ApplicationData;
import com.zynku.sync.activesync.model.AirSyncCommand.CommandType;
import com.zynku.sync.activesync.wbxml.codepage.ContactsCodePage;
import com.zynku.sync.activesync.wbxml.marshal.AirSyncMarshaller;

public class SyncCommandInvoker implements CommandInvoker {

	private static final Log log = LogFactory.getLog(SyncCommandInvoker.class);
	
	private static final String NAME = "Sync";
	private static final Collection<String> VERSIONS = Collections.singleton("12.1");
	
	private static final String INITIAL_SYNCKEY = "0";
	private static final String DEFAULT_SYNCKEY = "1";
	
	private final AirSyncMarshaller marshaller = null;
	
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
		
		final AirSync airSyncResponse = new AirSync();
		
		final AirSync airSyncRequest = marshaller.unmarshal(is);
		final List<AirSyncCollection> collections = airSyncRequest.getCollections();
		for (AirSyncCollection asc : collections) {
			if (INITIAL_SYNCKEY.equals(asc.getSyncKey())) {
				final AirSyncCollection resp = new AirSyncCollection();
				resp.setStatus("1");
				resp.setCollectionId(asc.getCollectionId());
				resp.setSyncKey(DEFAULT_SYNCKEY);
				airSyncResponse.getCollections().add(resp);
			} else if (DEFAULT_SYNCKEY.equals(asc.getSyncKey())) {
				final Random r = new Random();

				final AirSyncCollection resp = new AirSyncCollection();
				resp.setStatus("1");
				resp.setCollectionId(asc.getCollectionId());
				resp.setSyncKey(String.valueOf(r.nextInt()));
				
				Collection<Contact> contacts = Collections.EMPTY_LIST;
//				try {
//					contacts = dataController.contactGetAll(user.getId());
//				} catch (ServiceException e) {
//					contacts = Collections.emptyList();
//				}
				
				// TODO : need to check the type of the collection to see where we get the data from 
				//        Just do contacts for the moment.
				
				for (Contact c : contacts) {
					final AirSyncCommand airSyncCommand = new AirSyncCommand();
					airSyncCommand.setCommandType(CommandType.Add);
					airSyncCommand.setServerId(String.valueOf(r.nextInt()));
					
					ApplicationData appData = new ApplicationData();
					appData.setCodePage(new ContactsCodePage());
					
					resp.getCommands().add(airSyncCommand);
				}
				
				airSyncResponse.getCollections().add(resp);
			} else {
				// look up the sync key, and check to see if there are any changes.
				// Send back the app data as required.
				
			}
		}
		
		marshaller.marshal(os, airSyncResponse);
		
		
		return returnHeaders;
	}
}
