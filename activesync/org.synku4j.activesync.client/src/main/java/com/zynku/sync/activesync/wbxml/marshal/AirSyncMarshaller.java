package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.MarshalException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.sync.activesync.model.AirSync;
import com.zynku.sync.activesync.model.AirSyncBase;
import com.zynku.sync.activesync.model.AirSyncBaseBodyPreference;
import com.zynku.sync.activesync.model.AirSyncCollection;
import com.zynku.sync.activesync.model.AirSyncCommand;
import com.zynku.sync.activesync.model.ApplicationData;
import com.zynku.sync.activesync.model.AirSyncCommand.CommandType;
import com.zynku.sync.activesync.wbxml.codepage.ActiveSyncCodePageLookup;
import com.zynku.sync.activesync.wbxml.codepage.ActiveSyncCodepages;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncBaseCodePage;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncBaseCodePageField;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncCodePage;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncCodePageField;
import com.zynku.sync.activesync.wbxml.codepage.Contacts2CodePage;
import com.zynku.sync.activesync.wbxml.codepage.Contacts2CodePageField;
import com.zynku.sync.activesync.wbxml.codepage.ContactsCodePage;
import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.decoder.WbxmlDecoder;
import com.zynku.sync.wbxml.decoder.WbxmlDecoderException;
import com.zynku.sync.wbxml.decoder.WbxmlEvent;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

/**
 * The <code>AirSyncMarshaller</code> is a marshaller for the <code>AirSync</code> model class
 *
 * @author Jools Enticknap
 */
public final class AirSyncMarshaller extends AbstractMarshaller<AirSync> {
	
	private static final Log log = LogFactory.getLog(AirSyncMarshaller.class);

	private static final CodePage AIRSYNC_CODEPAGE = new AirSyncCodePage();
	
	private static final AirSyncBaseMarshaller AIRSYNCBASE_MARSHALLER = new AirSyncBaseMarshaller();

	private static final CodePage[] CODEPAGES = { 
		AIRSYNC_CODEPAGE, 
	};
	
	public AirSyncMarshaller() {
		if (log.isDebugEnabled()) {
			log.debug("Created new instance of AirSyncMarshaller");
		}
	}
	
	public void marshal(final OutputStream os, final AirSync airsync) throws IOException, MarshalException {
		WbxmlEncoder.switchCodePage(os, AIRSYNC_CODEPAGE);
		WbxmlEncoder.pushElement(os, AirSyncCodePageField.Sync, true);
		
		final List<AirSyncCollection> collections = airsync.getCollections();
		if (!collections.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("Found ("+collections.size()+") collections to marshal");
			}
			
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.Collections, true);

			for (AirSyncCollection asc : collections) {
				marshal(os, asc);
			}
			
