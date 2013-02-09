package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.MarshalException;
import java.util.List;

import com.zynku.sync.activesync.model.Folder;
import com.zynku.sync.activesync.model.Ping;
import com.zynku.sync.activesync.wbxml.codepage.PingCodePage;
import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

public class PingMarshaller implements Marshaller<Ping> {

	private static final CodePage CODEPAGE = new PingCodePage();

	public PingMarshaller() {
	}

	public void marshal(final OutputStream os, final Ping ping) throws IOException, MarshalException {
		WbxmlEncoder.switchCodePage(os, CODEPAGE);
		WbxmlEncoder.pushElement(os, CODEPAGE, "Ping", true);

		String strValue;
		if ((strValue = ping.getStatus()) != null) {
			WbxmlEncoder.pushElement(os, CODEPAGE, "Status", strValue);
		}

		Integer intValue;
		if ((intValue = ping.getHeartbeatInterval()) != null) {
			WbxmlEncoder.pushElement(os, CODEPAGE, "HeartbeatInterval", intValue.toString());
		}

		if ((intValue = ping.getMaxFolders()) != null) {
			WbxmlEncoder.pushElement(os, CODEPAGE, "MaxFolders", intValue.toString());
		}

		final List<Folder> folders = ping.getFolders();
		if (!folders.isEmpty()) {
			WbxmlEncoder.pushElement(os, CODEPAGE, "Folders", true);

			for (Folder f : folders) {
				WbxmlEncoder.pushElement(os, CODEPAGE, "Folder", true);

				// Id maps to ServerId, why not just use folder id ??????
				WbxmlEncoder.pushElement(os, CODEPAGE, "Id", f.getServerId());

				// Class, should we really bother with this ???

				WbxmlEncoder.popElement(os);
			}

			WbxmlEncoder.popElement(os); // Folders
		}

		WbxmlEncoder.popElement(os); // Ping
	}

	public Ping unmarshal(final InputStream is) throws IOException, MarshalException {
		final Ping ping = new Ping();
		
		return ping;
	}

}
