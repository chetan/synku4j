package com.zynku.sync.activesync.wbxml.marshal;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.MarshalException;

import com.zynku.sync.activesync.model.AirSyncBase;
import com.zynku.sync.activesync.model.AirSyncBaseBodyPreference;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncBaseCodePage;
import com.zynku.sync.activesync.wbxml.codepage.AirSyncBaseCodePageField;
import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.decoder.WbxmlDecoder;
import com.zynku.sync.wbxml.decoder.WbxmlEvent;
import com.zynku.sync.wbxml.encoder.WbxmlEncoder;

import static com.zynku.sync.wbxml.decoder.WbxmlEvent.START_ELEMENT;
import static com.zynku.sync.wbxml.decoder.WbxmlEvent.TEXT_CONTENT;

public class AirSyncBaseMarshaller extends AbstractMarshaller<AirSyncBase> {
	
	private static final CodePage CODEPAGE = new AirSyncBaseCodePage();
	
	AirSyncBaseMarshaller() {
		
	}

	public void marshal(final OutputStream os, final AirSyncBase airSyncBase) throws IOException, MarshalException {
		WbxmlEncoder.switchCodePage(os, CODEPAGE);
		
		final AirSyncBaseBodyPreference bodyPrefs = airSyncBase.getBodyPreference();
		if (bodyPrefs != null) {
			WbxmlEncoder.pushElement(os, AirSyncBaseCodePageField.BodyPreference, true);
			
			WbxmlEncoder.pushElement(os, AirSyncBaseCodePageField.Type, bodyPrefs.getType());
			
			Integer i = bodyPrefs.getTruncationSize();
			if (i != null) {
				WbxmlEncoder.pushElement(os, AirSyncBaseCodePageField.TruncationSize,i.toString());
			}
			
			WbxmlEncoder.popElement(os); // BodyPreference
		}
		
	}

	public AirSyncBase unmarshal(final InputStream is) throws IOException, MarshalException {
		final AirSyncBase airSyncBase = new AirSyncBase();
		final WbxmlDecoder decoder = createDecoder(is);
		
		WbxmlEvent event;
		while ((event = next(decoder)) != null) {
			final int code = event.getCode();
			final String name = event.getElementName();
			switch (AirSyncBaseCodePageField.valueOf(name)) {
				case BodyPreference:
					if (code == START_ELEMENT) {
						airSyncBase.setBodyPreference(new AirSyncBaseBodyPreference());
					}
					break;
				case Type:
					if (code == TEXT_CONTENT) {
						airSyncBase.getBodyPreference().setType(decoder.text());
					}
					break;
				case TruncationSize:
					if (code == TEXT_CONTENT) {
						airSyncBase.getBodyPreference().setTruncationSize(Integer.parseInt(decoder.text()));
					}
					break;
			}
		}
		
		return airSyncBase;
	}

	@Override
	protected CodePage[] getCodePages() {
		return new CodePage[] { new AirSyncBaseCodePage() };
	}
}
