package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.MarshalException;
import java.util.List;

import com.zynku.sync.activesync.model.Folder;
import com.zynku.sync.activesync.model.FolderHierachy;
import com.zynku.sync.activesync.model.FolderType;
import com.zynku.sync.activesync.wbxml.codepage.ActiveSyncCodePageLookup;
import com.zynku.sync.activesync.wbxml.codepage.FolderHierachyCodePageField;
import com.zynku.sync.activesync.wbxml.codepage.FolderHierarchyCodePage;
import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.CodePageField;
import com.zynku.sync.wbxml.decoder.WbxmlDecoder;
import com.zynku.sync.wbxml.decoder.WbxmlDecoderException;
import com.zynku.sync.wbxml.decoder.WbxmlEvent;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

/**
 * The <code>FolderHierachyMarshaller</code> class
 * 
 * @author Jools Enticknap
 */
public final class FolderHeirachyMarshaller implements Marshaller<FolderHierachy> {

	private static final CodePage CODEPAGE = new FolderHierarchyCodePage();

	public FolderHeirachyMarshaller() {
		FolderHierachyCodePageField.values();
	}

	public void marshal(final OutputStream os, final FolderHierachy folderHierachy) throws IOException {
		WbxmlEncoder.switchCodePage(os, CODEPAGE);
		WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.FolderSync, true);

		String value;
		if ((value = folderHierachy.getSyncKey()) != null) {
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.SyncKey, value);
		}

		if ((value = folderHierachy.getStatus()) != null) {
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.Status, value);
		}

		final int folderCount = folderHierachy.getFolderCount();
		if (folderCount > 0) {
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.Changes, true);

			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.Count, String.valueOf(folderCount));

			marshal(os, FolderHierachyCodePageField.Add, folderHierachy.getAdded());
			marshal(os, FolderHierachyCodePageField.Delete, folderHierachy.getDeleted());
			marshal(os, FolderHierachyCodePageField.Update, folderHierachy.getUpdated());

			WbxmlEncoder.popElement(os); // Changes
		}

		WbxmlEncoder.popElement(os); // FolderSync
	}

	private void marshal(final OutputStream os, final CodePageField field, final List<Folder> folders) throws IOException {
		if (folders.isEmpty()) {
			return;
		}

		for (Folder f : folders) {
			WbxmlEncoder.pushElement(os, field, true);

			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.ServerId, f.getServerId());
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.ParentId, f.getParentId());
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.DisplayName, f.getDisplayName());
			WbxmlEncoder.pushElement(os, FolderHierachyCodePageField.Type, String.valueOf(f.getType().getId()));

			WbxmlEncoder.popElement(os); // root
		}
	}

	public FolderHierachy unmarshal(InputStream is) throws IOException, MarshalException {
		final FolderHierachy fh = new FolderHierachy();

		try {
			final WbxmlDecoder decoder = new WbxmlDecoder(is, ActiveSyncCodePageLookup.getInstance());

			WbxmlEvent event;
			Folder folder = null;
			while ((event = decoder.next()) != null) {
				final String name = event.getElementName();

				switch (FolderHierachyCodePageField.valueOf(name)) {
					case Add:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							folder = new Folder();
							fh.getAdded().add(folder);
						}
						break;
					case Delete:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							folder = new Folder();
							fh.getDeleted().add(folder);
						}
						break;
					case Update:
						if (event.getCode() == WbxmlEvent.START_ELEMENT) {
							folder = new Folder();
							fh.getUpdated().add(folder);
						}
						break;
					case Status:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							fh.setStatus(decoder.text());
						}
						break;
					case SyncKey:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							fh.setSyncKey(decoder.text());
						}
						break;
					case ParentId:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							folder.setParentId(decoder.text());
						}
						break;
					case DisplayName:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							folder.setDisplayName(decoder.text());
						}
						break;
					case Type:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							final int id = Integer.parseInt(decoder.text()) - 1;
							folder.setType(FolderType.values()[id]);
						}
						break;
					case ServerId:
						if (event.getCode() == WbxmlEvent.TEXT_CONTENT) {
							folder.setServerId(decoder.text());
						}
						break;
					case Changes:
					default:
						break;
				}
			}

		} catch (WbxmlDecoderException e) {
			throw new MarshalException("Exception raised unmarshalling", e);
		}

		return fh;
	}
}