			WbxmlEncoder.popElement(os); // Collections
		}
		
		WbxmlEncoder.popElement(os); // Sync
	}
	
	
	private void marshal(final OutputStream os, final AirSyncCollection asc) throws IOException, MarshalException {
		WbxmlEncoder.pushElement(os, AirSyncCodePageField.Collection, true);

		WbxmlEncoder.pushElement(os, AirSyncCodePageField.SyncKey, asc.getSyncKey());
		WbxmlEncoder.pushElement(os, AirSyncCodePageField.CollectionId, asc.getCollectionId());
		WbxmlEncoder.pushElement(os, AirSyncCodePageField.Status, asc.getStatus());
		
		Integer i = asc.getWindowSize();
		if (i != null) {
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.WindowSize, i.toString());
		}
		
		Boolean b = asc.getDeleteAsMoves();
		if (b != null && b.booleanValue()) {
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.DeletesAsMoves, false);
		}
		
		b = asc.getChanges();
		if (b != null && b.booleanValue()) {
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.GetChanges, false);
		}
		
		b = asc.getMoreAvailable();
		if (b != null && b.booleanValue()) {
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.MoreAvailable, false);
		}
		
		final AirSyncBase options = asc.getOptions();
		if (options != null) {
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.Options, true);
			
			AIRSYNCBASE_MARSHALLER.marshal(os, options);
			
			WbxmlEncoder.switchCodePage(os, AIRSYNC_CODEPAGE);
			WbxmlEncoder.popElement(os); // Options
		}
		
		
		final List<AirSyncCommand> commands = asc.getCommands();
		for (AirSyncCommand cmd : commands) {
			switch (cmd.getCommandType()) {
			case Add:
				WbxmlEncoder.pushElement(os, AirSyncCodePageField.Add, true);
				break;
			}

			WbxmlEncoder.pushElement(os, AirSyncCodePageField.ServerId, cmd.getServerId());
			
			WbxmlEncoder.pushElement(os, AirSyncCodePageField.ApplicationData, true);
			
			final ApplicationData appData = cmd.getAppData();
			WbxmlEncoder.switchCodePage(os, appData.getCodePage());
			for (Map.Entry<String, String> entry : appData.getApplicationData().entrySet()) {
				WbxmlEncoder.pushElement(os, AIRSYNC_CODEPAGE, entry.getKey(), entry.getValue());
			}
			
			WbxmlEncoder.switchCodePage(os, AIRSYNC_CODEPAGE);
			
			WbxmlEncoder.popElement(os); // ApplicationData
			
			WbxmlEncoder.popElement(os); // Add/Change/Delete/Up
		}
		
		// Add in the Air Sync  base options
		
		WbxmlEncoder.popElement(os); // Collection
		
	}

	public AirSync unmarshal(final InputStream is) throws IOException, MarshalException {
		final AirSync airSync = new AirSync();
		
		try {
			Contacts2CodePageField.values();
			final WbxmlDecoder decoder = new WbxmlDecoder(is, ActiveSyncCodePageLookup.getInstance());
			WbxmlEvent event;
			AirSyncCollection collection = null;
			AirSyncCommand command = null;
			while ((event = decoder.next()) != null) {
				final String name = event.getElementName();
				switch (ActiveSyncCodepages.CodePages.valueOf(event.getNamespace())) {
				case AirSync: {
					switch (AirSyncCodePage.CodePageField.valueOf(name)) {
					case Collection:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							collection = new AirSyncCollection();
							airSync.getCollections().add(collection);
						} else if (event.getCode() == WbxmlEvent.END_ELEMENT) {
							collection = null;
						}
						break;
					case Add:
					case Change:
					case Delete:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							command = new AirSyncCommand();
							command.setCommandType(CommandType.valueOf(name));
							collection.getCommands().add(command);
						} else if (event.getCode() == WbxmlEvent.END_ELEMENT) {
							command = null;
						}
						break;
					case DeletesAsMoves:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							collection.setDeleteAsMoves(Boolean.TRUE);
						}
						break;
					case GetChanges:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							collection.setChanges(Boolean.TRUE);
						}
						break;
					case MoreAvailable:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							collection.setMoreAvailable(Boolean.TRUE);
						}
						break;
					case CollectionId:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							collection.setCollectionId(decoder.text());
						}
						break;
					case SyncKey: 
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							collection.setSyncKey(decoder.text());
						}
						break;
						
					case WindowSize:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							collection.setWindowSize(Integer.parseInt(decoder.text()));
						}
						break;
					case Status:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							collection.setStatus(decoder.text());
						}
						break;
					case ServerId:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							command.setServerId(decoder.text());
						}						
						break;
					}
					
					break;
				}
				case AirNotify : {
					switch (AirSyncBaseCodePageField.valueOf(name)) {
					case BodyPreference:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							final AirSyncBase asb = new AirSyncBase();
							asb.setBodyPreference(new AirSyncBaseBodyPreference());
							collection.setOptions(asb);
						}
						break;
					case Type:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
 							collection.getOptions().getBodyPreference().setType(decoder.text());
						}
						break;
					case TruncationSize:	
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							collection.getOptions().getBodyPreference().setTruncationSize(Integer.parseInt(decoder.text()));
						}
						break;
					}
					break;
				}
				
				default: // AppData -- do we need to set the codepage on the app data ?
					if (event.getCode() == WbxmlEvent.TEXT_CONTENT && command != null) {
						command.getAppData().set(name, decoder.text());
					}
					break;
				}
			}
		} catch (WbxmlDecoderException e) {
			throw new MarshalException("Exception raised unmarshalling", e);
		}
		
		return airSync;
	}

	@Override
	protected CodePage[] getCodePages() {
		return null;
	}
}
