package com.zynku.sync.activesync.control.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.sync.activesync.context.ActiveSyncContext;
import com.zynku.sync.activesync.model.ActiveSyncApplicationData;
import com.zynku.sync.activesync.model.AirSync;
import com.zynku.sync.activesync.model.AirSyncBase;
import com.zynku.sync.activesync.model.AirSyncBaseBodyPreference;
import com.zynku.sync.activesync.model.AirSyncCollection;
import com.zynku.sync.activesync.model.AirSyncCommand;
import com.zynku.sync.activesync.model.ApplicationData;
import com.zynku.sync.activesync.model.Folder;
import com.zynku.sync.activesync.model.FolderType;
import com.zynku.sync.activesync.model.AirSyncCommand.CommandType;
import com.zynku.sync.activesync.model.folderheirachy.ContactSyncResponse;
import com.zynku.sync.activesync.wbxml.marshal.AirSyncMarshaller;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

/**
 * 
 * @author Jools Enticknap
 */
public class ContactSyncHandler extends AbstractHttpHandler<ContactSyncResponse> {

	private static final Log log = LogFactory.getLog(ContactSyncHandler.class);

	private static final String COMMAND = "Sync";

	private final AirSyncMarshaller airSyncMarshaller = new AirSyncMarshaller();

	public ContactSyncHandler() {
	}

	public ContactSyncResponse process(final HttpClient httpClient, final ActiveSyncContext context) throws HandlerException {
		final ContactSyncResponse resp = new ContactSyncResponse();
		
		final List<Folder> folders = context.getFolders(FolderType.DEFAULT_CONTACTS);
		for (Folder folder : folders) {
			boolean moreRecords = true;
			String syncKey = "1";
			int retries = 5;
			do {
				final PostMethod postMethod = new PostMethod(createRequestURL(context, COMMAND));
				populateHeaders(context, postMethod);
				try {
					postMethod.setRequestEntity(new ByteArrayRequestEntity(createRequest(folder, syncKey)));
				} catch (IOException e) {
					if (log.isWarnEnabled()) {
						log.warn("Exception raised creating request", e);
					}
					moreRecords = false;
					continue;
				}
				
				int result;
				try {
					result = httpClient.executeMethod(postMethod);
				} catch (Exception e) {
					moreRecords = false;
					continue;
				} 
				
				if (result != 200) {
					moreRecords = false;
					continue;
				}
				
				try {
					final AirSync airSync = airSyncMarshaller.unmarshal(postMethod.getResponseBodyAsStream());
					// If we get no collections back then go again.
					
					final List<AirSyncCollection> collections = airSync.getCollections();
					if (collections.isEmpty() && retries > 0) {
						if (log.isDebugEnabled()) {
							log.debug("No response trying ("+retries+")");
						}
						retries--;
						continue;
					}
					
					for (AirSyncCollection asc : collections) {
						final String ascSynckey = asc.getSyncKey();
						if (ascSynckey.equals("1")) {
							break;
						}
						syncKey = ascSynckey;
						
						// Was this collection a success ?
						final String status = asc.getStatus();
						
						// If it was a success, then extract the app Data
						for (AirSyncCommand cmd :asc.getCommands()) {
							if (cmd.getCommandType() == CommandType.Add) {
								final ApplicationData appData = cmd.getAppData();
								final ActiveSyncApplicationData aspd = new ActiveSyncApplicationData();
								
								for (Map.Entry<String, String> entry : appData.getApplicationData().entrySet()) {
									aspd.set(entry.getKey(), entry.getValue());
								}
								
								resp.add(aspd);
							}
						}
						
						final Boolean more = asc.getMoreAvailable();
						if (more == null || !more.booleanValue()) {
							moreRecords = false;
						} else {
							syncKey = asc.getSyncKey();
						}
					}
				} catch (Exception e) {
					log.warn("Exception raised unmarshalling response", e);
					moreRecords = false;
					continue;
				}
			} while (moreRecords);
		}
		
		resp.setStatus("1");
		
		return resp;
	}

	private byte[] createRequest(Folder folder, final String synckey) throws IOException {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		WbxmlEncoder.writePreamble(os);

		final AirSync airSync = new AirSync();

		final AirSyncCollection asc = new AirSyncCollection();
		asc.setSyncKey(synckey); // We should have a record the the current sync
									// key for this folder.
		asc.setCollectionId(folder.getServerId());
		asc.setChanges(Boolean.TRUE);
		asc.setDeleteAsMoves(Boolean.TRUE);
		asc.setWindowSize(1000);

		final AirSyncBaseBodyPreference bodyPref = new AirSyncBaseBodyPreference();
		bodyPref.setType("1"); // plain text
		//bodyPref.setTruncationSize(32768); // no more that 32K per record

		final AirSyncBase asb = new AirSyncBase();
		asb.setBodyPreference(bodyPref);

		asc.setOptions(asb);

		airSync.getCollections().add(asc);

		airSyncMarshaller.marshal(os, airSync);

		return os.toByteArray();
	}
}
