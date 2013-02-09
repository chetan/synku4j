package com.zynku.sync.activesync.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.sync.activesync.context.ActiveSyncContext;
import com.zynku.sync.activesync.control.handler.ContactSyncHandler;
import com.zynku.sync.activesync.control.handler.HandlerException;
import com.zynku.sync.activesync.control.handler.InitialFolderSyncHandler;
import com.zynku.sync.activesync.model.ActiveSyncApplicationData;
import com.zynku.sync.activesync.model.folderheirachy.ContactSyncResponse;
import com.zynku.sync.activesync.model.folderheirachy.FolderSyncResponse;

/**
 * The <code>ActiveSyncController</code> class manages the interaction between 
 * the active sync server the caller. 
 * 
 * @author Jools Enticknap
 * @version $Revision:$
 */
public final class ActiveSyncController {

	private static Log log = LogFactory.getLog(ActiveSyncController.class);
	
	private final HttpClient httpClient;
	
	private final ActiveSyncContext context;
	
	private InitialFolderSyncHandler folderSyncHandler = new InitialFolderSyncHandler();
	private ContactSyncHandler contactsSyncHandler = new ContactSyncHandler();
	
	/**
	 * Create a new controller passing in the sync context.
	 * 
	 * @param context the current context.
	 */
	public ActiveSyncController(final ActiveSyncContext context) {
		this.context = context;
		httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
	}
	
	/**
	 * 
	 * 
	 * @throws HandlerException
	 */
	public void initialFolderSync() throws HandlerException {
		final FolderSyncResponse response = folderSyncHandler.process(httpClient, context);
		if (response.getStatus().equals("1")) {
			// Update the folders returned from the sync
			context.getFolders().addAll(response.getChanged());
			context.getFolders().addAll(response.getAdded());
			
			// Update the current sync key for the FolderSync operation
			context.getSyncKeys().put("FolderSync", response.getSyncKey());
		}
	}
	

	public List<ActiveSyncApplicationData> getContacts() throws HttpException, IOException {
		if (!context.hasFolders()) {
			try {
				initialFolderSync();
			} catch (HandlerException e) {
				if (log.isWarnEnabled()) {
					log.warn("Exception raised calling initial folder sync", e);
				}
			}
		}
		
		// If this is a transient error we could just use the folders we
		// already have. 
		
		final List<ActiveSyncApplicationData> contacts = new ArrayList<ActiveSyncApplicationData>();
		try {
			final ContactSyncResponse response = contactsSyncHandler.process(httpClient, context);
			contacts.addAll(response.getApplicationData());
		} catch (HandlerException e) {
			if (log.isWarnEnabled()) {
				log.warn("Exception raised calling contact sync", e);
			}
		}
		
		return contacts;
	}
}
